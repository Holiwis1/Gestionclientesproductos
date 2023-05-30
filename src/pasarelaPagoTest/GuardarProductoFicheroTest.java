package pasarelaPagoTest;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import org.junit.Test;

import pasarelaPago.Producto;

public class GuardarProductoFicheroTest {

	@Test
	public void guardarProductoFichero() {

		ArrayList<Producto> listaProductos = new ArrayList<>();
		Producto producto1 = new Producto("Producto A", 10.0, 20);
		Producto producto2 = new Producto("Producto B", 15.0, 30);
		listaProductos.add(producto1);
		listaProductos.add(producto2);

		guardarProductoFichero(listaProductos);

		ArrayList<Producto> productosGuardados = leerProductosDesdeFichero();
		assertEquals(listaProductos.size(), productosGuardados.size());
		for (int i = 0; i < listaProductos.size(); i++) {
			assertEquals(listaProductos.get(i).getNombre(), productosGuardados.get(i).getNombre());
			assertEquals(listaProductos.get(i).getPrecio(), productosGuardados.get(i).getPrecio(), 0.001);
			assertEquals(listaProductos.get(i).getCantidad(), productosGuardados.get(i).getCantidad());
		}
	}

	private void guardarProductoFichero(ArrayList<Producto> listaProductos) {
		FileOutputStream archivoSalida = null;
		ObjectOutputStream objetoSalida = null;

		try {
			archivoSalida = new FileOutputStream(
					"C:\\Users\\joaquin\\Documents\\GitHub\\Gestionclientesproductos\\src\\pasarelaPagoTest\\ProductoPrueba.txt");
			objetoSalida = new ObjectOutputStream(archivoSalida);
			objetoSalida.writeObject(listaProductos);
			objetoSalida.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private ArrayList<Producto> leerProductosDesdeFichero() {
		ArrayList<Producto> listaProductos = null;
		FileInputStream archivoEntrada = null;
		ObjectInputStream objetoEntrada = null;

		try {
			archivoEntrada = new FileInputStream(
					"C:\\Users\\joaquin\\Documents\\GitHub\\Gestionclientesproductos\\src\\pasarelaPagoTest\\ProductoPrueba.txt");
			objetoEntrada = new ObjectInputStream(archivoEntrada);
			listaProductos = (ArrayList<Producto>) objetoEntrada.readObject();
			objetoEntrada.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		return listaProductos;
	}
}