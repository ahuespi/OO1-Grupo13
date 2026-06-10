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

        Festival festival = traerFestival(nombre);

        if (festival != null) {
            throw new Exception("Error: ya existe un festival con ese nombre");
        }

        return lstFestivales.add(new Festival(nombre, temporada, fechaInicio, fechaFin,
                costoSuperficie, costoMontaje, plusElectricidad, sueldoBase));
    }

    public boolean agregarFoodTruck(String codigo, String nombreComercial, Personal responsable,
                                    int superficieMetroCuadrado, String patente,
                                    boolean requiereConexionElectrica) throws Exception {

        UnidadDeVenta unidad = traerUnidad(codigo);

        if (unidad != null) {
            throw new Exception("Error: ya existe una unidad con ese código");
        }

        int id = calcularProximoIdUnidad();

        return lstUnidades.add(new FoodTruck(id, codigo, nombreComercial, responsable,
                superficieMetroCuadrado, patente, requiereConexionElectrica));
    }

    public boolean agregarPuestoDesarmable(String codigo, String nombreComercial, Personal responsable,
                                           int superficieMetroCuadrado, int cantidadCarpas,
                                           int tiempoMontajeMinutos) throws Exception {

        UnidadDeVenta unidad = traerUnidad(codigo);

        if (unidad != null) {
            throw new Exception("Error: ya existe una unidad con ese código");
        }

        int id = calcularProximoIdUnidad();

        return lstUnidades.add(new PuestoDesarmable(id, codigo, nombreComercial, responsable,
                superficieMetroCuadrado, cantidadCarpas, tiempoMontajeMinutos));
    }

    public boolean agregarCocinero(String nombre, String apellido, long dni,
                                   java.time.LocalDate fechaNacimiento,
                                   java.time.LocalDate fechaIngreso,
                                   String especialidad, double plusCategoria) throws Exception {

        Personal personal = traerPersonal(dni);

        if (personal != null) {
            throw new Exception("Error: ya existe personal con ese DNI");
        }

        int id = calcularProximoIdPersonal();

        return lstPersonal.add(new Cocinero(id, nombre, apellido, dni,
                fechaNacimiento, fechaIngreso, especialidad, plusCategoria));
    }

    public boolean agregarCajero(String nombre, String apellido, long dni,
                                 java.time.LocalDate fechaNacimiento,
                                 java.time.LocalDate fechaIngreso,
                                 String turno) throws Exception {

        Personal personal = traerPersonal(dni);

        if (personal != null) {
            throw new Exception("Error: ya existe personal con ese DNI");
        }

        int id = calcularProximoIdPersonal();

        return lstPersonal.add(new Cajero(id, nombre, apellido, dni,
                fechaNacimiento, fechaIngreso, turno));
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
        Personal encontrado = null;
        int i = 0;

        while (i < lstPersonal.size() && encontrado == null) {
            if (lstPersonal.get(i).getDni() == dni) {
                encontrado = lstPersonal.get(i);
            }
            i++;
        }

        return encontrado;
    }

    public UnidadDeVenta traerUnidad(String codigo) {
        UnidadDeVenta encontrado = null;
        int i = 0;

        while (i < lstUnidades.size() && encontrado == null) {
            if (lstUnidades.get(i).getCodigo().equalsIgnoreCase(codigo)) {
                encontrado = lstUnidades.get(i);
            }
            i++;
        }

        return encontrado;
    }

    public Festival traerFestival(String nombre) {
        Festival encontrado = null;
        int i = 0;

        while (i < lstFestivales.size() && encontrado == null) {
            if (lstFestivales.get(i).getNombre().equalsIgnoreCase(nombre)) {
                encontrado = lstFestivales.get(i);
            }
            i++;
        }

        return encontrado;
    }

    // =========================================================
    // MÉTODOS AUXILIARES PARA ID AUTOMÁTICO
    // =========================================================

    private int calcularProximoIdUnidad() {
        int proximoId = 1;

        if (!lstUnidades.isEmpty()) {
            proximoId = lstUnidades.get(lstUnidades.size() - 1).getId() + 1;
        }

        return proximoId;
    }

    private int calcularProximoIdPersonal() {
        int proximoId = 1;

        if (!lstPersonal.isEmpty()) {
            proximoId = lstPersonal.get(lstPersonal.size() - 1).getId() + 1;
        }

        return proximoId;
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