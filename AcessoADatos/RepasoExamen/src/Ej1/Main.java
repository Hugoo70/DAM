package Ej1;

import java.io.*;

public class Main {
	private static String[][] leerVentas(String nombreFichero,String[][] datos) {
		String linea;
		int numeroProductos = datos.length;
		
		try(BufferedReader bf = new BufferedReader(new FileReader(nombreFichero)))
		{
			while((linea=bf.readLine())!=null) {
				String[]partesLinea = linea.split(",");
				//partesLinea[0] tiene el nombre producto
				//PartesLinea[1] tiene la cantidad en forma de String
				//System.out.println(partesLinea[0]+":"+partesLinea[1]);
				
				int indice = 0;
				boolean encontrado =false;
				
				while(indice<numeroProductos && !encontrado) {
				  if(datos[indice][0]!=null) {	
					if(datos[indice][0].equals(partesLinea[0]))
						encontrado = true;
					else
						indice++;
				  }else
					  encontrado = true;
				}
				
				// Si lo he encontrado
				if(encontrado) {
					//1. Es el primero
					if(datos[indice][1]==null) {
						datos[indice][0]=partesLinea[0];
						datos[indice][1]=partesLinea[1];
					}else {
						//2. ya existe el producto porque indice apunta a su posicion
						int cantidad = Integer.valueOf(datos[indice][1])+Integer.valueOf(partesLinea[1]);
						datos[indice][1]= String.valueOf(cantidad);
					}
				}else {
					//Añadirimos el elemento
					//En dinamico add
					datos[numeroProductos][0]=partesLinea[0];
					datos[numeroProductos][1]=partesLinea[1];
				}
			}
			bf.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		return datos;
	}
	public static void VisualizarDatos(String[][]datos) {
		int i=0;
		while(datos[i][0]!=null) {
			System.out.print(datos[i][0]+":");
			System.out.println(datos[i][1]);
			i++;
		}
	}
	public static void crearFichero(String nombreFichero) throws IOException {
		File Fichero = new File(nombreFichero);
		if(!Fichero.exists()) {
			if(Fichero.createNewFile()) {
				System.out.println("Crea el fichero");
			}
		}
	}
	public static void main(String[] args) {
		String[][] datos = new String[100][2];//Max. 100 productos
		String nombreFichero="ventasProductos.txt";
		try {
			crearFichero(nombreFichero);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		VisualizarDatos(leerVentas(nombreFichero,datos));
	}

}

