public class PuestoDesarmable extends UnidadDeVenta {

    private int cantidadCarpas;
    private int tiempoMontajeMinutos;

    public PuestoDesarmable() {
        super();
    }

    public PuestoDesarmable(int id, String codigo, String nombreComercial, Personal responsable,
                            int superficieMetroCuadrado, int cantidadCarpas,
                            int tiempoMontajeMinutos) throws Exception {
        super(id, codigo, nombreComercial, responsable, superficieMetroCuadrado);
        this.cantidadCarpas = cantidadCarpas;
        this.tiempoMontajeMinutos = tiempoMontajeMinutos;
    }

    @Override
    public double calcularCanon() {
        return (getSuperficieMetroCuadrado() * 500) - (tiempoMontajeMinutos * 10);
    }

    public int getCantidadCarpas() {
        return cantidadCarpas;
    }

    public void setCantidadCarpas(int cantidadCarpas) {
        this.cantidadCarpas = cantidadCarpas;
    }

    public int getTiempoMontajeMinutos() {
        return tiempoMontajeMinutos;
    }

    public void setTiempoMontajeMinutos(int tiempoMontajeMinutos) {
        this.tiempoMontajeMinutos = tiempoMontajeMinutos;
    }

    @Override
    public String toString() {
        return "PuestoDesarmable [" + super.toString() +
                ", cantidadCarpas=" + cantidadCarpas +
                ", tiempoMontajeMinutos=" + tiempoMontajeMinutos +
                "]";
    }
}