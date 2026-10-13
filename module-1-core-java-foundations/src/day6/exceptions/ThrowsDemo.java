package day6.exceptions;

/**
 * Day 6 - throw and throws.
 *
 *   throw   really throws one exception object, at one place in the code
 *   throws  in a method header: warns the caller that the method may throw this (checked) exception
 *
 * If a method does not handle an exception it travels up the call stack until some method catches it.
 */
public class ThrowsDemo {

    public static void main(String[] args) {
        try {
            level1();
        } catch (Exception e) {
            System.out.println("main caught: " + e.getMessage());
        }

        // an unchecked exception needs no throws in the header
        try {
            checkMarks(120);
        } catch (IllegalArgumentException e) {
            System.out.println("main caught: " + e.getMessage());
        }
        System.out.println("Program continues normally");
    }

    private static void level1() throws Exception {
        System.out.println("level1 starts");
        level2();
        System.out.println("level1 ends");                  // not printed: the exception leaves level1
    }

    private static void level2() throws Exception {
        System.out.println("level2 starts");
        level3();
        System.out.println("level2 ends");                  // not printed
    }

    private static void level3() throws Exception {
        throw new Exception("Problem in level3");
    }

    private static void checkMarks(int marks) {
        if (marks < 0 || marks > 100) {
            throw new IllegalArgumentException("Marks must be between 0 and 100");
        }
    }
}
