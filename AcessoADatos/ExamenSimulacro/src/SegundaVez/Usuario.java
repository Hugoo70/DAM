package SegundaVez;

import java.io.Serializable;

public class Usuario implements Serializable{
	private String nmombre;
	private String pass;
	private String rol;
	
	
	public Usuario(String nmombre, String pass, String rol) {
		//super();
		this.nmombre = nmombre;
		this.pass = pass;
		this.rol = rol;
	}


	public String getNmombre() {
		return nmombre;
	}


	public void setNmombre(String nmombre) {
		this.nmombre = nmombre;
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
		return "Usuario [nmombre=" + nmombre + ", pass=" + pass + ", rol=" + rol + "]";
	}
	
	
}
