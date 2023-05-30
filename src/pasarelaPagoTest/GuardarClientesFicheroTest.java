package pasarelaPagoTest;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import org.junit.Test;

import pasarelaPago.Cliente;

public class GuardarClientesFicheroTest {

	@Test
    public void guardarClienteFichero_DeberiaGuardarClientesEnArchivo() {
        // Arrange
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        Cliente cliente1 = new Cliente("John Doe", "123 Main St", "555-1234", "667709880");
        Cliente cliente2 = new Cliente("Jane Smith", "456 Elm St", "555-5678", "295240869");
        listaClientes.add(cliente1);
        listaClientes.add(cliente2);

        // Act
        guardarClienteFichero(listaClientes);

        // Assert
        ArrayList<Cliente> clientesGuardados = leerClientesDesdeArchivo();
        assertEquals(listaClientes.size(), clientesGuardados.size());
        for (int i = 0; i < listaClientes.size(); i++) {
            assertEquals(listaClientes.get(i).getNombre(), clientesGuardados.get(i).getNombre());
            assertEquals(listaClientes.get(i).getDireccion(), clientesGuardados.get(i).getDireccion());
            assertEquals(listaClientes.get(i).getTelefono(), clientesGuardados.get(i).getTelefono());
        }
    }

    private void guardarClienteFichero(ArrayList<Cliente> listaClientes) {
        FileOutputStream archivoSalida = null;
        ObjectOutputStream objetoSalida = null;

        try {
            archivoSalida = new FileOutputStream("C:\\Users\\joaquin\\Documents\\GitHub\\Gestionclientesproductos\\src\\pasarelaPagoTest\\ClientesPrueba.txt");
            objetoSalida = new ObjectOutputStream(archivoSalida);
            objetoSalida.writeObject(listaClientes);
            objetoSalida.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<Cliente> leerClientesDesdeArchivo() {
        ArrayList<Cliente> listaClientes = null;
        FileInputStream archivoEntrada = null;
        ObjectInputStream objetoEntrada = null;

        try {
            archivoEntrada = new FileInputStream("C:\\Users\\joaquin\\Documents\\GitHub\\Gestionclientesproductos\\src\\pasarelaPagoTest\\ClientesPrueba.txt");
            objetoEntrada = new ObjectInputStream(archivoEntrada);
            listaClientes = (ArrayList<Cliente>) objetoEntrada.readObject();
            objetoEntrada.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return listaClientes;
    }
}
