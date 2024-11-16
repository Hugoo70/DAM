package XML;

import java.io.File;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class Main {

    public static void main(String[] args) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document documento = null;

        try {
            // Configuración y ruta del archivo
            DocumentBuilder builder = factory.newDocumentBuilder();
            File xmlFile = new File("books.xml");
            System.out.println("Ruta del archivo: " + xmlFile.getAbsolutePath());
            if (!xmlFile.exists()) {
                System.out.println("El archivo books.xml no existe. Por favor, verifica la ruta.");
                return;
            }

            // Parseo del documento
            documento = builder.parse(xmlFile);
            documento.getDocumentElement().normalize();

            // Información del documento raíz
            System.out.println("Elemento raíz: " + documento.getDocumentElement().getNodeName());

            // Lectura de nodos "book"
            NodeList listaLibros = documento.getElementsByTagName("Book");
            System.out.println("Número de libros encontrados: " + listaLibros.getLength());

            for (int i = 0; i < listaLibros.getLength(); i++) {
                Node nodo = listaLibros.item(i);
                System.out.println("Procesando nodo: " + nodo.getNodeName());

                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) nodo;
                    NodeList hijos = e.getChildNodes();

                    for (int j = 0; j < hijos.getLength(); j++) {
                        Node hijo = hijos.item(j);
                        if (hijo.getNodeType() == Node.ELEMENT_NODE) {
                            System.out.println("Etiqueta: " + hijo.getNodeName() + " --> " + hijo.getTextContent().trim());
                        }
                    }
                }
            }
        } catch (ParserConfigurationException e) {
            System.out.println("Error en la configuración del analizador XML.");
            e.printStackTrace();
        } catch (SAXException e) {
            System.out.println("Error al parsear el archivo XML. Verifica su formato.");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
