package Examen;

import java.io.Serializable;

public class Alumno implements Serializable{
	private String nombre;
	private int nota;
	private int curso;
	
	
	public Alumno(String nombre, int nota, int curso) {
		//super();
		this.nombre = nombre;
		this.nota = nota;
		this.curso = curso;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public int getNota() {
		return nota;
	}


	public void setNota(int nota) {
		this.nota = nota;
	}


	public int getCurso() {
		return curso;
	}


	public void setCurso(int curso) {
		this.curso = curso;
	}


	@Override
	public String toString() {
		return "Alumno [nombre=" + nombre + ", nota=" + nota + ", curso=" + curso + "]";
	}
	
	
	
	
}
