package PracticaFinalHJS;

import java.nio.file.*;
import java.util.*;
import java.io.*;

public class ClienteManager {
    private List<Cliente> clientes;

    public ClienteManager() {
        this.clientes = new ArrayList<>();
        cargarClientes();
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    private void cargarClientes() {
        try {
            // Leer todas las líneas del archivo de texto
            List<String> lineas = Files.readAllLines(Paths.get("clientes.txt"));
            int numeroCliente = 0;
            String nombre = "";
            String direccionCompleta = "";
            
            // Recorrer las líneas y parsear cada cliente
            for (String linea : lineas) {
                // Si encontramos la línea separadora, creamos un cliente y lo agregamos a la lista
                if (linea.equals("---")) {
                    if (numeroCliente != 0) { // Verificar si ya tenemos datos de un cliente
                        Cliente cliente = new Cliente(numeroCliente, nombre, direccionCompleta);
                        clientes.add(cliente);
                        cliente.guardarFicha(); // Guardar la ficha del cliente
                    }
                    // Resetear las variables para el siguiente cliente
                    numeroCliente = 0;
                    nombre = "";
                    direccionCompleta = "";
                } else {
                    // Dividir la línea en clave y valor
                    String[] partes = linea.split(":", 2);
                    if (partes.length < 2) continue;
                    String clave = partes[0].trim();
                    String valor = partes[1].trim();

                    // Asignar los valores a las variables correspondientes
                    switch (clave) {
                        case "numerodecliente":
                            numeroCliente = Integer.parseInt(valor);
                            break;
                        case "nombre":
                            nombre = valor;
                            break;
                        case "calle":
                            direccionCompleta = valor;
                            break;
                        case "ciudad":
                            direccionCompleta += ", " + valor;
                            break;
                        case "codigoPostal":
                            direccionCompleta += ", " + valor;
                            break;
                        case "pais":
                            direccionCompleta += ", " + valor;
                            break;
                    }
                }
            }
            // Añadir el último cliente si el archivo no termina con '---'
            if (numeroCliente != 0) {
                Cliente cliente = new Cliente(numeroCliente, nombre, direccionCompleta);
                clientes.add(cliente);
                cliente.guardarFicha();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

