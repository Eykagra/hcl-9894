package day2.constructors;

/**
 * Day 2 - Constructor chaining with super().
 * When a child object is created, the parent constructor runs first, then the child's.
 */
public class ConstructorChainingDemo {

    static class Parent {
        Parent() {
            System.out.println("Parent constructor");
        }
    }

    static class Child extends Parent {
        Child() {
            super();   // calls the Parent constructor (the compiler adds this if you leave it out)
            System.out.println("Child constructor");
        }
    }

    public static void main(String[] args) {
        new Child();   // prints "Parent constructor", then "Child constructor"
    }
}
