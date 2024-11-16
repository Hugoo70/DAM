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
    }

    public ArrayList<Usuario> getListaUsuarios() {
        return listaUsuarios;
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

			leerDatos = br.readLine();
			//ALMACENAMOS DATOS EN USUARIO ID, USUARIO, CONTRASEÑA
			while(leerDatos != null) {
				listaUsuarios.add(new Usuario((leerDatos.split(";")[0]), (leerDatos.split(";")[1]), (leerDatos.split(";")[2])));
				leerDatos = br.readLine();
			}

			br.close();

		}catch(IOException e) {
			JOptionPane.showMessageDialog(null, "ERROR DE LECTURA", "NO SE HAN PODIDO LEER LOS ARCHIVOS, CERRANDO...", 0);
			System.exit(0);
		}
	}
}
