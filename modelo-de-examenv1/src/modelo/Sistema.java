package modelo;
import java.util.ArrayList;
import java.util.List;

public class Sistema {
	private List<Cliente> lstClientes;
	
	public Sistema() {
		this.lstClientes = new ArrayList<Cliente>();
	}
	
	public List<Cliente> getLstClientes(){
		return lstClientes;
	}
	
	// CU 1
	public Cliente traerCliente(long dni) {
		Cliente cliente = null;
		int i = 0;
		
		while (cliente == null && i < lstClientes.size()) {
			if (lstClientes.get(i).getDni() == dni) {
				cliente = lstClientes.get(i);
			}
			i++;
		}
		
		return cliente;
	}
	
	// CU 2
	public boolean agregarCliente(long dni, String nombre, String apellido) throws Exception {
		if (traerCliente(dni) != null) {
			throw new Exception("El cliente con DNI nro: " + dni + " ya existe.");
		}
		int id = lstClientes.isEmpty() ? 1 : lstClientes.get(lstClientes.size() - 1).getIdCliente() + 1;
		
		return lstClientes.add(new Cliente(id, dni, nombre, apellido));
	}
}
