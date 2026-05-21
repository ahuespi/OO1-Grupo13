import java.time.LocalDate;

/**
 * Representa un cajero (personal) del festival.
 */
public class Cajero extends Personal {
    private int cajaId;
    private String turno;

    public Cajero(int id, String nombre, String apellido, long dni,
                  LocalDate fechaNacimiento, LocalDate fechaIngreso,
                  int cajaId, String turno) {
        super(id, nombre, apellido, dni, fechaNacimiento, fechaIngreso);
        this.cajaId = cajaId;
        this.turno = turno;
    }

    public int getCajaId() {
        return cajaId;
    }

    public void setCajaId(int cajaId) {
        this.cajaId = cajaId;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }
}
