mermaid

classDiagram

class Sistema {
    -List~Festival~ lstFestivales
    -List~UnidadDeVenta~ lstUnidades
    -List~Personal~ lstPersonal
    -List~Pedido~ lstPedidos
    +agregarFestival()
    +agregarUnidad()
    +agregarPersonal()
    +buscarPersonalPorDNI(String dni) Personal
    +buscarUnidadPorCodigo(String codigo) UnidadDeVenta
    +registrarPedido(Date fecha, Festival festival, UnidadDeVenta unidad)
    +filtrarPersonalPorEdad(LocalDate desde, LocalDate hasta) List~Personal~
    +rankingUnidad(Festival festival) List~UnidadDeVenta~
    +calcularRecaudacion(Festival festival) List~UnidadDeVenta~
    +platoEstrella(Festival festival, UnidadDeVenta unidad) Plato
    +auditoriaPersonalFestival(Festival festival) List~Personal~
    +top3UnidadesCanonMayor(Festival festival) List~ReporteCanon~
}

class Festival {
    -int id
    -String nombre
    -LocalDate fechaInicio
    -LocalDate fechaFin
    -String temporada
    -double costoSuperficie
    -double costoMontaje
    -double plusElectricidad
    -double costo
}



class UnidadDeVenta {
    <<abstract>>
    -int id
    -String codigo
    -String nombreComercial
    -Personal personal
    -int superficieMetroCuadrado
    -List~Personal~ lstPersonal
    -List~Plato~ lstPlatos
    -float plusPorCategoria
    -double sueldoBase
    +calcularCanon() float*
    +calcularSueldoStaff() float
    +rentabilidadNeta() float
    +rentabilidadNetaPorFecha() float
}

class FoodTruck {
    -String patente
    -boolean requiereConexion
    +calcularCanon() float
}

class PuestoDesarmable {
    -int cantidadCarpas
    -int tiempoEnMinutos
    +calcularCanon() float
}

class Personal {
    <<abstract>>
    -int id
    -String nombre
    -String apellido
    -long dni
    -LocalDate fechaNacimiento
    -LocalDate fechaIngreso
    +esMayorDeEdad() boolean
    +calcularAntiguedad() int
    +liquidarHaberes() float*
}

class Cocinero {
    -String especialidadCulinaria
    -String categoria
    +liquidarHaberes() float
}

class Cajero {
    -String turno
    +liquidarHaberes() float
}

class Plato {
    -int id
    -String nombre
    -double precio
    -double costo
}

class ItemPlatoPedido {
    -Plato plato
    -int cantidad
    +subtotalVenta() double
    +subtotalCosto() double
}

class Pedido {
    -int id
    -LocalDate fecha
    -Festival festival
    -UnidadDeVenta unidad
    -List~ItemPlatoPedido~ items
    +agregarPlato(Plato, int)
    +calcularMontoTotal() double
}

class ReporteVenta {
    <<non-persistent>>
    -List~UnidadDeVenta~ lstUnidadesDeVenta
    -float recaudacionTotal
    -Festival festival
}

class ReporteCanon {
    <<non-persistent>>
    -List~UnidadDeVenta~ nombreComer
    -float recaudacionTotal
    -Festival festival
}

%% Relaciones
Sistema "1" *-- "*" Festival
Sistema "1" *-- "*" UnidadDeVenta
Sistema "1" *-- "*" Personal
Sistema "1" *-- "*" Pedido


UnidadDeVenta <|-- FoodTruck
UnidadDeVenta <|-- PuestoDesarmable
Personal <|-- Cocinero
Personal <|-- Cajero

UnidadDeVenta "1" o-- "*" Personal : lstPersonal
UnidadDeVenta "1" o-- "1" Personal : personal a cargo
UnidadDeVenta "1" *-- "*" Plato : lstPlatos

Pedido "*" --> "1" Festival
Pedido "*" --> "1" UnidadDeVenta
Pedido "1" *-- "*" ItemPlatoPedido : contiene
ItemPlatoPedido "*" --> "1" Plato : referencia