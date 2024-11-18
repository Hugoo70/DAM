package Pr2;

public class RobotExplorador extends Robot implements Runnable {

	public RobotExplorador(String nombre, int tiempoOperacion) {
		super(nombre, tiempoOperacion);
	}

	@Override
	public void run() {
		operar();
	}

	@Override
	public void operar() {
		System.out.println(nombre + " ha comenzado a explorar su área asignada");
		try {
			Thread.sleep(tiempoOperacion * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(nombre + " ha terminado de explorar su área");

	}
}