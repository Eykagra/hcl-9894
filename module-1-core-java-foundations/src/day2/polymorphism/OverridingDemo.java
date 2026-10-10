package day2.polymorphism;

/**
 * Day 2 - Polymorphism: method overriding (runtime polymorphism).
 * A child class provides its own version of a method inherited from the parent.
 * Which version runs depends on the object created, not on the reference type.
 */
public class OverridingDemo {

    public static void main(String[] args) {
        Parent parent = new Parent();
        parent.show();                  // Parent version

        Parent upcast = new Child();    // parent reference, child object
        upcast.show();                  // Child version runs (decided at runtime)

        Child child = new Child();
        child.show();                   // Child version
    }
}
