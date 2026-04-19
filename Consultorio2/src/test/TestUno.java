package test;

import modelo.Paciente;

public class TestUno {
	public static void main(String[] args) {
		
		Paciente paciente1 = new Paciente("Amir", "Huespi", 1.63f, 93);
		Paciente paciente2 = new Paciente("Caris", "Martinez", 1.63f, 93);
		Paciente paciente3 = new Paciente("Ali", "Mart", 1.63f, 93);
		
		
		System.out.println("Pacientes:");
		System.out.println(paciente1.traerNombreCompleto());
		System.out.println(paciente2.traerNombreCompleto());
		System.out.println(paciente3.traerNombreCompleto());
		
		paciente1.setNombre("Jose");
		paciente1.setApellido("NoLastName");
		
		System.out.println(paciente1.traerNombreCompleto());
	}
}