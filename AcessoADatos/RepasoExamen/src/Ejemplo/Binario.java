package Ejemplo;

import java.io.*;

public class Binario {
	public static void lectura(String ruta) {
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta))) {
			Alumno a[] = (Alumno[])ois.readObject();
			System.out.println(a[0] + " " + a[1]);
			ois.close();
		} catch (IOException|ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void escritura(String ruta) {
		try(FileOutputStream fos = new FileOutputStream(ruta)) {
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			Alumno a[] = {new Alumno(1, 10), new Alumno(2,8)};
			oos.writeObject(a);
			oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		String ruta = "Alumno.txt";
		escritura(ruta);
		lectura(ruta);
		
		
	}

}
