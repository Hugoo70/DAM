package Pr3;

import com.google.gson.Gson;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        Gson gson = new Gson();
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        
        // Bucle para sacar todos los clientes del Json y meterlos en el Array de listaClientes para poder operar con ellos.
        for (int i = 1; i <= 6; i++) {
            String fileC = "data//Cliente" + i + ".json";
            try (FileReader reader = new FileReader(fileC)) {
                Cliente cliente = gson.fromJson(reader, Cliente.class);
                listaClientes.add(cliente);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // Comprobación de que están todos los clientes en el Array (Deben ser 6)
        System.out.println("Clientes iniciales:");
        for (Cliente cliente : listaClientes) {
            System.out.println(cliente);
        }

        // Crear un pool con un hilo por cada transferencia (10)
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        // Bucle para sacar todas las transferencias llamando a la funcion procesarTransferencia donde lanzaremos todos los hilos.
        for (int i = 1; i <= 10; i++) {
            String fileT = "data//transferencias" + i + ".json";
            executorService.submit(() -> procesarTransferencias(fileT, gson, listaClientes));
        }

        // Cerrar el pool de hilos cuando acaben todos
        while (!executorService.isTerminated()) {
            executorService.shutdown();

        }

        // Bucle para mostrar de nuevo a los clientes después de las transferencias y ver cómo ha quedado su saldo.
        System.out.println("\nSaldos finales:");
        for (Cliente cliente : listaClientes) {
            System.out.println(cliente.getNombre() + " tiene ahora un saldo de " + cliente.getSaldo());
        }
    }

    /**
     * Sacar las transferencias de los Json.
     * Calcular las transferencias y establecer el saldo tras los movimientos.
     * @param file Nombre del archivo donde estan las transferencias.
     * @param gson Objeto del Gson.
     * @param listaClientes ArrayList donde se guardan todos los clientes sacados del JSON de clientes.
     */
    private static void procesarTransferencias(String file, Gson gson, ArrayList<Cliente> listaClientes) {
        try (FileReader reader = new FileReader(file)) {
            Transferencia[] transferencias = gson.fromJson(reader, Transferencia[].class);

            // Bucle para operar con cada transferencia
            for (Transferencia t : transferencias) {

                // Operamos con el cliente origen usando la función para encontrarlo, buscando en el array el id y sacando el nombre del Origen.
                Cliente clienteOrigen = encontrarCliente(listaClientes, t.getOrigen());
                /* 
                 * Comprobamos por si acaso que no sea null.
                 * - Si lo es decimos que cliente no encontrado.
                 * - Si lo encuentra restamos su saldo con el monto.
                 */
                if (clienteOrigen != null) {
                    synchronized (clienteOrigen) {
                        clienteOrigen.setSaldo(clienteOrigen.getSaldo() - t.getMonto());
                    }
                } else {
                    System.err.println("Cliente origen no encontrado: ID " + t.getOrigen());
                }

                // Operamos con el cliente destino usando la función para encontrarlo, buscando en el array el id y sacando el nombre del destino.
                Cliente clienteDestino = encontrarCliente(listaClientes, t.getDestino());
                /* 
                 * Comprobamos por si acaso que no sea null.
                 * - Si lo es decimos que cliente no encontrado.
                 * - Si lo encuentra sumamos el monto a su saldo.
                 */
                if (clienteDestino != null) {
                    synchronized (clienteDestino) {
                        clienteDestino.setSaldo(clienteDestino.getSaldo() + t.getMonto());
                    }
                } else {
                    System.err.println("Cliente destino no encontrado: ID " + t.getDestino());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Función para recorrer los clientes. 
     * Si el ID del cliente es igual al del Origen/Destinatario, devuelve el cliente. 
     * Si no, devuelve null.
     * @param listaClientes Array de los clientes de los JSON
     * @param id id del Origen o Destino para comparar.
     * @return cliente Cliente con el mismo id
     */
    private static Cliente encontrarCliente(ArrayList<Cliente> listaClientes, String id) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getId().equals(id)) {
                return cliente;
            }
        }
        return null;
    }
}

