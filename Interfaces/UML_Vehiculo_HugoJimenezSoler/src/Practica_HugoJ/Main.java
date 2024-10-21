package Practica_HugoJ;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class Main {
	static Scanner teclado = new Scanner(System.in);
	static ArrayList<Vehiculo> veh = new ArrayList<Vehiculo>();

	
	public static Vehiculo compararVelocidad() {
		//Función para comparar la Velocidad maxima del ArrayList de los vehiculos
		Vehiculo Max = veh.get(0);
		for (Vehiculo v : veh) {
			if (v.getVelocidadMax() > Max.getVelocidadMax()) {
				Max = v;
			}
		}
		return Max;
	}

	public static void esElectrico() {
		//Función para comprobar si el coche es electrico o no
		for (Vehiculo v : veh) {
			if (v.isElectrico() == true) {
				System.out.println(v);
			}
		}
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		veh.add(new Coche("Cibertruck", "Azul", 123, 25622, 2021, true, 250.40, false));
		veh.add(new Avion("F-22", "Gris", 231, 4565214, 2019, false, 1013.23, (byte) 1, true));
		veh.add(new Avion("EuroFigther", "Gris", 231, 4565214, 2014, false, 1018.60, (byte) 1, true));
		veh.add(new Coche("Citroen", "Blanco", 145, 203321, 2001, false, 170.40, true));
		veh.add(new Barco("Velero", "Blanco", 321, 6214, 2017, false, 55.20, 4, 2, 'V'));
		veh.add(new Barco("Moto de agua", "Morada", 321, 6214, 2017, true, 73.20, 4, 2, 'P'));



		System.out.println("Escoge una opción del menu:\n1º Vehiculo más rápido CompareTo.\n2ºVehiculo más rápido.\n3º Vehiculos electricos.\n3º Salir.");
		int num = teclado.nextInt();

		switch (num) {
		case 1:
			// Llamada a la funcion comparar Velocidad Maxima
			System.out.println(veh);
			veh.sort(null);
			System.out.println(veh);
			System.out.println("La persona que más cobra es: " + veh.getLast());
			
			break;
		case 2:
			// Llamada a la funcion ver si el vehiculo es electrico
			Vehiculo Max = compararVelocidad();
			System.out.println(Max);
			break;

		case 3:
			// Salida del menu
			esElectrico();;
			break;
		case 4:
			// Salida del menu
			System.out.println("Hasta pronto!");
			break;
		default:
			System.out.println("Ups! Has introdocido un valor erroneo.");
			break;
		}

	}

}