package day2.inheritance;

/**
 * Day 2 - Hierarchical inheritance: several child classes share one parent.
 *
 *            Animal
 *           /      \
 *        Dog        Cat
 *       /   \
 *  Labrador  Pug
 */
public class HierarchicalInheritanceDemo {

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

    static class Cat extends Animal {
        void meow() {
            System.out.println("Cat meows");
        }
    }

    static class Labrador extends Dog {
        void fetch() {
            System.out.println("Labrador fetches the ball");
        }
    }

    static class Pug extends Dog {
        void snore() {
            System.out.println("Pug snores");
        }
    }

    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.eat();
        dog.bark();

        Cat cat = new Cat();
        cat.eat();
        cat.meow();

        Labrador labrador = new Labrador();
        labrador.eat();     // from Animal
        labrador.bark();    // from Dog
        labrador.fetch();

        Pug pug = new Pug();
        pug.bark();         // from Dog
        pug.snore();
    }
}
