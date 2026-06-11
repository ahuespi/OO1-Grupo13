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

            sistema.agregarCocinero(
                    "Lucia", "Gomez",
                    32111222,
                    LocalDate.of(1992, 10, 15),
                    LocalDate.of(2021, 5, 10),
                    "Pastas",
                    25000
            );
            System.out.println("Cocinero adicional agregado correctamente.");

            sistema.agregarCajero(
                    "Martin", "Perez",
                    33111222,
                    LocalDate.of(1988, 3, 25),
                    LocalDate.of(2019, 11, 1),
                    "noche"
            );
            System.out.println("Cajero adicional agregado correctamente.");

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

            Date inicioPrimavera = crearFecha(2025, Calendar.SEPTEMBER, 21);
            Date finPrimavera    = crearFecha(2025, Calendar.SEPTEMBER, 30);
            sistema.agregarFestival(
                    "Festival Primavera 2025", "Primavera",
                    inicioPrimavera, finPrimavera,
                    480.0, 290.0, 100.0, 78000.0
            );
            System.out.println("Festival 'Festival Primavera 2025' agregado correctamente.");

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
            sistema.agregarFoodTruck(
                    "FT12345678", "Food Truck Norte",
                    sistema.traerPersonal(30111222),
                    10, "ABC123", true
            );
            System.out.println("FoodTruck 'FT12345678' agregado correctamente.");

            sistema.agregarPuestoDesarmable(
                    "PD12345678", "Puesto Sur",
                    sistema.traerPersonal(30111222),
                    8, 3, 60
            );
            System.out.println("PuestoDesarmable 'PD12345678' agregado correctamente.");

            sistema.agregarFoodTruck(
                    "FT99999999", "Food Truck Sur",
                    sistema.traerPersonal(32111222),
                    12, "DEF456", true
            );
            System.out.println("FoodTruck 'FT99999999' agregado correctamente.");

            sistema.agregarPuestoDesarmable(
                    "PD99999999", "Puesto Oeste",
                    sistema.traerPersonal(33111222),
                    6, 2, 45
            );
            System.out.println("PuestoDesarmable 'PD99999999' agregado correctamente.");

            // Error esperado: código duplicado
            sistema.agregarFoodTruck(
                    "FT12345678", "Otro Truck",
                    sistema.traerPersonal(30111222),
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

            // 2. Pasamos el objeto UnidadDeVenta al método del Sistema
            System.out.println("Agregando: " + plato1.getNombre() + " a FT12345678 -> " + sistema.agregarPlatoAUnidad(plato1, sistema.traerUnidad("FT12345678")));
            System.out.println("Agregando: " + plato2.getNombre() + " a FT12345678 -> " + sistema.agregarPlatoAUnidad(plato2, sistema.traerUnidad("FT12345678")));
            System.out.println("Agregando: " + plato1Duplicado.getNombre() + " (duplicado) a FT12345678 -> " + sistema.agregarPlatoAUnidad(plato1Duplicado, sistema.traerUnidad("FT12345678")));

            System.out.println("Platos en " + sistema.traerUnidad("FT12345678").getNombreComercial() + ": " + sistema.traerUnidad("FT12345678").getLstPlatos().size());

            // Agregar platos a la nueva unidad FT99999999 y PD99999999
            Plato taco = new Plato(4, "Tacos de Carne", 4000.0, 1800.0);
            Plato empanada = new Plato(5, "Empanada Criolla", 1200.0, 500.0);
            System.out.println("Agregando: " + taco.getNombre() + " a FT99999999 -> " + sistema.agregarPlatoAUnidad(taco, sistema.traerUnidad("FT99999999")));
            System.out.println("Agregando: " + empanada.getNombre() + " a PD99999999 -> " + sistema.agregarPlatoAUnidad(empanada, sistema.traerUnidad("PD99999999")));
        } catch (Exception e) {
            System.err.println("Error al agregar platos: " + e.getMessage());
        }

        System.out.println("\n=========================================================");
        System.out.println("CASO DE USO 3: CÁLCULO DE CANON");
        System.out.println("=========================================================");
        try {
            System.out.println("Canon de Food Truck '" + sistema.traerUnidad("FT12345678").getNombreComercial() + "': $" + sistema.traerUnidad("FT12345678").calcularCanon(sistema.traerFestival("Festival Verano 2025")));

            System.out.println("Canon de Puesto Desarmable '" + sistema.traerUnidad("PD12345678").getNombreComercial() + "': $" + sistema.traerUnidad("PD12345678").calcularCanon(sistema.traerFestival("Festival Verano 2025")));

            System.out.println("Canon de Food Truck '" + sistema.traerUnidad("FT99999999").getNombreComercial() + "': $" + sistema.traerUnidad("FT99999999").calcularCanon(sistema.traerFestival("Festival Primavera 2025")));
        } catch (Exception e) {
            System.err.println("Error al calcular el canon: " + e.getMessage());
        }

        System.out.println("\n=========================================================");
        System.out.println("CASO DE USO 4: LIQUIDACIÓN DE HABERES");
        System.out.println("=========================================================");
        try {
            System.out.println("Sueldo de Cocinero (" + sistema.traerPersonal(30111222).getNombre() + " " + sistema.traerPersonal(30111222).getApellido() + "): $" + sistema.traerPersonal(30111222).calcularSueldo());

            System.out.println("Sueldo de Cajero (" + sistema.traerPersonal(28999333).getNombre() + " " + sistema.traerPersonal(28999333).getApellido() + "): $" + sistema.traerPersonal(28999333).calcularSueldo());
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
            // 3. Agregamos el plato al pedido usando el método de la clase Pedido
            sistema.traerPedido(1).agregarPlato(sistema.traerUnidad("FT12345678").traerPlato("Hamburguesa Doble"), 2);
            System.out.println("Se agregaron 2 " + sistema.traerUnidad("FT12345678").traerPlato("Hamburguesa Doble").getNombre() + " al pedido ID " + sistema.traerPedido(1).getIdPedido());
            
            sistema.traerPedido(1).agregarPlato(sistema.traerUnidad("FT12345678").traerPlato("Papas Fritas"), 1);
            System.out.println("Se agregaron 1 " + sistema.traerUnidad("FT12345678").traerPlato("Papas Fritas").getNombre() + " al pedido ID " + sistema.traerPedido(1).getIdPedido());
            
            System.out.println("Monto total del pedido: $" + sistema.traerPedido(1).calcularMontoTotal());
            
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
            System.out.println("Recaudación del " + sistema.traerFestival("Festival Verano 2025").getNombre() + ":");
            if(sistema.reporteRecaudacion(sistema.traerFestival("Festival Verano 2025")).isEmpty()) {
                System.out.println("No hubo ventas registradas para este festival.");
            } else {
                for (ReporteVenta rv : sistema.reporteRecaudacion(sistema.traerFestival("Festival Verano 2025"))) {
                    System.out.println(rv);
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
            sistema.traerUnidad("FT12345678").agregarPersonal(sistema.traerPersonal(30111222)); // Ana Gomez (Cocinero, sueldo 120000)
            System.out.println("Personal asignado correctamente.");
        } catch (Exception e) {
            System.err.println("Error al asignar personal: " + e.getMessage());
        }
        
        // 3. Agregar Pedido 2 (fecha = hoy) con los items descritos por el compañero
        try {
            List<ItemPlatoPedido> items = new ArrayList<>();
            items.add(new ItemPlatoPedido(sistema.traerUnidad("FT12345678").traerPlato("Hamburguesa"), 13));
            items.add(new ItemPlatoPedido(sistema.traerUnidad("FT12345678").traerPlato("Papas Fritas"), 22));
            items.add(new ItemPlatoPedido(sistema.traerUnidad("FT12345678").traerPlato("Pizza"), 5));
            items.add(new ItemPlatoPedido(sistema.traerUnidad("FT12345678").traerPlato("Rabas"), 10));
            items.add(new ItemPlatoPedido(sistema.traerUnidad("FT12345678").traerPlato("Cornalitos"), 7));
            
            sistema.agregarPedido(LocalDate.now(), "Festival Verano 2025", "FT12345678", items);
            System.out.println("Pedido de hoy con items agregado correctamente.");

            // Agregar pedido para la nueva unidad en Festival Primavera 2025
            List<ItemPlatoPedido> itemsPrimavera = new ArrayList<>();
            itemsPrimavera.add(new ItemPlatoPedido(sistema.traerUnidad("FT99999999").traerPlato("Tacos de Carne"), 50));
            sistema.agregarPedido(LocalDate.now(), "Festival Primavera 2025", "FT99999999", itemsPrimavera);
            System.out.println("Pedido para FT99999999 en Festival Primavera 2025 agregado correctamente.");
        } catch (Exception e) {
            System.err.println("Error al agregar pedido: " + e.getMessage());
        }

        // 4. Agregar Pedido 3 (fecha = hace 5 días)
        try {
            List<ItemPlatoPedido> items2 = new ArrayList<>();
            items2.add(new ItemPlatoPedido(sistema.traerUnidad("FT12345678").traerPlato("Pizza"), 1)); // Venta: 25000, Costo: 10000
            
            sistema.agregarPedido(LocalDate.now().minusDays(5), "Festival Verano 2025", "FT12345678", items2);
            System.out.println("Pedido de hace 5 días agregado correctamente.");
        } catch (Exception e) {
            System.err.println("Error al agregar pedido del pasado: " + e.getMessage());
        }

        // 5. Calcular la Rentabilidad Neta (CU 8) - Todos los pedidos
        try {
            double rentabilidadTotal = sistema.traerUnidad("FT12345678").calcularRentabilidadNeta(sistema.getLstPedidos());
            System.out.println("Rentabilidad Neta Total (CU 8): $" + rentabilidadTotal);
        } catch (Exception e) {
            System.err.println("Error al calcular rentabilidad total: " + e.getMessage());
        }

        // 6. Calcular la Rentabilidad Neta entre dos fechas (CU 9)
        // Rango A: incluye solo hoy y ayer (excluye el de hace 5 días)
        try {
            LocalDate desde = LocalDate.now().minusDays(1);
            LocalDate hasta = LocalDate.now().plusDays(1);
            double rentabilidadRangoA = sistema.traerUnidad("FT12345678").calcularRentabilidadNeta(sistema.getLstPedidos(), desde, hasta);
            System.out.println("Rentabilidad Neta Rango A (hoy +/- 1 día) (CU 9): $" + rentabilidadRangoA);
        } catch (Exception e) {
            System.err.println("Error al calcular rentabilidad Rango A: " + e.getMessage());
        }

        // Rango B: incluye todo (hace 10 días a hoy + 1)
        try {
            LocalDate desde2 = LocalDate.now().minusDays(10);
            LocalDate hasta2 = LocalDate.now().plusDays(1);
            double rentabilidadRangoB = sistema.traerUnidad("FT12345678").calcularRentabilidadNeta(sistema.getLstPedidos(), desde2, hasta2);
            System.out.println("Rentabilidad Neta Rango B (hace 10 días a hoy) (CU 9): $" + rentabilidadRangoB);
        } catch (Exception e) {
            System.err.println("Error al calcular rentabilidad Rango B: " + e.getMessage());
        }

        System.out.println("\n=========================================================");
        System.out.println("CASO DE USO 10: RANKING DE UNIDADES");
        System.out.println("=========================================================");
        try {
            
            
                System.out.println("Ranking de Unidades para 'Festival Verano 2025':");
                List<UnidadDeVenta> ranking = sistema.rankingUnidad(sistema.traerFestival("Festival Verano 2025"));
                for (UnidadDeVenta u : ranking) {
                    double rec = u.calcularRecaudacion(sistema.getLstPedidos(), sistema.traerFestival("Festival Verano 2025"));
                    System.out.println(u.getNombreComercial() + " (" + u.getCodigo() + ") - Recaudación: $" + rec);
                }

                System.out.println("\nRanking de Unidades General (todos los festivales):");
                List<UnidadDeVenta> rankingGral = sistema.rankingUnidades();
                for (UnidadDeVenta u : rankingGral) {
                    double rec = u.calcularRecaudacion(sistema.getLstPedidos());
                    System.out.println(u.getNombreComercial() + " (" + u.getCodigo() + ") - Recaudación General: $" + rec);
                }
            

            // Error esperado: Festival inexistente
            System.out.println("\nProbando ranking con festival inexistente (debe lanzar excepción):");
            sistema.rankingUnidad(null);
        } catch (Exception e) {
            System.err.println("Error esperado (Festival inexistente): " + e.getMessage());
        }

        System.out.println("\n=========================================================");
        System.out.println("CASO DE USO 11: PLATO ESTRELLA");
        System.out.println("=========================================================");
        try {
            System.out.println("--- 11.1 CASO EXITOSO ---");
            Festival festivalVerano = sistema.traerFestival("Festival Verano 2025");
            UnidadDeVenta ftNorte = sistema.traerUnidad("FT12345678");
            
            Plato estrella = sistema.platoEstrella(festivalVerano, ftNorte);
            System.out.println("Plato estrella de 'Food Truck Norte' en 'Festival Verano 2025': " 
                    + (estrella != null ? estrella.getNombre() : "Ninguno"));

            UnidadDeVenta ftSur = sistema.traerUnidad("FT99999999");
            Festival festivalPrimavera = sistema.traerFestival("Festival Primavera 2025");
            Plato estrellaSur = sistema.platoEstrella(ftSur, festivalPrimavera);
            System.out.println("Plato estrella de 'Food Truck Sur' en 'Festival Primavera 2025' (overload): " 
                    + (estrellaSur != null ? estrellaSur.getNombre() : "Ninguno"));

            System.out.println("\n--- 11.2 CASO SIN PEDIDOS ---");
            UnidadDeVenta puestoOeste = sistema.traerUnidad("PD99999999");
            Plato estrellaVacia = sistema.platoEstrella(festivalVerano, puestoOeste);
            System.out.println("Plato estrella de 'Puesto Oeste' en 'Festival Verano 2025' (sin ventas): " 
                    + (estrellaVacia != null ? estrellaVacia.getNombre() : "Ninguno (OK)"));

            System.out.println("\n--- 11.3 CASOS DE ERROR (VALIDACIONES) ---");
            try {
                sistema.platoEstrella(null, ftNorte);
            } catch (Exception e) {
                System.out.println("Error esperado (festival null): " + e.getMessage());
            }

            try {
                sistema.platoEstrella(festivalVerano, null);
            } catch (Exception e) {
                System.out.println("Error esperado (unidad null): " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Error inesperado en CU 11: " + e.getMessage());
            e.printStackTrace();
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
        System.out.println("CASO DE USO 12: AUDITORÍA DE PERSONAL DEL FESTIVAL");
        System.out.println("=========================================================");
        try {
            System.out.println("--- 12.1 PERSONAL DEL FESTIVAL VERANO 2025 ---");
            Festival festivalVerano = sistema.traerFestival("Festival Verano 2025");
            List<Personal> personalVerano = sistema.auditoriaPersonalFestival(festivalVerano);
            System.out.println("Personal que trabajó:");
            for (Personal p : personalVerano) {
                System.out.println("- " + p.getNombre() + " " + p.getApellido() + " (DNI: " + p.getDni() + ")");
            }

            System.out.println("\n--- 12.2 PERSONAL DEL FESTIVAL PRIMAVERA 2025 ---");
            Festival festivalPrimavera = sistema.traerFestival("Festival Primavera 2025");
            // Agreguemos otro miembro del personal a la unidad FT99999999 para probar múltiples trabajadores
            Personal martin = sistema.traerPersonal(33111222); // Martin Perez
            if (martin != null) {
                sistema.traerUnidad("FT99999999").agregarPersonal(martin);
            }
            List<Personal> personalPrimavera = sistema.auditoriaPersonalFestival(festivalPrimavera);
            System.out.println("Personal que trabajó:");
            for (Personal p : personalPrimavera) {
                System.out.println("- " + p.getNombre() + " " + p.getApellido() + " (DNI: " + p.getDni() + ")");
            }

            System.out.println("\n--- 12.3 CASOS DE ERROR (VALIDACIONES) ---");
            try {
                sistema.auditoriaPersonalFestival(null);
            } catch (Exception e) {
                System.out.println("Error esperado (festival null): " + e.getMessage());
            }

            try {
                Festival festivalInexistente = new Festival(99, "Inexistente", "Otoño", new Date(), new Date(), 0, 0, 0, 0);
                sistema.auditoriaPersonalFestival(festivalInexistente);
            } catch (Exception e) {
                System.out.println("Error esperado (festival inexistente): " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Error inesperado en CU 12: " + e.getMessage());
            e.printStackTrace();
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