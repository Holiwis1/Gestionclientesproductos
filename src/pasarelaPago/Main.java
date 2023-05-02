package pasarelaPago;
import java.util.Scanner;
import java.util.ArrayList;
public class Main {
	public static void main(String[] args) {
		
		int opcion = 0;
		Scanner sc = new Scanner(System.in);
		Menu menu = new Menu();
		Fichero fc = new Fichero();
		ArrayList<Cliente> listaClientes = fc.leerClientesFichero();
		ArrayList<Producto> listaProductos = fc.leerProductosFichero();
		listaProductos.add(new Producto("producto X", 2.0, 1));
		
		menu.bienvenidaPrograma();
		
		while(opcion != 4) {
			menu.opcionesMenuPrincipal();
			opcion = sc.nextInt();
			sc.nextLine();
			switch(opcion) {
			case 1:
				Cliente cliente = menu.crearCliente();
				listaClientes.add(cliente);
				fc.guardarClienteFichero(listaClientes);
				
				break;
			case 2:
				Producto producto = menu.crearProducto();
				listaProductos.add(producto);
				fc.guardarProductoFichero(listaProductos);
				break;
			case 3:
				System.out.println("Numero de telefono del cliente: ");
				String tlfCliente = menu.telefonoValidar();
				Cliente clienteSeleccionado = null;
				for(int i = 0; i < listaClientes.size(); i++) {
					if(listaClientes.get(i).telefono.equals(tlfCliente)) {
						clienteSeleccionado = listaClientes.get(i);
					}
				}
				if(clienteSeleccionado == null) {
					System.out.println("El cliente no existe");
				} else {
					String p;
					System.out.println("Cliente: "+clienteSeleccionado.nombre);
					Pedido pedido = new Pedido(clienteSeleccionado, "en curso");
					while(pedido.estado.equals("en curso")) {
						menu.mostrarProductos(listaProductos);
						pedido.mostrarPedido();
						System.out.println("Seleccione un producto: ");
						p = sc.nextLine();
						
						if(p.equals("pagar")) {
							pedido.estado = "finalizado";
							System.out.println("");
							System.out.println("------------");
							System.out.println("---TICKET---");
							System.out.println("------------");
							System.out.println("");
							pedido.mostrarPedido();
							System.out.println("-------------");
							SistemaPago pago = new SistemaPago(pedido.importeTotal);
							pago.elegirPago();
							break;
						}
						if(listaProductos.size() > Integer.parseInt(p)) {
							System.out.println("Cantidad: ");
							int cantidad = sc.nextInt();
							sc.nextLine();
							if(listaProductos.get(Integer.parseInt(p)).cantidad - cantidad >= 0) {
								pedido.agregarProducto(listaProductos.get(Integer.parseInt(p)), cantidad);
								listaProductos.get(Integer.parseInt(p)).reducirStock(cantidad);
							} else {
								System.out.println("No hay suficientes productos en stock");
							}
						} else {
							System.out.println("No existe el producto");
						}
						
					}
				}
				break;
			case 4:
				System.out.println("Cerrando programa.");
				break;
			default:
				System.out.println("Opcion no valida");
				break;
			}
		}
		
	}

}
