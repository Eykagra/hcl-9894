package day2.encapsulation;

/**
 * Day 2 - Encapsulation: private fields accessed through getters and setters.
 */
public class EncapsulationDemo {

    public static void main(String[] args) {
        Person person = new Person();

        // person.age = 22;   // does not compile: age is private
        person.setName("Alice");
        person.setAge(22);
        System.out.println("Name: " + person.getName());
        System.out.println("Age: " + person.getAge());

        person.setAge(-5);    // rejected by the validation inside the setter
        System.out.println("Age after invalid update: " + person.getAge());
    }
}
