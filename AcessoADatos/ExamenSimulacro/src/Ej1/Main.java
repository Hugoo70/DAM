package Ej1;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);  // Scanner global para evitar cerrarlo múltiples veces.

    /*    public static void crearFichero(String ruta) {
        File fichero = new File(ruta);
        if (!fichero.exists()) {
            try {
                fichero.createNewFile();
                System.out.println("El fichero se está creando...");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("El fichero ya está creado.");
        }
    }*/

    public static ArrayList<Usuario> leerFichero(String ruta, ArrayList<Usuario> listaUsuarios) {
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length >= 3) {  // Validar que la línea tenga los campos suficientes
                    Usuario u = new Usuario(partes[0], partes[1], partes[2]);
                    listaUsuarios.add(u);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listaUsuarios;
    }

    public static void visualizarArray(ArrayList<Usuario> listaUsuarios) {
        for (Usuario u : listaUsuarios) {
            String passOculto = "*".repeat(u.getPassword().length());
            System.out.println(u.getUsuario() + ": " + passOculto);
        }
    }

    public static String recogerDatos(ArrayList<Usuario> listaUsuarios) {
        boolean usuarioExiste = false;
        int i = 0;
        String rol = null;

        System.out.print("Ingrese el nombre de usuario: ");
        String usuarioIngresado = scanner.nextLine();

        System.out.print("Ingrese la contraseña: ");
        String contrasenaIngresada = scanner.nextLine();

        // Validar si el usuario existe y las credenciales son correctas
        while ((i < listaUsuarios.size()) && (!usuarioExiste)) {
            Usuario usuario = listaUsuarios.get(i);
            if (usuarioIngresado.equals(usuario.getUsuario()) && contrasenaIngresada.equals(usuario.getPassword())) {
                rol = usuario.getRol();
                usuarioExiste = true;
            }
            i++;
        }

        System.out.println("Usuario existe: " + usuarioExiste);
        return rol;
    }

    public static void contarUsuariosPorRol(ArrayList<Usuario> usuarios) {
        String[] roles = {"administrador", "usuario", "lector"};
        int[] conteo = new int[roles.length];

        // Contar los roles
        for (Usuario u : usuarios) {
            String rol = u.getRol();
            for (int i = 0; i < roles.length; i++) {
                if (rol.equals(roles[i])) {
                    conteo[i]++;
                    break;
                }
            }
        }

        // Mostrar el conteo por rol
        System.out.println("Número de usuarios por rol:");
        for (int i = 0; i < roles.length; i++) {
            System.out.println("Rol: " + roles[i] + " : " + conteo[i]);
        }
    }

    public static int escribirAlumnoEnFichero(String nombreFichero, ArrayList<Usuario> usuarios) {
        // Pedir datos al usuario
        System.out.print("Ingrese el usuario: ");
        String usuario = scanner.nextLine();
        System.out.print("Ingrese la contraseña: ");
        String contrasena = scanner.nextLine();
        System.out.print("Ingrese el rol: ");
        String rol = scanner.nextLine();

        Usuario u = new Usuario(usuario, contrasena, rol);

        // Escribir en el fichero
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreFichero, true))) {
            bw.write("\n" + u.getUsuario() + ";" + u.getPassword() + ";" + u.getRol());
            bw.newLine();
            System.out.println("Usuario guardado correctamente.");
        } catch (IOException e) {
            System.err.println("Error al escribir en el fichero: " + e.getMessage());
        }
        return 0;
    }

    public static void funcionesRoles(String rol, String nombreFichero, ArrayList<Usuario> usuarios) {
        if (rol != null) {
            switch (rol) {
                case "administrador":
                    mostrarMenuAdministrador(nombreFichero, usuarios);
                    break;
                case "usuario":
                    contarUsuariosPorRol(usuarios);
                    break;
                case "lector":
                    System.out.println("Bienvenido, lector.");
                    break;
                default:
                    System.out.println("No existe ese rol.");
                    break;
            }
        } else {
            System.out.println("No existe ese usuario.");
        }
    }

    public static void mostrarMenuAdministrador(String nombreFichero, ArrayList<Usuario> usuarios) {
        int opcion;
        do {
            // Mostrar el menú del administrador
            System.out.println("\n--- Menú Administrador ---");
            System.out.println("1. Añadir usuarios");
            System.out.println("2. Mostrar usuarios");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    escribirAlumnoEnFichero(nombreFichero, usuarios);
                    break;
                case 2:
                    visualizarArray(usuarios);
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    public static void main(String[] args) {
        String ruta = "usuarios.txt";
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        //crearFichero(ruta);  // Este método ya crea el fichero si no existe
        listaUsuarios = leerFichero(ruta, listaUsuarios);
        String rol = recogerDatos(listaUsuarios);
        funcionesRoles(rol, ruta, listaUsuarios);

        scanner.close();  // Cerrar el scanner aquí, cuando ya no se necesita.
    }
}
