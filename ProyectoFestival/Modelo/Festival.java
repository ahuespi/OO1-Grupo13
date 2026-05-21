import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Festival {
    private static int contadorId = 1;

    private int id;
    private String nombre;
    private String temporada;
    private Date fechaInicio;
    private Date fechaFin;
    private double costoSuperficie;
    private double costoMontaje;
    private double plusElectricidad;
    private double sueldoBase;
    private List<UnidadDeVenta> unidades;

    public Festival() {
        this.id = contadorId++;
        this.unidades = new ArrayList<>();
    }

    public Festival(String nombre, String temporada, Date fechaInicio, Date fechaFin,
                    double costoSuperficie, double costoMontaje, double plusElectricidad,
                    double sueldoBase) {
        this.id = contadorId++;
        this.nombre = nombre;
        this.temporada = temporada;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.costoSuperficie = costoSuperficie;
        this.costoMontaje = costoMontaje;
        this.plusElectricidad = plusElectricidad;
        this.sueldoBase = sueldoBase;
        this.unidades = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTemporada() {
        return temporada;
    }

    public void setTemporada(String temporada) {
        this.temporada = temporada;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public double getCostoSuperficie() {
        return costoSuperficie;
    }

    public void setCostoSuperficie(double costoSuperficie) {
        this.costoSuperficie = costoSuperficie;
    }

    public double getCostoMontaje() {
        return costoMontaje;
    }

    public void setCostoMontaje(double costoMontaje) {
        this.costoMontaje = costoMontaje;
    }

    public double getPlusElectricidad() {
        return plusElectricidad;
    }

    public void setPlusElectricidad(double plusElectricidad) {
        this.plusElectricidad = plusElectricidad;
    }

    public double getSueldoBase() {
        return sueldoBase;
    }

    public void setSueldoBase(double sueldoBase) {
        this.sueldoBase = sueldoBase;
    }

    public List<UnidadDeVenta> getUnidades() {
        return unidades;
    }

    public void setUnidades(List<UnidadDeVenta> unidades) {
        this.unidades = unidades;
    }

    public void agregarUnidad(UnidadDeVenta u) {
        this.unidades.add(u);
    }

    public void eliminarUnidad(UnidadDeVenta u) {
        this.unidades.remove(u);
    }

    @Override
    public String toString() {
        return "Festival(" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", temporada='" + temporada + '\'' +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", costoSuperficie=" + costoSuperficie +
                ", costoMontaje=" + costoMontaje +
                ", plusElectricidad=" + plusElectricidad +
                ", sueldoBase=" + sueldoBase +
                ", unidades=" + unidades +
                ')';
    }
}
