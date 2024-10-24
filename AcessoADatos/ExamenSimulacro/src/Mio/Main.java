package Mio;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import Ej1.Usuario;

public class Main {
	static Scanner teclado = new Scanner(System.in);

	public static ArrayList<Usuario> leerFichero(String ruta, ArrayList<Usuario> usuarios) {
		String Linea = "";
		try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
			while ((Linea = br.readLine()) != null) {
				String[] partesLinea = Linea.split(";");
				if (partesLinea.length >= 3) {
					Usuario u = new Usuario(partesLinea[0], partesLinea[1], partesLinea[2]);
					usuarios.add(u);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return usuarios;
	}

	public static String login(ArrayList<Usuario> usuarios) {
		int contador = 0;
		boolean encontrado = false;
		String rol = null;

		System.out.println("Introduce el nombre: ");
		String nombre = teclado.nextLine();
		System.out.println("Introduce la contrasena: ");
		String contra = teclado.nextLine();
		while ((contador < usuarios.size()) && (!encontrado)) {
			Usuario usuario = usuarios.get(contador);
			if ((nombre.equalsIgnoreCase(usuario.getUsuario())) && (contra.equalsIgnoreCase(usuario.getPassword()))) {
				rol = usuario.getRol();
				encontrado = true;
			}
			contador++;
		}

		System.out.println("Usuario existe: " + encontrado);
		return rol;
	}

	public static void menu(String rol, String ruta, ArrayList<Usuario> usuarios) {
		if (rol != null) {
			switch (rol) {
			case "administrador":
				MenuAdmin(ruta, usuarios);
				break;
			case "usuario":
				verRoles(usuarios);
				break;
			case "lector":
				System.out.println("Bienvenido lector!");
				break;

			default:
				break;
			}
		}

	}

	public static void verRoles(ArrayList<Usuario> usuarios) {
		int admin = 0;
		int user = 0;
		int lector = 0;

		for (Usuario usuario : usuarios) {
			if (usuario.getRol().equals("administrador")) {
				admin++;
			} else if (usuario.getRol().equals("usuario")) {
				user++;
			} else {
				lector++;
			}
		}
		System.out.println("Hay " + admin + " administrador, " + user + " usuarios, " + lector + " lectores");
	}

	public static void MenuAdmin(String ruta, ArrayList<Usuario> usuarios) {
		int opcion;
		do {
			// Mostrar el menú del administrador
			System.out.println("\n--- Menú Administrador ---");
			System.out.println("1. Añadir usuarios");
			System.out.println("2. Mostrar usuarios");
			System.out.println("0. Salir");
			System.out.print("Seleccione una opción: ");
			opcion = teclado.nextInt();
			teclado.nextLine(); // Limpiar buffer

			switch (opcion) {
			case 1:
				addUser(ruta, usuarios);
				break;
			case 2:
				mostrarUser(usuarios);
				break;
			case 0:
				System.out.println("Saliendo...");
				break;
			default:
				System.out.println("Opción no válida.");
			}
		} while (opcion != 0);

	}

	public static void mostrarUser(ArrayList<Usuario> usuarios) {
		for (Usuario u : usuarios) {
			String passOculto = "*".repeat(u.getPassword().length());
			System.out.println(u.getUsuario() + ": " + passOculto);
		}

	}

	public static void addUser(String ruta, ArrayList<Usuario> usuarios) {

		System.out.println("Introduce el nombre: ");
		String nombre = teclado.nextLine();
		System.out.println("Introduce la contra: ");
		String contra = teclado.nextLine();
		System.out.println("Introduce el rol: ");
		String rol = teclado.nextLine();

		Usuario u = new Usuario(nombre, contra, rol);

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta, true))) {
			bw.write("\n" + u.getUsuario() + ";" + u.getPassword() + ";" + u.getRol());
			bw.newLine();
			System.out.println("Usuario guardado correctamente.");
		} catch (IOException e) {
			System.out.println("Error al escribir en el fichero: " + e.getMessage());

		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String ruta = "Usuarios.txt";
		ArrayList<Usuario> usuarios = new ArrayList<>();
		usuarios = leerFichero(ruta, usuarios);
		String rol = login(usuarios);
		menu(rol, ruta, usuarios);
	}

}
