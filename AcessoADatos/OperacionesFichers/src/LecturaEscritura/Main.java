package LecturaEscritura;

import java.io.*;

public class Main {

	public static void lecturaBuffer(File f) {
		try {
			FileReader salida = new FileReader(f);
			BufferedReader buffer = new BufferedReader(salida);
			String texto;
			
			//System.out.println(buffer.readLine());
			
			while((texto=buffer.readLine())!=null) {
				System.out.println(texto);
			}
			
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
	
	
	public static void lectura(File f) {
		char[] texto = new char[100];
		try {
			FileReader salida = new FileReader(f);
			System.out.println("La codificación es: " + salida.getEncoding());
			salida.read(texto);
			System.out.println("La salida es: ");
			System.out.println(texto);
			salida.close();
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	public static void Escritura2(String ruta) {
		try {
			FileWriter fichero = new FileWriter(ruta, true);
			BufferedWriter bw = new BufferedWriter(fichero);
			
			for(int i=22;i<30;i++) {
				bw.write(" " + i);
				bw.newLine();
			}
			bw.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void Escritura(String ruta) {
		try{
			FileWriter fichero = new FileWriter(ruta);
			PrintWriter pw = new PrintWriter(fichero);
			for(int i=0;i<10;i++) {
				pw.println(" numero" + i);
			}
			fichero.close();
		}
		
		catch(Exception e) {
			e.getStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String ruta = ".\\datos\\Fichero.txt";
		File fichero = new File(ruta);
		if (fichero.exists()) {
			System.out.println("lEyEnDo...");
			// Llamamos a la funcion de lectura
			Escritura2(ruta);
			lecturaBuffer(fichero);

		} else {
			System.out.println("Un ErRoR, No ExIsTe El FiChErO" + fichero.getAbsolutePath());
		}
	}

}