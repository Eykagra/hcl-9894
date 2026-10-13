package assessment2.ui;

import assessment2.model.Cart;
import assessment2.model.Product;
import assessment2.model.ProductSortOption;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

/** Prints carts as tables. */
public class CartPrinter {

    private static final String CART_ROW_FORMAT = "%-10s %-12s %-25s %-20s %11s %4s %12s";
    private static final String USER_ROW_FORMAT = "%-20s %9s %16s";
    private static final String CURRENCY_PREFIX = "Rs. ";
    private static final int DECIMAL_PLACES = 2;

    private final PrintStream out;

    public CartPrinter(PrintStream out) {
        this.out = out;
    }

    // "Rs. 499.50": always two decimals
    public static String formatAmount(BigDecimal amount) {
        return CURRENCY_PREFIX + plainAmount(amount);
    }

    public void printCart(Cart cart, ProductSortOption sortOption) {
        List<Product> products = cart.getProducts(sortOption);
        String header = String.format(CART_ROW_FORMAT,
                "Id", "Category", "Name", "Details", "Price (Rs.)", "Qty", "Subtotal");
        String line = repeat('-', header.length());

        out.println();
        out.println("Cart of " + cart.getOwnerName() + ": " + products.size()
                + " product(s), sorted by " + sortOption.getLabel());
        out.println(line);
        out.println(header);
        out.println(line);
        for (Product product : products) {
            out.println(String.format(CART_ROW_FORMAT,
                    product.getId(),
                    product.getCategory().getDisplayName(),
                    product.getName(),
                    product.getSpecificDetails(),
                    plainAmount(product.getPrice()),
                    product.getQuantity(),
                    plainAmount(product.getTotalPrice())));
        }
        out.println(line);
        out.println("Total amount: " + formatAmount(Cart.calculateTotal(products)));
    }

    public void printUserSummary(List<Cart> carts) {
        String header = String.format(USER_ROW_FORMAT, "User", "Products", "Total amount");
        String line = repeat('-', header.length());

        out.println();
        out.println("All users (" + carts.size() + ")");
        out.println(line);
        out.println(header);
        out.println(line);
        for (Cart cart : carts) {
            List<Product> products = cart.getProducts(ProductSortOption.NAME);
            out.println(String.format(USER_ROW_FORMAT,
                    cart.getOwnerName(),
                    products.size(),
                    formatAmount(Cart.calculateTotal(products))));
        }
        out.println(line);
    }

    private static String plainAmount(BigDecimal amount) {
        return amount.setScale(DECIMAL_PLACES, RoundingMode.HALF_UP).toPlainString();
    }

    private static String repeat(char character, int times) {
        char[] characters = new char[times];
        Arrays.fill(characters, character);
        return new String(characters);
    }
}
