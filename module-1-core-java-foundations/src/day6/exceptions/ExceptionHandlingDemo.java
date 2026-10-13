package day6.exceptions;

/**
 * Day 6 - Exception handling: try, catch, finally.
 *
 * An exception is an object that describes a problem at run time. Without a handler the program stops;
 * with try/catch the program deals with the problem and carries on.
 *
 *   Unchecked exceptions  subclasses of RuntimeException (ArithmeticException, NullPointerException, ...).
 *                         The compiler does not force you to handle them.
 *   Checked exceptions    the other subclasses of Exception (IOException, ...).
 *                         You must handle them with try/catch or declare them with throws.
 */
public class ExceptionHandlingDemo {

    public static void main(String[] args) {
        uncheckedException();
        multipleCatchBlocks();
        multiCatch();
        checkedException();
        finallyBlock();
        System.out.println("Program continues normally");
    }

    private static void uncheckedException() {
        try {
            int divisor = 0;
            int result = 10 / divisor;                              // throws ArithmeticException
            System.out.println(result);                             // never reached
        } catch (ArithmeticException e) {
            System.out.println("Unchecked: " + e.getMessage());     // Unchecked: / by zero
        }
    }

    // the first catch block that matches runs; write the specific exceptions first
    private static void multipleCatchBlocks() {
        int[] marks = {80, 90, 70};
        String[] inputs = {"2", "abc", "7"};
        for (String input : inputs) {
            try {
                int index = Integer.parseInt(input);                // NumberFormatException for "abc"
                System.out.println("marks[" + index + "] = " + marks[index]);    // ArrayIndexOutOfBoundsException for 7
            } catch (NumberFormatException e) {
                System.out.println("'" + input + "' is not a number");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("No marks at index " + input);
            }
        }
    }

    // one catch block for several exception types, when the handling is the same
    private static void multiCatch() {
        String[] inputs = {"x", "9"};
        int[] marks = {80, 90, 70};
        for (String input : inputs) {
            try {
                System.out.println(marks[Integer.parseInt(input)]);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Invalid input '" + input + "': " + e.getClass().getSimpleName());
            }
        }
    }

    private static void checkedException() {
        try {
            checkNumber(-5);
        } catch (Exception e) {                                     // required: checkNumber declares "throws Exception"
            System.out.println("Checked: " + e.getMessage());       // Checked: Number cannot be negative
        }
    }

    // throws in the header tells the caller that this method can fail
    private static void checkNumber(int number) throws Exception {
        if (number < 0) {
            throw new Exception("Number cannot be negative");
        }
        System.out.println(number + " is fine");
    }

    // finally runs whether or not an exception happened, so it is the place for clean-up work
    private static void finallyBlock() {
        for (int divisor : new int[]{2, 0}) {
            try {
                System.out.println("20 / " + divisor + " = " + (20 / divisor));
            } catch (ArithmeticException e) {
                System.out.println("Cannot divide by zero");
            } finally {
                System.out.println("finally done for divisor " + divisor);
            }
        }
    }
}
