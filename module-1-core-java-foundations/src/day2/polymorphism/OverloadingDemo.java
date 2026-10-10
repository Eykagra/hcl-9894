package day2.polymorphism;

/**
 * Day 2 - Polymorphism: method overloading (compile-time polymorphism).
 * Same method name, different parameter lists (number, type or order of parameters).
 * The compiler decides which version to call from the arguments.
 */
public class OverloadingDemo {

    static int add(int a, int b) {
        return a + b;
    }

    static int add(int a, int b, int c) {      // different number of parameters
        return a + b + c;
    }

    static double add(double a, double b) {    // different parameter types
        return a + b;
    }

    static String add(String a, String b) {
        return a + b;
    }

    public static void main(String[] args) {
        System.out.println(add(2, 3));               // 5
        System.out.println(add(2, 3, 4));            // 9
        System.out.println(add(2.5, 3.5));           // 6.0
        System.out.println(add("Hello ", "Java"));   // Hello Java
    }
}
