/**
 * Representa un puesto desarmable que extiende `UnidadDeVenta`.
 */
public class PuestoDesarmable extends UnidadDeVenta {
    private int tiempoArmadoMinutos;
    private boolean tieneParedes;

    public PuestoDesarmable(int id, int codigo, String nombreComercial, Personal persona,
                            int superficieMetroCuadrado, double sueldoBase,
                            int tiempoArmadoMinutos, boolean tieneParedes) {
        super();
        setId(id);
        setCodigo(codigo);
        setNombreComercial(nombreComercial);
        setPersona(persona);
        setSuperficieMetroCuadrado(superficieMetroCuadrado);
        setSueldoBase(sueldoBase);
        this.tiempoArmadoMinutos = tiempoArmadoMinutos;
        this.tieneParedes = tieneParedes;
    }

    public int getTiempoArmadoMinutos() {
        return tiempoArmadoMinutos;
    }

    public void setTiempoArmadoMinutos(int tiempoArmadoMinutos) {
        this.tiempoArmadoMinutos = tiempoArmadoMinutos;
    }

    public boolean isTieneParedes() {
        return tieneParedes;
    }

    public void setTieneParedes(boolean tieneParedes) {
        this.tieneParedes = tieneParedes;
    }
}
