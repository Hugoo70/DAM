package Ej2;

import java.io.*;
import java.util.List;

public class Main {

	private static void EscribirFichero(String nombreFichero, Libreria libreria) {
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(nombreFichero)))) {
			oos.writeObject(libreria);
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	private static  Libreria LeerFichero(String nombreFichero, Libreria libreria) {
		
		Libreria l = new Libreria();
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(nombreFichero)))) {
			l=(Libreria)ois.readObject();

			
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			System.out.println("No hemos podido recuperar el objeto");
			e.printStackTrace();
		}catch (IOException e) {
			// TODO: handle exception
			System.out.println("Error en la lectura");
			e.printStackTrace();
		}
		return l;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String nombreFichero = "Libros.txt";
		Libreria libreria = new Libreria();
		Libreria aux = new Libreria();
		
		Libreria.insertarLibro("Geronimo Stilton", "Geronimo Stilton", 20.33);
		Libreria.insertarLibro("Geronimo Stilton2", "Geronimo Stilton", 20.33);
		Libreria.insertarLibro("Geronimo Stilton3", "Geronimo Stilton", 20.33);
		Libreria.insertarLibro("Geronimo Stilton4", "Geronimo Stilton", 30.33);
		Libreria.insertarLibro("Geronimo Stilton5", "Geronimo Stilton", 30.33);
		
		EscribirFichero(nombreFichero, libreria);
		aux = LeerFichero(nombreFichero, libreria);
		aux.ordenarLibroPrecio();
		aux.listarLibro();
		List<Libro> p = aux.filtrarLibrosPorPrecio(10,20);
		for (Libro l : p) {
			System.out.println(l);
		}

	}

}
