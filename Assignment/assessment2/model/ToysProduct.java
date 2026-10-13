package assessment2.model;

import assessment2.exception.InvalidInputException;

import java.math.BigDecimal;

/** A toys product. The extra detail is the minimum age in years (0 means suitable for all ages). */
public class ToysProduct extends Product {

    public static final int MAX_MINIMUM_AGE = 18;

    private final int minimumAge;

    public ToysProduct(String id, String name, BigDecimal price, int quantity, int minimumAge) {
        super(id, name, price, quantity);
        this.minimumAge = validateMinimumAge(minimumAge);
    }

    @Override
    public ProductCategory getCategory() {
        return ProductCategory.TOYS;
    }

    @Override
    public String getSpecificDetails() {
        if (minimumAge == 0) {
            return "All ages";
        }
        return "Age: " + minimumAge + "+";
    }

    @Override
    public Product withChanges(String newName, BigDecimal newPrice, Integer newQuantity) {
        return new ToysProduct(
                getId(),
                valueOrCurrent(newName, getName()),
                valueOrCurrent(newPrice, getPrice()),
                valueOrCurrent(newQuantity, getQuantity()),
                minimumAge);
    }

    private static int validateMinimumAge(int minimumAge) {
        if (minimumAge < 0 || minimumAge > MAX_MINIMUM_AGE) {
            throw new InvalidInputException("Minimum age must be between 0 and " + MAX_MINIMUM_AGE + " years.");
        }
        return minimumAge;
    }
}
