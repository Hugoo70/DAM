package Ejercicio6;

public class Modulo {
	private String Nombre;
	private int Horas;
	private Profesor Profe;
	private boolean convalidable;

	public Modulo(String nombre, int horas, Profesor profe, boolean convalidable) {
		Nombre = nombre;
		Horas = horas;
		Profe = profe;
		this.convalidable = convalidable;
	}

}
