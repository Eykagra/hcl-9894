package assessment2.ui;

import assessment2.exception.CartException;
import assessment2.exception.InvalidInputException;
import assessment2.model.Cart;
import assessment2.model.Product;
import assessment2.model.ProductCategory;
import assessment2.model.ProductSortOption;
import assessment2.service.CartManager;
import assessment2.service.ProductFactory;

import java.io.InputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.NoSuchElementException;

/**
 * Assessment 2: console shopping cart for clothes, electronic and toys products.
 * A user can add, remove, update and show products (sorted). "Switch user" gives every user a separate cart.
 */
public class ShoppingCartApp {

    private static final int MENU_EXIT = 0;
    private static final int MENU_ADD_PRODUCT = 1;
    private static final int MENU_REMOVE_PRODUCT = 2;
    private static final int MENU_UPDATE_PRODUCT = 3;
    private static final int MENU_SHOW_CART = 4;
    private static final int MENU_SWITCH_USER = 5;
    private static final int MENU_SHOW_ALL_USERS = 6;

    private final PrintStream out;
    private final ConsoleInput input;
    private final CartPrinter printer;
    private final CartManager cartManager = new CartManager();
    private Cart activeCart;                    // cart of the user who is shopping right now

    public ShoppingCartApp(InputStream in, PrintStream out) {
        this.out = out;
        this.input = new ConsoleInput(in, out);
        this.printer = new CartPrinter(out);
    }

    public static void main(String[] args) {
        new ShoppingCartApp(System.in, System.out).run();
    }

    public void run() {
        out.println("=== ONLINE SHOPPING CART ===");
        try {
            activeCart = askForUserCart();
            boolean keepRunning = true;
            while (keepRunning) {
                printMenu();
                keepRunning = handleMenuChoice(input.readInt("Enter your choice: "));
            }
        } catch (NoSuchElementException inputHasEnded) {      // input closed, for example with Ctrl+D
            out.println();
            out.println("No more input. Closing the application.");
        }
    }

    private void printMenu() {
        out.println();
        out.println("----- MENU (user: " + activeCart.getOwnerName() + ") -----");
        out.println(MENU_ADD_PRODUCT + ". Add product");
        out.println(MENU_REMOVE_PRODUCT + ". Remove product");
        out.println(MENU_UPDATE_PRODUCT + ". Update product");
        out.println(MENU_SHOW_CART + ". Show cart");
        out.println(MENU_SWITCH_USER + ". Switch user");
        out.println(MENU_SHOW_ALL_USERS + ". Show all users");
        out.println(MENU_EXIT + ". Exit");
    }

    // runs the chosen action; returns false when the user wants to exit
    private boolean handleMenuChoice(int choice) {
        try {
            switch (choice) {
                case MENU_ADD_PRODUCT:
                    addProduct();
                    break;
                case MENU_REMOVE_PRODUCT:
                    removeProduct();
                    break;
                case MENU_UPDATE_PRODUCT:
                    updateProduct();
                    break;
                case MENU_SHOW_CART:
                    showCart();
                    break;
                case MENU_SWITCH_USER:
                    activeCart = askForUserCart();
                    break;
                case MENU_SHOW_ALL_USERS:
                    printer.printUserSummary(cartManager.getAllCarts());
                    break;
                case MENU_EXIT:
                    out.println("Thank you for shopping. Goodbye!");
                    return false;
                default:
                    out.println("Invalid choice. Please enter a number from " + MENU_EXIT + " to " + MENU_SHOW_ALL_USERS + ".");
            }
        } catch (CartException problem) {
            // every cart error is shown as a message and the program goes on
            out.println("Error: " + problem.getMessage());
        }
        return true;
    }

