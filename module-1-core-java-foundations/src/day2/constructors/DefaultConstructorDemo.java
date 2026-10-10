package day2.constructors;

import day2.accessmodifiers.Greeter;

/**
 * Day 2 - Constructors: the default (no-argument) constructor.
 *
 * - A constructor has the same name as the class and no return type.
 * - If you write no constructor, the compiler adds a default no-arg constructor.
 * - The constructor's access modifier decides where objects can be created from.
 */
public class DefaultConstructorDemo {

    // a constructor we wrote ourselves
    public DefaultConstructorDemo() {
        System.out.println("DefaultConstructorDemo: no-arg constructor called");
    }

    // no constructor written here, so the compiler supplies a default one
    static class NoConstructor {
        int value;   // fields get default values (int -> 0)
    }

    public static void main(String[] args) {
        new DefaultConstructorDemo();

        NoConstructor noConstructor = new NoConstructor();
        System.out.println("NoConstructor.value = " + noConstructor.value);

        // Greeter is in another package; its constructor is public, so it can be called here
        new Greeter();
    }
}
