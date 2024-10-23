package Ejercicio6;

public class Profesor extends Persona {

	private double Salario;
	private int nAsignaturas;
	private boolean Tutor;

	public Profesor(String dNI, String nombre, String apellidos, double salario, int nAsignaturas, boolean tutor) {
		super(dNI, nombre, apellidos);
		Salario = salario;
		this.nAsignaturas = nAsignaturas;
		Tutor = tutor;
	}

	public int getnAsignaturas() {
		return nAsignaturas;
	}

	public void setnAsignaturas(int nAsignaturas) {
		this.nAsignaturas = nAsignaturas;
	}

	public boolean isTutor() {
		return Tutor;
	}

	public void setTutor(boolean tutor) {
		Tutor = tutor;
	}

	public String toString() {
		return PersonatoString() + ", Profesor [ Salario=" + Salario + "nAsignaturas=" + nAsignaturas + ", Tutor=" + Tutor + "]";
	}

}
