package EjerciciosAccesoAleatorioObjetos;

public class Estudiante {
	private int id;
	private String nombre;
	private double nota;
	
	
	public Estudiante(int id, String nombre, double nota) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.nota = nota;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public double getNota() {
		return nota;
	}


	public void setNota(double nota) {
		this.nota = nota;
	}


	@Override
	public String toString() {
		return "Estudiante [id=" + id + ", nombre=" + nombre + ", nota=" + nota + "]";
	}
	
	
	
	
	
	
}
