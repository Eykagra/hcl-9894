package day6.exceptions;

/**
 * Day 6 - Custom exception: extend Exception to get a checked exception
 * (extend RuntimeException instead to get an unchecked one).
 */
public class InvalidAgeException extends Exception {

    private static final long serialVersionUID = 1L;    // exceptions are Serializable, so this field is expected

    public InvalidAgeException(String message) {
        super(message);
    }
}
