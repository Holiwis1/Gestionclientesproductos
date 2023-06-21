package pasarelaPago;

import java.io.Serializable;

public class Cliente implements Serializable {

	
	/**
     * Nombre de la persona.
     * @var nombre
     */
	String nombre;
	/**
	 *@var apellidos
     * Apellidos de la persona.
     */
	String apellidos;
	
	/**
	 * @var direccion
     * Dirección de la persona.
     */
	String direccion;
	
	/**
	 * @var telefono
     * Número de teléfono de la persona.
     */
	String telefono;

	/**
	 * Constructor clase Cliente
	 * 
	 * @param nombre
	 * @param apellidos
	 * @param direccion
	 * @param telefono
	 */
	public Cliente(String nombre, String apellidos, String direccion, String telefono) {
		setNombre(nombre);
		setApellidos(apellidos);
		setDireccion(direccion);
		setTelefono(telefono);
	}

	// Getters & Setters

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getTelefono() {
		return this.telefono;
	}

}
