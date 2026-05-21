import java.time.LocalDate;

/**
 * Representa un cocinero (personal) del festival.
 */
public class Cocinero extends Personal {
    private String especialidad;
    private int experienciaAnios;

    public Cocinero(int id, String nombre, String apellido, long dni,
                    LocalDate fechaNacimiento, LocalDate fechaIngreso,
                    String especialidad, int experienciaAnios) {
        super(id, nombre, apellido, dni, fechaNacimiento, fechaIngreso);
        this.especialidad = especialidad;
        this.experienciaAnios = experienciaAnios;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public int getExperienciaAnios() {
        return experienciaAnios;
    }

    public void setExperienciaAnios(int experienciaAnios) {
        this.experienciaAnios = experienciaAnios;
    }
}
