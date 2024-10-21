package examen.repasandoPOO;

public class persona {
	private static String nombre;

	public persona(String nombre) {
		persona.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		persona.nombre = nombre;
	}
	
	public static String Nombre() {
		return nombre;
	}
	
}
