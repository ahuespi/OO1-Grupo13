import java.util.ArrayList;
import java.util.List;

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

    protected UnidadDeVenta(String codigo) {
        this();
        this.codigo = codigo;
    }

    public UnidadDeVenta(int id, String codigo, String nombreComercial, Personal responsable,
                         int superficieMetroCuadrado) throws Exception {
        this.id = id;
        this.setCodigo(codigo);
        this.nombreComercial = nombreComercial;
        this.responsable = responsable;
        this.superficieMetroCuadrado = superficieMetroCuadrado;
        this.lstPersonal = new ArrayList<Personal>();
        this.lstPlatos = new ArrayList<Plato>();
    }

    public abstract double calcularCanon();

    public boolean agregarPersonal(Personal personal) {
        return lstPersonal.add(personal);
    }

    public boolean agregarPlato(Plato plato) {
        if (!lstPlatos.contains(plato)) {
            plato.setIdPlato(generarIdPlato());
            return lstPlatos.add(plato);
        }
        return false;
    }

    private int generarIdPlato() {
        int id = 1;
        if (!lstPlatos.isEmpty()) {
            id = lstPlatos.get(lstPlatos.size() - 1).getIdPlato() + 1;
        }
        return id;
    }

    public Personal traerPersonal(long dni) {
        int i = 0;
        Personal encontrado = null;
        while (i < lstPersonal.size() && encontrado == null) {
            if (lstPersonal.get(i).getDni() == dni) {
                encontrado = lstPersonal.get(i);
            }
            i++;
        }
        return encontrado;
    }

    public Plato traerPlato(String nombre) {
        int i = 0;
        Plato encontrado = null;
        Plato dummy = new Plato();
        dummy.setNombre(nombre);

        while (i < lstPlatos.size() && encontrado == null) {
            if (lstPlatos.get(i).equals(dummy)) {
                encontrado = lstPlatos.get(i);
            }
            i++;
        }
        return encontrado;
    }

    public int getIdUnidad() {
        return id;
    }

    public void setIdUnidad(int id) {
        this.id = id;
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
        boolean sonIguales = false;

        if (obj != null && obj instanceof UnidadDeVenta) {
            UnidadDeVenta otra = (UnidadDeVenta) obj;
            if (this.codigo != null && this.codigo.equals(otra.getCodigo())) {
                sonIguales = true;
            }
        }

        return sonIguales;
    }
}