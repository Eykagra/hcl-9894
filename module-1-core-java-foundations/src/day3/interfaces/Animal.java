package day3.interfaces;

/**
 * Day 3 - Interface.
 * Abstract methods have no body; implementing classes must provide one.
 * A static method belongs to the interface itself and is called as Animal.sound().
 */
public interface Animal {

    void eat();   // implicitly public and abstract

    static void sound() {
        System.out.println("Animals make sounds");
    }
}
