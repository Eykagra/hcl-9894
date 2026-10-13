package assessment2.model;

import assessment2.exception.InvalidInputException;

import java.math.BigDecimal;

/** An electronic product. The extra detail is the warranty in months (0 means no warranty). */
public class ElectronicProduct extends Product {

    public static final int MAX_WARRANTY_MONTHS = 60;

    private final int warrantyMonths;

    public ElectronicProduct(String id, String name, BigDecimal price, int quantity, int warrantyMonths) {
        super(id, name, price, quantity);
        this.warrantyMonths = validateWarranty(warrantyMonths);
    }

    @Override
    public ProductCategory getCategory() {
        return ProductCategory.ELECTRONIC;
    }

    @Override
    public String getSpecificDetails() {
        if (warrantyMonths == 0) {
            return "No warranty";
        }
        return "Warranty: " + warrantyMonths + (warrantyMonths == 1 ? " month" : " months");
    }

    @Override
    public Product withChanges(String newName, BigDecimal newPrice, Integer newQuantity) {
        return new ElectronicProduct(
                getId(),
                valueOrCurrent(newName, getName()),
                valueOrCurrent(newPrice, getPrice()),
                valueOrCurrent(newQuantity, getQuantity()),
                warrantyMonths);
    }

    private static int validateWarranty(int warrantyMonths) {
        if (warrantyMonths < 0 || warrantyMonths > MAX_WARRANTY_MONTHS) {
            throw new InvalidInputException("Warranty must be between 0 and " + MAX_WARRANTY_MONTHS + " months.");
        }
        return warrantyMonths;
    }
}
