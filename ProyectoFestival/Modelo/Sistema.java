import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Sistema {

    private List<Festival> lstFestivales;
    private List<UnidadDeVenta> lstUnidades;
    private List<Personal> lstPersonal;
    private List<Pedido> lstPedidos;

    public Sistema() {
        this.lstFestivales = new ArrayList<Festival>();
        this.lstUnidades = new ArrayList<UnidadDeVenta>();
        this.lstPersonal = new ArrayList<Personal>();
        this.lstPedidos = new ArrayList<Pedido>();
    }

    // =========================================================
    // CASO DE USO 1: ALTAS
    // =========================================================

    public boolean agregarFestival(String nombre, String temporada, Date fechaInicio, Date fechaFin,
                                   double costoSuperficie, double costoMontaje,
                                   double plusElectricidad, double sueldoBase) throws Exception {

        if (traerFestival(nombre) != null) {
            throw new Exception("Error: ya existe un festival con ese nombre");
        }

        int id = generarIdFestival();

        return lstFestivales.add(new Festival(id, nombre, temporada, fechaInicio, fechaFin,
                costoSuperficie, costoMontaje, plusElectricidad, sueldoBase));
    }

    public boolean agregarFoodTruck(String codigo, String nombreComercial, Personal responsable,
                                    int superficieMetroCuadrado, String patente,
                                    boolean requiereConexionElectrica) throws Exception {

        if (traerUnidad(codigo) != null) {
            throw new Exception("Error: ya existe una unidad con ese código");
        }

        int id = generarIdUnidad();

        return lstUnidades.add(new FoodTruck(id, codigo, nombreComercial, responsable,
                superficieMetroCuadrado, patente, requiereConexionElectrica));
    }

    public boolean agregarPuestoDesarmable(String codigo, String nombreComercial, Personal responsable,
                                           int superficieMetroCuadrado, int cantidadCarpas,
                                           int tiempoMontajeMinutos) throws Exception {

        if (traerUnidad(codigo) != null) {
            throw new Exception("Error: ya existe una unidad con ese código");
        }

        int id = generarIdUnidad();

        return lstUnidades.add(new PuestoDesarmable(id, codigo, nombreComercial, responsable,
                superficieMetroCuadrado, cantidadCarpas, tiempoMontajeMinutos));
    }

    public boolean agregarCocinero(String nombre, String apellido, long dni,
                                   java.time.LocalDate fechaNacimiento,
                                   java.time.LocalDate fechaIngreso,
                                   String especialidad, double plusCategoria) throws Exception {

        if (traerPersonal(dni) != null) {
            throw new Exception("Error: ya existe personal con ese DNI");
        }

        int id = generarIdPersonal();

        return lstPersonal.add(new Cocinero(id, nombre, apellido, dni,
                fechaNacimiento, fechaIngreso, especialidad, plusCategoria));
    }

    public boolean agregarCajero(String nombre, String apellido, long dni,
                                 java.time.LocalDate fechaNacimiento,
                                 java.time.LocalDate fechaIngreso,
                                 String turno) throws Exception {

        if (traerPersonal(dni) != null) {
            throw new Exception("Error: ya existe personal con ese DNI");
        }

        int id = generarIdPersonal();

        return lstPersonal.add(new Cajero(id, nombre, apellido, dni,
                fechaNacimiento, fechaIngreso, turno));
    }

    public boolean agregarPlatoAUnidad(Plato plato, UnidadDeVenta unidad) throws Exception {
        // Validamos que la unidad exista en la lista del sistema
        if (!lstUnidades.contains(unidad)) {
            throw new Exception("Error: la unidad de venta no existe en el sistema.");
        }
        // La lógica de agregar el plato (y evitar duplicados) ya está en UnidadDeVenta
        return unidad.agregarPlato(plato);
    }

    public boolean agregarPlato(String codigoUnidad, String nombre, double precio, double costo) throws Exception {
        UnidadDeVenta unidad = traerUnidad(codigoUnidad);       
        if (unidad == null) {
            throw new Exception("No existe la unidad");
        }
        // Se asigna ID 0 temporalmente, ya que UnidadDeVenta.agregarPlato generará el ID definitivo
        return unidad.agregarPlato(new Plato(0, nombre, precio, costo));
    }

    // =========================================================
    // CASO DE USO 1: BAJAS
    // =========================================================

    public boolean eliminarFestival(String nombre) throws Exception {
        Festival festival = traerFestival(nombre);

        if (festival == null) {
            throw new Exception("Error: no existe un festival con ese nombre");
        }

        return lstFestivales.remove(festival);
    }

    public boolean eliminarUnidad(String codigo) throws Exception {
        UnidadDeVenta unidad = traerUnidad(codigo);

        if (unidad == null) {
            throw new Exception("Error: no existe una unidad con ese código");
        }

        return lstUnidades.remove(unidad);
    }

    public boolean eliminarPersonal(long dni) throws Exception {
        Personal personal = traerPersonal(dni);

        if (personal == null) {
            throw new Exception("Error: no existe personal con ese DNI");
        }

        return lstPersonal.remove(personal);
    }

    // =========================================================
    // CASO DE USO 2: BÚSQUEDAS POR ATRIBUTO ÚNICO
    // =========================================================

    public Personal traerPersonal(long dni) {
        int i = 0;
        Personal encontrado = null;
        while (i < lstPersonal.size() && encontrado == null) {
            if (lstPersonal.get(i).getDni() == dni) {
                encontrado = lstPersonal.get(i);
            }
            i++;
        }
        return encontrado;
    }

    public UnidadDeVenta traerUnidad(String codigo) {
        int i = 0;
        UnidadDeVenta encontrado = null;
        while (i < lstUnidades.size() && encontrado == null) {
            if (lstUnidades.get(i).getCodigo().equals(codigo)) {
                encontrado = lstUnidades.get(i);
            }
            i++;
        }
        return encontrado;
    }

    public Festival traerFestival(String nombre) {
        int i = 0;
        Festival encontrado = null;
        Festival dummy = new Festival();
        dummy.setNombre(nombre);

        while (i < lstFestivales.size() && encontrado == null) {
            if (lstFestivales.get(i).equals(dummy)) {
                encontrado = lstFestivales.get(i);
            }
            i++;
        }
        return encontrado;
    }

    public Pedido traerPedido(int id) {
        int i = 0;
        Pedido encontrado = null;
        Pedido dummy = new Pedido(id);

        while (i < lstPedidos.size() && encontrado == null) {
            if (lstPedidos.get(i).equals(dummy)) {
                encontrado = lstPedidos.get(i);
            }
            i++;
        }
        return encontrado;
    }

    // =========================================================
    // CASO DE USO 5: REGISTRO DE PEDIDO VALIDADO
    // =========================================================

    public boolean agregarPedido(LocalDate fecha, String nombreFestival, String codigoUnidad) throws Exception {
        return agregarPedido(fecha, nombreFestival, codigoUnidad, new ArrayList<>());
    }

    public boolean agregarPedido(LocalDate fecha, String nombreFestival, String codigoUnidad, List<ItemPlatoPedido> items) throws Exception {
        Festival festival = traerFestival(nombreFestival);
        if (festival == null) {
            throw new Exception("Error: no existe un festival con ese nombre");
        }

        UnidadDeVenta unidad = traerUnidad(codigoUnidad);
        if (unidad == null) {
            throw new Exception("Error: no existe una unidad con ese código");
        }

        int id = generarIdPedido();
        Pedido pedido = new Pedido(id, fecha, festival, unidad);
        pedido.setItems(items);
        return lstPedidos.add(pedido);
    }

    // =========================================================
    // CASO DE USO 6: REPORTE DE RECAUDACIÓN
    // =========================================================

    public List<ReporteVenta> reporteRecaudacion(Festival festival) throws Exception {
        if (!lstFestivales.contains(festival)) {
            throw new Exception("Error: el festival no existe en el sistema.");
        }

        List<ReporteVenta> reporte = new ArrayList<>();

        for (UnidadDeVenta unidad : lstUnidades) {
            double totalUnidad = 0.0;
            boolean tuvoVentas = false;

            for (Pedido pedido : lstPedidos) {
                if (pedido.getFestival().equals(festival) && pedido.getUnidad().equals(unidad)) {
                    totalUnidad += pedido.calcularMontoTotal();
                    tuvoVentas = true;
                }
            }

            if (tuvoVentas) {
                reporte.add(new ReporteVenta(unidad, totalUnidad));
            }
        }

        return reporte;
    }


    // =========================================================
    // MÉTODOS AUXILIARES PARA ID AUTOMÁTICO
    // =========================================================

    private int generarIdFestival() {
        int id = 1;
        if (!lstFestivales.isEmpty()) {
            id = lstFestivales.get(lstFestivales.size() - 1).getIdFestival() + 1;
        }
        return id;
    }

    private int generarIdUnidad() {
        int id = 1;
        if (!lstUnidades.isEmpty()) {
            id = lstUnidades.get(lstUnidades.size() - 1).getIdUnidad() + 1;
        }
        return id;
    }

    private int generarIdPersonal() {
        int id = 1;
        if (!lstPersonal.isEmpty()) {
            id = lstPersonal.get(lstPersonal.size() - 1).getIdPersonal() + 1;
        }
        return id;
    }

    private int generarIdPedido() {
        int id = 1;
        if (!lstPedidos.isEmpty()) {
            id = lstPedidos.get(lstPedidos.size() - 1).getIdPedido() + 1;
        }
        return id;
    }
    
    ///// CU N°7 /////
    public List<Personal> filtroPersonalPorEdad(LocalDate desde, LocalDate hasta){
    	List<Personal> resultado = new ArrayList<Personal>();
    	for(Personal p: lstPersonal) {
    		if((p.getFechaNacimiento().isEqual(desde) || p.getFechaNacimiento().isAfter(desde)) && (p.getFechaNacimiento().isEqual(hasta) || p.getFechaNacimiento().isBefore(hasta))) {
    			resultado.add(p);
    		}
    	} 
    	return resultado;
    }
    
    // =========================================================
    // CASO DE USO 10: RANKING DE UNIDADES
    // =========================================================
    public List<UnidadDeVenta> rankingUnidad(Festival festival) throws Exception {
        return rankingUnidades(festival);
    }

    public List<UnidadDeVenta> rankingUnidades(Festival festival) throws Exception {
        if (festival == null || !lstFestivales.contains(festival)) {
            throw new Exception("Error: el festival no existe en el sistema.");
        }

        List<UnidadDeVenta> ranking = new ArrayList<>(lstUnidades);
        ranking.sort((u1, u2) -> Double.compare(u2.calcularRecaudacion(lstPedidos, festival), u1.calcularRecaudacion(lstPedidos, festival)));
        return ranking;
    }

    public List<UnidadDeVenta> rankingUnidades() {
        List<UnidadDeVenta> ranking = new ArrayList<>(lstUnidades);
        ranking.sort((u1, u2) -> Double.compare(u2.calcularRecaudacion(lstPedidos), u1.calcularRecaudacion(lstPedidos)));
        return ranking;
    }
    
    
    // =========================================================
    // GETTERS Y SETTERS
    // =========================================================

    public List<Festival> getLstFestivales() {
        return lstFestivales;
    }

    public void setLstFestivales(List<Festival> lstFestivales) {
        this.lstFestivales = lstFestivales;
    }

    public List<UnidadDeVenta> getLstUnidades() {
        return lstUnidades;
    }

    public void setLstUnidades(List<UnidadDeVenta> lstUnidades) {
        this.lstUnidades = lstUnidades;
    }

    public List<Personal> getLstPersonal() {
        return lstPersonal;
    }

    public void setLstPersonal(List<Personal> lstPersonal) {
        this.lstPersonal = lstPersonal;
    }

    public List<Pedido> getLstPedidos() {
        return lstPedidos;
    }

    public void setLstPedidos(List<Pedido> lstPedidos) {
        this.lstPedidos = lstPedidos;
    }
}