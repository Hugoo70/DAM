package Examen;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static Scanner teclado = new Scanner(System.in);
	/*
	public static void crearFichero(String ruta) {
			try {
				File f = new File(ruta);
				if(!f.exists()) {
				f.createNewFile();
				System.out.println("Fichero creado con exito!");

				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
	/*
	public static void crearFicheroBin(String ruta2) {
		try {
			File f = new File(ruta2);
			if(!f.exists()) {
			f.createNewFile();
			System.out.println("Fichero Bin creado con exito!");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	public static void escribirFicheroBin(String ruta2,  ArrayList<Alumno> alumnos) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta2, true))){
			oos.writeObject(alumnos);
			oos.close();
			System.out.println("Escritura en fichero binario correctamente");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void leerFicheroBin(String ruta2) {
		
		ArrayList<Alumno> alumnos2 = new ArrayList<Alumno>();
		
		try(ObjectInputStream ios = new ObjectInputStream(new FileInputStream(ruta2))) {
			alumnos2 = (ArrayList<Alumno>) ios.readObject();
			for (Alumno alumno2 : alumnos2) {
				System.out.println(alumno2);
			}
			ios.close();
			
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("No lee");
			e.printStackTrace();
		}
		
	}
	
	public static ArrayList<Alumno> leerFichero(String ruta, ArrayList<Alumno> alumnos) {
		String Linea = "";
		try(BufferedReader br = new BufferedReader(new FileReader(ruta))) {
			while((Linea = br.readLine())!=null) {
				String[] partes = Linea.split(",");
				if(partes.length >= 3) {
					Alumno al = new Alumno(partes[0],Integer.parseInt(partes[1]),Integer.parseInt(partes[2]));
					alumnos.add(al);
				}
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return alumnos;
	}
	
	public static void menu(String ruta, String ruta2,  ArrayList<Alumno> alumnos) {
		

System.out.println("\n");
		System.out.println("##### MENU ALUMNOS #####");
		System.out.println("1. Mostrar alumnos");
		System.out.println("2. Mostrar segun curso");
		System.out.println("3. Nota media primero");
		System.out.println("4. Nota media segundo");
		System.out.println("5. Nota media ciclo entero");
		System.out.println("6. Añadir alumno nuevo");
		System.out.println("7. Escribir binario");
		System.out.println("8. Leer binario");
		System.out.println("0. Salir");
		int opcion = teclado.nextInt();
		do{

			switch (opcion) {
			case 1:
				mostrarAlumnos(alumnos);
				menu(ruta, ruta2, alumnos);
				break;
				
			case 2:
				mostrarAlumnosPorCurso(alumnos);
				menu(ruta, ruta2, alumnos);

				break;
				
			case 3:
				notaMediaPrimero(alumnos);
				menu(ruta, ruta2, alumnos);

				break;
				
			case 4:
				notaMediaSegundo(alumnos);
				menu(ruta, ruta2, alumnos);

				break;
				
			case 5:
				notaMediaCiclo(alumnos);
				menu(ruta, ruta2, alumnos);

				break;
				
			case 6:
				nuevoAlumno(ruta, alumnos);
				menu(ruta, ruta2, alumnos);

				break;
				
			case 7:
				escribirFicheroBin(ruta2, alumnos);
				menu(ruta, ruta2, alumnos);

				break;
				
			case 8:
				leerFicheroBin(ruta2);
				menu(ruta, ruta2, alumnos);

				break;
				
			case 0:
				System.out.println("Saliendo...");
				break;

			default:
				break;
			}
		}while(opcion!=0);
	}
	
	
	public static void nuevoAlumno(String ruta, ArrayList<Alumno> alumnos) {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(ruta, true))) {
			System.out.println("Introduce el nombre: ");
			teclado.nextLine();
			String nombre = teclado.nextLine();
			System.out.println("Introduce la nota media: ");
			int nota = teclado.nextInt();
			System.out.println("Introduce el curso: ");
			int curso = teclado.nextInt();
			
			Alumno al= new Alumno(nombre, nota, curso);
			bw.write(nombre + "," + nota + "," + curso);
			bw.newLine();
			
			System.out.println("Alumno introducido correctamente");
			
		} catch (IOException e) {
			System.out.println("Datos no introducidor al fichero " + e.getMessage());
		}
	}
	
	public static void notaMediaCiclo(ArrayList<Alumno> alumnos) {
		double suma = 0;
		double i = 0;
		for (Alumno alumno : alumnos) {
				 suma += alumno.getNota();
				 i++;
		}
		double res = suma/i;
		System.out.println("La media del ciclo es: " + res);
	}
	
	public static void notaMediaSegundo(ArrayList<Alumno> alumnos) {
		double suma = 0;
		double i = 0;
		for (Alumno alumno : alumnos) {
			if(alumno.getCurso()==2) {
				 suma += alumno.getNota();
				 i++;
			}
		}
		double res = suma/i;
		System.out.println("La media del segundo curso es: " + res);
	}
	
	public static void notaMediaPrimero(ArrayList<Alumno> alumnos) {
		double suma = 0;
		double i = 0;
		for (Alumno alumno : alumnos) {
			if(alumno.getCurso()==1) {
				 suma += alumno.getNota();
				 i++;
			}
		}
		double res = suma/i;
		System.out.println("La media del primer curso es: " + res);
	}

	public static void mostrarAlumnosPorCurso(ArrayList<Alumno> alumnos) {
		// TODO Auto-generated method stub
		System.out.println("Introduza el curso que quiere mostrar (1/2)");
		teclado.nextLine();
		int curso = teclado.nextInt();
		
		for (Alumno alumno : alumnos) {
			if(alumno.getCurso() == curso) {
			System.out.println("Nombre: " + alumno.getNombre() + ", Nota: " + alumno.getNota() + ", Curso: " + alumno.getCurso());
		}else if(alumno.getCurso() == curso) {
			System.out.println("Nombre: " + alumno.getNombre() + ", Nota: " + alumno.getNota() + ", Curso: " + alumno.getCurso());

		}
		}
	}

	public static void mostrarAlumnos(ArrayList<Alumno> alumnos) {
		for (Alumno alumno : alumnos) {
			System.out.println("Nombre: " + alumno.getNombre() + ", Nota: " + alumno.getNota() + ", Curso: " + alumno.getCurso());
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList <Alumno> alumnos = new ArrayList<>();
		String ruta = "alumnos.txt";
		String ruta2 = "alumnos2.bin";
		//crearFichero(ruta);
		alumnos = leerFichero(ruta, alumnos);
		menu(ruta, ruta2, alumnos);
		
	}



}
