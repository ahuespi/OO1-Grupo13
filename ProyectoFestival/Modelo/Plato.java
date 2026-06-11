/**
 * Representa un plato del catálogo del festival.
 * Buenas prácticas: atributos privados, constructores y getters/setters.
 */
public class Plato {
    private int id;
    private String nombre;
    private double precioVenta;
    private double costoProduccion;

    public Plato() {
    }

    public Plato(int id, String nombre, double precioVenta, double costoProduccion) {
        this.id = id;
        this.nombre = nombre;
        this.precioVenta = precioVenta;
        this.costoProduccion = costoProduccion;
    }

    public int getIdPlato() {
        return id;
    }

    public void setIdPlato(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public double getCostoProduccion() {
        return costoProduccion;
    }

    public void setCostoProduccion(double costoProduccion) {
        this.costoProduccion = costoProduccion;
    }

    @Override
    public boolean equals(Object obj) {
        boolean sonIguales = false;

        if (obj != null && obj instanceof Plato) {
            Plato otro = (Plato) obj;
            if (this.nombre != null && this.nombre.equals(otro.getNombre())) {
                sonIguales = true;
            }
        }

        return sonIguales;
    }

    @Override
    public String toString() {
        return "Plato(" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precioVenta=" + precioVenta +
                ", costoProduccion=" + costoProduccion +
                ')';
    }
}