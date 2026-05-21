/**
 * Representa una unidad móvil de venta (FoodTruck) que extiende `UnidadDeVenta`.
 */
public class FoodTruck extends UnidadDeVenta {
    private String licencia;
    private double capacidadGas;

    public FoodTruck(int id, int codigo, String nombreComercial, Personal persona,
                     int superficieMetroCuadrado, double sueldoBase,
                     String licencia, double capacidadGas) {
        super();
        setId(id);
        setCodigo(codigo);
        setNombreComercial(nombreComercial);
        setPersona(persona);
        setSuperficieMetroCuadrado(superficieMetroCuadrado);
        setSueldoBase(sueldoBase);
        this.licencia = licencia;
        this.capacidadGas = capacidadGas;
    }

    public String getLicencia() {
        return licencia;
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }

    public double getCapacidadGas() {
        return capacidadGas;
    }

    public void setCapacidadGas(double capacidadGas) {
        this.capacidadGas = capacidadGas;
    }
}
