package Mio;

import java.io.Serializable;

public class Usuario implements Serializable {
	private String nombre;
	private String pass;
	private String rol;
	
	public Usuario(String nombre, String pass, String rol) {
		//super();
		this.nombre = nombre;
		this.pass = pass;
		this.rol = rol;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", pass=" + pass + ", rol=" + rol + "]";
	}
	
	
	
	
}
