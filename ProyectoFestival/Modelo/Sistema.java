import java.util.ArrayList;
import java.util.List;

/**
 * Representa el sistema central que coordina festivales y unidades de venta.
 */
public class Sistema {
    private String nombre;
    private List<Festival> festivales;
    private List<UnidadDeVenta> unidades;

    public Sistema(String nombre) {
        this.nombre = nombre;
        this.festivales = new ArrayList<>();
        this.unidades = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Festival> getFestivales() {
        return festivales;
    }

    public void setFestivales(List<Festival> festivales) {
        this.festivales = festivales;
    }

    public List<UnidadDeVenta> getUnidades() {
        return unidades;
    }

    public void setUnidades(List<UnidadDeVenta> unidades) {
        this.unidades = unidades;
    }
}
