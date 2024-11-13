package XML;

import java.io.File;
import java.nio.file.attribute.AclEntry.Builder;

import javax.xml.parsers.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Main {

	public static void main(String[] args) throws ParserConfigurationException, SAXException {
		// TODO Auto-generated method stub
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		Document documento = null;
		
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			documento = builder.parse(new File("books.xml"));
			NodeList listaLibros = documento.getElementsByTagName("book");
			
			for(int i = 0;i<listaLibros.getLength();i++) {
				Node nodo = listaLibros.item(i);
				if(nodo.getNodeType()==Node.ELEMENT_NODE) {
					Element e = (Element) nodo;
					NodeList hijos = e.getChildNodes();
					
					for(int j=0;j<hijos.getLength();j++) {
						Node hijo = hijos.item(j);
						if(hijo.getNodeType()==Node.ELEMENT_NODE) {
							System.out.println("Etiqueta: " + hijo.getNodeName() + " --> " + hijo.getTextContent());
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
