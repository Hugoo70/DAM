package Ejercicio6;

public class Directivo extends Persona {

	private double Salario;
	private boolean Salesiano;
	private char Turno;



	public Directivo(String dNI, String nombre, String apellidos, double salario, boolean salesiano, char turno) {
		super(dNI, nombre, apellidos);
		Salario = salario;
		Salesiano = salesiano;
		Turno = turno;
	}

	public boolean isSalesiano() {
		return Salesiano;
	}

	public void setSalesiano(boolean salesiano) {
		Salesiano = salesiano;
	}

	public char isTurno() {
		return Turno;
	}

	public void setTurno(char turno) {
		Turno = turno;
	}

	public String toString() {
		return PersonatoString() + ", Directivo: Salario=" + Salario + "Salesiano=" + Salesiano + ", Turno=" + Turno + "]";
	}

}
