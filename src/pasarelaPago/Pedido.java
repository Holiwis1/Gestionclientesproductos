package pasarelaPago;

import java.util.ArrayList;

public class Pedido {
	Cliente cliente;
	double importeTotal;
	ArrayList<Integer> cantidades = new ArrayList<Integer>();
	ArrayList<Producto> productos = new ArrayList<Producto>();
	String estado;
	
	public Pedido(Cliente cliente, String estado) {
		this.cliente = cliente;
		this.estado = estado;
	}
	
	public void mostrarPedido() {
		System.out.println(" ------  ----------  --------");
		System.out.println("  CANT    PRODUCTO    PRECIO ");
		System.out.println(" ------  ----------  --------");
	
		for(int i = 0; i < productos.size(); i++) {
			int cantidad = this.cantidades.get(i);
			String producto = this.productos.get(i).nombre;
			System.out.println("   "+cantidad+"    "+producto+"     "+this.productos.get(i).precio + "$");
		}
		System.out.println("Total: "+this.importeTotal+" $");
	}
	
	public void agregarProducto(Producto producto, int cantidad) {
		this.productos.add(producto);
		this.cantidades.add(cantidad);
		this.importeTotal += producto.precio * cantidad;
	}
	
	
}
