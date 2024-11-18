package PracticaFinal;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Ticket {
    private static ArrayList<Ticket> ticketsGenerados = new ArrayList<>();
    private static int contadorTickets = 1; // Contador estático para los números de ticket

    private static int numeroTicket;
    private static Cliente cliente;
    private static Gasolinera gasolinera;
    private static String tipoCombustible;
    private  static double precioLitro;
    private static double litros;
    private static double total;

    public Ticket(Cliente cliente, Gasolinera gasolinera, String tipoCombustible, double precioLitro, double litros) {
        this.numeroTicket = contadorTickets++;
        this.cliente = cliente;
        this.gasolinera = gasolinera;
        this.tipoCombustible = tipoCombustible;
        this.precioLitro = precioLitro;
        this.litros = litros;
        this.total = precioLitro * litros;

        // Al llamar al constructor el ticket se guarda en el ArrayList
        ticketsGenerados.add(this);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public double getTotal() {
        return total;
    }

    // Función para calcular el dinero gastado por cliente
    public static double calcularDineroGastadoPorCliente(String numeroCliente) {
        double totalGastado = 0;
        for (Ticket ticket : ticketsGenerados) {
            if (ticket.getCliente().getNumeroCliente().equals(numeroCliente)) {
                totalGastado += ticket.getTotal();
            }
        }
        return totalGastado;
    }
    // Formato para el .txt
    public static String formatoTicket() {
        return String.format(
            "Número de Ticket: %d\n%s\n%s\n%s\n%s\n%s (%.2f)-------%.2f\nTotal: %.2f Euros\n",
            numeroTicket,
            cliente.getNumeroCliente(),
            cliente.getNombre(),
            gasolinera.getNombre(),
            gasolinera.getUbicacion(),
            tipoCombustible,
            precioLitro,
            litros,
            total
        );
    }

    // Guardar el ticket en un archivo
    public static void guardarEnArchivo() {
        try {
            File carpeta = new File("src/Tickets");
            if (!carpeta.exists()) {
                carpeta.mkdirs();
            }

            String nombreArchivo = String.format("src/Tickets/Ticket_%d.txt", numeroTicket);
            File archivo = new File(nombreArchivo);

            FileWriter escritor = new FileWriter(archivo);
            escritor.write(formatoTicket());
            escritor.close();

            System.out.println("Ticket guardado en: " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("Error al guardar el ticket: " + e.getMessage());
        }
    }

    // Para listar todos los tickets generados
    public static void listarTicketsGenerados() {
        System.out.println("Lista de Tickets Generados:");
        for (Ticket ticket : ticketsGenerados) {
            System.out.println(ticket.formatoTicket());
        }
    }
}
