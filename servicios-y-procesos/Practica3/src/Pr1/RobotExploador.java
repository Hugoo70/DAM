package Pr1;

public class RobotExploador extends Thread{

	private String nombre;
	private int tiempoExploracion;
	
	public RobotExploador(String nombre, int tiempoExploracion) {
		//super();
		this.nombre = nombre;
		this.tiempoExploracion = tiempoExploracion;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.explorar();
	}
	
	public void explorar() {
		try {
			System.out.printf("El robot %s ha comenzado a explorar su area asignada\n", nombre);
			Thread.sleep(tiempoExploracion * 1000);
			System.out.printf("El robot %s ha terminado su exploracion en %d seg\n", nombre, tiempoExploracion);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RobotExploador r1 = new RobotExploador("R1", 5);
		RobotExploador r2 = new RobotExploador("R2", 9);
		RobotExploador r3 = new RobotExploador("R3", 2);
		RobotExploador r4 = new RobotExploador("R4", 13);
		
		r1.start();
		r2.start();
		r3.start();
		r4.start();
		
		
	}

}
