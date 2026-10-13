package assessment2.exception;

/** A value entered by the user is not valid. */
public class InvalidInputException extends CartException {

    private static final long serialVersionUID = 1L;

    public InvalidInputException(String message) {
        super(message);
    }
}
