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

    public Plato(int id, String nombre, double precioVenta, double costoProduccion) throws Exception {
        this.setIdPlato(id); // FIX: Validación de regla intrínseca en setters (Regla 11)
        this.setNombre(nombre);
        this.setPrecioVenta(precioVenta);
        this.setCostoProduccion(costoProduccion);
    }

    public int getIdPlato() {
        return id;
    }

    public void setIdPlato(int id) throws Exception {
        if (id < 0) throw new Exception("Error: El ID no puede ser negativo"); // FIX: Validación (Regla 11)
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) throws Exception {
        if (nombre == null || nombre.trim().isEmpty()) throw new Exception("Error: El nombre no puede estar vacío"); // FIX: Validación (Regla 11)
        this.nombre = nombre;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) throws Exception {
        if (precioVenta < 0) throw new Exception("Error: El precio no puede ser negativo"); // FIX: Validación (Regla 11)
        this.precioVenta = precioVenta;
    }

    public double getCostoProduccion() {
        return costoProduccion;
    }

    public void setCostoProduccion(double costoProduccion) throws Exception {
        if (costoProduccion < 0) throw new Exception("Error: El costo no puede ser negativo"); // FIX: Validación (Regla 11)
        this.costoProduccion = costoProduccion;
    }

    // FIX: Redefinición y sobrecarga de equals (Regla 13)
    @Override
    public boolean equals(Object obj) {
        boolean sonIguales = false;
        if (obj != null && obj instanceof Plato) {
            sonIguales = this.equals((Plato) obj);
        }
        return sonIguales;
    }

    // FIX: Sobrecarga de equals (Regla 13)
    public boolean equals(Plato otro) {
        boolean sonIguales = false;
        if (otro != null) {
            if (this.nombre != null && this.nombre.equals(otro.getNombre())) {
                sonIguales = true;
            }
        }
        return sonIguales;
    }

    // FIX: Polimorfismo con toString (Regla 12)
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