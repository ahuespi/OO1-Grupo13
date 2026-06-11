import java.time.LocalDate;

public class Cajero extends Personal {

    private String turno;

    public Cajero(int id, String nombre, String apellido, long dni,
                  LocalDate fechaNacimiento, LocalDate fechaIngreso,
                  String turno) throws Exception {
        super(id, nombre, apellido, dni, fechaNacimiento, fechaIngreso);
        this.setTurno(turno);
    }

    @Override
    public double calcularSueldo() {
        return SUELDO_BASE + calcularAntiguedad() * VALOR_ANIO_ANTIGUEDAD;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) throws Exception {
        if (turno == null ||
                (!turno.equalsIgnoreCase("mañana") && !turno.equalsIgnoreCase("noche"))) {
            throw new Exception("Error: el turno debe ser mañana o noche");
        }

        this.turno = turno;
    }

    @Override
    public String toString() {
        return "Cajero [" + super.toString() +
                ", turno=" + turno +
                "]";
    }
}