import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CenaFilosofos {

    // Esta clase representa los tenedores que los filósofos usan para comer.
    static class Tenedor {
        private final Lock lock = new ReentrantLock(); // Cada tenedor tiene un "candado" para que no se pueda usar por dos a la vez.

        // Método para intentar tomar el tenedor. Si está libre, lo agarras, si no, sigues intentando más tarde.
        public boolean tomar() {
            return lock.tryLock();
        }

        // Método para soltar el tenedor después de comer.
        public void soltar() {
            lock.unlock();
        }
    }

    // Clase Filósofo que representa a cada compa sentado en la mesa.
    static class Filosofo extends Thread {
        private final int id; // Número del filósofo (por ejemplo, 0, 1, 2...).
        private final Tenedor tenedorIzquierdo; // Tenedor de la izquierda.
        private final Tenedor tenedorDerecho;  // Tenedor de la derecha.

        // Constructor para asignar el id y los tenedores que puede usar.
        public Filosofo(int id, Tenedor tenedorIzquierdo, Tenedor tenedorDerecho) {
            this.id = id;
            this.tenedorIzquierdo = tenedorIzquierdo;
            this.tenedorDerecho = tenedorDerecho;
        }

        @Override
        public void run() {
            try {
                while (true) { // Esto sigue corriendo para siempre (a menos que lo interrumpamos, pero por ahora, infinito).
                    pensar(); // Primero, el filósofo se pone a pensar.
                    if (tomarTenedores()) { // Después intenta tomar los tenedores.
                        comer(); // Si lo logra, se pone a comer.
                        soltarTenedores(); // Cuando termina de comer, libera los tenedores.
                    }
                }
            } catch (InterruptedException e) {
                // Si el hilo es interrumpido, lo manejamos aquí para evitar problemas.
                Thread.currentThread().interrupt();
                System.out.println("Filósofo " + id + " fue interrumpido.");
            }
        }

        // Este método simula cuando el filósofo está pensando. Básicamente hace una pausa.
        private void pensar() throws InterruptedException {
            System.out.println("Filósofo " + id + " está pensando."); // Mensaje para saber que está pensando.
            Thread.sleep((long) (1000)); // Pausa de 1 segundo (simula el tiempo de pensar).
        }

        // Aquí el filósofo intenta agarrar los dos tenedores que necesita para comer.
        private boolean tomarTenedores() {
            System.out.println("Filósofo " + id + " tiene hambre y quiere comer."); // Aviso de que quiere comer.
            if (tenedorIzquierdo.tomar()) { // Intenta agarrar el tenedor de la izquierda.
                System.out.println("Filósofo " + id + " tomó el tenedor izquierdo."); // Mensaje si lo logra.
                if (tenedorDerecho.tomar()) { // Si ya tiene el izquierdo, ahora intenta agarrar el derecho.
                    System.out.println("Filósofo " + id + " tomó el tenedor derecho."); // Mensaje si también lo logra.
                    return true; // ¡Éxito! Ya tiene ambos tenedores.
                } else {
                    // Si no puede agarrar el derecho, suelta el izquierdo para no bloquear a los demás.
                    tenedorIzquierdo.soltar();
                    System.out.println("Filósofo " + id + " soltó el tenedor izquierdo porque no pudo tomar el derecho.");
                }
            }
            return false; // No pudo agarrar ambos tenedores, tendrá que intentarlo más tarde.
        }

        // Simula el tiempo que el filósofo pasa comiendo.
        private void comer() throws InterruptedException {
            System.out.println("Filósofo " + id + " está comiendo."); // Aviso de que está comiendo.
            Thread.sleep(1000); // Pausa de 1 segundo (simula el tiempo de comer).
        }

        // Este método suelta ambos tenedores para que otros filósofos puedan usarlos.
        private void soltarTenedores() {
            tenedorIzquierdo.soltar(); // Suelta el tenedor izquierdo.
            tenedorDerecho.soltar(); // Suelta el tenedor derecho.
            System.out.println("Filósofo " + id + " terminó de comer y soltó los tenedores."); // Aviso de que ya terminó.
        }
    }

    // Método principal donde todo comienza.
    public static void main(String[] args) {
        final int NUM_FILOSOFOS = 5; // Número de filósofos y tenedores.

        // Creamos un arreglo de tenedores (porque necesitamos uno entre cada plato).
        Tenedor[] tenedores = new Tenedor[NUM_FILOSOFOS];
        for (int i = 0; i < NUM_FILOSOFOS; i++) {
            tenedores[i] = new Tenedor(); // Inicializamos cada tenedor.
        }

        // Creamos un arreglo de filósofos (para simular a cada compa en la mesa).
        Filosofo[] filosofos = new Filosofo[NUM_FILOSOFOS];
        for (int i = 0; i < NUM_FILOSOFOS; i++) {
            // Obtenemos los tenedores de la izquierda y la derecha del filósofo.
            Tenedor tenedorIzquierdo = tenedores[i];
            Tenedor tenedorDerecho = tenedores[(i + 1) % NUM_FILOSOFOS];

            // Para evitar que todos intenten lo mismo al mismo tiempo, alternamos el orden de los tenedores.
            if (i % 2 == 0) { // Filósofos con número par toman primero el izquierdo, luego el derecho.
                filosofos[i] = new Filosofo(i, tenedorIzquierdo, tenedorDerecho);
            } else { // Filósofos con número impar toman primero el derecho, luego el izquierdo.
                filosofos[i] = new Filosofo(i, tenedorDerecho, tenedorIzquierdo);
            }
        }

        // Iniciamos cada filósofo como un hilo separado.
        for (Filosofo filosofo : filosofos) {
            filosofo.start(); // Arrancamos el hilo para que empiece a pensar, tomar tenedores y comer.
        }
    }
}
