package Practica_HugoJ;

import java.util.ArrayList;

public class Vehiculo implements Comparable<Vehiculo>{
	public String Marca;
	public String Color;
	public int NumBastidor;
	public int Kilometros;
	protected int AñoFabricacion;
	//He añadido Electrico y VelMax a Vehiculo para poder comparar entre todos.
	private boolean Electrico;
	private double VelocidadMax;
	

	public Vehiculo(String marca, String color, int numBastidor, int kilometros, int añoFabricacion, boolean electrico,
			double velocidadMax) {
		super();
		Marca = marca;
		Color = color;
		NumBastidor = numBastidor;
		Kilometros = kilometros;
		AñoFabricacion = añoFabricacion;
		Electrico = electrico;
		VelocidadMax = velocidadMax;
	}





	public String getMarca() {
		return Marca;
	}



	public void setMarca(String marca) {
		Marca = marca;
	}



	public String getColor() {
		return Color;
	}



	public void setColor(String color) {
		Color = color;
	}



	public int getNumBastidor() {
		return NumBastidor;
	}



	public void setNumBastidor(int numBastidor) {
		NumBastidor = numBastidor;
	}



	public int getKilometros() {
		return Kilometros;
	}



	public void setKilometros(int kilometros) {
		Kilometros = kilometros;
	}



	public int getAñoFabricacion() {
		return AñoFabricacion;
	}



	public void setAñoFabricacion(int añoFabricacion) {
		AñoFabricacion = añoFabricacion;
	}



	public boolean isElectrico() {
		return Electrico;
	}



	public void setElectrico(boolean electrico) {
		Electrico = electrico;
	}



	public double getVelocidadMax() {
		return VelocidadMax;
	}



	public void setVelocidadMax(double velocidadMax) {
		VelocidadMax = velocidadMax;
	}


	@Override
	public String toString(){
		return "Vehiculo [Marca=" + Marca + ", Color=" + Color + ", NumBastidor=" + NumBastidor + ", Kilometros="
				+ Kilometros + ", AñoFabricacion=" + AñoFabricacion + ", Electrico=" + Electrico + ", VelocidadMax="
				+ VelocidadMax;
	}

	@Override
	public int compareTo(Vehiculo v) {
		if(VelocidadMax>v.getVelocidadMax())
			return 1;
		if(VelocidadMax<v.getVelocidadMax())
			return -1;
		
		return 0;
	}
	
}

