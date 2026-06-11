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

    public Personal(int id, String nombre, String apellido, long dni,
                    LocalDate fechaNacimiento, LocalDate fechaIngreso) throws Exception {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.setFechaNacimiento(fechaNacimiento);
        this.fechaIngreso = fechaIngreso;
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

    public void setIdPersonal(int id) {
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

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }   

    @Override
    public boolean equals(Object obj) {
        boolean sonIguales = false;

        if (obj != null && obj instanceof Personal) {
            Personal otro = (Personal) obj;
            if (this.dni == otro.getDni()) {
                sonIguales = true;
            }
        }

        return sonIguales;
    }

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
