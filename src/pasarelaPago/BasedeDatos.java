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
import java.sql.*;


public class BasedeDatos {
	/**
	 * Obtiene la URL de conexión desde un archivo de configuracion.
	 * 
	 * @author joaquin
	 * @return URL
	 * @throws
	 */
	
	private String obtenerURLConexion() throws IOException {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(
					"C:\\Users\\joaquin\\Documents\\GitHub\\Gestionclientesproductos\\src\\pasarelaPago\\configuracion.txt"));
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
					"C:\\Users\\joaquin\\Documents\\GitHub\\Gestionclientesproductos\\src\\pasarelaPago\\configuracion.txt"));
			br.readLine();
			return br.readLine();
		} finally {
			if (br != null) {
				br.close();
			}
		}
	}

	/**
	 * Obtiene la contrasenia desde un archivo de configuracion.
	 *
	 * 
	 * 
	 */

	private String obtenerContrasenia() throws IOException {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(
					"C:\\Users\\joaquin\\Documents\\GitHub\\Gestionclientesproductos\\src\\pasarelaPago\\configuracion.txt"));
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
			String contrasenia = obtenerContrasenia();
			connection = DriverManager.getConnection(url, usuario, contrasenia);

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
			String contrasenia = obtenerContrasenia();
			connection = DriverManager.getConnection(url, usuario, contrasenia);

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

	/**
	 * Lee los productos de la base de datos y los devuelve como una lista de
	 * objetos Producto.
	 *
	 * @return Lista de productos leídos de la base de datos.
	 */
	public ArrayList<Producto> leerProductosBaseDatos() {
		ArrayList<Producto> listaProductos = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			// Cargar el controlador JDBC de MySQL
			Class.forName("com.mysql.jdbc.Driver");

			// Establecer la conexion con la base de datos
			String url = obtenerURLConexion();
			String usuario = obtenerUsuario();
			String contrasenia = obtenerContrasenia();
			connection = DriverManager.getConnection(url, usuario, contrasenia);

			// Verificar que la conexion se haya establecido correctamente
			if (connection != null) {
				System.out.println("Conexión exitosa a la base de datos.");
			} else {
				System.out.println("Error al establecer la conexión a la base de datos.");
				return listaProductos;
			}

			// Consulta SQL para obtener los productos
			String sql = "SELECT * FROM producto";
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();

			// Recorrer el resultado y crear objetos Producto
			while (resultSet.next()) {
				String nombre = resultSet.getString("nombre");
				double precio = resultSet.getDouble("precio");
				int cantidad = resultSet.getInt("cantidad");

				Producto producto = new Producto(nombre, precio, cantidad);
				listaProductos.add(producto);
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

		return listaProductos;
	}

	/**
	 * Guarda una lista de productos en la base de datos.
	 *
	 * @param listaProductos
	 */
	public void guardarProductoBaseDatos(ArrayList<Producto> listaProductos) {
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			// Cargar el controlador JDBC de MySQL
			Class.forName("com.mysql.jdbc.Driver");

			// Establecer la conexion con la base de datos
			String url = obtenerURLConexion();
			String usuario = obtenerUsuario();
			String contrasenia = obtenerContrasenia();
			connection = DriverManager.getConnection(url, usuario, contrasenia);

			// Verificar que la conexión se haya establecido correctamente
			if (connection != null) {
				System.out.println("Conexión exitosa a la base de datos.");
			} else {
				System.out.println("Error al establecer la conexión a la base de datos.");
				return;
			}

			// insertar los productos
			String sql = "INSERT INTO producto (nombre, precio, cantidad) VALUES (?, ?, ?)";
			statement = connection.prepareStatement(sql);

			// Guardar cada producto en la base de datos
			for (Producto producto : listaProductos) {
				statement.setString(1, producto.getNombre());
				statement.setDouble(2, producto.getPrecio());
				statement.setInt(3, producto.getCantidad());
				statement.executeUpdate();
			}

			System.out.println("Productos guardados en la base de datos correctamente.");

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
	 * Guarda el pedido en la base de datos.
	 *
	 * @param pedido El pedido a guardar en la base de datos.
	 */

	public void guardarPedidoBaseDatos(Pedido pedido) {
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			// Establecer la conexión con la base de datos
			String url = obtenerURLConexion();
			String usuario = obtenerUsuario();
			String contrasenia = obtenerContrasenia();
			connection = DriverManager.getConnection(url, usuario, contrasenia);

			// Verificar que la conexión se haya establecido correctamente
			if (connection != null) {
				System.out.println("Conexión exitosa a la base de datos.");
			} else {
				System.out.println("Error al establecer la conexión a la base de datos.");
				return;
			}

			// Obtener el ID del cliente

			// Consulta SQL para insertar el pedido en la base de datos
			String sql = "INSERT INTO pedido ( nombre_producto, precio, cantidad, total) VALUES (?, ?, ?, ?)";
			statement = connection.prepareStatement(sql);

			for (int i = 0; i < pedido.productos.size(); i++) {
				Producto producto = pedido.productos.get(i);
				int cantidadProducto = pedido.cantidades.get(i);
				double totalProducto = producto.precio * cantidadProducto;

				statement.setString(1, producto.nombre);
				statement.setDouble(2, producto.precio);
				statement.setInt(3, cantidadProducto);
				statement.setDouble(4, totalProducto);

				// Ejecutar la consulta SQL para cada producto del pedido
				int filasInsertadas = statement.executeUpdate();

				if (filasInsertadas > 0) {
					System.out.println("Producto guardado en la base de datos.");
				} else {
					System.out.println("Error al guardar el producto en la base de datos.");
				}
			}

		} catch (SQLException e) {
			System.out.println("Error al establecer la conexión o ejecutar la consulta SQL.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error al leer el archivo de configuración.");
			e.printStackTrace();
		} finally {
			// Cerrar los recursos
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

}
