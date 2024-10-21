package Ejercicio0;

import java.util.ArrayList;

public class Usuario {

	private String idUsuario;
	private String nombre;
	private String telefono;
	private String email;
	
	ArrayList<Libro> lista = new ArrayList<>();

	public Usuario(String idUsuario, String nombre, String telefono, String email) {

		this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.telefono = telefono;
		this.email = email;

	}

	public void registrar(String registrar) {

		idUsuario = registrar;

	}

	public void actualizarInfo(String idUsuario) {

		this.idUsuario = idUsuario;

	}

}
