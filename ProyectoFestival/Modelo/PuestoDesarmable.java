public class PuestoDesarmable extends UnidadDeVenta {

    private int cantidadCarpas;
    private int tiempoMontajeMinutos;

    public PuestoDesarmable(int id, String codigo, String nombreComercial, Personal responsable,
                            int superficieMetroCuadrado, int cantidadCarpas,
                            int tiempoMontajeMinutos) throws Exception {
        super(id, codigo, nombreComercial, responsable, superficieMetroCuadrado);
        this.setCantidadCarpas(cantidadCarpas); // FIX: Validación (Regla 11)
        this.setTiempoMontajeMinutos(tiempoMontajeMinutos);
    }

    @Override
    public double calcularCanon(Festival festival) {
        return (getSuperficieMetroCuadrado() * festival.getCostoSuperficie()) - (tiempoMontajeMinutos * festival.getCostoMontaje()); // FIX: Único retorno (Regla 7)
    }

    public int getCantidadCarpas() {
        return cantidadCarpas;
    }

    public void setCantidadCarpas(int cantidadCarpas) throws Exception {
        if (cantidadCarpas < 0) throw new Exception("Error: La cantidad de carpas no puede ser negativa"); // FIX: Validación (Regla 11)
        this.cantidadCarpas = cantidadCarpas;
    }

    public int getTiempoMontajeMinutos() {
        return tiempoMontajeMinutos;
    }

    public void setTiempoMontajeMinutos(int tiempoMontajeMinutos) throws Exception {
        if (tiempoMontajeMinutos < 0) throw new Exception("Error: El tiempo de montaje no puede ser negativo"); // FIX: Validación (Regla 11)
        this.tiempoMontajeMinutos = tiempoMontajeMinutos;
    }

    // FIX: Sobrecarga de equals (Regla 13)
    @Override
    public boolean equals(Object obj) {
        boolean sonIguales = false;
        if (obj != null && obj instanceof PuestoDesarmable) {
            sonIguales = this.equals((PuestoDesarmable) obj);
        }
        return sonIguales;
    }

    public boolean equals(PuestoDesarmable otro) {
        boolean sonIguales = false;
        if (otro != null) {
            if (this.getCodigo() != null && this.getCodigo().equals(otro.getCodigo())) {
                sonIguales = true;
            }
        }
        return sonIguales;
    }

    @Override
    public String toString() {
        return "PuestoDesarmable [" + super.toString() +
                ", cantidadCarpas=" + cantidadCarpas +
                ", tiempoMontajeMinutos=" + tiempoMontajeMinutos +
                "]";
    }
}