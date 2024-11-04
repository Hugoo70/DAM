package traductor;

import java.awt.EventQueue;

public class main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					traductorClases window = new traductorClases();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
