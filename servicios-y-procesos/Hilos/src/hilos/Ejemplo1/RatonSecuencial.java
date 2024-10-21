package hilos.Ejemplo1;

public class RatonSecuencial {

	private String nombre;
	private int tiempoAlimentacion;
	
	public RatonSecuencial(String nombre, int tiempoAlimentacion) {
		this.nombre = nombre;
		this.tiempoAlimentacion = tiempoAlimentacion;
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
		RatonSecuencial Geronimo = new RatonSecuencial("Geronimo Stilton", 4);
		RatonSecuencial Stuart = new RatonSecuencial("Stuart Little", 6);
		RatonSecuencial Mickey = new RatonSecuencial("Mickey Mouse", 8);
		
		Geronimo.comer();
		Stuart.comer();
		Mickey.comer();
		
				
	}

}
