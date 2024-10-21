package Ejercicio6;

import java.util.ArrayList;

public class Alumno extends Persona {

	private String fechaNac;
	private char sexo;
	private boolean repetidor;
	static ArrayList<Modulo> modulos = new ArrayList<>();

	public Alumno(String dNI, String nombre, String apellidos, String fechaNac, char sexo, boolean repetidor) {
		super(dNI, nombre, apellidos);

		this.fechaNac = fechaNac;
		this.sexo = sexo;
		this.repetidor = repetidor;
	}

	public String getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(String fechaNac) {
		this.fechaNac = fechaNac;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public boolean isRepetidor() {
		return repetidor;
	}

	public void setRepetidor(boolean repetidor) {
		this.repetidor = repetidor;
	}


	
	
	public String toString() {
		return PersonatoString() + ", Alumno: fechaNac=" + fechaNac + ", sexo=" + sexo + ", repetidor=" + repetidor+ "Modulos=" +"]";
	}

}
