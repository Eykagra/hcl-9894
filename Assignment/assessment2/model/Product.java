package assessment2.model;

import assessment2.exception.InvalidInputException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * Base class of all products. Clothes, electronic and toys products add their own detail.
 * Products are immutable: an update creates a new object. Natural order is by name, then id.
 */
public abstract class Product implements Comparable<Product> {

    public static final int MAX_ID_LENGTH = 10;
    public static final int MAX_NAME_LENGTH = 25;
    public static final int MAX_QUANTITY = 100;
    public static final BigDecimal MAX_PRICE = new BigDecimal("1000000");

    private static final int PRICE_DECIMAL_PLACES = 2;
    private static final Pattern VALID_ID = Pattern.compile("[A-Z0-9-]+");

    private final String id;
    private final String name;
    private final BigDecimal price;
    private final int quantity;

    protected Product(String id, String name, BigDecimal price, int quantity) {
        this.id = validateId(id);
        this.name = validateName(name);
        this.price = validatePrice(price);
        this.quantity = validateQuantity(quantity);
    }

    public abstract ProductCategory getCategory();

    // detail of this kind of product, e.g. "Size: M"
    public abstract String getSpecificDetails();

    // copy of this product with new values (null = keep the old value)
    public abstract Product withChanges(String newName, BigDecimal newPrice, Integer newQuantity);

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    // price x quantity
    public BigDecimal getTotalPrice() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }

    // ids are not case sensitive: " c101 " and "C101" are the same product
    public static String normalizeId(String id) {
        if (id == null) {
            return "";
        }
        return id.trim().toUpperCase(Locale.ROOT);
    }

    @Override
    public int compareTo(Product other) {
        int byName = name.compareToIgnoreCase(other.name);
        if (byName != 0) {
            return byName;
        }
        return id.compareTo(other.id);
    }

    @Override
    public String toString() {
        return "[" + id + "] " + name + " x" + quantity;
    }

    // newValue, or the current value if newValue is null
    protected static <T> T valueOrCurrent(T newValue, T currentValue) {
        if (newValue != null) {
            return newValue;
        }
        return currentValue;
    }

    // validation: an invalid product can never be created

    private static String validateId(String id) {
        String cleanId = normalizeId(id);
        if (cleanId.isEmpty()) {
            throw new InvalidInputException("Product id must not be empty.");
        }
        if (cleanId.length() > MAX_ID_LENGTH) {
            throw new InvalidInputException("Product id can have at most " + MAX_ID_LENGTH + " characters.");
        }
        if (!VALID_ID.matcher(cleanId).matches()) {
            throw new InvalidInputException("Product id can contain only letters, digits and '-'.");
        }
        return cleanId;
    }

    private static String validateName(String name) {
        String cleanName = (name == null) ? "" : name.trim();
        if (cleanName.isEmpty()) {
            throw new InvalidInputException("Product name must not be empty.");
        }
        if (cleanName.length() > MAX_NAME_LENGTH) {
            throw new InvalidInputException("Product name can have at most " + MAX_NAME_LENGTH + " characters.");
        }
        return cleanName;
    }

    private static BigDecimal validatePrice(BigDecimal price) {
        if (price == null) {
            throw new InvalidInputException("Price must be given.");
        }
        if (price.signum() <= 0) {
            throw new InvalidInputException("Price must be greater than 0.");
        }
        if (price.compareTo(MAX_PRICE) > 0) {
            throw new InvalidInputException("Price can be at most " + MAX_PRICE.toPlainString() + ".");
        }
        if (price.stripTrailingZeros().scale() > PRICE_DECIMAL_PLACES) {
            throw new InvalidInputException("Price can have at most " + PRICE_DECIMAL_PLACES + " decimal places.");
        }
        return price.setScale(PRICE_DECIMAL_PLACES, RoundingMode.UNNECESSARY);
    }

    private static int validateQuantity(int quantity) {
        if (quantity < 1 || quantity > MAX_QUANTITY) {
            throw new InvalidInputException("Quantity must be between 1 and " + MAX_QUANTITY + ".");
        }
        return quantity;
    }
}
