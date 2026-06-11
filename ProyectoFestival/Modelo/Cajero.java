import java.time.LocalDate;

public class Cajero extends Personal {

    private String turno;

    public Cajero(int id, String nombre, String apellido, long dni,
                  LocalDate fechaNacimiento, LocalDate fechaIngreso,
                  String turno) throws Exception {
        super(id, nombre, apellido, dni, fechaNacimiento, fechaIngreso);
        this.setTurno(turno); // FIX: Validación en setter (Regla 11)
    }

    @Override
    public double calcularSueldo(double sueldoBase) {
        return sueldoBase + calcularAntiguedad() * VALOR_ANIO_ANTIGUEDAD;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) throws Exception {
        if (turno == null ||
                (!turno.equalsIgnoreCase("mañana") && !turno.equalsIgnoreCase("noche"))) {
            throw new Exception("Error: el turno debe ser mañana o noche"); // FIX: Validación (Regla 11)
        }

        this.turno = turno;
    }

    // FIX: Sobrecarga de equals (Regla 13)
    @Override
    public boolean equals(Object obj) {
        boolean sonIguales = false;
        if (obj != null && obj instanceof Cajero) {
            sonIguales = this.equals((Cajero) obj);
        }
        return sonIguales;
    }

    public boolean equals(Cajero otro) {
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
        return "Cajero [" + super.toString() +
                ", turno=" + turno +
                "]";
    }
}