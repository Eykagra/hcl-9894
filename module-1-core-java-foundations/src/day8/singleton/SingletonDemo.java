package day8.singleton;

public class SingletonDemo {

    public static void main(String[] args) {
        System.out.println("main starts");                      // the object does not exist yet
        Singleton first = Singleton.getInstance();              // Private constructor called (the class loads and creates its object)
        Singleton second = Singleton.getInstance();             // nothing printed: the same object is returned

        first.display();
        second.display();

        System.out.println("hashCode of first:  " + first.hashCode());
        System.out.println("hashCode of second: " + second.hashCode());                // same number
        System.out.println("first == second: " + (first == second));                   // true: both point to one object

        // Singleton third = new Singleton();                  // does not compile: the constructor is private
    }
}
