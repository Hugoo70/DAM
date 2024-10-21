package Practica_HugoJ;

public class Avion extends Vehiculo{
	private byte Motores;
	private boolean combate;
	
	
	public Avion(String marca, String color, int numBastidor, int kilometros, int añoFabricacion, boolean electrico,
			double velocidadMax, byte motores, boolean combate) {
		super(marca, color, numBastidor, kilometros, añoFabricacion, electrico, velocidadMax);
		Motores = motores;
		this.combate = combate;
	}
	public byte getMotores() {
		return Motores;
	}
	public void setMotores(byte motores) {
		Motores = motores;
	}
	public boolean isCombate() {
		return combate;
	}
	public void setCombate(boolean combate) {
		this.combate = combate;
	}
	
	public String toString() {
		return super.toString() + ", Avion [Motores=" + Motores + ", combate=" + combate + "]\n";
	}

	
	
}
