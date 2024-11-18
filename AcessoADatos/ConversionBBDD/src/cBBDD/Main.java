package cBBDD;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String URL="jdbc:mysql://localhost/nba";
		String usuario="root";
		String password="cfgs";
		String controlador="com.mysql.cj.jdbc.Driver";
		
		// JDBC -> conector de Java con MySQL
		try {
			Class.forName(controlador);
			Connection conexion = DriverManager.getConnection(URL, usuario, password);
			if(conexion!=null) {
				System.out.println("La conexión ha sido correcta!");
			}else {
				System.out.println("La conexión no es correcta!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
