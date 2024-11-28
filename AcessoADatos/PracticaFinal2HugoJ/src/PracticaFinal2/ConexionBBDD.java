package PracticaFinal2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ConexionBBDD {
	static Scanner teclado = new Scanner(System.in);

	public static void conexionBBDD(String consulta) {
	// Definir las credenciales y la URL de conexión
			String url = "jdbc:mysql://localhost:3306/practicafinal"; // URL de conexión (localhost:3306 es el puerto predeterminado
															// de MySQL)
			String usuario = "root"; // Usuario de la base de datos
			String clave = "cfgs"; // Contraseña de la base de datos
			String nombre = null;
			// Intentamos establecer la conexión
			try {
				// Cargar el driver de MySQL (no siempre necesario si usas versiones recientes
				// de JDBC)
				Class.forName("com.mysql.cj.jdbc.Driver");

				// Establecer la conexión
				Connection conexion = DriverManager.getConnection(url, usuario, clave);
				if (conexion != null) {
				//Sin inyeccion de SQL
					PreparedStatement sentencia = conexion.prepareStatement(consulta);

					ResultSet rs = sentencia.executeQuery();
			        // Procesar las columnas dinámicamente
			        int columnCount = rs.getMetaData().getColumnCount(); // Número de columnas en el resultado
			        while (rs.next()) {
			            for (int i = 1; i <= columnCount; i++) { // Iterar por todas las columnas
			                String columnName = rs.getMetaData().getColumnName(i); // Nombre de la columna
			                String value = rs.getString(i); // Valor de la columna
			                System.out.print(columnName + ": " + value + " | "); // Mostrar nombre y valor
			            }
			            System.out.println(); // Salto de línea para la siguiente fila
			        }


				}else
					System.out.println("No se ha podido conectar");

			} catch (ClassNotFoundException e) {
				System.out.println("No se pudo encontrar el driver JDBC.");
				e.printStackTrace();
			} catch (SQLException e1) {
				System.out.println("Error al conectar a la base de datos.");
				e1.printStackTrace();
			}

}
	
	
}
	
