package assessment2.exception;

/** The cart already holds the maximum number of different products. */
public class CartFullException extends CartException {

    private static final long serialVersionUID = 1L;

    public CartFullException(String message) {
        super(message);
    }
}
