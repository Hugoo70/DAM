package cBBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
	static Scanner teclado = new Scanner(System.in);
	public static void main(String[] args) {
		// Definir las credenciales y la URL de conexión
		String url = "jdbc:mysql://localhost:3306/nba";
														// de MySQL)
		String usuario = "root"; // Usuario de la base de datos
		String clave = "cfgs"; // Contraseña de la base de datos
		String nombre;
		String consulta = "SELECT * FROM equipos Where Nombre=? or Conferencia=?";
		System.out.println("Introduce un nombre");
		nombre = teclado.nextLine();
		// Intentamos establecer la conexión
		try {
			// Cargar el driver de MySQL (no siempre necesario si usas versiones recientes
			// de JDBC)
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establecer la conexión
			Connection conexion = DriverManager.getConnection(url, usuario, clave);
			if (conexion != null) {
				System.out.println("Conexión con exito!");
		/*
			// Crear un Statement para ejecutar una consulta o actualización (opcional)
			Statement stmt = conexion.createStatement();

			ResultSet rs = stmt.executeQuery(consulta);// Se pone un 1=1 por si el nombre no existe te saque la tabla entera ya que 1=1 es true
			while(rs.next()) {

				System.out.print(rs.getString(1) + " Segunda Opcion: ");
				System.out.println(rs.getString("Ciudad"));
							// No olvides cerrar la conexión después de usarla
			}
			conexion.close();
		*/
			//Sin inyeccion de SQL
				PreparedStatement sentencia = conexion.prepareStatement(consulta);
				sentencia.setString(1, nombre);
				sentencia.setString(2, "East");
				ResultSet rs = sentencia.executeQuery();
				while(rs.next()) {
					System.out.print(rs.getString("Nombre") + " ");
					System.out.print(rs.getString("Ciudad") + " ");
					System.out.print(rs.getString("Conferencia") + " ");
					System.out.println(rs.getString("Division"));
				}

			


			}else
				System.out.println("No se ha podido conectar");

		} catch (ClassNotFoundException e) {
			System.out.println("No se pudo encontrar el driver JDBC.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error al conectar a la base de datos.");
			e.printStackTrace();
		}

	}
}
