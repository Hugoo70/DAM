package Ej1;

import java.util.ArrayList;
import java.util.*;
import java.io.*;

public class main {

	public static void EscribirPersonas(List<Persona> p, String ruta) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(ruta)));
			oos.writeObject(p);
			oos.close();
			System.out.println("Las personas han sido guardadas");
		} catch (IOException e) {
			e.getStackTrace();
		}
	}

	public static List<Persona> LecturaPersonas(String ruta) {
		List<Persona> personas = new ArrayList<>();
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(ruta)));
			personas = (List<Persona>) ois.readObject();
			ois.close();
		} catch (IOException | ClassNotFoundException e) {
			e.getMessage();
			e.getStackTrace();

		}

		return personas;

	}

	public static void main(String[] args) {
// TODO Auto-generated method stub
		String ruta = "FicheroEj1.txt";
		String nombre = "";
		int edad;
		List<Persona> personas = new ArrayList<>();///
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce los datos de la persona");

		int opcion = 1;
		while (opcion != 0) {

			System.out.println("Introduce un nombre...");
			nombre = sc.nextLine();
			System.out.println("Introduce la edad...");
			edad = sc.nextInt();
			personas.add(new Persona(nombre, edad));

			System.out.println("Quieres conrinuar? Pulse 0 en el caso que no y cualquier num en caso de seguir");
			opcion = sc.nextInt();
			sc.nextLine();
		}
//haremos la llamada para escribir el array en el fich
		EscribirPersonas(personas, ruta);
		List<Persona> personasleidas = LecturaPersonas(ruta);
		for (Persona p : personasleidas) {
			System.out.println(p);
		}
		sc.close();

	}

}