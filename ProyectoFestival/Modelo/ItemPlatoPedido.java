/**
 * Clase intermedia que representa la relación entre un `Plato` y la cantidad
 * pedida en un `Pedido`.
 */
public class ItemPlatoPedido {
    private Plato plato;
    private int cantidad;

    public ItemPlatoPedido() {
    }

    public ItemPlatoPedido(Plato plato, int cantidad) {
        this.plato = plato;
        this.cantidad = cantidad;
    }

    public Plato getPlato() {
        return plato;
    }

    public void setPlato(Plato plato) {
        this.plato = plato;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Retorna el subtotal de venta: cantidad * precio del plato.
     */
    public double subtotalVenta() {
        if (plato == null) return 0.0;
        return cantidad * plato.getPrecioVenta();
    }

    /**
     * Retorna el subtotal de costo: cantidad * costo del plato.
     */
    public double subtotalCosto() {
        if (plato == null) return 0.0;
        return cantidad * plato.getCostoProduccion();
    }
}
