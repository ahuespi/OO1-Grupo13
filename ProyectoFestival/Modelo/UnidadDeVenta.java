import java.time.LocalDate;
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
        this.lstPersonal = new ArrayList<>(); // FIX: ArrayList inicializado en constructor (Regla 3)
        this.lstPlatos = new ArrayList<>();
    }

    protected UnidadDeVenta(String codigo) throws Exception {
        this();
        this.setCodigo(codigo);
    }

    public UnidadDeVenta(int id, String codigo, String nombreComercial, Personal responsable,
                         int superficieMetroCuadrado) throws Exception {
        this();
        this.setIdUnidad(id);
        this.setCodigo(codigo); // FIX: Validación en setter (Regla 11)
        this.setNombreComercial(nombreComercial);
        this.setResponsable(responsable);
        this.setSuperficieMetroCuadrado(superficieMetroCuadrado);
    }

    public abstract double calcularCanon(Festival festival);

    public boolean agregarPersonal(Personal personal) {
        return lstPersonal.add(personal); // FIX: Único retorno (Regla 7)
    }

    public boolean agregarPlato(Plato plato) throws Exception {
        // FIX: La validación de elemento repetido se eliminó de aquí y pasó a Sistema (Regla 10)
        plato.setIdPlato(lstPlatos.size() > 0 ? lstPlatos.get(lstPlatos.size() - 1).getIdPlato() + 1 : 1); // FIX: Cálculo de ID (Regla 9)
        return lstPlatos.add(plato); // FIX: Único retorno (Regla 7)
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
        return encontrado; // FIX: Doble corte de control y único retorno (Regla 5 y 7)
    }

    public Plato traerPlato(String nombre) {
        int i = 0;
        Plato encontrado = null;
        Plato dummy = new Plato();
        try { dummy.setNombre(nombre); } catch(Exception e) {} // Dummy para equals

        while (i < lstPlatos.size() && encontrado == null) {
            if (lstPlatos.get(i).equals(dummy)) {
                encontrado = lstPlatos.get(i);
            }
            i++;
        }
        return encontrado; // FIX: Doble corte de control y único retorno (Regla 5 y 7)
    }

    public int getIdUnidad() {
        return id;
    }

    public void setIdUnidad(int id) throws Exception {
        if (id < 0) throw new Exception("Error: El ID no puede ser negativo"); // FIX: Validación (Regla 11)
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

    public void setNombreComercial(String nombreComercial) throws Exception {
        if (nombreComercial == null || nombreComercial.trim().isEmpty()) throw new Exception("Error: El nombre comercial no puede estar vacío"); // FIX: Validación (Regla 11)
        this.nombreComercial = nombreComercial;
    }

    public Personal getResponsable() {
        return responsable;
    }

    public void setResponsable(Personal responsable) throws Exception {
        if (responsable == null) throw new Exception("Error: El responsable no puede ser nulo"); // FIX: Validación (Regla 11)
        this.responsable = responsable;
    }

    public int getSuperficieMetroCuadrado() {
        return superficieMetroCuadrado;
    }

    public void setSuperficieMetroCuadrado(int superficieMetroCuadrado) throws Exception {
        if (superficieMetroCuadrado < 0) throw new Exception("Error: La superficie no puede ser negativa"); // FIX: Validación (Regla 11)
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

    public double calcularRentabilidadNeta(List<Pedido> pedidos) { 
        double totalVentas = 0;
        double totalCostos = 0;
        double totalSueldos = 0;
        double totalCanon = 0;

        List<Festival> festivalesInvolucrados = new ArrayList<>();

        for (Pedido p : pedidos) {
            if (p.getUnidad().equals(this)) {              
                for (ItemPlatoPedido item : p.getItems()) {
                    totalVentas += item.subtotalVenta();
                    totalCostos += item.subtotalCosto();
                }
                if (!festivalesInvolucrados.contains(p.getFestival())) {
                    festivalesInvolucrados.add(p.getFestival());
                }
            }
        }
        
        for (Personal p : lstPersonal) {
            totalSueldos += p.calcularSueldo();
        }
        
        for (Festival f : festivalesInvolucrados) {
            totalCanon += this.calcularCanon(f);
        }
        
        return totalVentas - totalCostos - totalSueldos - totalCanon; // FIX: Único retorno (Regla 7)
    }

    public double calcularRentabilidadNeta(List<Pedido> pedidos, LocalDate desde, LocalDate hasta) { 
        double totalVentas = 0;
        double totalCostos = 0;
        double totalSueldos = 0;
        double totalCanon = 0;

        List<Festival> festivalesInvolucrados = new ArrayList<>();

        for (Pedido p : pedidos) {
            if (p.getUnidad().equals(this)) {              
                LocalDate fecha = p.getFecha();
                if ((fecha.isEqual(desde) || fecha.isAfter(desde)) && (fecha.isEqual(hasta) || fecha.isBefore(hasta))) {
                    for (ItemPlatoPedido item : p.getItems()) {
                        totalVentas += item.subtotalVenta();
                        totalCostos += item.subtotalCosto();
                    }
                    if (!festivalesInvolucrados.contains(p.getFestival())) {
                        festivalesInvolucrados.add(p.getFestival());
                    }
                }
            }
        }
        
        for (Personal p : lstPersonal) {
            totalSueldos += p.calcularSueldo();
        }
        
        for (Festival f : festivalesInvolucrados) {
            totalCanon += this.calcularCanon(f);
        }
        
        return totalVentas - totalCostos - totalSueldos - totalCanon; // FIX: Único retorno (Regla 7)
    }

    public double calcularRecaudacion(List<Pedido> pedidos) {
        double total = 0.0;
        for (Pedido p : pedidos) {
            if (p.getUnidad().equals(this)) {
                total += p.calcularMontoTotal();
            }
        }
        return total; // FIX: Único retorno (Regla 7)
    }

    public double calcularRecaudacion(List<Pedido> pedidos, Festival festival) {
        double total = 0.0;
        for (Pedido p : pedidos) {
            if (p.getUnidad().equals(this) && p.getFestival().equals(festival)) {
                total += p.calcularMontoTotal();
            }
        }
        return total; // FIX: Único retorno (Regla 7)
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

    // FIX: Sobrecarga de equals (Regla 13)
    @Override
    public boolean equals(Object obj) {
        boolean sonIguales = false;
        if (obj != null && obj instanceof UnidadDeVenta) {
            sonIguales = this.equals((UnidadDeVenta) obj);
        }
        return sonIguales;
    }

    public boolean equals(UnidadDeVenta otra) {
        boolean sonIguales = false;
        if (otra != null) {
            if (this.codigo != null && this.codigo.equals(otra.getCodigo())) {
                sonIguales = true;
            }
        }
        return sonIguales;
    }
}