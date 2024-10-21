package Ej1;

import java.io.Serializable;

public class Persona implements Serializable {

	private String nombre;

	private int edad;

	public Persona(String nombre, int edad) {

		super();

		this.nombre = nombre;

		this.edad = edad;

	}

	public Persona() {

		super();

	}

	public String getNombre() {

		return nombre;

	}

	public void setNombre(String nombre) {

		this.nombre = nombre;

	}

	public int getEdad() {

		return edad;

	}

	public void setEdad(int edad) {

		this.edad = edad;

	}

	@Override

	public String toString() {

		return "Persona [nombre=" + nombre + ", edad=" + edad + "]";

	}

}