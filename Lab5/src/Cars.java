//Patrycja Marucińska 42746
//Lab 5
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

class Car {
    protected String name;
    protected String licensePlate;
    public Car(String name, String licensePlate) {
        this.name = name;
        this.licensePlate = licensePlate;
    }
    public String getName() {
        return String.format("%s %s", this.getType(), this.name);
    }
    protected String getType() {
        return "samochód osobowy";
    }
    public String getLicensePlate() {
        return this.licensePlate;
    }
}
class Truck extends Car {
    public Truck(String name, String licensePlate) {
        super(name, licensePlate);
    }
    @Override
    protected String getType() {
        return "samochód dostawczy";
    }
}
class Camper extends Car {
    public Camper(String name, String licensePlate) {
        super(name, licensePlate);
    }
    @Override
    protected String getType() {
        return "kamper";
    }
}
class ParkingPriceList {
    private final Map<String, Double> licensePlatePrices;
    public ParkingPriceList() {
        this.licensePlatePrices = new HashMap<>();
    }
    public void addFreeParking(String licensePlate) {
        this.licensePlatePrices.put(licensePlate, 0.0);
    }
    public void removeFreeParking(String licensePlate) {
        this.licensePlatePrices.remove(licensePlate);
    }
    public double getPayment(Car car) {
        if (licensePlatePrices.containsKey(car.getLicensePlate())) {
            return 0.0; // Parking za darmo
        }
        return switch (car.getClass().getSimpleName()) {
            case "Truck" -> 5.0;
            default -> 4.0;
        };
    }
}
public class Cars {
    public static void main(String[] args) {
        Collection<Car> vehicles = new ArrayList<>();
        ParkingPriceList priceList = new ParkingPriceList();
        vehicles.add(new Car("Volkswagen Passat", "ABC123"));
        vehicles.add(new Car("BMW E46","XYZ263"));
        vehicles.add(new Truck("Volkswagen Transporter", "DEF456"));
        vehicles.add(new Truck("Mercedes Actros","PWH010"));
        vehicles.add(new Camper("Ford Camper", "GHI789"));
        vehicles.add(new Camper("Opel Camper","LRD249"));
        priceList.addFreeParking("ABC123"); // Dodanie pojazdu do darmowego parkingu
        priceList.addFreeParking("GHI789"); // Dodanie kampera do darmowego parkingu

        for (Car vehicle : vehicles) {
            double payment = priceList.getPayment(vehicle);
            System.out.printf("%s (%s): %.2f zł%n", vehicle.getName(), vehicle.getLicensePlate(), payment);
        }
    }
}
