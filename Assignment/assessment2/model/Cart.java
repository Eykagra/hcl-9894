package assessment2.model;

import assessment2.exception.CartFullException;
import assessment2.exception.DuplicateProductException;
import assessment2.exception.InvalidInputException;
import assessment2.exception.ProductNotFoundException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/** Shopping cart of one user. The methods are synchronized, so it is safe to use from several threads. */
public class Cart {

    public static final int MAX_PRODUCTS = 20;

    private final String ownerName;
    private final Map<String, Product> productsById = new HashMap<>();

    public Cart(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    // adds a product; fails if the id is already in the cart or the cart is full
    public synchronized void addProduct(Product product) {
        if (product == null) {
            throw new InvalidInputException("No product was given.");
        }
        if (productsById.containsKey(product.getId())) {
            throw new DuplicateProductException("Product '" + product.getId()
                    + "' is already in the cart. Use 'Update product' to change it.");
        }
        if (productsById.size() >= MAX_PRODUCTS) {
            throw new CartFullException("The cart is full: it can hold at most " + MAX_PRODUCTS + " different products.");
        }
        productsById.put(product.getId(), product);
    }

    // removes a product and returns it
    public synchronized Product removeProduct(String productId) {
        Product removed = productsById.remove(Product.normalizeId(productId));
        if (removed == null) {
            throw notInCart(productId);
        }
        return removed;
    }

    // changes name, price and/or quantity (null = keep the old value); nothing changes if a value is invalid
    public synchronized Product updateProduct(String productId, String newName, BigDecimal newPrice, Integer newQuantity) {
        Product current = getProduct(productId);
        Product updated = current.withChanges(newName, newPrice, newQuantity);
        productsById.put(updated.getId(), updated);
        return updated;
    }

    // returns the product with this id
    public synchronized Product getProduct(String productId) {
        Product product = productsById.get(Product.normalizeId(productId));
        if (product == null) {
            throw notInCart(productId);
        }
        return product;
    }

    // returns all products in the given sort order
    public synchronized List<Product> getProducts(ProductSortOption sortOption) {
        Objects.requireNonNull(sortOption, "sortOption");
        List<Product> products = new ArrayList<>(productsById.values());
        products.sort(sortOption.getComparator());
        return products;
    }

    public synchronized int getProductCount() {
        return productsById.size();
    }

    public synchronized boolean isEmpty() {
        return productsById.isEmpty();
    }

    public synchronized BigDecimal getTotalAmount() {
        return calculateTotal(productsById.values());
    }

    // sum of price x quantity
    public static BigDecimal calculateTotal(Collection<Product> products) {
        BigDecimal total = BigDecimal.ZERO;
        for (Product product : products) {
            total = total.add(product.getTotalPrice());
        }
        return total;
    }

    private static ProductNotFoundException notInCart(String productId) {
        String shownId = (productId == null) ? "" : productId.trim();
        return new ProductNotFoundException("Product '" + shownId + "' is not in the cart.");
    }
}
