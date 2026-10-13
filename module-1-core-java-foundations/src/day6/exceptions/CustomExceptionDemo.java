package day6.exceptions;

/**
 * Day 6 - Using a custom exception: the name says what went wrong, and it is thrown by our own rule.
 */
public class CustomExceptionDemo {

    public static void main(String[] args) {
        int[] ages = {25, 16};
        for (int age : ages) {
            try {
                checkAge(age);
            } catch (InvalidAgeException e) {
                System.out.println("Exception: " + e.getMessage());
            } finally {
                System.out.println("Age check completed for " + age);
            }
        }
        System.out.println("Program continues normally");
    }

    private static void checkAge(int age) throws InvalidAgeException {
        if (age < 18) {
            throw new InvalidAgeException("Age must be 18 or above, but was " + age);
        }
        System.out.println("Eligible to vote");
    }
}
