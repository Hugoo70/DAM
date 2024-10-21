package hilos.Ejemplo1;

/*
 * Usar la interfaz Runnable para poder tener la posiblidad de hacer un extends de una clase padre.
 * Usar la interfaz solo cuando tengas que tener una herencia, si no es mejor llamar directamente a la clase Thread
 */

public class RatonRunnable implements Runnable {

	private String nombre;
	private int tiempoAlimentacion;

	public RatonRunnable(String nombre, int tiempoAlimentacion) {
		this.nombre = nombre;
		this.tiempoAlimentacion = tiempoAlimentacion;
	}

	@Override // Es un método de la interfaz Runnable (Te obliga a usarla).
	public void run() {
		// Esto es lo que se va a paralelizar.
		this.comer();
	}

	public void comer() {
		try {
			System.out.printf("El raton %s ha comenzado a alimentarse\n", nombre);
			Thread.sleep(tiempoAlimentacion * 1000);
			System.out.printf("El raton %s ha terminado de alimentarse, ha tardado %d segundos\n", nombre,
					tiempoAlimentacion);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RatonRunnable Geronimo = new RatonRunnable("Geronimo Stilton", 4);
		RatonRunnable Stuart = new RatonRunnable("Stuart Little", 6);
		RatonRunnable Mickey = new RatonRunnable("Mickey Mouse", 8);

		/*
		 * Utilzar de alguna forma THREAD que es la clase que crea hilos.
		 * Podemos utilizar el constructor de THREAD
		 * 
		 * Thread tiene un constructor que admite un argumento de tipo Runnable
		 */
		
		// Esto no lanza la ejecución del hilo, faltaria llamar al método START()
		Thread t1 = new Thread (Geronimo);
		Thread t2 = new Thread (Stuart);
		Thread t3 = new Thread (Mickey);
		
		// Al llamar al método START(), se lanzan los hilos
		t1.start();
		t2.start();
		t3.start();
	
		/*
		 *  Forma abreviada de llamar al objeto y start a la vez (NO RECOMENDABLE)
		 *  (new Thread(Geronimo)).start();
		 */
		 
		
		
	}
	
}
