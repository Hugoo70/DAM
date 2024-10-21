package hilos.Ejercicios.condicionesDeCarrera.Ejer02;

class Venta implements Runnable {
	private Inventario inventario;
	private int cantidad;

	public Venta(Inventario inventario, int cantidad) {
		this.inventario = inventario;
		this.cantidad = cantidad;
	}

	@Override
	public void run() {
		for (int i = 0; i < 30; i++) {
			inventario.venderProducto(cantidad);
		}
	}
}

class Reabastecimiento implements Runnable {
	private Inventario inventario;
	private int cantidad;

	public Reabastecimiento(Inventario inventario, int cantidad) {
		this.inventario = inventario;
		this.cantidad = cantidad;

	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			inventario.agregarProducto(cantidad);
		}
	}
}

public class Inventario {
	volatile private Integer stock; // Para asegurar que todos los cambios que se hagan sobre esta variable son visibles para todos los hilos que la utilicen
	//Asi evitamos que los hilos hagan copuas locales de la variable
	
	//Crear objeto para el Synchronized, sirve como palo en una carrera de relevos
	final private Object stockLock = new Object();

	public Inventario(int stockInicial) {
		this.stock = stockInicial;
	}

	public void agregarProducto(int cantidad) {
		// Inicio Sección crtica 1 - Sobre Stock
		synchronized (stockLock) {
		// Realmente la Seccion crítica es el stock pero no podemos usar un synchronized stock al ser int (No recomendable usar integer)
		// 
		stock += cantidad;
		System.out.println("Se agregaron " + cantidad + " productos. Stock actual: " + stock);
		// Fin Sección crítica 1
		}
	}

	public void venderProducto(int cantidad) {
		synchronized (stockLock) {
		if (stock >= cantidad) {
			// Inicio Sección Crítica 2
				stock -= cantidad;
		
			// Fin Sección Crítica 2
			System.out.println("Se vendieron " + cantidad + " productos. Stock actual: " + stock);
		} else {
			System.out.println("No hay suficiente stock para vender " + cantidad + " productos.");
		}
		}
	}

	public int getStock() { // Si hago el synchronized en el método se bloquea el proceso y no queremos eso
		//synchronized (stockLock) {  Para evitar copias locales de los hilos
		return stock;
		//}
	}

	public static void main(String[] args) {
		Inventario inventario = new Inventario(50);

		Thread hiloVentas = new Thread(new Venta(inventario, 2));
		Thread hiloReabastecimiento = new Thread(new Reabastecimiento(inventario, 5));

		hiloVentas.start();
		hiloReabastecimiento.start();

		try {
			hiloVentas.join();
			hiloReabastecimiento.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Stock final: " + inventario.getStock());
	}

}
