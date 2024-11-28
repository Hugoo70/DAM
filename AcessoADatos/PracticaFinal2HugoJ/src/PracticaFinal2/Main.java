package PracticaFinal2;

import java.util.Scanner;

public class Main {

	static Scanner teclado = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MenuPrincipal();
	}

	public static void MenuPrincipal() {
		System.out.println("Menú Principal:");
		System.out.println("1. Usuario");
		System.out.println("2. Administrador");
		System.out.print("Selecciona una opción: ");

		int opcion = teclado.nextInt();
		teclado.nextLine();

		switch (opcion) {
		case 1:
			MenuAdministrador();
			break;
		case 2:
			MenuEmpleado();
			break;
		case 3:
			MenuCliente();

			break;
		default:
			System.out.println("Opción no válida.");
		}
		System.out.println();

	}

	private static void MenuAdministrador() {
		// TODO Auto-generated method stub
		System.out.println("Menú Administrador:");
		System.out.println("1. Insertar/modificar/eliminar clientes");
		System.out.println("2. Insertar/modificar/eliminar empleados");
		System.out.println("3. Insertar/modificar/eliminar productos");
		System.out.print("Selecciona una opción: ");

		int opcion = teclado.nextInt();
		teclado.nextLine();

		switch (opcion) {
		case 1:
			IME(1);
			MenuAdministrador();
			break;
		case 2:
			IME(2);
			MenuAdministrador();
			break;
		case 3:
			IME(3);
			MenuAdministrador();
			break;
		default:
			System.out.println("Opción no válida.");
		}
		System.out.println();
	}

	private static void IME(int tipoUsuario) {
		// TODO Auto-generated method stub
		String usuario = null;
		if (tipoUsuario == 1) {
			usuario = "clientes";
		} else if (tipoUsuario == 2) {
			usuario = "empleados";
		} else if (tipoUsuario == 3) {
			usuario = "productos";
		} else {
			System.out.println("No es tipo de usuario valido.");
		}

		System.out.println("Menú Insertar/modificar/eliminar de los " + usuario + ":");
		System.out.println("1. Id de " + usuario);
		System.out.println("2. Lista de " + usuario);
		System.out.print("Selecciona una opción: ");

		int opcion = teclado.nextInt();
		teclado.nextLine();

		switch (opcion) {
		case 1:
			SacarId(tipoUsuario);
			IME(tipoUsuario);
			break;
		case 2:
			Listar(tipoUsuario);
			IME(tipoUsuario);
			break;
		default:
			System.out.println("Opción no válida.");
		}
		System.out.println();
	}

	private static void SacarId(int tipoUsuario) {
		System.out.println("Introduce el ID:");
		int id = teclado.nextInt();
		teclado.nextLine();
		String consulta = null;

		if (tipoUsuario == 1) { // Clientes
			System.out.println("Selección clientes:\n"
					+ "1º Insertar\n"
					+ "2º Modificar\n"
					+ "3º Eliminar");
			int opcion = teclado.nextInt();
			teclado.nextLine();

			switch (opcion) {
			case 1:
				consulta = "INSERT * FROM cliente WHERE numerodecliente=" + id;
				break;
			case 2:
				consulta = "INSERT * FROM cliente WHERE numerodecliente=" + id;

				break;
			case 3:
				consulta = "REMOVE * FROM cliente WHERE numerodecliente=" + id;

				break;
			default:
				System.out.println("Opción no válida.");
			}
			
		} else if (tipoUsuario == 2) { // Empleados
			consulta = "SELECT * FROM empleado WHERE idempleado=" + id;
		} else if (tipoUsuario == 3) { // Productos
			consulta = "SELECT * FROM producto WHERE idproducto=" + id;
		}

		if (consulta != null) {
			ConexionBBDD.conexionBBDD(consulta);
		} else {
			System.out.println("Tipo de usuario no válido.");
		}

	}


	private static void Listar(int tipoUsuario) {
		// TODO Auto-generated method stub
		if (tipoUsuario == 1) {
			// consulta de Cliente

		} else if (tipoUsuario == 2) {
			// consulta de Empleado
		} else if (tipoUsuario == 3) {
			// Consulta de Productos
		} else {
			System.out.println("No es tipo de usuario valido.");
		}
	}
	
	

	private static void MenuEmpleado() {
		// TODO Auto-generated method stub

	}

	private static void MenuCliente() {
		// TODO Auto-generated method stub

	}
}
