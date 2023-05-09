

package pasarelaPago;

import java.io.Serializable;

/**
 * 
 * @author joaquin
 *
 */
public class Producto implements Serializable {
	
	//Atributos
	
	/**
	 * Nombre del producto
	 */
	String nombre;
	
	/**
	 * Precio del producto
	 */
	double precio;
	
	/**
	 * Cantidad de productos de este tipo
	 */
	int cantidad;
	

	/**
	 * Cosntructor clase producto
	 * @param nombre
	 * @param precio
	 * @param cantidad
	 */
	public Producto(String nombre,double precio,int cantidad) {
		setNombre(nombre);
		setPrecio(precio);
		setCantidad(cantidad);
	}

	// Getters & Setters
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public void reducirStock(int cantidad) {
		this.cantidad = this.cantidad - cantidad;
	}
	
	public void mostrarProducto() {
		System.out.println(this.nombre + " " + this.precio + " " + this.cantidad);
	}

}
