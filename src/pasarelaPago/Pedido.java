package pasarelaPago;

import java.util.ArrayList;

/**
 * @author joaquin Clase que representa un pedido realizado por un cliente.
 */
public class Pedido {
	Cliente cliente;
	double importeTotal;
	ArrayList<Integer> cantidades = new ArrayList<Integer>();
	ArrayList<Producto> productos = new ArrayList<Producto>();
	String estado;
	BasedeDatos BD;

	/**
	 * Constructor de la clase Pedido.
	 * 
	 * @param cliente El cliente que realiza el pedido.
	 * @param estado  El estado del pedido.
	 * @param BD      La base de datos utilizada para guardar el pedido.
	 */
	public Pedido(Cliente cliente, String estado, BasedeDatos BD) {
		this.cliente = cliente;
		this.estado = estado;
		this.BD = BD;
	}

	/**
	 * Muestra el detalle del pedido.
	 */
	public void mostrarPedido() {
		System.out.println(" ------  ----------  --------");
		System.out.println("  CANT    PRODUCTO    PRECIO ");
		System.out.println(" ------  ----------  --------");

		for (int i = 0; i < productos.size(); i++) {
			int cantidad = this.cantidades.get(i);
			String producto = this.productos.get(i).nombre;
			System.out.println("   " + cantidad + "    " + producto + "     " + this.productos.get(i).precio + "$");
			if (cliente != null && estado.equals("finalizado")) {
				BD.guardarPedidoBaseDatos(this);
			}
		}
		System.out.println("Total: " + this.importeTotal + " $");
	}

	/**
	 * Agrega un producto al pedido junto con la cantidad solicitada.
	 * 
	 * @param producto El producto a agregar.
	 * @param cantidad La cantidad del producto.
	 */

	public void agregarProducto(Producto producto, int cantidad) {
		this.productos.add(producto);
		this.cantidades.add(cantidad);
		this.importeTotal += producto.precio * cantidad;
	}

}
