import java.util.ArrayList;
import java.util.List;

/**
 * Representa un reporte de ventas asociado a una unidad de venta.
 * No persistente.
 */
public class ReporteVenta {
    private UnidadDeVenta unidad;
    private double recaudacionTotal;

    public ReporteVenta(UnidadDeVenta unidad, double recaudacionTotal) {
        this.unidad = unidad;
        this.recaudacionTotal = recaudacionTotal;
    }

    public UnidadDeVenta getUnidad() {
        return unidad;
    }

    public void setUnidad(UnidadDeVenta unidad) {
        this.unidad = unidad;
    }

    public double getRecaudacionTotal() {
        return recaudacionTotal;
    }

    public void setRecaudacionTotal(double recaudacionTotal) {
        this.recaudacionTotal = recaudacionTotal;
    }

    @Override
    public String toString() {
        return "ReporteVenta [" +
                "unidad=" + unidad.getNombreComercial() +
                ", recaudacionTotal=$" + recaudacionTotal +
                "]";
    }
}