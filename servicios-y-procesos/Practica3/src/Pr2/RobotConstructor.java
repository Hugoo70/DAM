package Pr2;

public class RobotConstructor extends Robot implements Runnable {
	private static int estructurasConstruidas = 0;

	public RobotConstructor(String nombre, int tiempoOperacion) {
		super(nombre, tiempoOperacion);
	}

    public synchronized void construir() {
        estructurasConstruidas += 1;
        System.out.println(nombre + " construyó una estructura. Total estructuras: " + estructurasConstruidas);
    }

    public synchronized void destruir() {
        if (estructurasConstruidas > 0) {
            estructurasConstruidas -= 1;
            System.out.println(nombre + " destruyó una estructura. Total estructuras: " + estructurasConstruidas);
        } else {
            System.out.println(nombre + " no puede destruir más estructuras.");
        }
    }
	@Override
	public void run() {
		// TODO Auto-generated method stub
		operar();
	}
    
	@Override
	public void operar() {
		try {
			if (estructurasConstruidas % 2 == 0) {
				for (int i = 0; i < 3; i++) {
					construir();
					Thread.sleep(tiempoOperacion * 1000);
				}
			} else {
				destruir();
				Thread.sleep(tiempoOperacion * 1000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static int getEstructurasConstruidas() {
		return estructurasConstruidas;
	}


	
	
	
}
