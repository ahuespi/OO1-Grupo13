package personal;

import java.time.LocalDate;

public class Personal {
	private int id;
	private String nombre;
	private String apellido;
	private long dni;
	private LocalDate fechaNacimiento;
	private LocalDate fechaIngreso;
	
	public Personal(int id, String nombre, String apellido, long dni, LocalDate fechaNacimiento,
			LocalDate fechaIngreso) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.fechaNacimiento = fechaNacimiento;
		this.fechaIngreso = fechaIngreso;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public long getDni() {
		return dni;
	}

	public void setDni(long dni) {
		this.dni = dni;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(LocalDate fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Personal: " + 
				" - ID: " + id + 
				" - Nombre: " + nombre + 
				" - Apellido: " + apellido + 
				" - DNI: " + dni + 
				" - Fecha de nacimiento: " + fechaNacimiento +
				" - Fecha de Ingreso: " + fechaIngreso;
	}
	
}
