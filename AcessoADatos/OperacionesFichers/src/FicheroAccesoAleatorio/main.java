package FicheroAccesoAleatorio;

import java.io.*;

public class main {

	public static void EscrituraAleatorio(String fichero, String cadena, RandomAccessFile raf) {
		try {
			// declaramos el RAF con el nombre de fichero, cadena de texto y el metodo de acceso W o RW
			raf = new RandomAccessFile(fichero, "rw");
			// Ver tamaño del fichero
			long size = raf.length();
			// Posicionarse en el fichero son seek
			raf.seek(size);
			//Escribir cadena de caracteres
			raf.writeBytes(cadena);;
			System.out.println("Se ha escrito en el fichero");
			// Cerramos el fichero
			raf.close();
			
			
			// Error por si no encuentra el fichero
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Error: no se encuentra el fichero");
			System.out.println(e.getMessage());


		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error: en la lectura o escritura del fichero");
			System.out.println(e.getMessage());

		}

	}
	
	public static void LecturaAleatoria(String fichero, long posicion, int longitud){
		
		String res;
		try(RandomAccessFile raf=new RandomAccessFile(fichero, "r")) {
			raf.seek(posicion);
			//Array para leer lo de dentro, para leer todo el texto usamos el raf.length en ves de llamar a posicion
			byte[] bytes = new byte[(int)raf.length()];
			//Leer el array
			raf.readFully(bytes);
			//Trim elimina los caracteres en blanco al principio y al final
			res = new String(bytes).trim();
			System.out.println(res);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String nombreFichero = "FicheroAccesoAleatorio.txt";
		String cadena = "Esto es una prueba de escritura";
		RandomAccessFile raf = null;
		
		//EscrituraAleatorio(nombreFichero, cadena, raf);
		LecturaAleatoria(nombreFichero,0,60);
	}

}
