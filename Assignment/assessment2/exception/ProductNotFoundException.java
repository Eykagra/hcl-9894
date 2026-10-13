package assessment2.exception;

/** The product id is not in the cart. */
public class ProductNotFoundException extends CartException {

    private static final long serialVersionUID = 1L;

    public ProductNotFoundException(String message) {
        super(message);
    }
}
