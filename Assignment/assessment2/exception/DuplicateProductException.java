package assessment2.exception;

/** A product with the same id is already in the cart. */
public class DuplicateProductException extends CartException {

    private static final long serialVersionUID = 1L;

    public DuplicateProductException(String message) {
        super(message);
    }
}
