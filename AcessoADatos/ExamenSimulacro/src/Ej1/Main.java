package Ej1;

import java.io.*;
import java.util.Scanner;

public class Main {
	static Scanner teclado = new Scanner(System.in);

	public static void Datos(String[][]datos, String ruta) {
		String Linea="";
		int numProductos=datos.length;
		
		try {
			BufferedReader bf = new BufferedReader(new FileReader (ruta));
			while((Linea=bf.readLine()) != null) {
                String[] partesLinea = Linea.split(";");
                
                

                int indice = 0;
                boolean encontrado = false;
                while(indice<numProductos && !encontrado) {
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
					datos[numProductos][0]=partesLinea[0];
					datos[numProductos][1]=partesLinea[1];
				}
			}
			bf.close();
        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void login() {
		System.out.println("Escribe tu nombre");
		String nombre = teclado.nextLine();
		System.out.println("Escribe tu contraseña");
		String contra = teclado.nextLine();
		System.out.println("Escribe tu puesto(administrador/usuario/lector)");
		String puesto = teclado.nextLine();
		
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[][]datos = new String[100][3];
		String ruta = "datos.txt";
	}

}
