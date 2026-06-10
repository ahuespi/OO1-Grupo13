import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
        // PRUEBA: AGREGAR PLATOS A TRAVÉS DE SISTEMA (CON CONTROL DE DUPLICADOS)
        // =========================================================
        System.out.println("\n=== AGREGAR PLATOS A UNIDAD DESDE SISTEMA ===");
        try {
            Plato plato1 = new Plato(1, "Hamburguesa Doble", 5000.0, 2000.0);
            Plato plato2 = new Plato(2, "Papas Fritas", 2500.0, 1000.0);
            Plato plato1Duplicado = new Plato(3, "Hamburguesa Doble", 6000.0, 2500.0);

            // 1. Obtenemos la unidad usando traerUnidad
            UnidadDeVenta foodTruckParaPlatos = sistema.traerUnidad("FT12345678");
            
            if (foodTruckParaPlatos != null) {
                // 2. Pasamos el objeto UnidadDeVenta al método del Sistema
                System.out.println("Agregando: " + plato1.getNombre() + " a FT12345678 -> " + sistema.agregarPlatoAUnidad(plato1, foodTruckParaPlatos));
                System.out.println("Agregando: " + plato2.getNombre() + " a FT12345678 -> " + sistema.agregarPlatoAUnidad(plato2, foodTruckParaPlatos));
                System.out.println("Agregando: " + plato1Duplicado.getNombre() + " (duplicado) a FT12345678 -> " + sistema.agregarPlatoAUnidad(plato1Duplicado, foodTruckParaPlatos));

                System.out.println("Platos en " + foodTruckParaPlatos.getNombreComercial() + ": " + foodTruckParaPlatos.getLstPlatos().size());
            } else {
                System.out.println("No se encontró la unidad para agregar platos.");
            }
        } catch (Exception e) {
            System.out.println("Error al agregar platos: " + e.getMessage());
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

        // =========================================================
        // CASO DE USO 4: LIQUIDACIÓN DE HABERES
        // =========================================================

        System.out.println("\n=== LIQUIDACIÓN DE HABERES ===");
        try {
            Personal cocinero = sistema.traerPersonal(30111222);
            if (cocinero != null) {
                System.out.println("Sueldo de Cocinero (" + cocinero.getNombre() + " " + cocinero.getApellido() + "): $" + cocinero.calcularSueldo());
            }

            Personal cajero = sistema.traerPersonal(28999333);
            if (cajero != null) {
                System.out.println("Sueldo de Cajero (" + cajero.getNombre() + " " + cajero.getApellido() + "): $" + cajero.calcularSueldo());
            }
        } catch (Exception e) {
            System.out.println("Error al calcular haberes: " + e.getMessage());
        }

        // =========================================================
        // CASO DE USO 5: REGISTRO DE PEDIDO VALIDADO Y AGREGADO DE ITEMS
        // =========================================================

        System.out.println("\n=== REGISTRO DE PEDIDO VALIDADO ===");
        try {
            // 1. Registramos el pedido vacío (solo con sus datos principales)
            sistema.agregarPedido(
                    LocalDate.now(),
                    "Festival Verano 2025",
                    "FT12345678"
            );
            System.out.println("Pedido registrado correctamente con Festival y Unidad válidos.");
            
            // 2. Traemos el pedido recién creado (el ID 1 por ser el primero)
            Pedido pedido = sistema.traerPedido(1);
            if (pedido != null) {
                // Recuperamos el plato de la unidad de venta para usarlo en el pedido
                UnidadDeVenta foodTruck = sistema.traerUnidad("FT12345678");
                Plato hamburguesa = foodTruck.traerPlato("Hamburguesa Doble");
                
                if (hamburguesa != null) {
                    // 3. Agregamos el plato al pedido usando el método de la clase Pedido
                    pedido.agregarPlato(hamburguesa, 2);
                    System.out.println("Se agregaron 2 " + hamburguesa.getNombre() + " al pedido ID " + pedido.getId());
                    System.out.println("Monto total del pedido: $" + pedido.calcularMontoTotal());
                } else {
                    System.out.println("No se encontró el plato en la unidad de venta.");
                }
            }
            
            // Error esperado: Festival inexistente
            sistema.agregarPedido(
                    LocalDate.now(),
                    "Festival Inexistente 2099",
                    "FT12345678"
            );
        } catch (Exception e) {
            System.out.println("Error esperado (Festival inexistente): " + e.getMessage());
        }
        
        try {
            // Error esperado: Unidad inexistente
            sistema.agregarPedido(
                    LocalDate.now(),
                    "Festival Verano 2025",
                    "INVENTADO1"
            );
        } catch (Exception e) {
            System.out.println("Error esperado (Unidad inexistente): " + e.getMessage());
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
        System.out.println("Pedidos:    " + sistema.getLstPedidos().size());
        
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