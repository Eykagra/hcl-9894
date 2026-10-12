package day5.calculator;

@FunctionalInterface
interface Arithmetic {
    int calculate(int a, int b);
}

public class Calculator {
    public static void main(String[] args) {

        Arithmetic add = (a, b) -> a + b;
        Arithmetic subtract = (a, b) -> a - b;
        Arithmetic multiply = (a, b) -> a * b;
        Arithmetic divide = (a, b) -> a / b;

        System.out.println("Addition: " + add.calculate(10, 5));
        System.out.println("Subtraction: " + subtract.calculate(10, 5));
        System.out.println("Multiplication: " + multiply.calculate(10, 5));
        System.out.println("Division: " + divide.calculate(10, 5));
    }
}
