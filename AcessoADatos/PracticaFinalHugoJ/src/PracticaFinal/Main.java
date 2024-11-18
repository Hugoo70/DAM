package PracticaFinal;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {
    static Scanner teclado = new Scanner(System.in);

    // Arrray de clientes y gasolineras
    private static List<Gasolinera> gasolineras = new ArrayList<>();
    private static List<Cliente> clientes = new ArrayList<>();

    public static void main(String[] args) {
        clientes.add(new Cliente("12345", "Luis García", 0));
        clientes.add(new Cliente("67890", "Ana Pérez", 0));

        gasolineras.add(new Gasolinera("Gasolinera1", "Madrid", 1000, 800, 1.95, 1.55));
        gasolineras.add(new Gasolinera("Gasolinera2", "Barcelona", 1500, 1200, 1.70, 1.80));
        gasolineras.add(new Gasolinera("Gasolinera3", "Madrid", 2000, 1800, 1.68, 1.78));

        mostrarMenuPrincipal();
    }

    // Menu para elegir el Usuario o Admin
    public static void mostrarMenuPrincipal() {
        System.out.println("Menú Principal:");
        System.out.println("1. Usuario");
        System.out.println("2. Administrador");
        System.out.print("Selecciona una opción: ");

        int opcion = teclado.nextInt();
        teclado.nextLine(); 

        if (opcion == 1) {
            mostrarMenuUsuario();
        } else if (opcion == 2) {
            mostrarMenuAdministrador();
        } else {
            System.out.println("Opción no válida.");
        }
    }

    // Menu de Usuario
    public static void mostrarMenuUsuario() {
        System.out.println("Menú Usuario:");
        System.out.println("1. Ver gasolineras");
        System.out.println("2. Realizar venta (repostaje)");
        System.out.println("3. Ver informe de dinero gastado");
        System.out.println("4. Volver");
        System.out.println("5. Salir");

        int opcion = teclado.nextInt();
        teclado.nextLine();

        switch (opcion) {
        case 1:
        	// Muestra las gasolineras disponibles
        	mostrarGasolineras();  
            mostrarMenuUsuario(); 
            break;
        case 2:
        	// Vende combustible al usuario y crea un Ticket
            realizarVenta();  
            mostrarMenuUsuario();  
            break;
        case 3:
        	// Muestra el dinero gastado por el usuario
            verInformeDeDineroGastado();  
            mostrarMenuUsuario(); 
            break;
        case 4:
        	// Vuelve al menú principal
            mostrarMenuPrincipal();  
            break;
        case 5:
            System.out.println("Saliendo...");  // Sale del programa
            break;
        default:
            System.out.println("Opción no válida.");
        }
    }

    // Menu de adminsitrador
    private static void mostrarMenuAdministrador() {
        System.out.println("Menú Administrador:");
        System.out.println("1. Ver todos los clientes");
        System.out.println("2. Añadir cliente");
        System.out.println("3. Eliminar cliente");
        System.out.println("4. Ver gasolineras");
        System.out.println("5. Añadir gasolinera");
        System.out.println("6. Realizar venta");
        System.out.println("7. Ver estadísticas generales");
        System.out.println("8. Ver estadísticas por cliente");
        System.out.println("9. Volver");
        System.out.println("10. Salir");

        int opcion = teclado.nextInt();
        teclado.nextLine();

        switch (opcion) {
            case 1:
            	// Ver todos los clientes de la app
                verTodosLosClientes();
                mostrarMenuAdministrador();
                break;
            case 2:
            	// Añadir un cliente 
                añadirCliente();
                mostrarMenuAdministrador();
                break;
            case 3:
            	// Eliminar un cliente moviendolo a la carpeta de Antiguos
                eliminarCliente();
                mostrarMenuAdministrador();
                break;
            case 4:
            	// Muestra todas las gasolineras de la app
                mostrarGasolineras();
                mostrarMenuAdministrador();
                break;
            case 5:
            	// Añadir una gasolinera
                añadirGasolinera();
                mostrarMenuAdministrador();
                break;
            case 6:
            	// Vende combustible al usuario y crea un Ticket
                realizarVenta();
                mostrarMenuAdministrador();
                break;
            case 7:
            	// Muestra las estadisticas de toda la app
                realizarEstadisticas();
                mostrarMenuAdministrador();
                break;
            case 8:
            	// Muestra las estadisticas del cliente elegido
                realizarEstadisticasPorCliente();
                mostrarMenuAdministrador();
                break;
            case 9:
                mostrarMenuPrincipal();
                break;
            case 10:
                System.out.println("Saliendo...");
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }

    // Muestra la lista de gasolineras disponibles
    public static void mostrarGasolineras() {
        for (Gasolinera gas : gasolineras) {
            System.out.println(gas);  
        }
    }

    // Permite al usuario realizar un repostaje en una gasolinera seleccionada
    public static void realizarVenta() {
        System.out.println("Introduce el número de cliente:");
        String numeroCliente = teclado.nextLine();

        // Busca el cliente por su id
        Cliente cliente = buscarCliente(numeroCliente);  
        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }
        // Buscamos la gasolinera para respostar
        System.out.println("Selecciona la gasolinera para repostar:");
        mostrarGasolineras();

        int opcionGasolinera = teclado.nextInt();
        teclado.nextLine();
        if (opcionGasolinera < 1 || opcionGasolinera > gasolineras.size()) {
            System.out.println("Gasolinera no válida.");
            return;
        }

        Gasolinera gasolinera = gasolineras.get(opcionGasolinera - 1);

        // El usuario selecciona el tipo de combustible (Gasolina 95 o Diesel)
        System.out.println("Selecciona el tipo de combustible:");
        System.out.println("1. Gasolina 95");
        System.out.println("2. Diesel");
        int tipoCombustible = teclado.nextInt();
        teclado.nextLine();

        System.out.println("Introduce los litros que deseas repostar:");
        int litros = teclado.nextInt();
        teclado.nextLine();

        // Se realiza el repostaje según el tipo de combustible seleccionado
        if (tipoCombustible == 1) {
            if (gasolinera.repostar95(litros)) {
                double total = litros * gasolinera.getPrecio95();
                cliente.setDineroGastado(cliente.getDineroGastado() + total);
                System.out.println("Repostaje realizado con éxito.");
                System.out.printf("Total: %.2f Euros\n", total);
                new Ticket(cliente, gasolinera, "Gasolina 95", gasolinera.getPrecio95(), litros);
                Ticket.guardarEnArchivo();  // Guarda el ticket en un archivo
            } else {
                System.out.println("No hay suficiente gasolina 95.");
            }
        } else if (tipoCombustible == 2) {
            if (gasolinera.repostarDiesel(litros)) {
                double total = litros * gasolinera.getPrecioDiesel();
                cliente.setDineroGastado(cliente.getDineroGastado() + total);
                System.out.println("Repostaje realizado con éxito.");
                System.out.printf("Total: %.2f Euros\n", total);
                new Ticket(cliente, gasolinera, "Diesel", gasolinera.getPrecioDiesel(), litros);
            } else {
                System.out.println("No hay suficiente Diesel.");
            }
        } else {
            System.out.println("Opción no válida.");
        }
    }

    // Muestra el dinero total gastado por un cliente
    public static void verInformeDeDineroGastado() {
        System.out.println("Introduce el número de cliente:");
        String numeroCliente = teclado.nextLine();

        Cliente cliente = buscarCliente(numeroCliente);
        if (cliente != null) {
            System.out.printf("El total gastado por el cliente %s es: %.2f Euros\n", cliente.getNombre(),
                    cliente.getDineroGastado());
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    // Busca un cliente por su número de cliente
    public static Cliente buscarCliente(String numeroCliente) {
        for (Cliente cliente : clientes) {
            if (cliente.getNumeroCliente().equals(numeroCliente)) {
                return cliente;
            }
        }

        // Si no se encuentra, verifica si el cliente está en la lista de clientes dados de baja
        File archivoAntiguo = new File(String.format("src/clientesAntiguos/Cliente_%s.txt", numeroCliente));
        if (archivoAntiguo.exists()) {
            System.out.println("Este cliente está dado de baja y no puede realizar compras.");
        }

        return null;
    }

    // Muestra todos los clientes registrados
    public static void verTodosLosClientes() {
        for (Cliente cliente : clientes) {
            System.out.println(cliente); 
        }
    }

    // Permite añadir un nuevo cliente
    public static void añadirCliente() {
        System.out.println("Introduce el número de cliente:");
        String numeroCliente = teclado.nextLine();
        System.out.println("Introduce el nombre del cliente:");
        String nombre = teclado.nextLine();

        // Se añade el nuevo cliente a la lista
        clientes.add(new Cliente(numeroCliente, nombre, 0));
        System.out.println("Cliente añadido.");
    }

    // Permite añadir una nueva gasolinera
    public static void añadirGasolinera() {
        System.out.println("Introduce el nombre de la nueva gasolinera:");
        String nombre = teclado.nextLine();

        System.out.println("Introduce la ubicación de la gasolinera:");
        String ubicacion = teclado.nextLine();

        System.out.println("Introduce la cantidad de litros de gasolina 95 disponibles:");
        int litros95 = teclado.nextInt();

        System.out.println("Introduce la cantidad de litros de diesel disponibles:");
        int litrosDiesel = teclado.nextInt();

        System.out.println("Introduce el precio de la gasolina 95:");
        double precio95 = teclado.nextDouble();

        System.out.println("Introduce el precio del diesel:");
        double precioDiesel = teclado.nextDouble();

        // Crear la nueva gasolinera y agregarla a la lista
        Gasolinera nuevaGasolinera = new Gasolinera(nombre, ubicacion, litros95, litrosDiesel, precio95, precioDiesel);
        gasolineras.add(nuevaGasolinera);

        System.out.println("Gasolinera añadida con éxito.");
    }

    // Permite eliminar un cliente y moverlo a una carpeta de clientes dados de baja
    public static void eliminarCliente() {
        System.out.println("Introduce el número de cliente a eliminar:");
        String numeroCliente = teclado.nextLine();

        Cliente cliente = buscarCliente(numeroCliente); 
        if (cliente != null) {
            try {
                // Crear carpeta clientesAntiguos si no existe
                File carpetaAntiguos = new File("src/clientesAntiguos");
                if (!carpetaAntiguos.exists()) {
                    carpetaAntiguos.mkdirs();
                }

                // Crear el archivo del cliente en la carpeta de clientes dados de baja
                String nombreArchivo = String.format("src/clientesAntiguos/Cliente_%s.txt", cliente.getNumeroCliente());
                File archivoCliente = new File(nombreArchivo);

                FileWriter escritor = new FileWriter(archivoCliente);
                escritor.write(cliente.toString());
                escritor.close();

                // Eliminar el cliente de la lista
                clientes.remove(cliente);
                System.out.println("Cliente dado de baja y movido a clientesAntiguos.");
            } catch (IOException e) {
                System.out.println("Error al dar de baja al cliente: " + e.getMessage());
            }
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    // Muestra estadísticas de dinero gastado por todos los clientes
    public static void realizarEstadisticas() {
        System.out.println("Estadísticas de Dinero Gastado:");

        double totalDineroGastado = 0;

        // Mostrar estadísticas de cada cliente
        for (Cliente cliente : clientes) {
            double dineroGastadoCliente = cliente.getDineroGastado();
            totalDineroGastado += dineroGastadoCliente;
            System.out.printf("Cliente: %s (%s), Dinero Gastado: %.2f Euros%n", cliente.getNombre(),
                    cliente.getNumeroCliente(), dineroGastadoCliente);
        }

        // Mostrar estadísticas generales
        System.out.printf("Total de dinero gastado por todos los clientes: %.2f Euros%n", totalDineroGastado);
    }

    // Muestra las estadísticas de dinero gastado por un cliente específico
    private static void realizarEstadisticasPorCliente() {
        System.out.println("Introduce el número de cliente para filtrar:");
        String numeroCliente = teclado.nextLine();

        Cliente cliente = buscarCliente(numeroCliente);

        if (cliente == null) {
            System.out.println("Cliente no encontrado o está dado de baja.");
        } else {
            System.out.printf("Cliente: %s (%s), Dinero Gastado: %.2f Euros%n", cliente.getNombre(),
                    cliente.getNumeroCliente(), cliente.getDineroGastado());
        }
    }
}
