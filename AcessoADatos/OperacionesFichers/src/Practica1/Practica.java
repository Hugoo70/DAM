package Practica1;

import java.io.*;
import java.util.Scanner;

public class Practica {
	static Scanner teclado = new Scanner(System.in);

	public static void CrearFicheroPrueba(File f) {
		try {
			if (!f.exists()) {
				// Crearemos fisicamente el fíchero
				if (f.createNewFile())
					System.out.println("Fichero creado");
				else
					System.out.println("Fichero no ha sido creado");
			} else {
				System.out.println("El fichero ya existe!");

			}
		}

		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void Menu() {
		int num = 0;

		System.out.println(
				"Escoge un ejercicio:\n1) Ejercicio 1.\n2) Ejercicio 2.\n3) Ejercicio 3.\n4) Ejercicio 4.\n5) Ejercicio 5.\n6) Ejercicio 6.\n7) Salir");
		num = teclado.nextInt();

		switch (num) {
		case 1:
			Ejercicio1();
			Menu();
			break;

		case 2:
			Ejercicio2();
			Menu();
			break;

		case 3:
			Ejercicio3();
			Menu();
			break;

		case 4:
			Ejercicio4();
			Menu();

			break;

		case 5:
			Ejercicio5();
			Menu();
			break;

		case 6:

			Menu();
			break;

		default:

			break;
		}
	}

	// ****************
	// ***** EJ 1 *****
	// ****************
	public static void Ejercicio1() {
		String nameDir = ".\\prueba\\";
		String nameFich = "fich.txt";
		File directorio = new File(nameDir);
		File fichero = new File(nameDir + nameFich);

		crearDirectorio(directorio);

		String NombreDirectorio;

		System.out.println("Nombre del directorio: ");
		teclado.nextLine();
		NombreDirectorio = teclado.nextLine();
		String ResDirectorio = (".\\" + NombreDirectorio + "\\");
		File dir = new File(ResDirectorio);

		listarFichero(dir);
	}

	public static void crearDirectorio(File d) {
		if (!d.exists()) {
			// Crearemos fisicamente el fíchero
			if (d.mkdir())
				System.out.println("Directorio creado");
			else
				System.out.println("Directorio no ha sido creado");
		} else {
			System.out.println("El Directorio ya existe!");

		}
	}

	public static void listarFichero(File d) {
		File[] ficheros = d.listFiles();

		for (File f : ficheros) {
			if (f.isFile()) {
				System.out.println(f.getName() + " es un fichero");
			}
			if (f.isDirectory()) {
				System.out.println(f.getName() + " es un directorio");
			}
		}
	}

	// ****************
	// ***** EJ 2 *****
	// ****************

	public static void Ejercicio2() {
		String nameFich = "fich.txt";
		File f = new File(nameFich);

		// Función para crear el fichero para la prueba del ejercicio, comentar siquieres probar el else!
		CrearFicheroPrueba(f);
		if (f.exists()) {
			f.delete();
			System.out.println("Fichero Borrado");
		} else {
			System.out.println("El fichero no existe!");

		}
	}
	
	// ****************
	// ***** EJ 3 *****
	// ****************
	
	public static void Ejercicio3() {
		teclado.nextLine();
		System.out.println("Escribe un nombre de fichero (terminación con .txt)");
		String NameFich = teclado.nextLine();
		
		System.out.println("Escribe un nombre de directorio");
		String NameDire = teclado.nextLine();
		
		String ResDirectorio = (".\\" + NameDire + "\\");
		
		File d = new File(ResDirectorio);
		File f = new File(ResDirectorio + NameFich);
		

		if (!d.exists()) {

			if (d.mkdir()) {
				try {
				f.createNewFile();
				System.out.println("Directorio y fichero creado");
				}
				catch (IOException e) {
					e.printStackTrace();
				}

			} else {
				System.out.println("No se ha podido crear el directorio");
			}

		}
		
	}
	
	
	// ****************
	// ***** EJ 4 *****
	// ****************
	
	public static void Ejercicio4(){
		teclado.nextLine();
		System.out.println("Escribe el nombre del directorio existente");
		String NameDire = teclado.nextLine();
		
		String ResDirectorio = (".\\" + NameDire + "\\");
		
		File d = new File(ResDirectorio);
	
			if (d.isDirectory()) {
				listarDirectorio(d);
			} else {
				if (d.isFile()) {
					System.out.println("Es un fichero, no se puede recorrer");
				}
			}
		
	
	}
	
	public static void listarDirectorio(File d) {
			File[] ficheros = d.listFiles();

			for (File f : ficheros) {
				if (f.isFile()) {
					System.out.println(f.getName() + " es un fichero");
				}
				if (f.isDirectory()) {
					System.out.println(f.getName() + " es un directorio");
				}
			}
		
		
	}
	
	// ****************
	// ***** EJ 5 *****
	// ****************
	
	public static void Ejercicio5(){
		File f = new File("Prueba.txt");
		
		// Función para crear el fichero para la prueba del ejercicio, comentar siquieres probar el else!
		CrearFicheroPrueba(f);
		
		if(f.exists()){
			ComprobarPermisos(f);
		}else {
			System.out.println("No existe el fichero");
		}
	}

	public static void ComprobarPermisos(File f) {
		String permisos = "";
		// Permisos de lectura
		if (f.canRead())
			permisos = permisos + "r";
		else
			permisos = permisos = " - ";

		// Permisos de escritura
		if (f.canWrite())
			permisos = permisos + "w";
		else
			permisos = permisos = " - ";

		// Permisos de ejecucion
		if (f.canExecute())
			permisos = permisos + "x";
		else
			permisos = permisos = " - ";

		System.out.println("Los permisos del archivo " + f.getName() + " son: " + permisos);
		
		if(permisos.equals("rwx")) {
			CambiarPermisos(f);
		}else {
			System.out.println("Nada que cambiar");
		}
	}
	
	public static void CambiarPermisos(File f) {
		String PermisoR, PermisoW, PermisoX;
		do {
		teclado.nextLine();
		System.out.println("Añade o quita permisos a Read(r): ");
		PermisoR = teclado.nextLine();
		System.out.println("Añade o quita permisos a Write(w): ");
		PermisoW = teclado.nextLine();
		System.out.println("Añade o quita permisos a Executable(x): ");
		PermisoX = teclado.nextLine();
		
		
		}while(PermisoR.equals("r") & PermisoW.equals("w") & PermisoX.equals("x"));
		
		if(PermisoR.equals("r")) {
			f.setReadable(false);
			
		}else if(PermisoW.equals("w")){
			f.setWritable(false);

		}else if(PermisoX.equals("x")){
			f.setExecutable(false);
	}
		System.out.println(PermisoR + PermisoW + PermisoX);
	}

	// ****************
	// ***** EJ 6 *****
	// ****************
	public static void Ejercicio6() {
		
		System.out.println("Nombre del fichero: ");
		String NameFile = teclado.nextLine();
		File f = new File(NameFile);
 
	try {
		if (!f.exists()) {
			// Crearemos fisicamente el fíchero
			if (f.createNewFile())
				System.out.println("Fichero creado");
			else
				System.out.println("Fichero no ha sido creado");
		} else {
			System.out.println("El fichero ya existe!" + f.getAbsolutePath());
			f.setReadable(true);
			f.setWritable(false);
			f.setExecutable(false);
		}
	}

	catch (IOException e) {
		e.printStackTrace();
	}
	
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Menu();

	}

}
