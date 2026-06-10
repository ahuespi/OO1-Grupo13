import java.util.Objects;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plato plato = (Plato) o;
        return Objects.equals(nombre, plato.nombre);
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