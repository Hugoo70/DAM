package Ej1;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static Scanner teclado = new Scanner(System.in);

	public static void crearFichero(String ruta) {
		File f = new File(ruta);
		if(!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			System.out.println("El fichero ya existe");
		}
		
	}
	public static ArrayList<Usuario> leerFichero(String ruta, ArrayList<Usuario> usuarios){
		String Linea = "";
		String partesLinea[];
		try(BufferedReader bf = new BufferedReader(new FileReader(ruta))){
			while((Linea = bf.readLine())!=null) {
				partesLinea = Linea.split(";");
				Usuario u = new Usuario(partesLinea[0],partesLinea[1], partesLinea[2]);
				usuarios.add(u);
			}
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		return usuarios;
		
	}
	
	public static void visualizarUsuario(ArrayList<Usuario> usuarios) {
		for (Usuario u : usuarios) {
			System.out.println(u);
		} 
		
	}
	
	public static String ComprobarDatos(ArrayList<Usuario> usuarios) {
		int contador = 0;
		boolean encontrado = false;
		Usuario usuarioComprobado;
		String rol = null;
		
		System.out.println("Introduce el nombre del usuario: ");
		String nombre = teclado.nextLine();
		System.out.println("Introduce la contraseña del usuario: ");
		String pass = teclado.nextLine();
		
		while((contador<usuarios.size())&&(!encontrado)) {
			usuarioComprobado=usuarios.get(contador);
			
			if((nombre.equals(usuarioComprobado.getNombre()))&&(nombre.equals(usuarioComprobado.getContra()))) {
				rol = usuarioComprobado.getRol();
				encontrado = true;
			}
			contador++;
		}
		return rol;
	}
	
	public static void EscribirUsuario(String ruta, ArrayList<Usuario> usuarios) {
		System.out.println("Introduce el usuario: ");
		String usuario = teclado.nextLine();
		System.out.println("Introduce la contraseña: ");
		String pass = teclado.nextLine();
		System.out.println("Introduce el rol: ");
		String rol= teclado.nextLine();

		Usuario u  = new Usuario(usuario, pass, rol);
		usuarios.add(u);
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(ruta,true))) { // True para no sobreescribir los datos del fichero
			bw.write(usuarios);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void opcionesAdmin() {
		int opcion = 0;
		teclado.nextLine();
		do {
		System.out.println("Teclea la opción\n1. Añadir usuarios.\n2. Mostrar Usuarios.\n0. Salir");
		
		switch (opcion) {
		case 1:
			//Escribir usuario
			break;
			
		case 2:
			// Mostrar al usuario
			break;
			
		case 0:
			
			break;

		default:
			System.out.println("No es valida la opción");
			break;
		}
		opcion = teclado.nextInt();
		}while(opcion!=0);
	}
	
	public static void filtrarRoles(String rol, ArrayList<Usuario> usuarios) {
		if(rol!=null) {
			
			if(rol.equalsIgnoreCase("administrador")) {
				//Añadir user
				//Mostrar user con contraseña ocultas
				opcionesAdmin();
			}else {
				if(rol.equalsIgnoreCase("usuario")) {
					
				}else {
					if(rol.equalsIgnoreCase("lector")) {
						
					}else {
						System.out.println("El rol no existe");
					}
				}
			}
			
		}else{
			System.out.println("El usuario o contraseña no coinciden");
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String ruta = "datos.txt";
		ArrayList<Usuario> usuarios = new ArrayList<>();
		crearFichero(ruta);
		usuarios = leerFichero(ruta,usuarios);
		//visualizarUsuario(usuarios);
		filtrarRoles(ComprobarDatos(usuarios), ruta, usuarios);
	}

}
