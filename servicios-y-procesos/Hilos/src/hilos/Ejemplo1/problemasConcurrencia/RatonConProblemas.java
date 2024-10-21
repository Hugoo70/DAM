package hilos.Ejemplo1.problemasConcurrencia;

public class RatonConProblemas implements Runnable {

	private String nombre;
	private int tiempoAlimentacion;
	private static int alimentoConsumido = 0;

	public RatonConProblemas(String nombre, int tiempoAlimentacion) {
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
			alimentoConsumido++; // Hay que conseguir sincronizar esto, porque puede dar lugar a condiciones de carrera
			System.out.printf("El raton %s ha terminado de alimentarse, ha tardado %d segundos\n", nombre, tiempoAlimentacion);
			System.out.printf("Alimento consumido %d\n", alimentoConsumido);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RatonConProblemas Geronimo = new RatonConProblemas("Geronimo Stilton", 4);

		for(int i =0; i<1000;i++) {
			(new Thread(Geronimo)).start();
		}


	}
}
