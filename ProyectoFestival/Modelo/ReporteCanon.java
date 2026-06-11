import java.time.LocalDate;

/**
 * Representa un reporte del canon aplicado a una unidad de venta en una fecha.
 */
public class ReporteCanon {
    private int id;
    private LocalDate fecha;
    private UnidadDeVenta unidad;
    private double montoCanon;

    public ReporteCanon(int id, LocalDate fecha, UnidadDeVenta unidad, double montoCanon) throws Exception {
        this.setId(id);
        this.setFecha(fecha); // FIX: Validación en setter (Regla 11)
        this.setUnidad(unidad);
        this.setMontoCanon(montoCanon);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) throws Exception {
        if (id < 0) throw new Exception("Error: El ID no puede ser negativo"); // FIX: Validación (Regla 11)
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) throws Exception {
        if (fecha == null) throw new Exception("Error: La fecha no puede ser nula"); // FIX: Validación (Regla 11)
        this.fecha = fecha;
    }

    public UnidadDeVenta getUnidad() {
        return unidad;
    }

    public void setUnidad(UnidadDeVenta unidad) throws Exception {
        if (unidad == null) throw new Exception("Error: La unidad no puede ser nula"); // FIX: Validación (Regla 11)
        this.unidad = unidad;
    }

    public double getMontoCanon() {
        return montoCanon;
    }

    public void setMontoCanon(double montoCanon) throws Exception {
        if (montoCanon < 0) throw new Exception("Error: El monto de canon no puede ser negativo"); // FIX: Validación (Regla 11)
        this.montoCanon = montoCanon;
    }

    // FIX: Sobrecarga de equals (Regla 13)
    @Override
    public boolean equals(Object obj) {
        boolean sonIguales = false;
        if (obj != null && obj instanceof ReporteCanon) {
            sonIguales = this.equals((ReporteCanon) obj);
        }
        return sonIguales;
    }

    public boolean equals(ReporteCanon otro) {
        boolean sonIguales = false;
        if (otro != null) {
            if (this.id == otro.getId()) {
                sonIguales = true;
            }
        }
        return sonIguales;
    }

    @Override
    public String toString() {
        return "ReporteCanon [" +
                "id=" + id +
                ", fecha=" + fecha +
                ", unidad=" + (unidad != null ? unidad.getNombreComercial() : "null") +
                ", montoCanon=" + montoCanon +
                "]";
    }
}
