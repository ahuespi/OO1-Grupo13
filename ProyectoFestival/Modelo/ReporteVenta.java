/**
 * Representa un reporte de ventas asociado a una unidad de venta.
 * No persistente.
 */
public class ReporteVenta {
    private UnidadDeVenta unidad;
    private double recaudacionTotal;

    public ReporteVenta(UnidadDeVenta unidad, double recaudacionTotal) throws Exception {
        this.setUnidad(unidad); // FIX: Validación (Regla 11)
        this.setRecaudacionTotal(recaudacionTotal);
    }

    public UnidadDeVenta getUnidad() {
        return unidad;
    }

    public void setUnidad(UnidadDeVenta unidad) throws Exception {
        if (unidad == null) throw new Exception("Error: La unidad no puede ser nula"); // FIX: Validación (Regla 11)
        this.unidad = unidad;
    }

    public double getRecaudacionTotal() {
        return recaudacionTotal;
    }

    public void setRecaudacionTotal(double recaudacionTotal) throws Exception {
        if (recaudacionTotal < 0) throw new Exception("Error: La recaudación no puede ser negativa"); // FIX: Validación (Regla 11)
        this.recaudacionTotal = recaudacionTotal;
    }

    // FIX: Sobrecarga de equals (Regla 13)
    @Override
    public boolean equals(Object obj) {
        boolean sonIguales = false;
        if (obj != null && obj instanceof ReporteVenta) {
            sonIguales = this.equals((ReporteVenta) obj);
        }
        return sonIguales;
    }

    public boolean equals(ReporteVenta otro) {
        boolean sonIguales = false;
        if (otro != null) {
            if (this.unidad != null && this.unidad.equals(otro.getUnidad())) {
                sonIguales = true;
            }
        }
        return sonIguales;
    }

    @Override
    public String toString() {
        return "ReporteVenta [" +
                "unidad=" + (unidad != null ? unidad.getNombreComercial() : "null") +
                ", recaudacionTotal=$" + recaudacionTotal +
                "]";
    }
}