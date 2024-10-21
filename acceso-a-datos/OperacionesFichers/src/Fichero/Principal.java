package Fichero;

import java.io.*;

public class Principal {

	public static void cambiarPermisos(File f) {
		f.setExecutable(false);
		f.setReadable(false);
		f.setReadable(false);
	}

	public static void verPermisos(File f) {
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
	}

	public static void crearFichero(File f) {
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

	public static void comprobarDirectorio(File f) {
		if (f.isDirectory()) {
			listarFichero(f);
		} else {
			if (f.isFile()) {
				System.out.println("Es un fichero, no se puede recorrer");
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Se escribe el nombre que se guarda fisicamente
		String NombreDirectorio = ".\\datos\\";
		String NombreFichero = "FicheroNuevo.txt";
		File directorio = new File(NombreDirectorio);
		File fichero = new File(NombreDirectorio + NombreFichero);

		if (!directorio.exists()) {

			if (directorio.mkdir()) {

				crearFichero(fichero);

				/*
				 * verPermisos(fichero); cambiarPermisos(fichero); verPermisos(fichero);
				 */

			} else {
				System.out.println("No se ha podido crear el directorio");
			}

		} else {
			crearFichero(fichero);
		}
		
		comprobarDirectorio(directorio);
	}

}