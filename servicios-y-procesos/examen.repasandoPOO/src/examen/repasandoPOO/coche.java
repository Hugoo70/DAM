package examen.repasandoPOO;

public class coche extends vehiculo{

	private int numeroPuertas;

	public coche(String marca, String modelo, int anio, persona persona, int numeroPuertas) {
		super(marca, modelo, persona, anio );
		this.setNumeroPuertas(numeroPuertas);
	}

	public int getNumeroPuertas() {
		return numeroPuertas;
	}

	public void setNumeroPuertas(int numeroPuertas) {
		this.numeroPuertas = numeroPuertas;
	}




	
	}


