import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Sistema {

    private List<Festival> lstFestivales;
    private List<UnidadDeVenta> lstUnidades;
    private List<Personal> lstPersonal;
    private List<Pedido> lstPedidos;

    public Sistema() {
        this.lstFestivales = new ArrayList<>();
        this.lstUnidades = new ArrayList<>();
        this.lstPersonal = new ArrayList<>();
        this.lstPedidos = new ArrayList<>();
    }

    // =========================================================
    // CASO DE USO 1: ALTAS
    // =========================================================

    public boolean agregarFestival(String nombre, String temporada, LocalDate fechaInicio, LocalDate fechaFin,
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
        if (traerUnidad(unidad.getCodigo()) == null) {
            throw new Exception("Error: la unidad de venta no existe en el sistema.");
        }
        
        // FIX: Validación de elemento repetido en el administrador (Regla 10)
        if (unidad.traerPlato(plato.getNombre()) != null) {
            throw new Exception("Error: El plato ya existe en la unidad");
        }

        return unidad.agregarPlato(plato);
    }

    public boolean agregarPlato(String codigoUnidad, String nombre, double precio, double costo) throws Exception {
        UnidadDeVenta unidad = traerUnidad(codigoUnidad);       
        if (unidad == null) {
            throw new Exception("No existe la unidad");
        }
        
        // FIX: Validación de elemento repetido en el administrador (Regla 10)
        if (unidad.traerPlato(nombre) != null) {
            throw new Exception("Error: El plato ya existe en la unidad");
        }

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
        try { dummy.setNombre(nombre); } catch (Exception e) {}

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
        Pedido dummy = new Pedido();
        try { dummy.setIdPedido(id); } catch (Exception e) {}

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
        
        for (ItemPlatoPedido item : items) {
            this.agregarItemAPedido(pedido, item); // Delega para validar duplicado
        }
        
        return lstPedidos.add(pedido);
    }
    
    // Método para agregar Items a un Pedido desde Sistema y validar duplicados
    public boolean agregarItemAPedido(int idPedido, Plato plato, int cantidad) throws Exception {
        Pedido pedido = traerPedido(idPedido);
        if (pedido == null) throw new Exception("Error: El pedido no existe");
        
        ItemPlatoPedido item = new ItemPlatoPedido(plato, cantidad);
        return agregarItemAPedido(pedido, item);
    }
    
    private boolean agregarItemAPedido(Pedido pedido, ItemPlatoPedido item) throws Exception {
        // Validar duplicado con un while
        int i = 0;
        boolean repetido = false;
        while (i < pedido.getItems().size() && !repetido) {
            if (pedido.getItems().get(i).getPlato().equals(item.getPlato())) {
                repetido = true;
            }
            i++;
        }
        if (repetido) throw new Exception("Error: El plato ya se encuentra en el pedido");
        
        return pedido.agregarItem(item);
    }

    // =========================================================
    // CASO DE USO 6: REPORTE DE RECAUDACIÓN
    // =========================================================

    public List<ReporteVenta> reporteRecaudacion(Festival festival) throws Exception {
        if (festival == null || traerFestival(festival.getNombre()) == null) {
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
    // MÉTODOS AUXILIARES PARA ID AUTOMÁTICO (FIX: Regla 9)
    // =========================================================

    private int generarIdFestival() {
        return lstFestivales.size() > 0 ? lstFestivales.get(lstFestivales.size() - 1).getIdFestival() + 1 : 1;
    }

    private int generarIdUnidad() {
        return lstUnidades.size() > 0 ? lstUnidades.get(lstUnidades.size() - 1).getIdUnidad() + 1 : 1;
    }

    private int generarIdPersonal() {
        return lstPersonal.size() > 0 ? lstPersonal.get(lstPersonal.size() - 1).getIdPersonal() + 1 : 1;
    }

    private int generarIdPedido() {
        return lstPedidos.size() > 0 ? lstPedidos.get(lstPedidos.size() - 1).getIdPedido() + 1 : 1;
    }
    
    ///// CU N°7 /////
    public List<Personal> filtroPersonalPorEdad(LocalDate desde, LocalDate hasta){
    	List<Personal> resultado = new ArrayList<>();
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
        if (festival == null || traerFestival(festival.getNombre()) == null) {
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
    // CASO DE USO 11: PLATO ESTRELLA
    // =========================================================
    public Plato platoEstrella(Festival festival, UnidadDeVenta unidad) throws Exception {
        if (festival == null || traerFestival(festival.getNombre()) == null) {
            throw new Exception("Error: el festival no existe en el sistema.");
        }
        if (unidad == null || traerUnidad(unidad.getCodigo()) == null) {
            throw new Exception("Error: la unidad de venta no existe en el sistema.");
        }

        java.util.Map<String, Integer> platoCantidades = new java.util.HashMap<>();
        java.util.Map<String, Plato> platoObjetos = new java.util.HashMap<>();

        for (Pedido pedido : lstPedidos) {
            if (pedido.getFestival().equals(festival) && pedido.getUnidad().equals(unidad)) {
                for (ItemPlatoPedido item : pedido.getItems()) {
                    Plato plato = item.getPlato();
                    if (plato != null && plato.getNombre() != null) {
                        String nombre = plato.getNombre();
                        int cantidad = item.getCantidad();
                        platoCantidades.put(nombre, platoCantidades.getOrDefault(nombre, 0) + cantidad);
                        platoObjetos.put(nombre, plato);
                    }
                }
            }
        }

        String nombreEstrella = null;
        int maxCantidad = -1;

        for (java.util.Map.Entry<String, Integer> entry : platoCantidades.entrySet()) {
            if (entry.getValue() > maxCantidad) {
                maxCantidad = entry.getValue();
                nombreEstrella = entry.getKey();
            }
        }

        return nombreEstrella != null ? platoObjetos.get(nombreEstrella) : null;
    }

    public Plato platoEstrella(UnidadDeVenta unidad, Festival festival) throws Exception {
        return platoEstrella(festival, unidad);
    }

    // =========================================================
    // CASO DE USO 12: AUDITORÍA DE PERSONAL DEL FESTIVAL
    // =========================================================
    public List<Personal> auditoriaPersonalFestival(Festival festival) throws Exception {
        if (festival == null || traerFestival(festival.getNombre()) == null) {
            throw new Exception("Error: el festival no existe en el sistema.");
        }

        List<Personal> personalTrabajo = new ArrayList<>();

        // 1. Buscar personal de unidades que tienen pedidos en este festival
        for (Pedido p : lstPedidos) {
            if (p.getFestival().equals(festival)) {
                UnidadDeVenta unidad = p.getUnidad();
                agregarPersonalDeUnidad(personalTrabajo, unidad);
            }
        }

        // 2. Buscar personal de unidades asociadas directamente al festival (si las hay)
        if (festival.getUnidades() != null) {
            for (UnidadDeVenta unidad : festival.getUnidades()) {
                agregarPersonalDeUnidad(personalTrabajo, unidad);
            }
        }

        return personalTrabajo;
    }

    private void agregarPersonalDeUnidad(List<Personal> lista, UnidadDeVenta unidad) {
        if (unidad != null) {
            Personal resp = unidad.getResponsable();
            if (resp != null && !lista.contains(resp)) {
                lista.add(resp);
            }
            if (unidad.getLstPersonal() != null) {
                for (Personal staff : unidad.getLstPersonal()) {
                    if (staff != null && !lista.contains(staff)) {
                        lista.add(staff);
                    }
                }
            }
        }
    }
    // =========================================================
    // CASO DE USO 13: UNIDADES CON MAYOR CANON
    // =========================================================
    public List<ReporteMayoresCanon> reporteMayoresCanon(Festival festival) throws Exception {
        if (festival == null || traerFestival(festival.getNombre()) == null) {
            throw new Exception("Error: el festival no existe en el sistema.");
        }

        List<UnidadDeVenta> unidadesFestival = new ArrayList<>();
        // 1. Agregar unidades asociadas directamente al festival
        if (festival.getUnidades() != null) {
            for (UnidadDeVenta u : festival.getUnidades()) {
                if (u != null && !unidadesFestival.contains(u)) {
                    unidadesFestival.add(u);
                }
            }
        }

        // 2. Agregar unidades que tienen pedidos en este festival
        for (Pedido p : lstPedidos) {
            if (p.getFestival().equals(festival)) {
                UnidadDeVenta u = p.getUnidad();
                if (u != null && !unidadesFestival.contains(u)) {
                    unidadesFestival.add(u);
                }
            }
        }

        // Calcular el canon para cada una y guardarlos en una lista temporaria de reportes
        List<ReporteMayoresCanon> todosReportes = new ArrayList<>();
        for (UnidadDeVenta u : unidadesFestival) {
            double canon = u.calcularCanon(festival);
            String tipoUnidad = u instanceof FoodTruck ? "Food Truck" : (u instanceof PuestoDesarmable ? "Puesto Desarmable" : u.getClass().getSimpleName());
            todosReportes.add(new ReporteMayoresCanon(u.getNombreComercial(), u.getCodigo(), tipoUnidad, canon));
        }

        // Ordenar de mayor a menor canon
        todosReportes.sort((r1, r2) -> Double.compare(r2.getCanon(), r1.getCanon()));

        // Tomar las 3 primeras
        List<ReporteMayoresCanon> resultado = new ArrayList<>();
        for (int i = 0; i < Math.min(3, todosReportes.size()); i++) {
            resultado.add(todosReportes.get(i));
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