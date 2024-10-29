package Ej1;

public class Usuario {
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


	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getRol() {
		return rol;
	}


	public void setRol(String rol) {
		this.rol = rol;
	}


	@Override
	public String toString() {
		return "Usuario [nombre=" + usuario + ", contra=" + password + ", rol=" + rol + "]";
	}
	
	
	
	
}
