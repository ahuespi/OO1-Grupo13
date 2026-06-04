import java.time.LocalDate;

public class MainTest {

    public static void main(String[] args) {
        try {
            Sistema sistema = new Sistema();

            Cocinero responsable = new Cocinero(
                    1,
                    "Ana",
                    "Gomez",
                    30111222,
                    LocalDate.of(1990, 5, 10),
                    LocalDate.of(2020, 3, 1),
                    "Parrilla",
                    20000
            );

            sistema.agregarFoodTruck(
                    "FT12345678",
                    "Food Truck Norte",
                    responsable,
                    10,
                    "ABC123",
                    true
            );

            sistema.agregarPuestoDesarmable(
                    "PD12345678",
                    "Puesto Sur",
                    responsable,
                    8,
                    3,
                    60
            );

            UnidadDeVenta foodTruck = sistema.traerUnidad("FT12345678");
            UnidadDeVenta puesto = sistema.traerUnidad("PD12345678");

            System.out.println("Canon FoodTruck: " + foodTruck.calcularCanon());
            System.out.println("Canon Puesto: " + puesto.calcularCanon());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}