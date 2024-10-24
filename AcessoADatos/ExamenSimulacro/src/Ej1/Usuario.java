package Ej1;

import java.io.Serializable;

public class Usuario implements Serializable{
	private String usuario;
	private String password;
	private String rol;
	
	public Usuario(String usuario, String password, String rol) {
		//super();
		this.usuario = usuario;
		this.password = password;
		this.rol = rol;
		
		
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String nombre) {
		this.usuario = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String pas) {
		this.password = password;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String puesto) {
		this.rol = puesto;
	}

	@Override
	public String toString() {
		return "Usuarios [nombre=" + usuario + ", pas=" + password + ", puesto=" + rol + "]";
	}
	
	
}
