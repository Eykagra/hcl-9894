package assessment2.exception;

/** Base class of all shopping cart errors. The console app catches it and shows the message. */
public class CartException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CartException(String message) {
        super(message);
    }
}
