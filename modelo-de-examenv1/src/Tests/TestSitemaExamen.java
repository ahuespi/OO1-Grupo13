package Tests;

import modelo.Sistema;

public class TestSitemaExamen {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sistema sistema = new Sistema();
		
		System.out.println("\n Imprimir Clientes");
		
		try {
			sistema.agregarCliente(1111111L, "Cliente", "Uno");
			sistema.agregarCliente(2222222L, "Cliente", "Dos");
			sistema.agregarCliente(3333333L, "Cliente", "Tres");
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println(sistema.getLstClientes());
		
		
		System.out.println("\n2) Traer e imprimir cliente dni =");
		try {
			long dni = 1111111;
			System.out.println(sistema.traerCliente(dni));
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("\n3) Intentar agregar el cliente 1");
		try {
			sistema.agregarCliente(1111111L, "Cliente", "Uno");
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
