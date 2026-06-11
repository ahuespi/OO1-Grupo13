/**
 * Representa un reporte de las unidades que más gastaron en canon en un festival.
 * No persistente.
 */
public class ReporteMayoresCanon {
    private String nombreComercial;
    private String codigo;
    private String tipoUnidad;
    private double canon;

    public ReporteMayoresCanon(String nombreComercial, String codigo, String tipoUnidad, double canon) throws Exception {
        this.setNombreComercial(nombreComercial); // FIX: Validación (Regla 11)
        this.setCodigo(codigo);
        this.setTipoUnidad(tipoUnidad);
        this.setCanon(canon);
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) throws Exception {
        if (nombreComercial == null || nombreComercial.trim().isEmpty()) throw new Exception("Error: El nombre comercial no puede estar vacío"); // FIX: Validación (Regla 11)
        this.nombreComercial = nombreComercial;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) throws Exception {
        if (codigo == null || codigo.trim().isEmpty()) throw new Exception("Error: El código no puede estar vacío"); // FIX: Validación (Regla 11)
        this.codigo = codigo;
    }

    public String getTipoUnidad() {
        return tipoUnidad;
    }

    public void setTipoUnidad(String tipoUnidad) throws Exception {
        if (tipoUnidad == null || tipoUnidad.trim().isEmpty()) throw new Exception("Error: El tipo de unidad no puede estar vacío"); // FIX: Validación (Regla 11)
        this.tipoUnidad = tipoUnidad;
    }

    public double getCanon() {
        return canon;
    }

    public void setCanon(double canon) throws Exception {
        if (canon < 0) throw new Exception("Error: El canon no puede ser negativo"); // FIX: Validación (Regla 11)
        this.canon = canon;
    }

    // FIX: Sobrecarga de equals (Regla 13)
    @Override
    public boolean equals(Object obj) {
        boolean sonIguales = false;
        if (obj != null && obj instanceof ReporteMayoresCanon) {
            sonIguales = this.equals((ReporteMayoresCanon) obj);
        }
        return sonIguales;
    }

    public boolean equals(ReporteMayoresCanon otro) {
        boolean sonIguales = false;
        if (otro != null) {
            if (this.codigo != null && this.codigo.equals(otro.getCodigo())) {
                sonIguales = true;
            }
        }
        return sonIguales;
    }

    @Override
    public String toString() {
        return "ReporteMayoresCanon [" +
                "nombreComercial=" + nombreComercial +
                ", codigo=" + codigo +
                ", tipoUnidad=" + tipoUnidad +
                ", canon=$" + canon +
                "]";
    }
}
