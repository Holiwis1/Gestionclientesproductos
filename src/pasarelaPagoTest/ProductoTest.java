package pasarelaPagoTest;

import static org.junit.Assert.*;

import org.junit.Test;

import pasarelaPago.Producto;

public class ProductoTest {

	@Test
	public void reducirStock_DeberiaReducirCantidadCorrectamente() {
		// Arrange
		Producto producto = new Producto("Producto A", 10.0, 20);

		// Act
		producto.reducirStock(5);

		// Assert
		int stockEsperado = 15;
		assertEquals(stockEsperado, producto.getCantidad());
	}
}
