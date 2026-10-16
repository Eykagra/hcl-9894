package day8.singleton;

public class LazySingletonDemo {

    public static void main(String[] args) {
        System.out.println("Before getInstance(): no object exists yet");
        LazySingleton first = LazySingleton.getInstance();      // LazySingleton created
        LazySingleton second = LazySingleton.getInstance();     // nothing printed: the same object is returned
        System.out.println("first == second: " + (first == second));    // true
    }
}
