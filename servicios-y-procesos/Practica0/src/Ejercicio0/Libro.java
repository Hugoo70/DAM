package Ejercicio0;

public class Libro {

	private String isbn;
	private String titulo;
	private String autor;
	private int anioPublicacion;
	private boolean disponible;

	public Libro(String isbn, String titulo, String autor, int anio) {

		this.isbn = isbn;
		this.titulo = titulo;
		this.autor = autor;
		this.anioPublicacion = anio;
	}

	public void Prestamo() {

		disponible = false;
	}

	public void devolucion() {

		disponible = true;
	}

	public String informacion() {
		return "Libro [isbn=" + isbn + ", titulo=" + titulo + ", autor=" + autor + ", anioPublicacion="
				+ anioPublicacion + ", disponible=" + disponible + "]";
	}

}
