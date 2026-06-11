import java.time.LocalDate;
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

            sistema.agregarCajero(
                    "Juan", "Perez",
                    34111222,
                    LocalDate.of(1991, 7, 15),
                    LocalDate.of(2020, 1, 1),
                    "mañana"
            );
            System.out.println("Cajero adicional (para CU 7) agregado correctamente.");

            sistema.agregarCocinero(
                    "Maria", "Rodriguez",
                    35111222,
                    LocalDate.of(1990, 12, 5),
                    LocalDate.of(2021, 2, 1),
                    "Pasteleria",
                    22000
            );
            System.out.println("Cocinera adicional (para CU 7) agregada correctamente.");

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
            LocalDate inicioVerano = LocalDate.of(2025, 1, 10);
            LocalDate finVerano    = LocalDate.of(2025, 1, 20);
            sistema.agregarFestival(
                    "Festival Verano 2025", "Verano",
                    inicioVerano, finVerano,
                    500.0, 300.0, 150.0, 80000.0, 5000.0
            );
            System.out.println("Festival 'Festival Verano 2025' agregado correctamente.");

            LocalDate inicioInvierno = LocalDate.of(2025, 7, 5);
            LocalDate finInvierno    = LocalDate.of(2025, 7, 15);
            sistema.agregarFestival(
                    "Festival Invierno 2025", "Invierno",
                    inicioInvierno, finInvierno,
                    450.0, 280.0, 0.0, 75000.0, 5000.0
            );
            System.out.println("Festival 'Festival Invierno 2025' agregado correctamente.");

            LocalDate inicioPrimavera = LocalDate.of(2025, 9, 21);
            LocalDate finPrimavera    = LocalDate.of(2025, 9, 30);
            sistema.agregarFestival(
                    "Festival Primavera 2025", "Primavera",
                    inicioPrimavera, finPrimavera,
                    480.0, 290.0, 100.0, 78000.0, 5000.0
            );
            System.out.println("Festival 'Festival Primavera 2025' agregado correctamente.");

            // Error esperado: nombre duplicado
            sistema.agregarFestival(
                    "Festival Verano 2025", "Verano",
                    inicioVerano, finVerano,
                    500.0, 300.0, 150.0, 80000.0, 5000.0
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
            System.out.println("Agregando: Hamburguesa Doble a FT12345678 -> " + sistema.agregarPlato("FT12345678", "Hamburguesa Doble", 5000.0, 2000.0));
            System.out.println("Agregando: Papas Fritas a FT12345678 -> " + sistema.agregarPlato("FT12345678", "Papas Fritas", 2500.0, 1000.0));
            try {
                System.out.println("Agregando: Hamburguesa Doble (duplicado) a FT12345678 -> " + sistema.agregarPlato("FT12345678", "Hamburguesa Doble", 6000.0, 2500.0));
            } catch (Exception e) {
                System.out.println("Error esperado (Plato duplicado): " + e.getMessage());
            }

            System.out.println("Platos en " + sistema.traerUnidad("FT12345678").getNombreComercial() + ": " + sistema.traerUnidad("FT12345678").getLstPlatos().size());

            // Agregar platos a la nueva unidad FT99999999 y PD99999999
            System.out.println("Agregando: Tacos de Carne a FT99999999 -> " + sistema.agregarPlato("FT99999999", "Tacos de Carne", 4000.0, 1800.0));
            System.out.println("Agregando: Empanada Criolla a PD99999999 -> " + sistema.agregarPlato("PD99999999", "Empanada Criolla", 1200.0, 500.0));
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
            Festival festivalVerano = sistema.traerFestival("Festival Verano 2025");
            System.out.println("Sueldo de Cocinero (" + sistema.traerPersonal(30111222).getNombre() + " " + sistema.traerPersonal(30111222).getApellido() + "): $" + sistema.traerPersonal(30111222).calcularSueldo(festivalVerano));

            System.out.println("Sueldo de Cajero (" + sistema.traerPersonal(28999333).getNombre() + " " + sistema.traerPersonal(28999333).getApellido() + "): $" + sistema.traerPersonal(28999333).calcularSueldo(festivalVerano));
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
            // 3. Agregamos el plato al pedido usando el método de la clase Sistema
            sistema.agregarItemAPedido(1, sistema.traerUnidad("FT12345678").traerPlato("Hamburguesa Doble"), 2);
            System.out.println("Se agregaron 2 " + sistema.traerUnidad("FT12345678").traerPlato("Hamburguesa Doble").getNombre() + " al pedido ID " + sistema.traerPedido(1).getIdPedido());
            
            sistema.agregarItemAPedido(1, sistema.traerUnidad("FT12345678").traerPlato("Papas Fritas"), 1);
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
        System.out.println("CASO DE USO 7: FILTRAR PERSONAL POR EDAD");
        System.out.println("=========================================================");
        try {
            LocalDate fechaDesde = LocalDate.of(1990, 1, 1);
            LocalDate fechaHasta = LocalDate.of(1991, 12, 31);
            System.out.println("Personal nacido entre " + fechaDesde + " y " + fechaHasta + ":");
            List<Personal> filtrados = sistema.filtroPersonalPorEdad(fechaDesde, fechaHasta);
            for (Personal p : filtrados) {
                System.out.println("- " + p.getNombre() + " " + p.getApellido() + " (F. Nac: " + p.getFechaNacimiento() + ")");
            }
        } catch (Exception e) {
            System.err.println("Error al filtrar personal por edad: " + e.getMessage());
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
            sistema.agregarPedido(LocalDate.now(), "Festival Verano 2025", "FT12345678");
            int idPedido2 = sistema.getLstPedidos().size();
            sistema.agregarItemAPedido(idPedido2, sistema.traerUnidad("FT12345678").traerPlato("Hamburguesa"), 13);
            sistema.agregarItemAPedido(idPedido2, sistema.traerUnidad("FT12345678").traerPlato("Papas Fritas"), 22);
            sistema.agregarItemAPedido(idPedido2, sistema.traerUnidad("FT12345678").traerPlato("Pizza"), 5);
            sistema.agregarItemAPedido(idPedido2, sistema.traerUnidad("FT12345678").traerPlato("Rabas"), 10);
            sistema.agregarItemAPedido(idPedido2, sistema.traerUnidad("FT12345678").traerPlato("Cornalitos"), 7);
            
            System.out.println("Pedido de hoy con items agregado correctamente.");

            // Agregar pedido para la nueva unidad en Festival Primavera 2025
            sistema.agregarPedido(LocalDate.now(), "Festival Primavera 2025", "FT99999999");
            int idPedidoPrimavera = sistema.getLstPedidos().size();
            sistema.agregarItemAPedido(idPedidoPrimavera, sistema.traerUnidad("FT99999999").traerPlato("Tacos de Carne"), 50);
            
            System.out.println("Pedido para FT99999999 en Festival Primavera 2025 agregado correctamente.");
        } catch (Exception e) {
            System.err.println("Error al agregar pedido: " + e.getMessage());
        }

        // 4. Agregar Pedido 3 (fecha = hace 5 días)
        try {
            sistema.agregarPedido(LocalDate.now().minusDays(5), "Festival Verano 2025", "FT12345678");
            int idPedidoPasado = sistema.getLstPedidos().size();
            sistema.agregarItemAPedido(idPedidoPasado, sistema.traerUnidad("FT12345678").traerPlato("Pizza"), 1); // Venta: 25000, Costo: 10000
            
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
                Festival festivalInexistente = new Festival(99, "Inexistente", "Otoño", LocalDate.now(), LocalDate.now(), 0, 0, 0, 0, 5000.0);
                sistema.auditoriaPersonalFestival(festivalInexistente);
            } catch (Exception e) {
                System.out.println("Error esperado (festival inexistente): " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Error inesperado en CU 12: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\n=========================================================");
        System.out.println("CASO DE USO 13: UNIDADES CON MAYOR CANON");
        System.out.println("=========================================================");
        try {
            // Creamos un nuevo festival de prueba para no alterar el flujo de los anteriores
            LocalDate inicioTest = LocalDate.of(2026, 1, 1);
            LocalDate finTest    = LocalDate.of(2026, 1, 10);
            sistema.agregarFestival("Festival Canon 2026", "Verano", inicioTest, finTest, 1000.0, 10.0, 500.0, 50000.0, 5000.0);
            Festival fest = sistema.traerFestival("Festival Canon 2026");

            // Creamos 4 unidades
            // Unidad A: Food Truck, superficie 20, requiere electrica -> Canon = 20*1000 + 500 = 20500
            sistema.agregarFoodTruck("FT0000000A", "Food Truck A", sistema.traerPersonal(30111222), 20, "AAA111", true);
            UnidadDeVenta ua = sistema.traerUnidad("FT0000000A");
            fest.agregarUnidad(ua);

            // Unidad B: Food Truck, superficie 10, requiere electrica -> Canon = 10*1000 + 500 = 10500
            sistema.agregarFoodTruck("FT0000000B", "Food Truck B", sistema.traerPersonal(30111222), 10, "BBB222", true);
            UnidadDeVenta ub = sistema.traerUnidad("FT0000000B");
            fest.agregarUnidad(ub);

            // Unidad C: Food Truck, superficie 30, no requiere electrica -> Canon = 30*1000 = 30000
            sistema.agregarFoodTruck("FT0000000C", "Food Truck C", sistema.traerPersonal(30111222), 30, "CCC333", false);
            UnidadDeVenta uc = sistema.traerUnidad("FT0000000C");
            fest.agregarUnidad(uc);

            // Unidad D: Puesto Desarmable, superficie 15, montaje 60 min -> Canon = 15*1000 - 60*10 = 15000 - 600 = 14400
            sistema.agregarPuestoDesarmable("PD0000000D", "Puesto D", sistema.traerPersonal(30111222), 15, 2, 60);
            UnidadDeVenta ud = sistema.traerUnidad("PD0000000D");
            fest.agregarUnidad(ud);

            // Los cánones esperados para este festival:
            // Food Truck C (uc): 30000.0
            // Food Truck A (ua): 20500.0
            // Puesto D (ud): 14400.0
            // Food Truck B (ub): 10500.0

            System.out.println("--- 13.1 CASO EXITOSO (Top 3 de 4 unidades) ---");
            List<ReporteMayoresCanon> reporte = sistema.reporteMayoresCanon(fest);
            for (ReporteMayoresCanon r : reporte) {
                System.out.println("- " + r.getNombreComercial() + " (" + r.getCodigo() + ") | Tipo: " + r.getTipoUnidad() + " | Canon: $" + r.getCanon());
            }

            System.out.println("\n--- 13.2 CASO CON MENOS DE 3 UNIDADES ---");
            LocalDate inicioTestPocos = LocalDate.of(2026, 2, 1);
            LocalDate finTestPocos    = LocalDate.of(2026, 2, 5);
            sistema.agregarFestival("Festival Canon Pocos", "Verano", inicioTestPocos, finTestPocos, 1000.0, 10.0, 500.0, 50000.0, 5000.0);
            Festival festPocos = sistema.traerFestival("Festival Canon Pocos");
            // Le agregamos solo 2 unidades
            festPocos.agregarUnidad(ua);
            festPocos.agregarUnidad(ub);
            List<ReporteMayoresCanon> reportePocos = sistema.reporteMayoresCanon(festPocos);
            System.out.println("Cantidad de reportes devueltos (debe ser 2): " + reportePocos.size());
            for (ReporteMayoresCanon r : reportePocos) {
                System.out.println("- " + r.getNombreComercial() + " (" + r.getCodigo() + ") | Canon: $" + r.getCanon());
            }

            System.out.println("\n--- 13.3 CASOS DE ERROR (VALIDACIONES) ---");
            try {
                sistema.reporteMayoresCanon(null);
            } catch (Exception e) {
                System.out.println("Error esperado (festival null): " + e.getMessage());
            }

            try {
                Festival festInexistente = new Festival(999, "Inexistente", "Otoño", LocalDate.now(), LocalDate.now(), 0, 0, 0, 0, 5000.0);
                sistema.reporteMayoresCanon(festInexistente);
            } catch (Exception e) {
                System.out.println("Error esperado (festival inexistente): " + e.getMessage());
            }

        } catch (Exception e) {
            System.err.println("Error inesperado en CU 13: " + e.getMessage());
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
    }
}