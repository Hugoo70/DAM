package PracticaFinal;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class LeerCrearGasolineraBin {
	public static void CrearFicheroBin(String FileBin) {
	       ArrayList<Gasolinera> gasolineras = new ArrayList<>();
	       			gasolineras.add(new Gasolinera("Gasolinera1", "Madrid", 1000, 800, 1.95, 1.55)); 
	       			gasolineras.add(new Gasolinera("Gasolinera2", "Barcelona", 1500, 1200, 1.70, 1.80));
	       			gasolineras.add(new Gasolinera("Gasolinera3", "Oviedo", 2000, 1800, 1.68, 1.78));

	           try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FileBin))) {
	               oos.writeObject(gasolineras);
	               System.out.println("Archivo binario creado con éxito.");
	           } catch (Exception e) {
	               System.out.println("Error al escribir el archivo binario: " + e.getMessage());
	               e.printStackTrace();
	           }
	}
	public static void LeerFicheroBin(String FileBin) {
	    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FileBin))) {
	        ArrayList<Gasolinera> gasolineras = (ArrayList<Gasolinera>) ois.readObject();

	        System.out.println("Datos de las gasolineras cargadas:");
	        for (Gasolinera gas : gasolineras) {
	            System.out.println(gas);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	


    public static void main(String[] args) {
        String FileBin = "src\\gasolineras.bin";
        CrearFicheroBin(FileBin);
        LeerFicheroBin(FileBin);

    }

}
