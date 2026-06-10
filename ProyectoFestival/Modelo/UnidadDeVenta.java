import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class UnidadDeVenta {

    private int id;
    private String codigo;
    private String nombreComercial;
    private Personal responsable;
    private int superficieMetroCuadrado;

    private List<Personal> lstPersonal;
    private List<Plato> lstPlatos;

    public UnidadDeVenta() {
        this.lstPersonal = new ArrayList<Personal>();
        this.lstPlatos = new ArrayList<Plato>();
    }

    public UnidadDeVenta(int id, String codigo, String nombreComercial, Personal responsable,
                         int superficieMetroCuadrado) throws Exception {
        this();
        this.id = id;
        this.setCodigo(codigo);
        this.nombreComercial = nombreComercial;
        this.responsable = responsable;
        this.superficieMetroCuadrado = superficieMetroCuadrado;
    }

    public abstract double calcularCanon();

    public boolean agregarPersonal(Personal personal) {
        return lstPersonal.add(personal);
    }

    public boolean agregarPlato(Plato plato) {
        if (!lstPlatos.contains(plato)) {
            return lstPlatos.add(plato);
        }
        return false;
    }

    public Personal traerPersonal(long dni) {
        Personal encontrado = null;
        int i = 0;

        while (i < lstPersonal.size() && encontrado == null) {
            if (lstPersonal.get(i).getDni() == dni) {
                encontrado = lstPersonal.get(i);
            }
            i++;
        }

        return encontrado;
    }

    public Plato traerPlato(String nombre) {
        Plato encontrado = null;
        int i = 0;

        while (i < lstPlatos.size() && encontrado == null) {
            if (lstPlatos.get(i).getNombre().equalsIgnoreCase(nombre)) {
                encontrado = lstPlatos.get(i);
            }
            i++;
        }

        return encontrado;
    }

    public int getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) throws Exception {
        if (codigo == null || codigo.length() != 10) {
            throw new Exception("Error: el código de la unidad debe tener exactamente 10 caracteres");
        }
        this.codigo = codigo;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public Personal getResponsable() {
        return responsable;
    }

    public void setResponsable(Personal responsable) {
        this.responsable = responsable;
    }

    public int getSuperficieMetroCuadrado() {
        return superficieMetroCuadrado;
    }

    public void setSuperficieMetroCuadrado(int superficieMetroCuadrado) {
        this.superficieMetroCuadrado = superficieMetroCuadrado;
    }

    public List<Personal> getLstPersonal() {
        return lstPersonal;
    }

    public void setLstPersonal(List<Personal> lstPersonal) {
        this.lstPersonal = lstPersonal;
    }

    public List<Plato> getLstPlatos() {
        return lstPlatos;
    }

    public void setLstPlatos(List<Plato> lstPlatos) {
        this.lstPlatos = lstPlatos;
    }

    @Override
    public String toString() {
        return "UnidadDeVenta [id=" + id +
                ", codigo=" + codigo +
                ", nombreComercial=" + nombreComercial +
                ", responsable=" + responsable +
                ", superficieMetroCuadrado=" + superficieMetroCuadrado +
                "]";
    }

    @Override
    public boolean equals(Object obj) {
        boolean resultado = false;

        if (obj != null && obj instanceof UnidadDeVenta) {
            UnidadDeVenta otraUnidad = (UnidadDeVenta) obj;
            resultado = this.codigo.equals(otraUnidad.getCodigo());
        }

        return resultado;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
}