    // asks for a user name until it is valid and returns the cart of that user
    private Cart askForUserCart() {
        while (true) {
            String userName = input.readLine("Enter user name: ");
            try {
                Cart cart = cartManager.getCartOf(userName);
                if (cart.isEmpty()) {
                    out.println("Welcome, " + cart.getOwnerName() + ". Your cart is empty.");
                } else {
                    out.println("Welcome back, " + cart.getOwnerName() + ". Your cart has "
                            + cart.getProductCount() + " product(s).");
                }
                return cart;
            } catch (InvalidInputException problem) {
                out.println("Error: " + problem.getMessage());
            }
        }
    }

    // asks for the product details and adds the product to the cart
    private void addProduct() {
        ProductCategory category = askForCategory();
        String id = input.readLine("Product id (letters and digits, for example C101): ");
        String name = input.readLine("Product name: ");
        BigDecimal price = input.readAmount("Price (Rs.): ");
        int quantity = input.readInt("Quantity: ");
        String extraDetail = input.readLine(category.getExtraDetailLabel() + ": ");

        Product product = ProductFactory.create(category, id, name, price, quantity, extraDetail);
        activeCart.addProduct(product);
        out.println("Added to cart: " + describe(product));
    }

    // removes the product with the given id
    private void removeProduct() {
        if (activeCart.isEmpty()) {
            out.println("Your cart is empty, there is nothing to remove.");
            return;
        }
        String id = input.readLine("Product id to remove: ");
        Product removed = activeCart.removeProduct(id);
        out.println("Removed from cart: " + describe(removed));
    }

    // changes name, price and/or quantity; Enter keeps the old value
    private void updateProduct() {
        if (activeCart.isEmpty()) {
            out.println("Your cart is empty, there is nothing to update.");
            return;
        }
        String id = input.readLine("Product id to update: ");
        Product current = activeCart.getProduct(id);            // fails here if the id is unknown
        out.println("Current: " + describe(current));
        out.println("Press Enter to keep the current value.");

        String newName = emptyToNull(input.readLine("New name: "));
        BigDecimal newPrice = input.readOptionalAmount("New price (Rs.): ");
        Integer newQuantity = input.readOptionalInt("New quantity: ");

        if (newName == null && newPrice == null && newQuantity == null) {
            out.println("Nothing was changed.");
            return;
        }
        Product updated = activeCart.updateProduct(id, newName, newPrice, newQuantity);
        out.println("Updated: " + describe(updated));
    }

    // shows the cart in the sort order chosen by the user
    private void showCart() {
        if (activeCart.isEmpty()) {
            out.println("Your cart is empty.");
            return;
        }
        printer.printCart(activeCart, askForSortOption());
    }

    private ProductCategory askForCategory() {
        ProductCategory[] categories = ProductCategory.values();
        out.println("Product category:");
        for (int i = 0; i < categories.length; i++) {
            out.println("  " + (i + 1) + ". " + categories[i].getDisplayName());
        }
        int choice = input.readInt("Choose category (1-" + categories.length + "): ");
        if (choice < 1 || choice > categories.length) {
            throw new InvalidInputException("Category must be a number from 1 to " + categories.length + ".");
        }
        return categories[choice - 1];
    }

    private ProductSortOption askForSortOption() {
        ProductSortOption[] options = ProductSortOption.values();
        out.println("Sort by:");
        for (int i = 0; i < options.length; i++) {
            out.println("  " + (i + 1) + ". " + options[i].getLabel());
        }
        Integer choice = input.readOptionalInt("Choose sorting (1-" + options.length + ", Enter = 1): ");
        if (choice == null) {
            return options[0];
        }
        if (choice < 1 || choice > options.length) {
            throw new InvalidInputException("Sorting must be a number from 1 to " + options.length + ".");
        }
        return options[choice - 1];
    }

    private static String describe(Product product) {
        return product + ", " + product.getCategory().getDisplayName() + ", "
                + product.getSpecificDetails() + ", price " + CartPrinter.formatAmount(product.getPrice());
    }

    private static String emptyToNull(String text) {
        if (text.isEmpty()) {
            return null;
        }
        return text;
    }
}
