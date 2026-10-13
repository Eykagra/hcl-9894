package assessment2.service;

import assessment2.exception.InvalidInputException;
import assessment2.model.Cart;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/** Keeps one cart per user. User names are not case sensitive ("asha" = "Asha"). */
public class CartManager {

    public static final int MAX_USER_NAME_LENGTH = 20;

    // sorted by user name, ignoring upper/lower case
    private final Map<String, Cart> cartsByUserName = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    // returns the cart of the user, creates it on first use
    public synchronized Cart getCartOf(String userName) {
        String cleanName = validateUserName(userName);
        Cart cart = cartsByUserName.get(cleanName);
        if (cart == null) {
            cart = new Cart(cleanName);
            cartsByUserName.put(cleanName, cart);
        }
        return cart;
    }

    // all carts, sorted by user name
    public synchronized List<Cart> getAllCarts() {
        return new ArrayList<>(cartsByUserName.values());
    }

    private static String validateUserName(String userName) {
        String cleanName = (userName == null) ? "" : userName.trim();
        if (cleanName.isEmpty()) {
            throw new InvalidInputException("User name must not be empty.");
        }
        if (cleanName.length() > MAX_USER_NAME_LENGTH) {
            throw new InvalidInputException("User name can have at most " + MAX_USER_NAME_LENGTH + " characters.");
        }
        return cleanName;
    }
}
