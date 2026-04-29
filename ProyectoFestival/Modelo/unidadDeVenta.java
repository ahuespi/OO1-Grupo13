import java.util.List;
import java.util.Objects;

public class unidadDeVenta {
    private int id;
    private int codigo;
    private String nombreComercial;
    private Personal persona;
    private int superficieMetroCuadrado;
    private double sueldoBase;
    List<Personal> lstPersonal;
    List<Plato> lstPlatos;

    public unidadDeVenta() {
    }

    public unidadDeVenta(int id, int codigo, String nombreComercial, Personal persona,
                         int superficieMetroCuadrado, double sueldoBase,
                         List<Personal> lstPersonal, List<Plato> lstPlatos) {
        this.id = id;
        this.codigo = codigo;
        this.nombreComercial = nombreComercial;
        this.persona = persona;
        this.superficeMetroCuadrado = superficeMetroCuadrado;
        this.sueldoBase = sueldoBase;
        this.lstPersonal = new ArrayList<Personal>();
        this.lstPlatos = new ArrayList<Plato>();
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

    public void setSuperficieMetroCuadrado(int superficeMetroCuadrado) {
        this.superficieMetroCuadrado = superficeMetroCuadrado;
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

    
    //equals
    public boolean equals(unidadDeVenta uv){
        return uv.getId() == this.id;
    }
}
