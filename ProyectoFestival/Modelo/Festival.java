import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Festival {
    private int id;
    private String nombre;
    private String temporada;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private double costoSuperficie;
    private double costoMontaje;
    private double plusElectricidad;
    private double sueldoBase;
    private double plusAntiguedad;
    private double precio;
    private double costo;
    private List<UnidadDeVenta> unidades;

    public Festival() {
        this.precio = 0.0;
        this.costo = 0.0;
        this.unidades = new ArrayList<>(); // FIX: Constructor vacio con ArrayList (Regla 3)
    }

    public Festival(int id, String nombre, String temporada, LocalDate fechaInicio, LocalDate fechaFin,
                    double costoSuperficie, double costoMontaje, double plusElectricidad,
                    double sueldoBase, double plusAntiguedad) throws Exception {
        this();
        this.setIdFestival(id);
        this.setNombre(nombre); // FIX: Validación (Regla 11)
        this.setTemporada(temporada);
        this.setFechaInicio(fechaInicio);
        this.setFechaFin(fechaFin);
        this.setCostoSuperficie(costoSuperficie);
        this.setCostoMontaje(costoMontaje);
        this.setPlusElectricidad(plusElectricidad);
        this.setSueldoBase(sueldoBase);
        this.setPlusAntiguedad(plusAntiguedad);
    }

    public int getIdFestival() {
        return id;
    }

    public void setIdFestival(int id) throws Exception {
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

    public String getTemporada() {
        return temporada;
    }

    public void setTemporada(String temporada) throws Exception {
        if (temporada == null || temporada.trim().isEmpty()) throw new Exception("Error: La temporada no puede estar vacía"); // FIX: Validación (Regla 11)
        this.temporada = temporada;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) throws Exception {
        if (fechaInicio == null) throw new Exception("Error: La fecha de inicio no puede ser nula"); // FIX: Validación (Regla 11)
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) throws Exception {
        if (fechaFin == null) throw new Exception("Error: La fecha de fin no puede ser nula"); // FIX: Validación (Regla 11)
        this.fechaFin = fechaFin;
    }

    public double getCostoSuperficie() {
        return costoSuperficie;
    }

    public void setCostoSuperficie(double costoSuperficie) throws Exception {
        if (costoSuperficie < 0) throw new Exception("Error: El costo no puede ser negativo"); // FIX: Validación (Regla 11)
        this.costoSuperficie = costoSuperficie;
    }

    public double getCostoMontaje() {
        return costoMontaje;
    }

    public void setCostoMontaje(double costoMontaje) throws Exception {
        if (costoMontaje < 0) throw new Exception("Error: El costo no puede ser negativo"); // FIX: Validación (Regla 11)
        this.costoMontaje = costoMontaje;
    }

    public double getPlusElectricidad() {
        return plusElectricidad;
    }

    public void setPlusElectricidad(double plusElectricidad) throws Exception {
        if (plusElectricidad < 0) throw new Exception("Error: El plus no puede ser negativo"); // FIX: Validación (Regla 11)
        this.plusElectricidad = plusElectricidad;
    }

    public double getSueldoBase() {
        return sueldoBase;
    }

    public void setSueldoBase(double sueldoBase) throws Exception {
        if (sueldoBase < 0) throw new Exception("Error: El sueldo no puede ser negativo"); // FIX: Validación (Regla 11)
        this.sueldoBase = sueldoBase;
    }

    public double getPlusAntiguedad() {
        return plusAntiguedad;
    }

    public void setPlusAntiguedad(double plusAntiguedad) throws Exception {
        if (plusAntiguedad < 0) throw new Exception("Error: El plus de antigüedad no puede ser negativo");
        this.plusAntiguedad = plusAntiguedad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) throws Exception {
        if (precio < 0) throw new Exception("Error: El precio no puede ser negativo"); // FIX: Validación (Regla 11)
        this.precio = precio;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) throws Exception {
        if (costo < 0) throw new Exception("Error: El costo no puede ser negativo");
        this.costo = costo;
    }

    public List<UnidadDeVenta> getUnidades() {
        return unidades;
    }

    public void setUnidades(List<UnidadDeVenta> unidades) {
        this.unidades = unidades;
    }

    public void agregarUnidad(UnidadDeVenta u) {
        this.unidades.add(u);
    }

    public void eliminarUnidad(UnidadDeVenta u) {
        this.unidades.remove(u);
    }

    // FIX: Sobrecarga de equals (Regla 13)
    @Override
    public boolean equals(Object obj) {
        boolean sonIguales = false;
        if (obj != null && obj instanceof Festival) {
            sonIguales = this.equals((Festival) obj);
        }
        return sonIguales;
    }

    public boolean equals(Festival otro) {
        boolean sonIguales = false;
        if (otro != null) {
            if (this.nombre != null && otro.getNombre() != null && this.nombre.equalsIgnoreCase(otro.getNombre())) {
                sonIguales = true;
            }
        }
        return sonIguales;
    }

    @Override
    public String toString() {
        return "Festival(" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", temporada='" + temporada + '\'' +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", costoSuperficie=" + costoSuperficie +
                ", costoMontaje=" + costoMontaje +
                ", plusElectricidad=" + plusElectricidad +
                ", sueldoBase=" + sueldoBase +
                ", plusAntiguedad=" + plusAntiguedad +
                ", precio=" + precio +
                ", costo=" + costo +
                ", unidades=" + unidades +
                ')';
    }
}