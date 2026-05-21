import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UnidadDeVenta {
    private int id;
    private int codigo;
    private String nombreComercial;
    private Personal persona;
    private int superficieMetroCuadrado;
    private double sueldoBase;
    private List<Personal> lstPersonal;
    private List<Plato> lstPlatos;

    public UnidadDeVenta() {
        this.lstPersonal = new ArrayList<>();
        this.lstPlatos = new ArrayList<>();
    }

    public UnidadDeVenta(int id, int codigo, String nombreComercial, Personal persona,
                         int superficieMetroCuadrado, double sueldoBase,
                         List<Personal> lstPersonal, List<Plato> lstPlatos) {
        this.id = id;
        this.codigo = codigo;
        this.nombreComercial = nombreComercial;
        this.persona = persona;
        this.superficieMetroCuadrado = superficieMetroCuadrado;
        this.sueldoBase = sueldoBase;
        this.lstPersonal = lstPersonal != null ? new ArrayList<>(lstPersonal) : new ArrayList<>();
        this.lstPlatos = lstPlatos != null ? new ArrayList<>(lstPlatos) : new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public Personal getPersona() {
        return persona;
    }

    public void setPersona(Personal persona) {
        this.persona = persona;
    }

    public int getSuperficieMetroCuadrado() {
        return superficieMetroCuadrado;
    }

    public void setSuperficieMetroCuadrado(int superficieMetroCuadrado) {
        this.superficieMetroCuadrado = superficieMetroCuadrado;
    }

    public double getSueldoBase() {
        return sueldoBase;
    }

    public void setSueldoBase(double sueldoBase) {
        this.sueldoBase = sueldoBase;
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
        return "unidadDeVenta(" +
                "id=" + id +
                ", codigo=" + codigo +
                ", nombreComercial='" + nombreComercial + '\'' +
                ", persona=" + persona +
                ", superficieMetroCuadrado=" + superficieMetroCuadrado +
                ", sueldoBase=" + sueldoBase +
                ", lstPersonal=" + lstPersonal +
                ", lstPlatos=" + lstPlatos +
                ')';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UnidadDeVenta)) return false;
        UnidadDeVenta that = (UnidadDeVenta) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
