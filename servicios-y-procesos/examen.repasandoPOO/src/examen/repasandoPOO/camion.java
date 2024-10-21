package examen.repasandoPOO;

public class camion extends vehiculo{
	private int capacidadCarga;

	public camion(String marca, String modelo, int anio, persona persona, int capacidadCarga) {
		super(marca, modelo, persona, anio );
		this.setCapacidadCarga(capacidadCarga);
	}

	public int getCapacidadCarga() {
		return capacidadCarga;
	}

	public void setCapacidadCarga(int capacidadCarga) {
		this.capacidadCarga = capacidadCarga;
	}
	

}
