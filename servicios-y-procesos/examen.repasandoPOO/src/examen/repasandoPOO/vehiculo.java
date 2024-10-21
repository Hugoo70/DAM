package examen.repasandoPOO;

public class vehiculo {
	static int contador = 0;
	
	protected String marca;
	private String modelo;
	private int anio;
	protected persona propietario;
	
	//Constructor con propietario
	public vehiculo(String marca, String modelo, persona persona, int anio) {
		this.marca = marca;
		this.modelo = modelo;
		this.anio = anio;
		propietario = persona;
		contador++;
	}
	//Constructor sin propietario
	public vehiculo(String marca, String modelo, int anio) {
		this.marca = marca;
		this.modelo = modelo;
		this.anio = anio;
		contador++;
	}

	public persona getPropietario() {
		return propietario;
	}

	public void setPropietario(persona propietario) {
		this.propietario = propietario;
	}
	public static int verContador() {
		return contador;
	}
	
	// Me sale Ana en todos los vehiculos, no puede solucionar el error.
	public String info() {
		if(propietario == null) {
			return " No tiene propietario";
		}else {
			return persona.Nombre();
		}		

	}

	
	
	
}
