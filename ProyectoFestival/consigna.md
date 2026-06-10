# Licenciatura en Sistemas - Orientación a Objetos I – 2026

## Equipo docente

- Mg. María Alejandra Vranic
- Esp. Lic. Gustavo Siciliano
- Esp. Lic. Ezequiel Scordamaglia
- Lic. Oscar Ruina
- Lic. Nicolás Borea
- Lic. Rita Bustos

### Auxiliares docentes

- Bonino Ezequiel
- Mendez Livia
- Obando Rodrigo
- Ramil Elías
- Cofman Santiago
- Saucedo Ulises Justo
- Savall Arián
- Ginarte Delgado Emiliano
- Gómez Eric

# Laboratorio Grupal 2026: Sistema de Gestión "Epicentro Gourmet"

El centro de convenciones y predio ferial "Epicentro Gourmet" está organizando una serie de festivales temáticos anuales y, para optimizar la administración de estos eventos, requiere un sistema que permita gestionar las unidades de venta, el personal especializado de cada unidad y el rendimiento económico de cada jornada.

Se debe diseñar una clase de negocio única denominada **Sistema**, la cual será responsable de administrar las listas globales de la aplicación, y asegurando que cada entidad posea un identificador numérico único y automático.

De cada festival organizado se debe conocer su nombre, la temporada en la que ocurre y sus fechas de inicio y fin.

Para que estos festivales funcionen, se habilitan unidades de venta que se identifican por un nombre comercial, un responsable a cargo (que es uno de los miembros del Staff), la superficie en m² que ocupan y un código único de 10 caracteres que debe contar con una lógica de validación propia.

Existen unidades de tipo **Food Truck**, que registran su patente y si requieren o no conexión eléctrica para operar, y unidades de tipo **Puesto Desarmable**, que registran la cantidad de carpas que los componen y el tiempo que toma su montaje en minutos.

Cada una de estas unidades debe administrar su propia lista de platos ofrecidos y su lista de personal asignado o staff.

El personal del predio debe ser mayor de edad y se registra con su nombre, apellido, dni, fecha de nacimiento y una fecha de ingreso, la cual es fundamental para determinar su antigüedad.

Todos los empleados poseen un sueldo base, pero existen particularidades según su rol:

- Quienes se desempeñan como cocineros poseen una especialidad culinaria y perciben un plus fijo por categoría.
- Los cajeros registran su turno de trabajo, ya sea mañana o noche.

Por otro lado, los platos del menú cuentan con un nombre, un precio de venta y un costo de producción.

Cada vez que se realiza una operación, se genera un **Pedido** que debe registrar:

- La fecha de la transacción.
- El festival donde ocurrió.
- La unidad de venta que realizó la entrega.
- Los platos.
- La cantidad pedida de cada plato.

Además, cada festival establece una serie de costos que pueden aplicarse a todas las unidades o algún tipo particular de unidad.

Los costos son:

- Costo por superficie.
- Costo por montaje.
- Plus por uso de electricidad.
- Sueldo base.

---

# Hito 2: Casos de Uso

Definir e implementar los siguientes métodos dentro de la clase que corresponda según el diseño y realizar sus respectivos Tests Unitarios.

## 1. Altas y Bajas

Métodos para agregar festivales, unidades y personal a las listas de la clase Sistema.

## 2. Búsqueda por Atributo Único

Localizar cualquier entidad:

- Personal por DNI.
- Unidad por Código.

## 3. Cálculo de Canon

Método que devuelve el monto a pagar por una unidad.

### Food Truck

```text
(Superficie * $500) + $2000 si requiere conexión eléctrica
```

### Puesto

```text
(Superficie * $500) - (Tiempo de Montaje * $10)
```

## 4. Liquidación de Haberes

Calcular el sueldo del personal según su tipo.

### Cocinero

```text
Sueldo Base ($100.000) + Plus por Categoría
```

### Cajero

```text
Sueldo Base ($100.000) + (Antigüedad × $5000 por cada año)
```

## 5. Registro de Pedido Validado

Método para agregar un pedido que invoque internamente al CU #2 para validar la existencia de la Unidad y el Festival.

## 6. Reporte de Recaudación

Dado un festival, retornar la lista de unidades y su recaudación total.

Utilizar la clase:

```text
ReporteVenta
```

(no persistente)

## 7. Filtro de Personal por Edad

Retornar una lista de empleados nacidos entre dos fechas.

## 8. Cálculo de Rentabilidad Neta

Calcular la ganancia de una unidad:

```text
Pedidos Totales
- Costos de Platos
- Sueldos
- Canon
```

## 9. Cálculo de Rentabilidad Neta entre dos Fechas

Para una unidad, calcular la rentabilidad neta entre dos fechas.

## 10. Ranking de Unidades

Generar una lista de objetos ordenada de mayor a menor recaudación.

## 11. Plato Estrella

Dada una unidad, devolver el objeto Plato que registró mayor cantidad de pedidos en un festival particular.

## 12. Auditoría de Personal del Festival

Retornar la lista de todo el personal que trabajó en un festival específico.

## 13. Unidades con Mayor Canon

Dado un festival, devolver las 3 unidades que más gastaron en canon indicando:

- Nombre comercial.
- Código.
- Tipo de unidad.
- Canon.

Utilizar la clase:

```text
ReporteMayoresCanon
```

(no persistente)

---

# Notas

## 1.

Tener en cuenta en la funcionalidad no utilizar datos hardcodeados en los métodos, sino pensar en alguna clase para encapsular esos datos como por ejemplo:

```text
Sueldo Base = $100.000
```

## 2.

Diseñar el Diagrama de Clases utilizando aproximadamente:

- 10 clases de tipo dato.
- 1 clase Sistema.

para resolver el modelo de negocios.

---

# Calendario

| Semana | T. Mañana | T. Noche | Corrección |
|----------|------------|-----------|-------------|
| 5 | 22/04/2026 | 23/04/2026 | Hito 1: Diagrama de clases |
| 6 | 29/04/2026 | 29/04/2026 | Hito 2: Casos de uso (firma de c/u) |