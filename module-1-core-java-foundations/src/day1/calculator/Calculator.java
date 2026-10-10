package day1.calculator;

/**
 * Day 1 - Packages.
 *
 * This class lives in its own package so that classes from other packages can import it.
 * The class and its methods are public; without "public" they would only be visible
 * inside this package.
 */
public class Calculator {

    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public double divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return (double) a / b;
    }

    // static method: called as Calculator.square(5), or with a static import
    public static int square(int n) {
        return n * n;
    }
}
