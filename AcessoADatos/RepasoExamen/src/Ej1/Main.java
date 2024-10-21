package Ej1;

import java.io.*;

public class Main {

    private static String[][] leerVentas(String nombreFichero, String[][] datos, int[] contadorProductos) {
        String linea;
        try (BufferedReader bf = new BufferedReader(new FileReader(nombreFichero))) {
            // Asignar línea con bf.readLine y cuando sea null finaliza el while
            while ((linea = bf.readLine()) != null) {
                String[] partesLinea = linea.split(",");
                // partesLinea[0] Nombre del producto
                // partesLinea[1] Cantidad de la venta en forma de String

                int indice = 0;
                boolean encontrado = false;

                // Buscamos si el producto ya existe en el array
                while (indice < contadorProductos[0] && !encontrado) {
                    if (datos[indice][0].equals(partesLinea[0])) {
                        encontrado = true;
                    } else {
                        indice++;
                    }
                }

                if (encontrado) {
                    // Si el producto ya existe, actualizamos su cantidad
                    int cantidad = Integer.parseInt(datos[indice][1]) + Integer.parseInt(partesLinea[1]);
                    datos[indice][1] = String.valueOf(cantidad);
                } else {
                    // Si el producto no existe, lo añadimos al array
                    datos[contadorProductos[0]][0] = partesLinea[0];
                    datos[contadorProductos[0]][1] = partesLinea[1];
                    contadorProductos[0]++; // Incrementamos el número de productos
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return datos;
    }

    public static void visualizarDatos(String[][] datos, int numeroProductos) {
        for (int i = 0; i < numeroProductos; i++) {
            System.out.println("Producto: " + datos[i][0] + ", Cantidad: " + datos[i][1]);
        }
    }

    public static void main(String[] args) {
        String[][] datos = new String[100][2]; // Máximo 100 productos
        String nombreFichero = "ventaProductos.txt";
        int[] contadorProductos = {0}; // Control del número de productos agregados

        // Leer las ventas y actualizar el array de productos
        leerVentas(nombreFichero, datos, contadorProductos);

        // Visualizar los datos
        visualizarDatos(datos, contadorProductos[0]);
    }
}
