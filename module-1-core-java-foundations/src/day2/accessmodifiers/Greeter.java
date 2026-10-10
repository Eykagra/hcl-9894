package day2.accessmodifiers;

/**
 * Day 2 - Access modifiers and packages.
 *
 * The constructor is public, so objects can be created from any package.
 * Without "public" (package-private) objects could only be created inside this package.
 */
public class Greeter {

    public Greeter() {
        System.out.println("Hello from Greeter");
    }
}
