package TXT;

import java.io.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class LecturaEscritura {
	private ArrayList<Usuario> listaUsuarios;
	private ArrayList<String> textos;
	private ArrayList<Estadisticas> estadisticas;

	// Constructor para inicializar la lista
	public LecturaEscritura() {
		this.listaUsuarios = new ArrayList<>();
		this.textos = new ArrayList<>();
		this.estadisticas = new ArrayList<>();

	}

	public ArrayList<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public ArrayList<String> getListaTexto() {
		return textos;
	}

	public ArrayList<Estadisticas> getListaEstadisticas() {
		return estadisticas;
	}

	public void ArchivosTXT() {

		File usuarioFile = new File("src/TXT/Usuarios.txt");
		File estadisticasFile = new File("src/TXT/estadisticas.txt");
		File textosFiles = new File("src/TXT/textos.txt");

		if (!(usuarioFile.exists() && estadisticasFile.exists() && textosFiles.exists())) {

			JOptionPane.showMessageDialog(null, "Error, Fichero no encontrado!", "ERROR", 0);
			System.exit(0);

		}
	}

	public void FicheroUsuario(String fichero) {
	    try {
	        FileReader fr = new FileReader(fichero);
	        BufferedReader br = new BufferedReader(fr);
	        String leerDatos;

	        while ((leerDatos = br.readLine()) != null) {
	            listaUsuarios.add(
	                new Usuario(
	                    (leerDatos.split(";")[0]), 
	                    (leerDatos.split(";")[1]), 
	                    (leerDatos.split(";")[2]), 
	                    (leerDatos.split(";")[3]))
	            );
	        }
	        
	        br.close();

	        if (listaUsuarios.size() < 3 || listaUsuarios.size() > 5) {
	            JOptionPane.showMessageDialog(null, "El archivo debe contener entre 3 y 5 usuarios. Cerrando...",
	                    "ERROR DE USUARIOS", 0);
	            System.exit(0);
	        }

	    } catch (IOException e) {
<<<<<<< HEAD
	        JOptionPane.showMessageDialog(null, "ERROR DE LECTURA",
	                "NO SE HAN PODIDO LEER LOS ARCHIVOS DE " + fichero + ".\nCERRANDO...", 0);
=======
	        JOptionPane.showMessageDialog(null, "Archivos no encontrados o defectuosos (" + fichero + ").\nCerrando programa...",
	                "ERROR DE LECTURA", 0);
>>>>>>> 2b3675a273ea83f4162927c2526994249f6e2bae
	        System.exit(0);
	    }
	}


	public void FicheroTexto(String fichero) {
		try {
			FileReader fr = new FileReader(fichero);
			BufferedReader br = new BufferedReader(fr);
			String lineaTexto;

			while ((lineaTexto = br.readLine()) != null) {

				textos.add(lineaTexto.split(";")[0]);
				textos.add(lineaTexto.split(";")[1]);
			}
			br.close();
		} catch (IOException e) {
<<<<<<< HEAD
			JOptionPane.showMessageDialog(null, "ERROR DE LECTURA",
					"NO SE HAN PODIDO LEER LOS ARCHIVOS DE " + fichero + ".\nCERRANDO...", 0);
=======
	        JOptionPane.showMessageDialog(null, "Archivos no encontrados o defectuosos (" + fichero + ").\nCerrando programa...",
	                "ERROR DE LECTURA", 0);
>>>>>>> 2b3675a273ea83f4162927c2526994249f6e2bae
			System.exit(0);
		}
	}

	public void FicheroEstadistica(String fichero) {
		try {

			FileReader fr = new FileReader(fichero);
			BufferedReader br = new BufferedReader(fr);
			String leerStats;

			while ((leerStats = br.readLine()) != null) {

				this.estadisticas.add(new Estadisticas(leerStats.split(";")[0],
						Integer.parseInt(leerStats.split(";")[1]), 
						Integer.parseInt(leerStats.split(";")[2]),
						Integer.parseInt(leerStats.split(";")[3]), 
						Integer.parseInt(leerStats.split(";")[4])));
			}

			br.close();

		} catch (IOException e) {
	        JOptionPane.showMessageDialog(null, "Archivos no encontrados o defectuosos (" + fichero + ").\nCerrando programa...",
	                "ERROR DE LECTURA", 0);
	        System.exit(0);
		}
	}
	
	public void guardarEstadistica(String fichero, Estadisticas nuevaEstadistica) {
	    try (FileWriter fw = new FileWriter(fichero, true);
	         BufferedWriter bw = new BufferedWriter(fw);
	         PrintWriter out = new PrintWriter(bw)) {

	        // Escribir la estadística en formato CSV
	        out.println(
	            nuevaEstadistica.getUser() + ";" +
	            nuevaEstadistica.getDif() + ";" +
	            nuevaEstadistica.getTiempoTranscurrido() + ";" +
	            nuevaEstadistica.getPpm() + ";" +
	            nuevaEstadistica.getErrores()+ ";"
	        );

	    } catch (IOException e) {
	        JOptionPane.showMessageDialog(null, 
	            "Error al guardar las estadísticas", 
	            "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}

}
