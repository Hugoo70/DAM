package PracticaFinal2;

import java.sql.*;
import java.util.Scanner;

public class Main {
	static Scanner teclado = new Scanner(System.in);

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/practicafinal";
		String usuario = "root";
		String clave = "cfgs";

		try (Connection conexion = DriverManager.getConnection(url, usuario, clave)) {
			if (conexion != null) {
				System.out.println("Conexión establecida con éxito.");

				// Inicio de sesión, por la tabla de Usuario, donde pedimos el Alias y la clave,
				// despues sacamos el rol para llevar a su menu.
				System.out.println("Ingrese su alias:");
				String alias = teclado.nextLine();

				System.out.println("Ingrese su clave:");
				String claveUsuario = teclado.nextLine();

				// Llamada al método para comprobar el rol del usuario, si es nulo, el programa
				// no seguira.
				String rol = verificarUsuario(conexion, alias, claveUsuario);

				if (rol != null) {
					System.out.println("Bienvenido, " + alias + ". Tu rol es: " + rol);
					mostrarOpciones(rol, conexion);
				} else {
					System.out.println("Usuario no encontrado. Cerrando la app...");
				}
			}
		} catch (SQLException e) {
			System.out.println("Error al conectar a la base de datos.");
			e.printStackTrace();
		}
	}

	// Método para selecionar el rol del usuario que se ha logeado. (Si todo va bien
	// devuelve un String con el rol, si no nulo)
	public static String verificarUsuario(Connection conexion, String alias, String claveUsuario) throws SQLException {
		String consulta = "SELECT rol FROM Usuario WHERE Alias = ? AND clave = ?";
		try (PreparedStatement stmt = conexion.prepareStatement(consulta)) {
			stmt.setString(1, alias);
			stmt.setString(2, claveUsuario);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getString("rol");
			}
		}
		return null;
	}

	// Método automatico para redirigir al usuario seún su rol a un menu en
	// específico.
	public static void mostrarOpciones(String rol, Connection conexion) throws SQLException {
		switch (rol.toLowerCase()) { // Se declara la palabra a minusculas para no tener ningún problema a la hora de
										// meter el parámetro en el case.
		case "admin":
			opcionesAdministrador(conexion);
			break;
		case "empleado":
			opcionesEmpleado(conexion);
			break;
		case "cliente":
			opcionesCliente(conexion);
			break;
		default:
			System.out.println("El rol no existe.");
		}
	}

	/*
	 * Menú para los administradores, estos pueden insertar/modificar/eliminar
	 * Clientes, empleados o productos
	 * 
	 * Intente crear menos apartados con unas declaraciones de int y segun el numero
	 * era de un rol u otro pero al final me acaba rompiendo.
	 */
	public static void opcionesAdministrador(Connection conexion) throws SQLException {
		System.out.println("Opciones de Administrador:");
		System.out.println("1. Insertar cliente");
		System.out.println("2. Modificar cliente");
		System.out.println("3. Eliminar cliente");
		System.out.println("4. Insertar empleado");
		System.out.println("5. Modificar empleado");
		System.out.println("6. Eliminar empleado");
		System.out.println("7. Insertar producto");
		System.out.println("8. Modificar producto");
		System.out.println("9. Eliminar producto");
		System.out.println("10. Salir de la app");

		int opcion = teclado.nextInt();
		teclado.nextLine();
		switch (opcion) {
		case 1:
			insertarCliente(conexion);
			opcionesAdministrador(conexion);
			break;
		case 2:
			modificarCliente(conexion);
			opcionesAdministrador(conexion);
			break;
		case 3:
			eliminarCliente(conexion);
			opcionesAdministrador(conexion);
			break;
		case 4:
			insertarEmpleado(conexion);
			opcionesAdministrador(conexion);
			break;
		case 5:
			modificarEmpleado(conexion);
			opcionesAdministrador(conexion);
			break;
		case 6:
			eliminarEmpleado(conexion);
			opcionesAdministrador(conexion);
			break;
		case 7:
			insertarProducto(conexion);
			opcionesAdministrador(conexion);
			break;
		case 8:
			modificarProducto(conexion);
			opcionesAdministrador(conexion);
			break;
		case 9:
			eliminarProducto(conexion);
			opcionesAdministrador(conexion);
			break;
		case 10:
			System.out.println("Hasta pronto!!");
			break;
		default:
			System.out.println("Opción no válida.");
		}
	}

	/*
	 * Método para incluir un nuevo cliente en la base de datos
	 * 
	 * AAREGLAR: SI COINCIDE EL NUMEROCLIENTE EL PROGRAMA ROMPE.
	 */
	public static void insertarCliente(Connection conexion) throws SQLException {
		System.out.println("Insertar nuevo cliente:");
		System.out.print("Número de cliente: ");
		int numeroCliente = teclado.nextInt();
		teclado.nextLine();
		System.out.print("Nombre: ");
		String nombre = teclado.nextLine();
		System.out.print("Dirección: ");
		String direccion = teclado.nextLine();
		System.out.print("Alias de usuario: ");
		String alias = teclado.nextLine();

		String sql = "INSERT INTO Cliente (numerodecliente, Nombre, Direccion, Usuario_Alias) VALUES (?, ?, ?, ?)";
		try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
			stmt.setInt(1, numeroCliente);
			stmt.setString(2, nombre);
			stmt.setString(3, direccion);
			stmt.setString(4, alias);
		}
	}

	// Método para modificar el cliente.
	public static void modificarCliente(Connection conexion) throws SQLException {
		System.out.println("Modificar cliente existente:");
		System.out.print("Número de cliente: ");
		int numeroCliente = teclado.nextInt();
		teclado.nextLine();
		System.out.print("Nuevo nombre: ");
		String nombre = teclado.nextLine();
		System.out.print("Nueva dirección: ");
		String direccion = teclado.nextLine();

		String sql = "UPDATE Cliente SET Nombre = ?, Direccion = ? WHERE numerodecliente = ?";
		try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
			stmt.setString(1, nombre);
			stmt.setString(2, direccion);
			stmt.setInt(3, numeroCliente);
			int filas = stmt.executeUpdate();
			System.out.println("Cliente modificado con éxito. Filas afectadas: " + filas);
		}
	}
	// Método para eliminar un cliente.
	public static void eliminarCliente(Connection conexion) throws SQLException {
		System.out.println("Eliminar cliente:");
		System.out.print("Número de cliente: ");
		int numeroCliente = teclado.nextInt();

		String sql = "DELETE FROM Cliente WHERE numerodecliente = ?";
		try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
			stmt.setInt(1, numeroCliente);
			int filas = stmt.executeUpdate();
			System.out.println("Cliente eliminado con éxito. Filas afectadas: " + filas);
		}
	}

	/*
	 * Método para incluir un nuevo empleado en la base de datos
	 * 
	 * AAREGLAR: SI COINCIDE EL NUMEROCLIENTE EL PROGRAMA ROMPE.
	 */
	public static void insertarEmpleado(Connection conexion) throws SQLException {
		System.out.println("Insertar nuevo empleado:");
		System.out.print("Código de empleado: ");
		int codigoEmpleado = teclado.nextInt();
		teclado.nextLine();
		System.out.print("Nombre: ");
		String nombre = teclado.nextLine();
		System.out.print("Fecha de ingreso (YYYY-MM-DD): ");
		String fechaIngreso = teclado.nextLine();
		System.out.print("Puesto: ('Dependiente','Encargado','Cajero','Pistolero')");
		String puesto = teclado.nextLine();
		System.out.print("Alias de usuario: ");
		String alias = teclado.nextLine();

		String sql = "INSERT INTO Empleado (codigoEmpleado, Nombre, fechaIngreso, puesto, Usuario_Alias) VALUES (?, ?, ?, ?, ?)";
		try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
			stmt.setInt(1, codigoEmpleado);
			stmt.setString(2, nombre);
			stmt.setDate(3, Date.valueOf(fechaIngreso));
			stmt.setString(4, puesto);
			stmt.setString(5, alias);
			int filas = stmt.executeUpdate();
			System.out.println("Empleado insertado con éxito. Filas afectadas: " + filas);
		}
	}
	
	// Método para modificar el empleado.
	public static void modificarEmpleado(Connection conexion) throws SQLException {
		System.out.println("Modificar empleado existente:");
		System.out.print("Código de empleado: ");
		int codigoEmpleado = teclado.nextInt();
		teclado.nextLine();
		System.out.print("Nuevo nombre: ");
		String nombre = teclado.nextLine();
		System.out.print("Nueva fecha de ingreso (YYYY-MM-DD): ");
		String fechaIngreso = teclado.nextLine();
		System.out.print("Nuevo puesto: ('Dependiente','Encargado','Cajero','Pistolero')");
		String puesto = teclado.nextLine();

		String sql = "UPDATE Empleado SET Nombre = ?, fechaIngreso = ?, puesto = ? WHERE codigoEmpleado = ?";
		try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
			stmt.setString(1, nombre);
			stmt.setDate(2, Date.valueOf(fechaIngreso));
			stmt.setString(3, puesto);
			stmt.setInt(4, codigoEmpleado);
			int filas = stmt.executeUpdate();
			System.out.println("Empleado modificado con éxito. Filas afectadas: " + filas);
		}
	}

	// Método para eliminar el empleado.
	public static void eliminarEmpleado(Connection conexion) throws SQLException {
		System.out.println("Eliminar empleado:");
		System.out.print("Código de empleado: ");
		int codigoEmpleado = teclado.nextInt();

		String sql = "DELETE FROM Empleado WHERE codigoEmpleado = ?";
		try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
			stmt.setInt(1, codigoEmpleado);
			int filas = stmt.executeUpdate();
			System.out.println("Empleado eliminado con éxito. Filas afectadas: " + filas);
		}
	}


	/*
	 * Método para incluir un nuevo producto en la base de datos
	 * 
	 * AAREGLAR: SI COINCIDE EL NUMEROCLIENTE EL PROGRAMA ROMPE.
	 */
	public static void insertarProducto(Connection conexion) throws SQLException {
		System.out.println("Insertar nuevo producto:");
		System.out.print("ID del producto: ");
		int idProducto = teclado.nextInt();
		teclado.nextLine();
		System.out.print("Nombre: ");
		String nombre = teclado.nextLine();
		System.out.print("Precio unitario: (,)");
		float precioUnitario = teclado.nextFloat();
		System.out.print("Stock: ");
		int stock = teclado.nextInt();

		String sql = "INSERT INTO Producto (idProducto, Nombre, PrecioUnitario, Stock) VALUES (?, ?, ?, ?)";
		try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
			stmt.setInt(1, idProducto);
			stmt.setString(2, nombre);
			stmt.setFloat(3, precioUnitario);
			stmt.setInt(4, stock);
			int filas = stmt.executeUpdate();
			System.out.println("Producto insertado con éxito. Filas afectadas: " + filas);
		}
	}

	public static void modificarProducto(Connection conexion) throws SQLException {
		System.out.println("Modificar producto existente:");
		System.out.print("ID del producto: ");
		int idProducto = teclado.nextInt();
		teclado.nextLine();
		System.out.print("Nuevo nombre: ");
		String nombre = teclado.nextLine();
		System.out.print("Nuevo precio unitario: ");
		float precioUnitario = teclado.nextFloat();
		System.out.print("Nuevo stock: ");
		int stock = teclado.nextInt();

		String sql = "UPDATE Producto SET Nombre = ?, PrecioUnitario = ?, Stock = ? WHERE idProducto = ?";
		try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
			stmt.setString(1, nombre);
			stmt.setFloat(2, precioUnitario);
			stmt.setInt(3, stock);
			stmt.setInt(4, idProducto);
			int filas = stmt.executeUpdate();
			System.out.println("Producto modificado con éxito. Filas afectadas: " + filas);
		}
	}

	public static void eliminarProducto(Connection conexion) throws SQLException {
		System.out.println("Eliminar producto:");
		System.out.print("ID del producto: ");
		int idProducto = teclado.nextInt();

		String sql = "DELETE FROM Producto WHERE idProducto = ?";
		try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
			stmt.setInt(1, idProducto);
			int filas = stmt.executeUpdate();
			System.out.println("Producto eliminado con éxito. Filas afectadas: " + filas);
		}
	}

	public static void opcionesEmpleado(Connection conexion) throws SQLException {
		System.out.println("Opciones de Empleado:");
		System.out.println("1. Visualizar productos");
		System.out.println("2. Realizar ventas");
		System.out.println("3. Ver tickets generados");

		int opcion = teclado.nextInt();
		teclado.nextLine(); // Consumir el salto de línea
		switch (opcion) {
		case 1 -> visualizarProductos(conexion);
		case 2 -> realizarVenta(conexion);
		case 3 -> verTickets(conexion);
		default -> System.out.println("Opción no válida.");
		}
	}

	public static void opcionesCliente(Connection conexion) throws SQLException {
		System.out.println("Opciones de Cliente:");
		System.out.println("1. Visualizar productos");
		System.out.println("2. Realizar compras");
		System.out.println("3. Ver historial de compras");
		System.out.println("4. Canjear puntos");

		int opcion = teclado.nextInt();
		teclado.nextLine(); // Consumir el salto de línea
		switch (opcion) {
		case 1 -> visualizarProductos(conexion);
		case 2 -> realizarCompra(conexion);
		case 3 -> verHistorialCompras(conexion);
		case 4 -> canjearPuntos(conexion);
		default -> System.out.println("Opción no válida.");
		}
	}

	public static void visualizarProductos(Connection conexion) throws SQLException {
		System.out.println("Visualizar productos:");
		System.out.println("1. Por inicial");
		System.out.println("2. Por precio (ascendente)");
		System.out.println("3. Por precio (descendente)");
		System.out.println("4. Por stock");

		int opcion = teclado.nextInt();
		teclado.nextLine(); // Consumir el salto de línea

		String sql = "";
		switch (opcion) {
		case 1:
			System.out.print("Ingrese la inicial: ");
			String inicial = teclado.nextLine();
			sql = "SELECT * FROM Producto WHERE Nombre LIKE ?";
			try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
				stmt.setString(1, inicial + "%");
				mostrarProductos(stmt);
			}
			break;
		case 2:
			sql = "SELECT * FROM Producto ORDER BY PrecioUnitario ASC";
			mostrarProductos(conexion.prepareStatement(sql));
			break;
		case 3:
			sql = "SELECT * FROM Producto ORDER BY PrecioUnitario DESC";
			mostrarProductos(conexion.prepareStatement(sql));
			break;
		case 4:
			sql = "SELECT * FROM Producto ORDER BY Stock DESC";
			mostrarProductos(conexion.prepareStatement(sql));
			break;
		default:
			System.out.println("Opción no válida.");
		}
	}

	public static void realizarCompra(Connection conexion) throws SQLException {
		System.out.println("Realizar compra como cliente:");
		System.out.print("Ingrese su número de cliente: ");
		int numeroCliente = teclado.nextInt();
		teclado.nextLine(); // Consumir el salto de línea

		int totalCompra = 0; // Total de la compra

		while (true) {
			System.out.print("Ingrese el ID del producto: ");
			int idProducto = teclado.nextInt();
			System.out.print("Ingrese la cantidad: ");
			int cantidad = teclado.nextInt();

			String productoQuery = "SELECT * FROM Producto WHERE idProducto = ?";
			try (PreparedStatement productoStmt = conexion.prepareStatement(productoQuery)) {
				productoStmt.setInt(1, idProducto);

				ResultSet rs = productoStmt.executeQuery();
				if (rs.next()) {
					int stock = rs.getInt("Stock");
					if (cantidad > stock) {
						System.out.println("No hay suficiente stock para este producto.");
						continue;
					}

					double precioUnitario = rs.getDouble("PrecioUnitario");
					totalCompra += precioUnitario * cantidad;

					// Actualizar el stock del producto
					String actualizarStock = "UPDATE Producto SET Stock = Stock - ? WHERE idProducto = ?";
					try (PreparedStatement updateStmt = conexion.prepareStatement(actualizarStock)) {
						updateStmt.setInt(1, cantidad);
						updateStmt.setInt(2, idProducto);
						updateStmt.executeUpdate();
					}
				} else {
					System.out.println("Producto no encontrado.");
					continue;
				}
			}

			// Insertar el ticket en la base de datos
			String insertarTicket = "INSERT INTO Ticket (PrecioTotal, Empleado_codigoEmpleado, Cliente_numerocliente) VALUES (?, ?, ?)";
			try (PreparedStatement ticketStmt = conexion.prepareStatement(insertarTicket,
					Statement.RETURN_GENERATED_KEYS)) {
				ticketStmt.setDouble(1, totalCompra);
				ticketStmt.setInt(2, 1); // Código de empleado por defecto para clientes
				ticketStmt.setInt(3, numeroCliente);
				ticketStmt.executeUpdate();

				ResultSet generatedKeys = ticketStmt.getGeneratedKeys();
				if (generatedKeys.next()) {
					int idTicket = generatedKeys.getInt(1);
					System.out.println("Compra registrada con éxito. ID del Ticket: " + idTicket);

					// Añadir puntos al cliente
					int puntosGanados = (int) totalCompra / 10;
					String actualizarPuntos = "UPDATE Cliente SET puntos = puntos + ? WHERE numerocliente = ?";
					try (PreparedStatement puntosStmt = conexion.prepareStatement(actualizarPuntos)) {
						puntosStmt.setInt(1, puntosGanados);
						puntosStmt.setInt(2, numeroCliente);
						puntosStmt.executeUpdate();
						System.out.println("Puntos añadidos: " + puntosGanados);
					}
				}
			}
		}
	}

	public static void mostrarProductos(PreparedStatement stmt) throws SQLException {
		ResultSet rs = stmt.executeQuery();
		System.out.println("ID | Nombre | Precio | Stock");
		while (rs.next()) {
			System.out.println(rs.getInt("idProducto") + " | " + rs.getString("Nombre") + " | "
					+ rs.getFloat("PrecioUnitario") + " | " + rs.getInt("Stock"));
		}
	}

	public static void realizarVenta(Connection conexion) throws SQLException {
		System.out.print("Ingrese el número o nombre del cliente: ");
		String clienteInput = teclado.nextLine();

		String clienteQuery = "SELECT * FROM Cliente WHERE numerodecliente = ? OR Nombre = ?";
		int numeroCliente = -1;
		try (PreparedStatement clienteStmt = conexion.prepareStatement(clienteQuery)) {
			clienteStmt.setString(1, clienteInput);
			clienteStmt.setString(2, clienteInput);

			ResultSet rs = clienteStmt.executeQuery();
			if (rs.next()) {
				numeroCliente = rs.getInt("numerodecliente");
				System.out.println("Cliente seleccionado: " + rs.getString("Nombre"));
			} else {
				System.out.println("Cliente no encontrado.");
				return;
			}
		}

		int totalVenta = 0;
		while (true) {
			System.out.print("Ingrese el ID del producto: ");
			int idProducto = teclado.nextInt();
			System.out.print("Ingrese la cantidad: ");
			int cantidad = teclado.nextInt();

			String productoQuery = "SELECT * FROM Producto WHERE idProducto = ?";
			try (PreparedStatement productoStmt = conexion.prepareStatement(productoQuery)) {
				productoStmt.setInt(1, idProducto);

				ResultSet rs = productoStmt.executeQuery();
				if (rs.next()) {
					int stock = rs.getInt("Stock");
					if (cantidad > stock) {
						System.out.println("No hay suficiente stock para este producto.");
						continue;
					}

					int precioUnitario = rs.getInt("PrecioUnitario");
					totalVenta += precioUnitario * cantidad;

					// Insertar en la tabla cantidadProducto
					String insertarCantidadProducto = "INSERT INTO cantidadProducto (Ticket_idTicket, Producto_idProducto, Cantidad) VALUES (?, ?, ?)";
					try (PreparedStatement insertStmt = conexion.prepareStatement(insertarCantidadProducto)) {
						insertStmt.setInt(1, 0); // Reemplaza con ID del ticket generado
						insertStmt.setInt(2, idProducto);
						insertStmt.setInt(3, cantidad);
						insertStmt.executeUpdate();
					}

					// Actualizar el stock del producto
					String actualizarStock = "UPDATE Producto SET Stock = Stock - ? WHERE idProducto = ?";
					try (PreparedStatement updateStmt = conexion.prepareStatement(actualizarStock)) {
						updateStmt.setInt(1, cantidad);
						updateStmt.setInt(2, idProducto);
						updateStmt.executeUpdate();
					}
				} else {
					System.out.println("Producto no encontrado.");
				}
			}

			System.out.print("¿Desea agregar otro producto? (s/n): ");
			teclado.nextLine(); // Consumir el salto de línea
			String respuesta = teclado.nextLine();
			if (!respuesta.equalsIgnoreCase("s")) {
				break;
			}
		}

		// Insertar el ticket
		String insertarTicket = "INSERT INTO Ticket (PrecioTotal, Cliente_numerocliente) VALUES (?, ?)";
		try (PreparedStatement ticketStmt = conexion.prepareStatement(insertarTicket,
				Statement.RETURN_GENERATED_KEYS)) {
			ticketStmt.setInt(1, totalVenta);
			ticketStmt.setInt(2, numeroCliente);
			ticketStmt.executeUpdate();

			ResultSet generatedKeys = ticketStmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				int idTicket = generatedKeys.getInt(1);
				System.out.println("Venta registrada con éxito. ID del Ticket: " + idTicket);

				// Añadir puntos al cliente
				int puntosGanados = totalVenta / 10;
				String actualizarPuntos = "UPDATE Cliente SET puntos = puntos + ? WHERE numerocliente = ?";
				try (PreparedStatement puntosStmt = conexion.prepareStatement(actualizarPuntos)) {
					puntosStmt.setInt(1, puntosGanados);
					puntosStmt.setInt(2, numeroCliente);
					puntosStmt.executeUpdate();
					System.out.println("Puntos añadidos: " + puntosGanados);
				}
			}
		}
	}

	public static void verTickets(Connection conexion) throws SQLException {
		System.out.println("Ver tickets generados:");
		System.out.print("Ingrese su código de empleado: ");
		int codigoEmpleado = teclado.nextInt();

		String sql = "SELECT t.idTicket, t.PrecioTotal, t.fecha, c.Nombre AS Cliente " + "FROM Ticket t "
				+ "JOIN Cliente c ON t.Cliente_numerodecliente = c.numerodecliente "
				+ "WHERE t.Empleado_codigoEmpleado = ?";
		try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
			stmt.setInt(1, codigoEmpleado);
			ResultSet rs = stmt.executeQuery();

			System.out.println("ID Ticket | Precio Total | Fecha       | Cliente");
			System.out.println("---------------------------------------------------");
			while (rs.next()) {
				System.out.printf("%-10d | %-12.2f | %-10s | %-10s%n", rs.getInt("idTicket"),
						rs.getDouble("PrecioTotal"), rs.getDate("fecha"), rs.getString("Cliente"));
			}
		}
	}

	public static void verHistorialCompras(Connection conexion) throws SQLException {
		System.out.print("Ingrese su número de cliente: ");
		int numeroCliente = teclado.nextInt();

		String sql = "SELECT * FROM Ticket WHERE Cliente_numerodecliente = ?";
		try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
			stmt.setInt(1, numeroCliente);
			ResultSet rs = stmt.executeQuery();

			System.out.println("ID | Fecha | Total");
			while (rs.next()) {
				System.out.println(
						rs.getInt("idTicket") + " | " + rs.getDate("fecha") + " | " + rs.getDouble("PrecioTotal"));
			}
		}
	}

	public static void canjearPuntos(Connection conexion) throws SQLException {
		System.out.print("Ingrese su número de cliente: ");
		int numeroCliente = teclado.nextInt();

		String puntosQuery = "SELECT puntos FROM Cliente WHERE numerodecliente = ?";
		int puntosDisponibles = 0;
		try (PreparedStatement stmt = conexion.prepareStatement(puntosQuery)) {
			stmt.setInt(1, numeroCliente);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				puntosDisponibles = rs.getInt("puntos");
				System.out.println("Tienes " + puntosDisponibles + " puntos disponibles.");
			} else {
				System.out.println("Cliente no encontrado.");
				return;
			}
		}

		System.out.print("¿Cuántos puntos deseas canjear? ");
		int puntosACanjear = teclado.nextInt();
		if (puntosACanjear > puntosDisponibles) {
			System.out.println("No tienes suficientes puntos.");
			return;
		}

		realizarVenta(conexion); // Realizar la compra normal

		// Restar los puntos del cliente
		String actualizarPuntos = "UPDATE Cliente SET puntos = puntos - ? WHERE numerodecliente = ?";
		try (PreparedStatement stmt = conexion.prepareStatement(actualizarPuntos)) {
			stmt.setInt(1, puntosACanjear);
			stmt.setInt(2, numeroCliente);
			stmt.executeUpdate();
			System.out.println("Se han canjeado " + puntosACanjear + " puntos.");
		}
	}

}
