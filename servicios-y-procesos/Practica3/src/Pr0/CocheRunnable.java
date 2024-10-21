package Pr0;

public class CocheRunnable implements Runnable {

	private String nombre;
	private int velocidad;
	private int distanciaTotal;

	public CocheRunnable(String nombre, int velocidad, int distanciaTotal) {
		// super();
		this.nombre = nombre;
		this.velocidad = velocidad;
		this.distanciaTotal = distanciaTotal;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.Avanzar();
	}

	public void Avanzar() {
		try {
			while(velocidad<distanciaTotal) {
			System.out.printf("El coche %s esta empezando a moverse...\n", nombre);
			Thread.sleep(velocidad);
			velocidad ++;
			System.out.printf("El coche %s lleva una velocidad de %d km/h\n", nombre, velocidad);
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public static void main(String[] args) {
		CocheRunnable Porsche = new CocheRunnable("Posche", 1, 12);
		CocheRunnable Lamborghini = new CocheRunnable("Lamborghini", 2, 10);
		CocheRunnable Mclaren = new CocheRunnable("Mclaren", 5, 18);
		CocheRunnable Audi = new CocheRunnable("Audi", 3, 15);
		
		(new Thread(Porsche)).start();
		(new Thread(Lamborghini)).start();
		(new Thread(Mclaren)).start();
		(new Thread(Audi)).start();
		
	}

}
