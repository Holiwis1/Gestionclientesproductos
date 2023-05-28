package pasarelaPago;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



public class BasedeDatos {
	/**
	 * Obtiene la URL de conexión desde un archivo de configuracion.
	 *
	 * @return URL
	 * @throws
	 */
	private String obtenerURLConexion() throws IOException {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(
					"C:\\Users\\joaquin\\eclipse-workspace\\pasarelaPago\\src\\pasarelaPago\\configuracion.txt"));
			return br.readLine();
		} finally {
			if (br != null) {
				br.close();
			}
		}
	}

	/**
	 * Obtiene el usuario desde un archivo de configuracion.
	 *
	 * @return usuario.
	 * @throws IOException
	 */

	private String obtenerUsuario() throws IOException {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(
					"C:\\\\Users\\\\joaquin\\\\eclipse-workspace\\\\pasarelaPago\\\\src\\\\pasarelaPago\\\\configuracion.txt"));
			br.readLine();
			return br.readLine();
		} finally {
			if (br != null) {
				br.close();
			}
		}
	}

	/**
	 * Obtiene la contraseña desde un archivo de configuracion.
	 *
	 * @return contraseña.
	 * @throws
	 */

	private String obtenerContraseña() throws IOException {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(
					"C:\\\\Users\\\\joaquin\\\\eclipse-workspace\\\\pasarelaPago\\\\src\\\\pasarelaPago\\\\configuracion.txt"));
			br.readLine();
			br.readLine();
			return br.readLine();
		} finally {
			if (br != null) {
				br.close();
			}
		}
	}

	/**
	 * Guarda una lista de clientes en la base de datos.
	 *
	 * @param listaClientes
	 */
	public void guardarClienteBaseDatos(ArrayList<Cliente> listaClientes) {
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			// Cargar el controlador JDBC de MySQL
			Class.forName("com.mysql.jdbc.Driver");

			// Establecer la conexion con la base de datos
			String url = obtenerURLConexion();
			String usuario = obtenerUsuario();
			String contraseña = obtenerContraseña();
			connection = DriverManager.getConnection(url, usuario, contraseña);

			// Verificar que la conexion se haya establecido correctamente
			if (connection != null) {
				System.out.println("Conexión exitosa a la base de datos.");
			} else {
				System.out.println("Error al establecer la conexión a la base de datos.");
				return;
			}

			// Insertar valores cliente
			String sql = "INSERT INTO cliente (nombre, apellidos, telefono) VALUES (?, ?, ?)";
			statement = connection.prepareStatement(sql);

			// Guardar cada cliente en la base de datos
			for (Cliente cliente : listaClientes) {
				statement.setString(1, cliente.getNombre());
				statement.setString(2, cliente.getApellidos());
				statement.setString(3, cliente.getTelefono());

				statement.executeUpdate();
			}

			System.out.println("Clientes guardados en la base de datos correctamente.");

		} catch (ClassNotFoundException e) {
			System.out.println("Error al cargar el controlador JDBC.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error al establecer la conexión o ejecutar la consulta SQL.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error al leer el archivo de configuración.");
			e.printStackTrace();
		} finally {
			// Cerrar los datos
			try {
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Lee los clientes almacenados en la base de datos.
	 *
	 * @return lista de clientes leida.
	 */

	public ArrayList<Cliente> leerClientesBaseDatos() {
		ArrayList<Cliente> listaClientes = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			// Cargar el controlador JDBC de MySQL
			Class.forName("com.mysql.jdbc.Driver");

			// Establecer la conexión con la base de datos
			String url = obtenerURLConexion();
			String usuario = obtenerUsuario();
			String contraseña = obtenerContraseña();
			connection = DriverManager.getConnection(url, usuario, contraseña);

			// Verificar que la conexión se haya establecido correctamente
			if (connection != null) {
				System.out.println("Conexión exitosa a la base de datos.");
			} else {
				System.out.println("Error al establecer la conexión a la base de datos.");
				return listaClientes;
			}

			// Consulta SQL para obtener los clientes
			String sql = "SELECT * FROM cliente";
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();

			// Recorrer el resultado y crear objetos Cliente
			while (resultSet.next()) {
				String nombre = resultSet.getString("nombre");
				String apellidos = resultSet.getString("apellidos");
				String telefono = resultSet.getString("telefono");

				Cliente cliente = new Cliente(nombre, apellidos, telefono, telefono);
				listaClientes.add(cliente);
			}

		} catch (ClassNotFoundException e) {
			System.out.println("Error al cargar el controlador JDBC.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error al establecer la conexión o ejecutar la consulta SQL.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error al leer el archivo de configuración.");
			e.printStackTrace();
		} finally {
			// Cerrar los recursos
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return listaClientes;
	}
}
