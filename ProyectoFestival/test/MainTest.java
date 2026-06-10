import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class MainTest {

    public static void main(String[] args) {
        Sistema sistema = new Sistema();

        // =========================================================
        // CASO DE USO 1: Altas y Bajas:
        // Métodos para agregar festivales, unidades y personal a las listas de
        // la clase Sistema
        // =========================================================

        System.out.println("=== ALTAS: PERSONAL ===");
        try {
            sistema.agregarCocinero(
                    "Ana", "Gomez",
                    30111222,
                    LocalDate.of(1990, 5, 10),
                    LocalDate.of(2020, 3, 1),
                    "Parrilla",
                    20000
            );
            System.out.println("Cocinero agregado correctamente.");

            sistema.agregarCajero(
                    "Carlos", "Lopez",
                    28999333,
                    LocalDate.of(1985, 8, 20),
                    LocalDate.of(2018, 6, 15),
                    "mañana"
            );
            System.out.println("Cajero agregado correctamente.");

            // Error esperado: DNI duplicado
            sistema.agregarCocinero(
                    "Otro", "Nombre",
                    30111222,
                    LocalDate.of(1992, 1, 1),
                    LocalDate.of(2021, 1, 1),
                    "Pizzas",
                    15000
            );
        } catch (Exception e) {
            System.out.println("Error esperado (DNI duplicado): " + e.getMessage());
        }

        System.out.println("\n=== ALTAS: FESTIVALES ===");
        try {
            Date inicioVerano = crearFecha(2025, Calendar.JANUARY, 10);
            Date finVerano    = crearFecha(2025, Calendar.JANUARY, 20);
            sistema.agregarFestival(
                    "Festival Verano 2025", "Verano",
                    inicioVerano, finVerano,
                    500.0, 300.0, 150.0, 80000.0
            );
            System.out.println("Festival 'Festival Verano 2025' agregado correctamente.");

            Date inicioInvierno = crearFecha(2025, Calendar.JULY, 5);
            Date finInvierno    = crearFecha(2025, Calendar.JULY, 15);
            sistema.agregarFestival(
                    "Festival Invierno 2025", "Invierno",
                    inicioInvierno, finInvierno,
                    450.0, 280.0, 0.0, 75000.0
            );
            System.out.println("Festival 'Festival Invierno 2025' agregado correctamente.");

            // Error esperado: nombre duplicado
            sistema.agregarFestival(
                    "Festival Verano 2025", "Verano",
                    inicioVerano, finVerano,
                    500.0, 300.0, 150.0, 80000.0
            );
        } catch (Exception e) {
            System.out.println("Error esperado (festival duplicado): " + e.getMessage());
        }

        System.out.println("\n=== ALTAS: UNIDADES DE VENTA ===");
        try {
            Personal responsable = sistema.traerPersonal(30111222);

            sistema.agregarFoodTruck(
                    "FT12345678", "Food Truck Norte",
                    responsable,
                    10, "ABC123", true
            );
            System.out.println("FoodTruck 'FT12345678' agregado correctamente.");

            sistema.agregarPuestoDesarmable(
                    "PD12345678", "Puesto Sur",
                    responsable,
                    8, 3, 60
            );
            System.out.println("PuestoDesarmable 'PD12345678' agregado correctamente.");

            // Error esperado: código duplicado
            sistema.agregarFoodTruck(
                    "FT12345678", "Otro Truck",
                    responsable,
                    5, "XYZ999", false
            );
        } catch (Exception e) {
            System.out.println("Error esperado (código duplicado): " + e.getMessage());
        }

        // =========================================================
        // CASO DE USO 3: CÁLCULO DE CANON
        // =========================================================

        System.out.println("\n=== CÁLCULO DE CANON ===");
        try {
            UnidadDeVenta foodTruck = sistema.traerUnidad("FT12345678");
            if (foodTruck != null) {
                System.out.println("Canon de Food Truck '" + foodTruck.getNombreComercial() + "': $" + foodTruck.calcularCanon());
            }

            UnidadDeVenta puesto = sistema.traerUnidad("PD12345678");
            if (puesto != null) {
                System.out.println("Canon de Puesto Desarmable '" + puesto.getNombreComercial() + "': $" + puesto.calcularCanon());
            }
        } catch (Exception e) {
            System.out.println("Error al calcular el canon: " + e.getMessage());
        }

        System.out.println("\n=== BAJAS: ELIMINAR UNIDAD ===");
        try {
            sistema.eliminarUnidad("PD12345678");
            System.out.println("Unidad 'PD12345678' eliminada correctamente.");

            // Error esperado: unidad inexistente
            sistema.eliminarUnidad("PD12345678");
        } catch (Exception e) {
            System.out.println("Error esperado (unidad inexistente): " + e.getMessage());
        }

        System.out.println("\n=== BAJAS: ELIMINAR PERSONAL ===");
        try {
            sistema.eliminarPersonal(28999333);
            System.out.println("Personal con DNI 28999333 eliminado correctamente.");

            // Error esperado: personal inexistente
            sistema.eliminarPersonal(28999333);
        } catch (Exception e) {
            System.out.println("Error esperado (personal inexistente): " + e.getMessage());
        }

        System.out.println("\n=== BAJAS: ELIMINAR FESTIVAL ===");
        try {
            sistema.eliminarFestival("Festival Invierno 2025");
            System.out.println("Festival 'Festival Invierno 2025' eliminado correctamente.");

            // Error esperado: festival inexistente
            sistema.eliminarFestival("Festival Invierno 2025");
        } catch (Exception e) {
            System.out.println("Error esperado (festival inexistente): " + e.getMessage());
        }

        System.out.println("\n=== ESTADO FINAL DEL SISTEMA ===");
        System.out.println("Festivales: " + sistema.getLstFestivales().size());
        System.out.println("Unidades:   " + sistema.getLstUnidades().size());
        System.out.println("Personal:   " + sistema.getLstPersonal().size());
        
        System.out.println("\n### FILTRAR PERSONAL POR EDAD###");
        System.out.println(sistema.filtroPersonalPorEdad(LocalDate.of(1990, 1, 1), LocalDate.of(1991, 12, 31)));
    }
    
    private static Date crearFecha(int anio, int mes, int dia) {
        Calendar cal = Calendar.getInstance();
        cal.set(anio, mes, dia, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
    
    
}