package PracticaFinal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class CrearUsuarioFichero {
    static ArrayList<Cliente> clientes = new ArrayList<>();

	public static void XML(String f, String d) throws SAXException, IOException {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Comprobar si clientes.xml existe en la rutaL
            File fichero = new File(f);
            if (!fichero.exists()) {
                System.out.println("El archivo XML no existe en la ruta: " + fichero.getAbsolutePath());
                return;
            }

            Document documento = builder.parse(fichero);
            documento.getDocumentElement().normalize();

            // Crear la carpeta para almacenar las fichas
            File carpeta = new File(d);
            if (!carpeta.exists()) {
                carpeta.mkdir();
            }

            // Obtener la lista de cliente.xml 
            NodeList listaClientes = documento.getElementsByTagName("cliente");
            System.out.println("Número de clientes encontrados: " + listaClientes.getLength());

            // Bucle para comprobar cada cliente
            for (int i = 0; i < listaClientes.getLength(); i++) {
                Node nodoCliente = listaClientes.item(i);

                if (nodoCliente.getNodeType() == Node.ELEMENT_NODE) {
                    Element cliente = (Element) nodoCliente;

                    // Extraer los datos del cliente
                    String numeroCliente = cliente.getElementsByTagName("numerodecliente").item(0).getTextContent();
                    String nombre = cliente.getElementsByTagName("Nombre").item(0).getTextContent();
                    String calle = cliente.getElementsByTagName("Calle").item(0).getTextContent();
                    String ciudad = cliente.getElementsByTagName("Ciudad").item(0).getTextContent();
                    String codigoPostal = cliente.getElementsByTagName("CodigoPostal").item(0).getTextContent();
                    String pais = cliente.getElementsByTagName("Pais").item(0).getTextContent();

                    // Formato de los datos en formato de ficha
                    String ficha = String.format(
                            "numerodecliente: %s\nnombre: %s\ndirección: %s, %s, %s, %s",
                            numeroCliente, nombre, calle, ciudad, codigoPostal, pais);
                    clientes.add(new Cliente(numeroCliente,nombre,0));

                    // Crear el archivo de texto con el número de cliente como nombre
                    File archivoFicha = new File(carpeta, numeroCliente + ".txt");
                    try (FileWriter writer = new FileWriter(archivoFicha)) {
                        writer.write(ficha);
                        System.out.println("Ficha creada para el cliente: " + numeroCliente);
                    } catch (IOException e) {
                        System.out.println("Error al escribir la ficha del cliente " + numeroCliente);
                        e.printStackTrace();
                    }
                }
            }

        } catch (ParserConfigurationException e) {
            System.out.println("Error en la configuración del analizador XML.");
            e.printStackTrace();
        }
	}

}
