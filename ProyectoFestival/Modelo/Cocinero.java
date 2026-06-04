import java.time.LocalDate;

public class Cocinero extends Personal {

    private String especialidad;
    private double plusCategoria;

    public Cocinero(int id, String nombre, String apellido, long dni,
                    LocalDate fechaNacimiento, LocalDate fechaIngreso,
                    String especialidad, double plusCategoria) throws Exception {
        super(id, nombre, apellido, dni, fechaNacimiento, fechaIngreso);
        this.especialidad = especialidad;
        this.plusCategoria = plusCategoria;
    }

    @Override
    public double calcularSueldo() {
        return SUELDO_BASE + plusCategoria;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public double getPlusCategoria() {
        return plusCategoria;
    }

    public void setPlusCategoria(double plusCategoria) {
        this.plusCategoria = plusCategoria;
    }

    @Override
    public String toString() {
        return "Cocinero [" + super.toString() +
                ", especialidad=" + especialidad +
                ", plusCategoria=" + plusCategoria +
                "]";
    }
}