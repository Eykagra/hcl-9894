package day7.sealedinterface;

/**
 * Day 7 - Sealed interface with records, used in a switch.
 */
public class SealedInterfaceDemo {

    public static void main(String[] args) {
        System.out.println(describe(new Car("Swift")));         // Car: Swift
        System.out.println(describe(new Bike("Splendor")));     // Bike: Splendor

        // record Truck(String name) implements Vehicle { }     // does not compile: Truck is not in the permits list
    }

    // The switch has no default: Car and Bike are the only Vehicles, so the compiler knows all cases are covered.
    // If a new type is added to permits, this switch stops compiling until a case for it is added.
    private static String describe(Vehicle vehicle) {
        return switch (vehicle) {
            case Car car -> "Car: " + car.name();
            case Bike bike -> "Bike: " + bike.name();
        };
    }
}
