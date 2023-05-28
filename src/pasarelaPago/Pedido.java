package pasarelaPago;

import java.util.ArrayList;

public class Pedido {
	Cliente cliente;
	double importeTotal;
	ArrayList<Integer> cantidades = new ArrayList<Integer>();
	ArrayList<Producto> productos = new ArrayList<Producto>();
	String estado;
	BasedeDatos BD;
	
	public Pedido(Cliente cliente, String estado,BasedeDatos BD) {
		this.cliente = cliente;
		this.estado = estado;
		this.BD=BD;
	}
	
	public void mostrarPedido() {
		System.out.println(" ------  ----------  --------");
		System.out.println("  CANT    PRODUCTO    PRECIO ");
		System.out.println(" ------  ----------  --------");
	
		for(int i = 0; i < productos.size(); i++) {
			int cantidad = this.cantidades.get(i);
			String producto = this.productos.get(i).nombre;
			System.out.println("   "+cantidad+"    "+producto+"     "+this.productos.get(i).precio + "$");
			if (cliente != null && estado.equals("finalizado")) {
	            BD.guardarPedidoBaseDatos(this);
		}
		}
		System.out.println("Total: "+this.importeTotal+" $");
	}
	
	public void agregarProducto(Producto producto, int cantidad) {
		this.productos.add(producto);
		this.cantidades.add(cantidad);
		this.importeTotal += producto.precio * cantidad;
	}
	
	
}
