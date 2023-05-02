package pasarelaPago;
import java.io.IOException;
/**
 * @author joaquin
 */
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
public class Fichero {
	public void guardarClienteFichero(ArrayList<Cliente> listaClientes) {	
			FileOutputStream archivoSalida = null;
			ObjectOutputStream objetoSalida = null; 
		
			try {
				archivoSalida = new FileOutputStream("C:\\Users\\joaquin\\eclipse-workspace\\pasarelaPago\\src\\pasarelaPago\\clientes.txt");
				objetoSalida = new ObjectOutputStream(archivoSalida);
				objetoSalida.writeObject(listaClientes);
				objetoSalida.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	public ArrayList<Cliente> leerClientesFichero() {
		FileInputStream archivoSalida = null;
		ObjectInputStream objetoSalida = null;
		ArrayList<Cliente> listaClientes = null;
		
		try {
			archivoSalida = new FileInputStream("C:\\Users\\joaquin\\eclipse-workspace\\pasarelaPago\\src\\pasarelaPago\\clientes.txt");
			objetoSalida = new ObjectInputStream(archivoSalida);
			try {
				listaClientes = (ArrayList<Cliente>) objetoSalida.readObject();
				objetoSalida.close();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			listaClientes = new ArrayList<Cliente>();
		}
		return listaClientes;
	}
	public void guardarProductoFichero(ArrayList<Producto> listaProductos) {	
		FileOutputStream archivoSalida = null;
		ObjectOutputStream objetoSalida = null; 
	
		try {
			archivoSalida = new FileOutputStream("C:\\Users\\joaquin\\eclipse-workspace\\pasarelaPago\\src\\pasarelaPago\\productos.txt");
			objetoSalida = new ObjectOutputStream(archivoSalida);
			objetoSalida.writeObject(listaProductos);
			objetoSalida.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ArrayList<Producto> leerProductosFichero() {
		FileInputStream archivoSalida = null;
		ObjectInputStream objetoSalida = null;
		ArrayList<Producto> listaProductos = null;
		
		try {
			archivoSalida = new FileInputStream("C:\\Users\\joaquin\\eclipse-workspace\\pasarelaPago\\src\\pasarelaPago\\productos.txt");
			objetoSalida = new ObjectInputStream(archivoSalida);
			try {
				listaProductos = (ArrayList<Producto>) objetoSalida.readObject();
				objetoSalida.close();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (IOException e) {
			listaProductos = new ArrayList<Producto>();
		}
		
		return listaProductos;
	}
}

