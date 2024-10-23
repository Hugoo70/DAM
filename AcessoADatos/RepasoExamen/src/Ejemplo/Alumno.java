package Ejemplo;

import java.io.Serializable;

public class Alumno implements Serializable{
	private int id;
	private int nota;
	
	public Alumno(int id, int nota) {
		//super();
		this.id = id;
		this.nota = nota;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	@Override
	public String toString() {
		return "Alumno [id=" + id + ", nota=" + nota + "]";
	}
	
	
}
