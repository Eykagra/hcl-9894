package assessment2.service;

import assessment2.exception.InvalidInputException;
import assessment2.model.ClothesProduct;
import assessment2.model.ElectronicProduct;
import assessment2.model.Product;
import assessment2.model.ProductCategory;
import assessment2.model.ToysProduct;

import java.math.BigDecimal;

/** Creates the right product class for a category, so the UI does not need to know them. */
public final class ProductFactory {

    private ProductFactory() {
    }

    // extraDetail = size (clothes), warranty in months (electronic) or minimum age (toys)
    public static Product create(ProductCategory category, String id, String name, BigDecimal price,
                                 int quantity, String extraDetail) {
        if (category == null) {
            throw new InvalidInputException("A product category must be chosen.");
        }
        switch (category) {
            case CLOTHES:
                return new ClothesProduct(id, name, price, quantity, extraDetail);
            case ELECTRONIC:
                return new ElectronicProduct(id, name, price, quantity, parseWholeNumber(extraDetail, "Warranty"));
            case TOYS:
                return new ToysProduct(id, name, price, quantity, parseWholeNumber(extraDetail, "Minimum age"));
            default:
                throw new InvalidInputException("Unsupported category: " + category);
        }
    }

    private static int parseWholeNumber(String text, String fieldName) {
        if (text == null) {
            throw new InvalidInputException(fieldName + " must be a whole number.");
        }
        try {
            return Integer.parseInt(text.trim());
        } catch (NumberFormatException e) {
            throw new InvalidInputException(fieldName + " must be a whole number, but was '" + text.trim() + "'.");
        }
    }
}
