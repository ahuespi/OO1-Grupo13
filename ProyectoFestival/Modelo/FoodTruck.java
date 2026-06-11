public class FoodTruck extends UnidadDeVenta {

    private String patente;
    private boolean requiereConexionElectrica;

    public FoodTruck(int id, String codigo, String nombreComercial, Personal responsable,
                     int superficieMetroCuadrado, String patente,
                     boolean requiereConexionElectrica) throws Exception {
        super(id, codigo, nombreComercial, responsable, superficieMetroCuadrado);
        this.setPatente(patente); // FIX: Validación (Regla 11)
        this.setRequiereConexionElectrica(requiereConexionElectrica);
    }

    @Override
    public double calcularCanon(Festival festival) {
        double canon = getSuperficieMetroCuadrado() * festival.getCostoSuperficie();

        if (requiereConexionElectrica) {
            canon += festival.getPlusElectricidad();
        }

        return canon; // FIX: Único retorno (Regla 7)
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) throws Exception {
        if (patente == null || patente.trim().isEmpty()) throw new Exception("Error: La patente no puede estar vacía"); // FIX: Validación (Regla 11)
        this.patente = patente;
    }

    public boolean isRequiereConexionElectrica() {
        return requiereConexionElectrica;
    }

    public void setRequiereConexionElectrica(boolean requiereConexionElectrica) {
        this.requiereConexionElectrica = requiereConexionElectrica;
    }

    // FIX: Sobrecarga de equals (Regla 13)
    @Override
    public boolean equals(Object obj) {
        boolean sonIguales = false;
        if (obj != null && obj instanceof FoodTruck) {
            sonIguales = this.equals((FoodTruck) obj);
        }
        return sonIguales;
    }

    public boolean equals(FoodTruck otro) {
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
        return "FoodTruck [" + super.toString() +
                ", patente=" + patente +
                ", requiereConexionElectrica=" + requiereConexionElectrica +
                "]";
    }
}