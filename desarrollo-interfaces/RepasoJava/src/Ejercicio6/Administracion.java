package Ejercicio6;

public class Administracion extends Persona {
	private double Salario;
	private String Estudios;
	private int Antiguedad;

	public Administracion(String dNI, String nombre, String apellidos, double salario, String estudios,
			int antiguedad) {
		super(dNI, nombre, apellidos);
		Salario = salario;
		Estudios = estudios;
		Antiguedad = antiguedad;
	}

	public String getEstudios() {
		return Estudios;
	}

	public void setEstudios(String estudios) {
		Estudios = estudios;
	}

	public int getAntiguedad() {
		return Antiguedad;
	}

	public void setAntiguedad(int antiguedad) {
		Antiguedad = antiguedad;
	}

	public String toString() {
		return PersonatoString() + ", Administracion [ Salario=" + Salario + "Estudios=" + Estudios + ", Antiguedad=" + Antiguedad + "]";
	}

}
