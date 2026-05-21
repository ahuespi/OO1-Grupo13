import java.time.LocalDate;

/**
 * Representa un reporte del canon aplicado a una unidad de venta en una fecha.
 */
public class ReporteCanon {
    private int id;
    private LocalDate fecha;
    private UnidadDeVenta unidad;
    private double montoCanon;

    public ReporteCanon(int id, LocalDate fecha, UnidadDeVenta unidad, double montoCanon) {
        this.id = id;
        this.fecha = fecha;
        this.unidad = unidad;
        this.montoCanon = montoCanon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public UnidadDeVenta getUnidad() {
        return unidad;
    }

    public void setUnidad(UnidadDeVenta unidad) {
        this.unidad = unidad;
    }

    public double getMontoCanon() {
        return montoCanon;
    }

    public void setMontoCanon(double montoCanon) {
        this.montoCanon = montoCanon;
    }
}
