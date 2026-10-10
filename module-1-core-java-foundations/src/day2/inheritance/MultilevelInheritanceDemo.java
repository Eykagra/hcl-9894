package day2.inheritance;

/**
 * Day 2 - Multilevel inheritance: a chain of classes, each extending the one before it.
 *
 *   Animal -> Dog -> Puppy
 */
public class MultilevelInheritanceDemo {

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

    static class Puppy extends Dog {
        void weep() {
            System.out.println("Puppy weeps");
        }
    }

    public static void main(String[] args) {
        Puppy puppy = new Puppy();
        puppy.eat();    // from Animal (grandparent)
        puppy.bark();   // from Dog (parent)
        puppy.weep();   // from Puppy
    }
}
