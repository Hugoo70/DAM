package EjerciciosAccesoAleatorioObjetos;

import java.io.*;

public class main {
	// Poniendo el throws nos ahorramos el try catch
	public static void EscribirFicheroAleatorio(RandomAccessFile raf, int id, String nombre, double nota)
			throws IOException {

		raf.writeInt(id);
		raf.writeUTF(nombre);
		raf.writeDouble(nota);

	}

	public static Estudiante LecturaFicheroAleatorio(RandomAccessFile raf) throws IOException {
		Estudiante e = new Estudiante(raf.readInt(), raf.readUTF(), raf.readDouble());

		return e;
	}

	public static void añadirEstudiante(String nombreFichero, int id, String nombre, double nota) {
		try (RandomAccessFile raf = new RandomAccessFile(nombreFichero, "rw")) {
			raf.seek(raf.length());
			EscribirFicheroAleatorio(raf, id, nombre, nota);

		} catch (IOException e) {
			// TODO: handle exception
			System.out.println("Error: al abrir fichero para estudiante");
			e.printStackTrace();
			;
		}

	}

	public static void listarEstudiante(String nombreFichero) {
		try {
			RandomAccessFile raf = new RandomAccessFile(nombreFichero, "rw");
			while (raf.getFilePointer() < raf.length()) {
				Estudiante estudiante = LecturaFicheroAleatorio(raf);
				System.out.println(estudiante);

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error: no se encuentra el fichero de estudiantes");
			e.printStackTrace();
		}

	}

	public static Estudiante buscarEstudiantePorId(String nombreFichero, int id) {
		try {
			RandomAccessFile raf = new RandomAccessFile(nombreFichero, "rw");
			while (raf.getFilePointer() < raf.length()) {
				Estudiante estudiante = LecturaFicheroAleatorio(raf);
				System.out.println(estudiante);
				if (estudiante.getId() == id) {
					return estudiante;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error: no se encuentra el fichero para lectura");
			e.printStackTrace();
		}
		return null;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String nombreFichero = "Estudiantes.dat";

		añadirEstudiante(nombreFichero, 1, "Geronimo Stilton", 9.99);
		añadirEstudiante(nombreFichero, 2, "Bob Esponja", 4.75);
		añadirEstudiante(nombreFichero, 3, "Kovalski", 10.00);

		//listarEstudiante(nombreFichero);

		Estudiante e = buscarEstudiantePorId(nombreFichero, 2);
		if (e != null) {
			System.out.println("Se ha encontrado al estudiante");
		} else {
			System.out.println("No se ha encontrado al estudiante");
		}
	}

}
