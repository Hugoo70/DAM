package Ej1;

public class Usuario {
	private String nombre;
	private String contra;
	private String rol;
	
	
	public Usuario(String nombre, String contra, String rol) {
		//super();
		this.nombre = nombre;
		this.contra = contra;
		this.rol = rol;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getContra() {
		return contra;
	}


	public void setContra(String contra) {
		this.contra = contra;
	}


	public String getRol() {
		return rol;
	}


	public void setRol(String rol) {
		this.rol = rol;
	}


	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", contra=" + contra + ", rol=" + rol + "]";
	}
	
	
	
	
}
