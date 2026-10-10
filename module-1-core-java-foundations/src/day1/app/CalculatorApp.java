package day1.app;

import day1.calculator.Calculator;                 // import a class from another package
import static day1.calculator.Calculator.square;   // import a single static method

/**
 * Day 1 - Packages: using a class (and a static method) from a different package.
 */
public class CalculatorApp {

    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        System.out.println("10 + 5 = " + calculator.add(10, 5));
        System.out.println("10 - 5 = " + calculator.subtract(10, 5));
        System.out.println("10 * 5 = " + calculator.multiply(10, 5));
        System.out.println("10 / 4 = " + calculator.divide(10, 4));

        // static import: no class name needed
        System.out.println("square(6) = " + square(6));
    }
}
