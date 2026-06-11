import java.time.LocalDate;
import java.time.Period;

public abstract class Personal {

    protected static final double SUELDO_BASE = 100000;
    protected static final double VALOR_ANIO_ANTIGUEDAD = 5000;

    private int id;
    private String nombre;
    private String apellido;
    private long dni;
    private LocalDate fechaNacimiento;
    private LocalDate fechaIngreso;

    public Personal() {
    }

    public Personal(int id, String nombre, String apellido, long dni,
                    LocalDate fechaNacimiento, LocalDate fechaIngreso) throws Exception {
        this.setIdPersonal(id); // FIX: Validación de dato en setter (Regla 11)
        this.setNombre(nombre);
        this.setApellido(apellido);
        this.setDni(dni);
        this.setFechaNacimiento(fechaNacimiento);
        this.setFechaIngreso(fechaIngreso);
    }

    public abstract double calcularSueldo();

    public int calcularEdad() {
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }

    public int calcularAntiguedad() {
        return Period.between(fechaIngreso, LocalDate.now()).getYears();
    }

    public boolean esMayorDeEdad() {
        return calcularEdad() >= 18;
    }

    public int getIdPersonal() {
        return id;
    }

    public void setIdPersonal(int id) throws Exception {
        if (id < 0) throw new Exception("Error: El ID no puede ser negativo"); // FIX: Validación (Regla 11)
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) throws Exception {
        if (nombre == null || nombre.trim().isEmpty()) throw new Exception("Error: El nombre no puede estar vacío"); // FIX: Validación (Regla 11)
        this.nombre = nombre;
    }   

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) throws Exception {
        if (apellido == null || apellido.trim().isEmpty()) throw new Exception("Error: El apellido no puede estar vacío"); // FIX: Validación (Regla 11)
        this.apellido = apellido;
    }   

    public long getDni() {
        return dni;
    }

    public void setDni(long dni) throws Exception {
        if (dni <= 0) throw new Exception("Error: El DNI debe ser mayor a 0"); // FIX: Validación (Regla 11)
        this.dni = dni;
    }   

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) throws Exception {
        if (fechaNacimiento == null) {
            throw new Exception("Error: la fecha de nacimiento no puede ser nula");
        }

        int edad = Period.between(fechaNacimiento, LocalDate.now()).getYears();

        if (edad < 18) {
            throw new Exception("Error: el personal debe ser mayor de edad");
        }

        this.fechaNacimiento = fechaNacimiento;
    }   

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) throws Exception {
        if (fechaIngreso == null) throw new Exception("Error: La fecha de ingreso no puede ser nula"); // FIX: Validación (Regla 11)
        this.fechaIngreso = fechaIngreso;
    }   

    // FIX: Redefinición y sobrecarga de equals (Regla 13)
    @Override
    public boolean equals(Object obj) {
        boolean sonIguales = false;
        if (obj != null && obj instanceof Personal) {
            sonIguales = this.equals((Personal) obj);
        }
        return sonIguales;
    }

    public boolean equals(Personal otro) {
        boolean sonIguales = false;
        if (otro != null) {
            if (this.dni == otro.getDni()) {
                sonIguales = true;
            }
        }
        return sonIguales;
    }

    // FIX: Polimorfismo con toString (Regla 12)
    @Override
    public String toString() {
        return "Personal [id=" + id +
                ", nombre=" + nombre +
                ", apellido=" + apellido +
                ", dni=" + dni +
                ", fechaNacimiento=" + fechaNacimiento +
                ", fechaIngreso=" + fechaIngreso +
                "]";
    }
}
