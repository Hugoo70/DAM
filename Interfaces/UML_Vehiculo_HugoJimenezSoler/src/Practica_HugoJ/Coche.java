package Practica_HugoJ;

public class Coche extends Vehiculo{

	private boolean antiguo;

	public Coche(String marca, String color, int numBastidor, int kilometros, int añoFabricacion, boolean electrico,
			double velocidadMax, boolean antiguo) {
		super(marca, color, numBastidor, kilometros, añoFabricacion, electrico, velocidadMax);
		this.antiguo = antiguo;
	}

	public boolean isAntiguo() {
		return antiguo;
	}

	public void setAntiguo(boolean antiguo) {
		this.antiguo = antiguo;
	}

	public String toString() {
		return super.toString() + ", Coche [antiguo=" + antiguo + "]\n";
	}
	

	
	
	
}
