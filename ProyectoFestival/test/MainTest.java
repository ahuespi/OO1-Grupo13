import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class MainTest {

    public static void main(String[] args) {
        Sistema sistema = new Sistema();

        System.out.println("\n=========================================================");
        System.out.println("CASO DE USO 1: ALTAS Y BAJAS (Prueba de Altas)");
        System.out.println("=========================================================");

        System.out.println("\n--- 1.1 ALTAS: PERSONAL ---");
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
            System.err.println("Error esperado (DNI duplicado): " + e.getMessage());
        }

        System.out.println("\n--- 1.2 ALTAS: FESTIVALES ---");
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
            System.err.println("Error esperado (festival duplicado): " + e.getMessage());
        }

        System.out.println("\n--- 1.3 ALTAS: UNIDADES DE VENTA ---");
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
            System.err.println("Error esperado (código duplicado): " + e.getMessage());
        }

        System.out.println("\n=========================================================");
        System.out.println("CASO DE USO 2: AGREGAR PLATOS A TRAVÉS DE SISTEMA");
        System.out.println("=========================================================");
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
            System.err.println("Error al agregar platos: " + e.getMessage());
        }

        System.out.println("\n=========================================================");
        System.out.println("CASO DE USO 3: CÁLCULO DE CANON");
        System.out.println("=========================================================");
        try {
            Festival festival = sistema.traerFestival("Festival Verano 2025");
            UnidadDeVenta foodTruck = sistema.traerUnidad("FT12345678");
            if (foodTruck != null && festival != null) {
                System.out.println("Canon de Food Truck '" + foodTruck.getNombreComercial() + "': $" + foodTruck.calcularCanon(festival));
            }

            UnidadDeVenta puesto = sistema.traerUnidad("PD12345678");
            if (puesto != null && festival != null) {
                System.out.println("Canon de Puesto Desarmable '" + puesto.getNombreComercial() + "': $" + puesto.calcularCanon(festival));
            }
        } catch (Exception e) {
            System.err.println("Error al calcular el canon: " + e.getMessage());
        }

        System.out.println("\n=========================================================");
        System.out.println("CASO DE USO 4: LIQUIDACIÓN DE HABERES");
        System.out.println("=========================================================");
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
            System.err.println("Error al calcular haberes: " + e.getMessage());
        }

        System.out.println("\n=========================================================");
        System.out.println("CASO DE USO 5: REGISTRO DE PEDIDO VALIDADO Y AGREGADO DE ITEMS");
        System.out.println("=========================================================");
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
                Plato papas = foodTruck.traerPlato("Papas Fritas");
                
                if (hamburguesa != null) {
                    // 3. Agregamos el plato al pedido usando el método de la clase Pedido
                    pedido.agregarPlato(hamburguesa, 2);
                    System.out.println("Se agregaron 2 " + hamburguesa.getNombre() + " al pedido ID " + pedido.getIdPedido());
                }
                
                if (papas != null) {
                    pedido.agregarPlato(papas, 1);
                    System.out.println("Se agregaron 1 " + papas.getNombre() + " al pedido ID " + pedido.getIdPedido());
                }
                System.out.println("Monto total del pedido: $" + pedido.calcularMontoTotal());
            }
            
            // Error esperado: Festival inexistente
            sistema.agregarPedido(
                    LocalDate.now(),
                    "Festival Inexistente 2099",
                    "FT12345678"
            );
        } catch (Exception e) {
            System.err.println("Error esperado (Festival inexistente): " + e.getMessage());
        }
        
        try {
            // Error esperado: Unidad inexistente
            sistema.agregarPedido(
                    LocalDate.now(),
                    "Festival Verano 2025",
                    "INVENTADO1"
            );
        } catch (Exception e) {
            System.err.println("Error esperado (Unidad inexistente): " + e.getMessage());
        }

        System.out.println("\n=========================================================");
        System.out.println("CASO DE USO 6: REPORTE DE RECAUDACIÓN");
        System.out.println("=========================================================");
        try {
            Festival festivalVerano = sistema.traerFestival("Festival Verano 2025");
            if (festivalVerano != null) {
                List<ReporteVenta> reporte = sistema.reporteRecaudacion(festivalVerano);
                
                System.out.println("Recaudación del " + festivalVerano.getNombre() + ":");
                if(reporte.isEmpty()) {
                	System.out.println("No hubo ventas registradas para este festival.");
                } else {
                    for (ReporteVenta rv : reporte) {
                        System.out.println(rv);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error al generar el reporte: " + e.getMessage());
        }

        System.out.println("\n=========================================================");
        System.out.println("CASO DE USO 8 y 9: CÁLCULO DE RENTABILIDAD NETA");
        System.out.println("=========================================================");
        
        // 1. Dar de alta platos para la prueba de rentabilidad
        System.out.println("Altas de platos para rentabilidad...");
        try {
            sistema.agregarPlato("FT12345678", "Hamburguesa", 12000.0, 4000.0);
            sistema.agregarPlato("FT12345678", "Papas Fritas", 5200.0, 1500.0);
            sistema.agregarPlato("FT12345678", "Pizza", 25000.0, 10000.0);
            sistema.agregarPlato("FT12345678", "Rabas", 15000.0, 7300.0);
            sistema.agregarPlato("FT12345678", "Cornalitos", 7500.0, 3300.0);
            System.out.println("Platos agregados correctamente.");
        } catch (Exception e) {
            System.err.println("Error al agregar platos: " + e.getMessage());
        } 
        
        // 2. Asignar el personal
        try {
            UnidadDeVenta ft = sistema.traerUnidad("FT12345678");
            ft.agregarPersonal(sistema.traerPersonal(30111222)); // Ana Gomez (Cocinero, sueldo 120000)
            System.out.println("Personal asignado correctamente.");
        } catch (Exception e) {
            System.err.println("Error al asignar personal: " + e.getMessage());
        }
        
        // 3. Agregar Pedido 2 (fecha = hoy) con los items descritos por el compañero
        try {
            UnidadDeVenta ft = sistema.traerUnidad("FT12345678");
            List<ItemPlatoPedido> items = new ArrayList<>();
            items.add(new ItemPlatoPedido(ft.traerPlato("Hamburguesa"), 13));
            items.add(new ItemPlatoPedido(ft.traerPlato("Papas Fritas"), 22));
            items.add(new ItemPlatoPedido(ft.traerPlato("Pizza"), 5));
            items.add(new ItemPlatoPedido(ft.traerPlato("Rabas"), 10));
            items.add(new ItemPlatoPedido(ft.traerPlato("Cornalitos"), 7));
            
            sistema.agregarPedido(LocalDate.now(), "Festival Verano 2025", "FT12345678", items);
            System.out.println("Pedido de hoy con items agregado correctamente.");
        } catch (Exception e) {
            System.err.println("Error al agregar pedido: " + e.getMessage());
        }

        // 4. Agregar Pedido 3 (fecha = hace 5 días)
        try {
            UnidadDeVenta ft = sistema.traerUnidad("FT12345678");
            List<ItemPlatoPedido> items = new ArrayList<>();
            items.add(new ItemPlatoPedido(ft.traerPlato("Pizza"), 1)); // Venta: 25000, Costo: 10000
            
            sistema.agregarPedido(LocalDate.now().minusDays(5), "Festival Verano 2025", "FT12345678", items);
            System.out.println("Pedido de hace 5 días agregado correctamente.");
        } catch (Exception e) {
            System.err.println("Error al agregar pedido del pasado: " + e.getMessage());
        }

        // 5. Calcular la Rentabilidad Neta (CU 8) - Todos los pedidos
        try {
            UnidadDeVenta ft = sistema.traerUnidad("FT12345678");
            double rentabilidadTotal = ft.calcularRentabilidadNeta(sistema.getLstPedidos());
            System.out.println("Rentabilidad Neta Total (CU 8): $" + rentabilidadTotal);
        } catch (Exception e) {
            System.err.println("Error al calcular rentabilidad total: " + e.getMessage());
        }

        // 6. Calcular la Rentabilidad Neta entre dos fechas (CU 9)
        // Rango A: incluye solo hoy y ayer (excluye el de hace 5 días)
        try {
            UnidadDeVenta ft = sistema.traerUnidad("FT12345678");
            LocalDate desde = LocalDate.now().minusDays(1);
            LocalDate hasta = LocalDate.now().plusDays(1);
            double rentabilidadRangoA = ft.calcularRentabilidadNeta(sistema.getLstPedidos(), desde, hasta);
            System.out.println("Rentabilidad Neta Rango A (hoy +/- 1 día) (CU 9): $" + rentabilidadRangoA);
        } catch (Exception e) {
            System.err.println("Error al calcular rentabilidad Rango A: " + e.getMessage());
        }

        // Rango B: incluye todo (hace 10 días a hoy + 1)
        try {
            UnidadDeVenta ft = sistema.traerUnidad("FT12345678");
            LocalDate desde = LocalDate.now().minusDays(10);
            LocalDate hasta = LocalDate.now().plusDays(1);
            double rentabilidadRangoB = ft.calcularRentabilidadNeta(sistema.getLstPedidos(), desde, hasta);
            System.out.println("Rentabilidad Neta Rango B (hace 10 días a hoy) (CU 9): $" + rentabilidadRangoB);
        } catch (Exception e) {
            System.err.println("Error al calcular rentabilidad Rango B: " + e.getMessage());
        }

        System.out.println("\n=========================================================");
        System.out.println("CASO DE USO 1: ALTAS Y BAJAS (Prueba de Bajas)");
        System.out.println("=========================================================");

        System.out.println("\n--- BAJAS: ELIMINAR UNIDAD ---");
        try {
            sistema.eliminarUnidad("PD12345678");
            System.out.println("Unidad 'PD12345678' eliminada correctamente.");

            // Error esperado: unidad inexistente
            sistema.eliminarUnidad("PD12345678");
        } catch (Exception e) {
            System.err.println("Error esperado (unidad inexistente): " + e.getMessage());
        }

        System.out.println("\n--- BAJAS: ELIMINAR PERSONAL ---");
        try {
            sistema.eliminarPersonal(28999333);
            System.out.println("Personal con DNI 28999333 eliminado correctamente.");

            // Error esperado: personal inexistente
            sistema.eliminarPersonal(28999333);
        } catch (Exception e) {
            System.err.println("Error esperado (personal inexistente): " + e.getMessage());
        }

        System.out.println("\n--- BAJAS: ELIMINAR FESTIVAL ---");
        try {
            sistema.eliminarFestival("Festival Invierno 2025");
            System.out.println("Festival 'Festival Invierno 2025' eliminado correctamente.");

            // Error esperado: festival inexistente
            sistema.eliminarFestival("Festival Invierno 2025");
        } catch (Exception e) {
            System.err.println("Error esperado (festival inexistente): " + e.getMessage());
        }

        System.out.println("\n=========================================================");
        System.out.println("EXTRAS: ESTADO FINAL DEL SISTEMA Y FILTRADO");
        System.out.println("=========================================================");

        System.out.println("\n--- ESTADO FINAL DEL SISTEMA ---");
        System.out.println("Festivales: " + sistema.getLstFestivales().size());
        System.out.println("Unidades:   " + sistema.getLstUnidades().size());
        System.out.println("Personal:   " + sistema.getLstPersonal().size());
        System.out.println("Pedidos:    " + sistema.getLstPedidos().size());
        
        System.out.println("\n--- FILTRAR PERSONAL POR EDAD ---");
        System.out.println(sistema.filtroPersonalPorEdad(LocalDate.of(1990, 1, 1), LocalDate.of(1991, 12, 31)));
    }
    
    private static Date crearFecha(int anio, int mes, int dia) {
        Calendar cal = Calendar.getInstance();
        cal.set(anio, mes, dia, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
    
    
}