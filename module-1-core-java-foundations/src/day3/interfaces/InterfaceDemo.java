package day3.interfaces;

/**
 * Day 3 - Interface: implementing an interface and calling its static method.
 */
public class InterfaceDemo {

    static class Dog implements Animal {

        @Override
        public void eat() {   // interface methods are public, so the implementation must be public too
            System.out.println("Dog eats bones");
        }
    }

    public static void main(String[] args) {
        Animal.sound();            // static interface method: called on the interface, not on an object

        Animal dog = new Dog();    // interface reference, class object
        dog.eat();
    }
}
