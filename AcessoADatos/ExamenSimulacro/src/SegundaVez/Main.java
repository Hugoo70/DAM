package SegundaVez;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import Ej1.Usuario;

public class Main {
	static Scanner teclado = new Scanner(System.in);

	public static ArrayList<Usuario> leer(String ruta, ArrayList<Usuario> usuarios) {
		String Linea = "";
		try(BufferedReader br = new BufferedReader(new FileReader(ruta))) {
			while((Linea = br.readLine())!=null) {
				String[] partesLinea = Linea.split(";");
				if(partesLinea.length>=3) {
					Usuario user = new Usuario(partesLinea[0],partesLinea[1],partesLinea[2]);
					usuarios.add(user);
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return usuarios;
	}
	
	public static void menu(String ruta, String rol, ArrayList<Usuario> usuarios) {
		if(rol!=null) {
			switch (rol) {
			case "administrador":
				menuAdmin(ruta, usuarios);
				break;
				
			case "usuario":
				numRoles(usuarios);

				break;
				
			case "lector":
				System.out.println("Bienvenido lector!");
				break;

			default:
				break;
			}
		}
		
	}
	
	private static void numRoles(ArrayList<Usuario> usuarios) {
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

	public static void menuAdmin(String ruta, ArrayList<Usuario> usuarios) {
		// TODO Auto-generated method stub
		int opcion = 0;
		do {
            
            System.out.println("\n--- Menú Administrador ---");
            System.out.println("1. Añadir usuarios");
            System.out.println("2. Mostrar usuarios");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = teclado.nextInt();
            teclado.nextLine(); 
			switch (opcion) {
			case 1:
				addUser(ruta, usuarios);
				break;
				
			case 2:
				showUser(usuarios);
				break;
				
			case 0:
				
				break;

			default:
				break;
			}
			
		}while(opcion != 0);
	}

	private static void showUser(ArrayList<Usuario> usuarios) {
		// TODO Auto-generated method stub
		for (Usuario usuario : usuarios) {
			String passA = ("*").repeat(usuario.getPassword().length());
			System.out.println(usuario.getUsuario() + passA);
		}
	}

	public static void addUser(String ruta, ArrayList<Usuario> usuarios) {
		System.out.println("Introduce el nombre: ");
		String nombre = teclado.nextLine();
		System.out.println("Introduce la pass: ");
		String pass = teclado.nextLine();
		System.out.println("Introduce el rol: ");
		String rol = teclado.nextLine();

		try(BufferedWriter bw = new BufferedWriter(new FileWriter(ruta, true))) {
			bw.write(nombre + ";" + pass + ";" + rol);
			bw.newLine();
			System.out.println("Usuario introducido correctamente");
		} catch (IOException e) {
			System.out.println("Error al introducir el usuario al fichero " + e.getMessage());

		}
		
	}

	private static String login(String ruta, ArrayList<Usuario> usuarios) {
		String rol = null;
		int i = 0;
		boolean encontrado = false;
		
		System.out.println("Introduce el nombre: ");
		String nombre = teclado.nextLine();
		System.out.println("Introduce la contra: ");
		String pass = teclado.nextLine();

		
		while((i<usuarios.size())&(!encontrado)) {
			Usuario u = usuarios.get(i);
			if((nombre.equalsIgnoreCase(u.getUsuario()))&(pass.equalsIgnoreCase(u.getPassword()))){
				rol = u.getRol();
				encontrado = true;
			}
			i++;
		}
		
		System.out.println("Usuario encontrado: " + encontrado);
		return rol;
	}
	
	public static void main(String[] args) {
		ArrayList<Usuario> usuarios = new ArrayList<>();
		String ruta = "usuarios.txt";
		
		usuarios = leer(ruta, usuarios);
		String rol = login(ruta, usuarios);
		menu(ruta, rol, usuarios);

	}









}
