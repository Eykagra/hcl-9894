package assessment2.model;

import assessment2.exception.InvalidInputException;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/** A clothes product. The extra detail is the size. */
public class ClothesProduct extends Product {

    private static final List<String> VALID_SIZES =
            Collections.unmodifiableList(Arrays.asList("XS", "S", "M", "L", "XL", "XXL"));

    private final String size;

    public ClothesProduct(String id, String name, BigDecimal price, int quantity, String size) {
        super(id, name, price, quantity);
        this.size = validateSize(size);
    }

    @Override
    public ProductCategory getCategory() {
        return ProductCategory.CLOTHES;
    }

    @Override
    public String getSpecificDetails() {
        return "Size: " + size;
    }

    @Override
    public Product withChanges(String newName, BigDecimal newPrice, Integer newQuantity) {
        return new ClothesProduct(
                getId(),
                valueOrCurrent(newName, getName()),
                valueOrCurrent(newPrice, getPrice()),
                valueOrCurrent(newQuantity, getQuantity()),
                size);
    }

    private static String validateSize(String size) {
        String cleanSize = (size == null) ? "" : size.trim().toUpperCase(Locale.ROOT);
        if (!VALID_SIZES.contains(cleanSize)) {
            throw new InvalidInputException("Size must be one of " + VALID_SIZES + ".");
        }
        return cleanSize;
    }
}
