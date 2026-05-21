import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa un reporte de ventas asociado a una fecha y lista de pedidos.
 */
public class ReporteVenta {
    private int id;
    private LocalDate fecha;
    private List<Pedido> pedidos;
    private double totalVentas;

    public ReporteVenta(int id, LocalDate fecha) {
        this.id = id;
        this.fecha = fecha;
        this.pedidos = new ArrayList<>();
        this.totalVentas = 0.0;
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

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public double getTotalVentas() {
        return totalVentas;
    }

    public void setTotalVentas(double totalVentas) {
        this.totalVentas = totalVentas;
    }
}
