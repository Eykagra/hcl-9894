package day3.abstraction;

/**
 * Day 3 - Abstraction: using a subclass through its abstract parent type.
 */
public class AbstractionDemo {

    public static void main(String[] args) {
        // Person person = new Person();   // does not compile: Person is abstract

        Person employee = new Employee("Alice", 28);
        employee.introduce();
    }
}
