package day3.abstraction;

/**
 * Day 3 - Abstraction with an abstract class.
 * An abstract class cannot be instantiated; subclasses must implement its abstract methods.
 * It can also hold normal (concrete) methods that all subclasses share.
 */
public abstract class Person {

    public abstract String getName();   // no body: the subclass decides

    public abstract int getAge();

    // concrete method, inherited as it is
    public void introduce() {
        System.out.println("Hi, I am " + getName() + " and I am " + getAge() + " years old");
    }
}
