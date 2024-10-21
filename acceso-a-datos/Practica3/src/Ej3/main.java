package Ej3;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class main {

	static Scanner teclado = new Scanner(System.in);
	static List<Alumno> alu = new ArrayList<Alumno>();

	public static void main(String[] args) {
// TODO Auto-generated method stub
		String ruta = "FicheroEj3.txt";

		Menu(ruta);
		System.out.println("Introduce los datos del libro");

	}

	public static void guardarAlu(List<Alumno> l, String ruta) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(ruta)));
			oos.writeObject(l);
			oos.close();
			System.out.println("Los librod han sido guardadas");
		} catch (IOException e) {
			e.getStackTrace();
		}
	}

	public static List<Alumno> LecturaAlu(String ruta) {

		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(ruta)));
			alu = (List<Alumno>) ois.readObject();
			ois.close();
		} catch (IOException | ClassNotFoundException e) {
			e.getMessage();
			e.getStackTrace();

		}

		return alu;

	}

	private static void Menu(String ruta) {
		// TODO Auto-generated method stub
		System.out.println(
				"Bievenido! Escoge una opción:\n1º Introducir Alumno.\n2º Introducir Nota.\n3º Mostrar Suspensos.\n4º Mostrar Aprobados.\n5º Sair.");
		int num = teclado.nextInt();

		switch (num) {
		case 1:
			 teclado.nextLine();
			System.out.println("Agregar un nuevo Alumno.");
			System.out.println("Introduce un Nombre...");
			String titulo = teclado.nextLine();
			System.out.println("Introduce un Apellido...");
			String autor = teclado.nextLine();
			System.out.println("Introduce la nota...(poner 0)");
			int nota = teclado.nextInt();
			alu.add(new Alumno(titulo, autor, nota));
			guardarAlu(alu, ruta);

			Menu(ruta);

			break;

		case 2:
			teclado.nextLine();
			System.out.println("Di el nombre del alumno.");
			String nombre = teclado.nextLine();
			for (Alumno v : alu) {
				if (nombre.equals(v.getNombre())) {
					System.out.println("Introduce la nota del alumno: ");
					int notaAlu = teclado.nextInt();
					v.setNota(notaAlu);
					LecturaAlu(ruta);

				}
			}

			Menu(ruta);

			break;

		case 3:
			for (Alumno v : alu) {
				if (v.getNota() < 5) {
					System.out.println(v);
				}
			}

			Menu(ruta);

			break;

		case 4:
			teclado.nextLine();
			System.out.println("Alumnos aprobados: ");

			for (Alumno v : alu) {
				if (v.getNota() >= 5) {
					System.out.println(v);
				}
			}

			Menu(ruta);

			break;

		case 5:
			System.out.println("Hasta pronto!");
			break;

		default:
			System.out.println("Ops, error de sistema");
			break;
		}
	}

}
