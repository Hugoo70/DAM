import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Leer {
	public static String leerFichero(String body) {
	ArrayList<String> bodyA = new ArrayList<>();
	String bodyB = null;
	try (BufferedReader br = new BufferedReader(new FileReader(body))) {
		String Linea = "";
		while ((Linea = br.readLine()) != null) {
			bodyA.add(Linea);
		}
		
		for (String string : bodyA) 
			bodyB  += (string + "\n");
		
		br.close();
		
	} catch (Exception e2) {
		// TODO: handle exception
	}
	return bodyB;
}
}