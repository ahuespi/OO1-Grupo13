/**
 * Clase intermedia que representa la relación entre un `Plato` y la cantidad
 * pedida en un `Pedido`.
 */
public class ItemPlatoPedido {
    private Plato plato;
    private int cantidad;

    public ItemPlatoPedido(Plato plato, int cantidad) throws Exception {
        this.setPlato(plato); // FIX: Validación de dato en setter (Regla 11)
        this.setCantidad(cantidad);
    }

    public Plato getPlato() {
        return plato;
    }

    public void setPlato(Plato plato) throws Exception {
        if (plato == null) throw new Exception("Error: El plato no puede ser nulo"); // FIX: Validación (Regla 11)
        this.plato = plato;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) throws Exception {
        if (cantidad <= 0) throw new Exception("Error: La cantidad debe ser mayor a 0"); // FIX: Validación (Regla 11)
        this.cantidad = cantidad;
    }

    /**
     * Retorna el subtotal de venta: cantidad * precio del plato.
     */
    public double subtotalVenta() {
        double subtotal = 0.0;
        if (plato != null) {
            subtotal = cantidad * plato.getPrecioVenta(); // FIX: Único retorno (Regla 7)
        }
        return subtotal;
    }

    /**
     * Retorna el subtotal de costo: cantidad * costo del plato.
     */
    public double subtotalCosto() {
        double subtotal = 0.0;
        if (plato != null) {
            subtotal = cantidad * plato.getCostoProduccion(); // FIX: Único retorno (Regla 7)
        }
        return subtotal;
    }

    // FIX: Redefinición y sobrecarga de equals (Regla 13)
    @Override
    public boolean equals(Object obj) {
        boolean sonIguales = false;
        if (obj != null && obj instanceof ItemPlatoPedido) {
            sonIguales = this.equals((ItemPlatoPedido) obj);
        }
        return sonIguales;
    }

    public boolean equals(ItemPlatoPedido otro) {
        boolean sonIguales = false;
        if (otro != null) {
            if (this.plato != null && this.plato.equals(otro.getPlato())) {
                sonIguales = true;
            }
        }
        return sonIguales;
    }

    // FIX: Polimorfismo con toString (Regla 12)
    @Override
    public String toString() {
        return "ItemPlatoPedido(" +
                "plato=" + plato +
                ", cantidad=" + cantidad +
                ')';
    }
}
