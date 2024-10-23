package hilos.Ejercicios.condicionesDeCarrera.Ejer03;
import java.util.ArrayList;
import java.util.List;

class AgregarPedido implements Runnable {
    private ProcesadorDePedidos procesador;

    public AgregarPedido(ProcesadorDePedidos procesador) {
        this.procesador = procesador;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 20; i++) {
            procesador.agregarPedido("Pedido " + i);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


class ProcesarPedido implements Runnable {
    private ProcesadorDePedidos procesador;

    public ProcesarPedido(ProcesadorDePedidos procesador) {
        this.procesador = procesador;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 20; i++) {
            procesador.procesarPedido();
            try {
                Thread.sleep(70);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


public class ProcesadorDePedidos {
    private List<String> pedidos = new ArrayList<>(); // No es segura para concurrencia.

    /*
     * Como esta clase no tiene absolutamente nada más que la variable pedido
     * y metodos que la utilizan y la sección crítica de cada método es el método
     * enterp, synchronized al método es una solución válida. si tuviera, además de pedidos, un arraylist de clientes,
     * tendría que crear 2 locks, uno para clientes y otro para pedidos.
     * 
     * Lo único que seguirá pasando que se quedan pedidos sin procesar. Esto no es un
     * problema de concurrencia. Si fuera secuencial, podría pasarnos también.
     * Dependiendo de los tiempos, podrían quedarse pedidos sin procesar
     */
    
    public synchronized void agregarPedido(String pedido) { // Bloquear todo el objeto cada vez que necesite hacer algo
        pedidos.add(pedido);
        System.out.println("Pedido agregado: " + pedido);
    }

    public synchronized void procesarPedido() {
        if (!pedidos.isEmpty()) {
            String pedido = pedidos.remove(0);
            System.out.println("Pedido procesado: " + pedido);
        } else {
        	// Aquí en lugar de esto tendria que ESPERAR a que hayan
        	// llegado los siguientes pedidos, Esa espera es variable.
            System.out.println("No hay pedidos para procesar.");
        }
    }
    
    public static void main(String[] args) {
        ProcesadorDePedidos procesador = new ProcesadorDePedidos();

        Thread hiloAgregar = new Thread(new AgregarPedido(procesador));
        Thread hiloProcesar = new Thread(new ProcesarPedido(procesador));

        hiloAgregar.start();
        hiloProcesar.start();

        try {
            hiloAgregar.join();
            hiloProcesar.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
