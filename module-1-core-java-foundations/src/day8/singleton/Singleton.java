package day8.singleton;

public class Singleton {

    private static final Singleton INSTANCE = new Singleton();

    private Singleton() {
        System.out.println("Private constructor called");
    }

    public static Singleton getInstance() {
        return INSTANCE;
    }

    public void display() {
        System.out.println("Hello from Singleton");
    }
}
