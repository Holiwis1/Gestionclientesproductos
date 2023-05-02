package pasarelaPago;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class Menu {
	public Menu() {}
	public void mostrarProductos(ArrayList<Producto> productos) {
		System.out.println("");
		System.out.println("- PRODUCTOS -");
		for(int i = 0; i < productos.size(); i++) {
			System.out.println( i+") "+productos.get(i).nombre + "  " + productos.get(i).precio + " $   " + productos.get(i).cantidad + " en stock" );
		}
		System.out.println("");
	}
	public String telefonoValidar() {
		Scanner sc = new Scanner(System.in);
		String telefono = "";
		while(true) {
			System.out.println("Telefono");
			telefono = sc.nextLine();
			
			Pattern patron = Pattern.compile("(\\+34|0034|34)?[ -]*(6|7)[ -]*([0-9][ -]*){8}");
			Matcher matcher = patron.matcher(telefono);
			
			if (matcher.matches()) {
				break;
			} else {
				System.out.println("Telefono no valido");
			}
			
		}
		return telefono;
	}
	
	public void bienvenidaPrograma() {
		System.out.println("********************");
		System.out.println("**PASARELA DE PAGO**");
		System.out.println("********************");
	}
	public void opcionesMenuPrincipal() {
		System.out.println("Opciones -->");
		System.out.println("(1) Crear cliente");
		System.out.println("(2) Agregar producto");
		System.out.println("(3) Acceder a cliente");
		System.out.println("(4) Salir");
	}
 	public Cliente crearCliente() {
 		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Nombre: ");
		String nombre = sc.next();
		
		System.out.println("Apellidos: ");
		String apellidos = sc.next();
		
		System.out.println("Direccion: ");
		String direccion = sc.next();
		
		String telefono = this.telefonoValidar();
		
		return new Cliente(nombre, apellidos, direccion, telefono);
	}
	public Producto crearProducto() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Nombre del producto: ");
		String nombre = sc.next();
		
		System.out.println("Precio: ");
		double precio = sc.nextDouble();
		sc.nextLine();
		
		System.out.println("Cantidad: ");
		int cantidad = sc.nextInt();
		sc.nextLine();
		
		
		return new Producto(nombre, precio, cantidad);
	}
}
