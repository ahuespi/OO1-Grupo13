import java.time.LocalDate;

public class Cocinero extends Personal {

    private String especialidad;
    private double plusCategoria;

    public Cocinero(int id, String nombre, String apellido, long dni,
                    LocalDate fechaNacimiento, LocalDate fechaIngreso,
                    String especialidad, double plusCategoria) throws Exception {
        super(id, nombre, apellido, dni, fechaNacimiento, fechaIngreso);
        this.setEspecialidad(especialidad); // FIX: Validación en setter (Regla 11)
        this.setPlusCategoria(plusCategoria);
    }

    @Override
    public double calcularSueldo() {
        return SUELDO_BASE + plusCategoria;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) throws Exception {
        if (especialidad == null || especialidad.trim().isEmpty()) throw new Exception("Error: La especialidad no puede estar vacía"); // FIX: Validación (Regla 11)
        this.especialidad = especialidad;
    }

    public double getPlusCategoria() {
        return plusCategoria;
    }

    public void setPlusCategoria(double plusCategoria) throws Exception {
        if (plusCategoria < 0) throw new Exception("Error: El plus no puede ser negativo"); // FIX: Validación (Regla 11)
        this.plusCategoria = plusCategoria;
    }

    // FIX: Sobrecarga de equals (Regla 13)
    @Override
    public boolean equals(Object obj) {
        boolean sonIguales = false;
        if (obj != null && obj instanceof Cocinero) {
            sonIguales = this.equals((Cocinero) obj);
        }
        return sonIguales;
    }

    public boolean equals(Cocinero otro) {
        boolean sonIguales = false;
        if (otro != null) {
            if (this.getDni() == otro.getDni()) {
                sonIguales = true;
            }
        }
        return sonIguales;
    }

    @Override
    public String toString() {
        return "Cocinero [" + super.toString() +
                ", especialidad=" + especialidad +
                ", plusCategoria=" + plusCategoria +
                "]";
    }
}