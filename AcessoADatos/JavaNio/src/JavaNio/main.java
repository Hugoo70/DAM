package JavaNio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class main {

	public static void crearDirectorio(Path dir) throws IOException {
		if (!Files.exists(dir)) {
			Files.createDirectory(dir);
			System.out.println(
					"Directorio está creado en: " + dir.toAbsolutePath() + ". Mi padre es: " + dir.getParent());
		} else {
			System.out.println(
					"El directorio ya existe en: " + dir.toAbsolutePath() + ". Mi padre es: " + dir.getParent());
		}
	}

	public static void escribirArchivo(Path Fich, String texto) throws IOException {
		Files.writeString(Fich, texto);
		System.out.println("Crea el archivo y escribe el archivo");
	}

	public static void leerArchivoCompleto(Path fich) throws IOException {
		String contenidoCompletado = Files.readString(fich);
		System.out.println(contenidoCompletado);
	}

	public static void leerArchivoLinea(Path fich) throws IOException {
		byte[] bytes = Files.readAllBytes(fich);
		System.out.println(new String(bytes));
		List<String> contenidoLeido = Files.readAllLines(fich);
		for (String string : contenidoLeido) {
			System.out.println(string);
		}
	}

	public static void copiarArchivos(Path fichero, Path fichero2) throws IOException {
		Files.copy(fichero, fichero2);
	}
	
	public static void moverArchivos(Path fich, Path fich2) throws IOException {
		//Path copia = "carpetaAdicional/copia.txt";
		Files.move(fich, fich2);
		System.out.println("Movido correctamente");
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Path directorio = Paths.get("Carpeta");
		Path directorioCopia = Paths.get("Carpeta2");
		Path fichero = directorio.resolve("ficheroJavaNio.txt");
		Path ficheroCopia = directorioCopia.resolve("ficheroJavaNio2.txt");

		try {
			// Crear Directorio
			crearDirectorio(directorio);
			escribirArchivo(fichero, "Hola, Soy hugo\nAdios!");
			leerArchivoCompleto(fichero);
			leerArchivoLinea(fichero);
			copiarArchivos(fichero, ficheroCopia);
			moverArchivos(fichero, ficheroCopia);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
