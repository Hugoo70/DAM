package PracticaFinalHJS;

import java.nio.file.*;
import java.io.*;

public class Cliente implements Serializable {
    private int numeroCliente;
    private String nombre;
    private String direccion;

    public Cliente(int numeroCliente, String nombre, String direccion) {
        this.numeroCliente = numeroCliente;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public int getNumeroCliente() {
        return numeroCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    // Método para guardar la ficha del cliente en un archivo de texto
    public void guardarFicha() throws IOException {
        String contenido = "numerodecliente:" + numeroCliente + "\n"
                         + "nombre: " + nombre + "\n"
                         + "dirección: " + direccion + "\n";
        Path ruta = Paths.get(numeroCliente + ".txt");
        
        // Crear la carpeta "clientes" si no existe
        Files.createDirectories(ruta.getParent());
        
        // Guardar el contenido en el archivo del cliente
        Files.writeString(ruta, contenido);
    }
}

