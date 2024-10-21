package Ej2;

public class Libro implements Comparable<Libro>{

	private String titulo;
	private String autor;
	private double precio;
	
	public Libro(String titulo, String autor, double precio) {
		//super();
		this.titulo = titulo;
		this.autor = autor;
		this.precio = precio;
	}


	public String getTitulo() {
		return titulo;
	}





	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}





	public String getAutor() {
		return autor;
	}





	public void setAutor(String autor) {
		this.autor = autor;
	}





	public double getPrecio() {
		return precio;
	}





	public void setPrecio(double precio) {
		this.precio = precio;
	}




	
	


	@Override
	public String toString() {
		return "Libro [titulo=" + titulo + ", autor=" + autor + ", precio=" + precio + "]";
	}

	@Override
	public int compareTo(Libro v) {
		if(precio>v.getPrecio())
			return 1;
		if(precio<v.getPrecio())
			return -1;
		
		return 0;
	}
	
}

