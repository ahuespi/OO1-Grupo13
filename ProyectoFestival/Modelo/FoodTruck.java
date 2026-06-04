public class FoodTruck extends UnidadDeVenta {

    private String patente;
    private boolean requiereConexionElectrica;

    public FoodTruck() {
        super();
    }

    public FoodTruck(int id, String codigo, String nombreComercial, Personal responsable,
                     int superficieMetroCuadrado, String patente,
                     boolean requiereConexionElectrica) throws Exception {
        super(id, codigo, nombreComercial, responsable, superficieMetroCuadrado);
        this.patente = patente;
        this.requiereConexionElectrica = requiereConexionElectrica;
    }

    @Override
    public double calcularCanon() {
        double canon = getSuperficieMetroCuadrado() * 500;

        if (requiereConexionElectrica) {
            canon += 2000;
        }

        return canon;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public boolean isRequiereConexionElectrica() {
        return requiereConexionElectrica;
    }

    public void setRequiereConexionElectrica(boolean requiereConexionElectrica) {
        this.requiereConexionElectrica = requiereConexionElectrica;
    }

    @Override
    public String toString() {
        return "FoodTruck [" + super.toString() +
                ", patente=" + patente +
                ", requiereConexionElectrica=" + requiereConexionElectrica +
                "]";
    }
}