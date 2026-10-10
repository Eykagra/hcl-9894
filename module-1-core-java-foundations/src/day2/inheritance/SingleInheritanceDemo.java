package day2.inheritance;

/**
 * Day 2 - Single inheritance: one child class extends one parent class.
 *
 *   Animal
 *     |
 *    Dog
 */
public class SingleInheritanceDemo {

    static class Animal {
        void eat() {
            System.out.println("Animal eats");
        }
    }

    static class Dog extends Animal {
        void bark() {
            System.out.println("Dog barks");
        }
    }

    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.eat();    // inherited from Animal
        dog.bark();   // defined in Dog
    }
}
