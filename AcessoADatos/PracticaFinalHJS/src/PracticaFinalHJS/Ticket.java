package PracticaFinalHJS;

import java.nio.file.*;
import java.io.IOException;

public class Ticket {
    private static int contador = 1;
    private int numeroTicket;
    private Cliente cliente;
    private Gasolinera gasolinera;
    private String tipoCombustible;
    private double precioPorLitro;
    private double litros;
    private double total;

    public Ticket(Cliente cliente, Gasolinera gasolinera, String tipoCombustible, double precioPorLitro, double litros) {
        this.numeroTicket = contador++;
        this.cliente = cliente;
        this.gasolinera = gasolinera;
        this.tipoCombustible = tipoCombustible;
        this.precioPorLitro = precioPorLitro;
        this.litros = litros;
        this.total = precioPorLitro * litros;
    }

    public void guardarTicket() throws IOException {
        String contenido = "Número de Ticket: " + numeroTicket + "\n"
                         + cliente.getNumeroCliente() + "\n"
                         + cliente.getNombre() + "\n"
                         + gasolinera.getNombre() + "\n"
                         + gasolinera.getUbicacion() + "\n"
                         + tipoCombustible + " (" + precioPorLitro + ")-------" + litros + "\n"
                         + "total: " + total + " Euros\n";
        Path ruta = Paths.get("TICKETS/" + numeroTicket + ".txt");
        Files.writeString(ruta, contenido);
    }
}
