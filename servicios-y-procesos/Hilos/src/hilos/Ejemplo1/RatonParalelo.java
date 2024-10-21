package hilos.Ejemplo1;

/*
 * Ojo, heredar de la clase Thread imposibilita heredar de otras clases.
 * Java no permite herencia multiple.
 * Hay interfaces como Runnble y Callable.
 * 
 * Otros lenguajes de programación que si admiten herencia multiple son: C++, C#, Ruby.
 */

public class RatonParalelo extends Thread{

	private String nombre;
	private int tiempoAlimentacion;
	
	public RatonParalelo(String nombre, int tiempoAlimentacion) {
		this.nombre = nombre;
		this.tiempoAlimentacion = tiempoAlimentacion;
	}
	
	@Override // Es un método de la clase Thread.
	public void run() {
		// Esto es lo que se va a paralelizar.
		this.comer();
	}
	
	public void comer() {
		try {
			System.out.printf("El raton %s ha comenzado a alimentarse\n", nombre);
			Thread.sleep(tiempoAlimentacion*1000);
			System.out.printf("El raton %s ha terminado de alimentarse, ha tardado %d segundos\n", nombre, tiempoAlimentacion);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RatonParalelo Geronimo = new RatonParalelo("Geronimo Stilton", 4);
		RatonParalelo Stuart = new RatonParalelo("Stuart Little", 6);
		RatonParalelo Mickey = new RatonParalelo("Mickey Mouse", 8);
		
		// Si hacemos la llamada a run, se queda en Secuencial, USAR START PARA CREAR HILOS
		Geronimo.start(); // El método start es el que inicia un hilo nuevo
		Stuart.start();	
		Mickey.start();	
		
		
				
	}
}
