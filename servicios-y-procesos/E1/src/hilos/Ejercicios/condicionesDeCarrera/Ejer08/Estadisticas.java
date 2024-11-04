package hilos.Ejercicios.condicionesDeCarrera.Ejer08;

class Actualizador implements Runnable {
    private Estadisticas estadisticas;
    private int inicio;
    private int fin;

    public Actualizador(Estadisticas estadisticas, int inicio, int fin) {
        this.estadisticas = estadisticas;
        this.inicio = inicio;
        this.fin = fin;
    }

    @Override
    public void run() {
        for (int i = inicio; i <= fin; i++) {
            estadisticas.actualizarEstadisticas(i);
        }
    }
}

public class Estadisticas {
    private int suma = 0;
    private int conteo = 0;

    final private Object lock = new Object();
    public void actualizarEstadisticas(int valor) {
    	double promedio;
		synchronized (lock) {
    	// Inicio seccción critica
        suma += valor;
        conteo++;
        promedio = (double) suma / conteo;
        //Fin seccion critica
        //Soluciones: declarar promedio fuera del sync.
    }
        System.out.println("Valor agregado: " + valor + ". Promedio actual: " + promedio);
    }
    
    public static void main(String[] args) {
        Estadisticas estadisticas = new Estadisticas();

        Thread hilo1 = new Thread(new Actualizador(estadisticas, 1, 100));
        Thread hilo2 = new Thread(new Actualizador(estadisticas, 101, 200));

        hilo1.start();
        hilo2.start();
        
		try {
			hilo1.join();
			hilo2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
			//Este código se ejecuta cuando a alguno de los hilos no se les ha
			//permitido terminar normalmente, "Desde fuera" se interrumpe (mata)
		
    }
    }
}