import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa un pedido realizado en el festival por una unidad de venta.
 * La lista de items se inicializa internamente para garantizar encapsulamiento
 * y evitar mutaciones externas inesperadas.
 */
public class Pedido {
    private int id;
    private LocalDate fecha;
    private Festival festival;
    private UnidadDeVenta unidad;
    private List<ItemPlatoPedido> items;

    /**
     * Constructor obligatorio: la lista `items` se inicializa aquí con
     * `new ArrayList<>()` para asegurar que siempre exista y evitar NPEs.
     */
    public Pedido(int id, LocalDate fecha, Festival festival, UnidadDeVenta unidad) {
        this.id = id;
        this.fecha = fecha;
        this.festival = festival;
        this.unidad = unidad;
        this.items = new ArrayList<>();
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

    public Festival getFestival() {
        return festival;
    }

    public void setFestival(Festival festival) {
        this.festival = festival;
    }

    public UnidadDeVenta getUnidad() {
        return unidad;
    }

    public void setUnidad(UnidadDeVenta unidad) {
        this.unidad = unidad;
    }

    public List<ItemPlatoPedido> getItems() {
        return items;
    }

    public void setItems(List<ItemPlatoPedido> items) {
        this.items = items;
    }

    public boolean agregarItem(ItemPlatoPedido item) {
        return this.items.add(item);
    }

    public boolean eliminarItem(ItemPlatoPedido item) {
        return this.items.remove(item);
    }

    /**
     * Agrega un plato al pedido creando internamente el ItemPlatoPedido.
     * Mantener la creación del item dentro de la clase favorece la cohesión
     * y simplifica la lógica en capas superiores.
     */
    public void agregarPlato(Plato plato, int cantidad) {
        if (plato == null || cantidad <= 0) return;
        ItemPlatoPedido item = new ItemPlatoPedido(plato, cantidad);
        this.items.add(item);
    }

    /**
     * Recorre los items y suma los subtotales de venta.
     */
    public double calcularMontoTotal() {
        double total = 0.0;
        for (ItemPlatoPedido item : items) {
            total += item.subtotalVenta();
        }
        return total;
    }
}