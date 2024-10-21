package Practica_HugoJ;

public class Barco extends Vehiculo{
	private int eslora;
	private int calado;
	private char tipo;
	public Barco(String marca, String color, int numBastidor, int kilometros, int añoFabricacion, boolean electrico,
			double velocidadMax, int eslora, int calado, char tipo) {
		super(marca, color, numBastidor, kilometros, añoFabricacion, electrico, velocidadMax);
		this.eslora = eslora;
		this.calado = calado;
		this.tipo = tipo;
	}
	public int getEslora() {
		return eslora;
	}
	public void setEslora(int eslora) {
		this.eslora = eslora;
	}
	public int getCalado() {
		return calado;
	}
	public void setCalado(int calado) {
		this.calado = calado;
	}
	public char getTipo() {
		return tipo;
	}
	public void setTipo(char tipo) {
		this.tipo = tipo;
	}
	
	public String toString() {
		return super.toString() + ", Barco [eslora=" + eslora + ", calado=" + calado + ", tipo=" + tipo + "]\n";
	}
	
	


	
}

