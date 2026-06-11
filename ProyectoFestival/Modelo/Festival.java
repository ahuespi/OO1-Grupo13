import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Festival {
    private int id;
    private String nombre;
    private String temporada;
    private Date fechaInicio;
    private Date fechaFin;
    private double costoSuperficie;
    private double costoMontaje;
    private double plusElectricidad;
    private double sueldoBase;
    private double precio;
    private List<UnidadDeVenta> unidades;

    public Festival() {
        this.precio = 0.0;
        this.unidades = new ArrayList<>();
    }

    public Festival(int id, String nombre, String temporada, Date fechaInicio, Date fechaFin,
                    double costoSuperficie, double costoMontaje, double plusElectricidad,
                    double sueldoBase) {
        this.id = id;
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

    public int getIdFestival() {
        return id;
    }

    public void setIdFestival(int id) {
        this.id = id;
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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
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
    public boolean equals(Object obj) {
        boolean sonIguales = false;

        if (obj != null && obj instanceof Festival) {
            Festival otro = (Festival) obj;
            if (this.nombre != null && otro.getNombre() != null && this.nombre.equalsIgnoreCase(otro.getNombre())) {
                sonIguales = true;
            }
        }

        return sonIguales;
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