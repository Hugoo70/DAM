package Ejercicio0;

import java.util.Date;

public class Préstamo {

	private String idPréstamo;
	private Date fechaPréstamo;
	private Date fechaDevolucion;
	private Libro libro;
	private Usuario usuario;


	public Préstamo(String idprestamo, Libro libro, Usuario usuario, Date fechaPrestamo) {
		
		this.idPréstamo = idprestamo;
		this.libro = libro;
		this.usuario = usuario;
		this.fechaPréstamo = fechaPrestamo;
		
		
	}

	public void realizarPrestamo() {
		
		libro.Prestamo();
		usuario.lista.add(libro);
		
		
	}

	public void finalizarPrestamo(Date fechaDevolucion) {
		
		libro.devolucion();
		this.fechaDevolucion = fechaDevolucion; 
	}

}
