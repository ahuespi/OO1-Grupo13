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
    public Pedido() {
        this.items = new ArrayList<>(); // FIX: Constructor vacio con ArrayList (Regla 3)
    }

    public Pedido(int id) throws Exception {
        this();
        this.setIdPedido(id);
    }

    public Pedido(int id, LocalDate fecha, Festival festival, UnidadDeVenta unidad) throws Exception {
        this();
        this.setIdPedido(id);
        this.setFecha(fecha); // FIX: Validación (Regla 11)
        this.setFestival(festival);
        this.setUnidad(unidad);
    }

    public int getIdPedido() {
        return id;
    }

    public void setIdPedido(int id) throws Exception {
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

    public Festival getFestival() {
        return festival;
    }

    public void setFestival(Festival festival) throws Exception {
        if (festival == null) throw new Exception("Error: El festival no puede ser nulo"); // FIX: Validación (Regla 11)
        this.festival = festival;
    }

    public UnidadDeVenta getUnidad() {
        return unidad;
    }

    public void setUnidad(UnidadDeVenta unidad) throws Exception {
        if (unidad == null) throw new Exception("Error: La unidad no puede ser nula"); // FIX: Validación (Regla 11)
        this.unidad = unidad;
    }

    public List<ItemPlatoPedido> getItems() {
        return items;
    }

    public void setItems(List<ItemPlatoPedido> items) {
        this.items = items;
    }

    public boolean agregarItem(ItemPlatoPedido item) throws Exception {
        // FIX: La lógica de duplicados se movió a Sistema (Regla 10). Aquí solo delegamos.
        return this.items.add(item); // FIX: Único retorno (Regla 7)
    }

    public boolean eliminarItem(ItemPlatoPedido item) {
        return this.items.remove(item); // FIX: Único retorno (Regla 7)
    }

    /**
     * Recorre los items y suma los subtotales de venta.
     */
    public double calcularMontoTotal() {
        double total = 0.0;
        for (ItemPlatoPedido item : items) {
            total += item.subtotalVenta();
        }
        return total; // FIX: Único retorno (Regla 7)
    }

    // FIX: Redefinición y sobrecarga de equals (Regla 13)
    @Override
    public boolean equals(Object obj) {
        boolean sonIguales = false;
        if (obj != null && obj instanceof Pedido) {
            sonIguales = this.equals((Pedido) obj);
        }
        return sonIguales;
    }

    public boolean equals(Pedido otro) {
        boolean sonIguales = false;
        if (otro != null) {
            if (this.id == otro.getIdPedido()) {
                sonIguales = true;
            }
        }
        return sonIguales;
    }

    @Override
    public String toString() {
        return "Pedido(" +
                "id=" + id +
                ", fecha=" + fecha +
                ", festival=" + (festival != null ? festival.getNombre() : "null") +
                ", unidad=" + (unidad != null ? unidad.getCodigo() : "null") +
                ", items.size()=" + items.size() +
                ')';
    }
}