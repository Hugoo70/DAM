package hilos.Ejercicios.condicionesDeCarrera.Ejer01;

class Cliente implements Runnable {
	private ReservaEntradas reserva;
	private String nombreCliente;

	public Cliente(ReservaEntradas reserva, String nombreCliente) {
		this.reserva = reserva;
		this.nombreCliente = nombreCliente;
	}

	@Override
	public void run() {
		reserva.reservarEntrada(nombreCliente);
	}
}

public class ReservaEntradas {
	private int entradasDisponibles = 5;

	// Como todo el método es Critico podemos declarar la función como synchronized
	public synchronized void reservarEntrada(String nombreCliente) {
		// Comienza Ceccion critica (Todo trozo de codigo que si a mitad de lectura se
		// interrumpe y otro se ejecuta, el programa rompe)
		if (entradasDisponibles > 0) {
			entradasDisponibles--;
			System.out.println(
					"Entrada reservada para " + nombreCliente + ". Entradas restantes: " + entradasDisponibles);
		} else {
			System.out.println("No hay entradas disponibles para " + nombreCliente);
		}
		// Fin de Seccion Critica

	}

	public static void main(String[] args) {
		ReservaEntradas reserva = new ReservaEntradas();

		Thread cliente1 = new Thread(new Cliente(reserva, "Cliente 1"));
		Thread cliente2 = new Thread(new Cliente(reserva, "Cliente 2"));
		Thread cliente3 = new Thread(new Cliente(reserva, "Cliente 3"));
		Thread cliente4 = new Thread(new Cliente(reserva, "Cliente 4"));
		Thread cliente5 = new Thread(new Cliente(reserva, "Cliente 5"));
		Thread cliente6 = new Thread(new Cliente(reserva, "Cliente 6"));
		Thread cliente7 = new Thread(new Cliente(reserva, "Cliente 7"));
		Thread cliente8 = new Thread(new Cliente(reserva, "Cliente 8"));

		cliente1.start();
		cliente2.start();
		cliente3.start();
		cliente4.start();
		cliente5.start();
		cliente6.start();
		cliente7.start();
		cliente8.start();
	}
}
