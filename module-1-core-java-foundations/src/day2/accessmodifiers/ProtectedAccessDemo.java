package day2.accessmodifiers;

import day2.encapsulation.Person;

/**
 * Day 2 - Access modifiers: protected.
 * Person.showDetails() is protected. This class is in a different package,
 * so it can use that method only because it extends Person.
 */
public class ProtectedAccessDemo extends Person {

    public static void main(String[] args) {
        ProtectedAccessDemo demo = new ProtectedAccessDemo();
        demo.setName("Alice");
        demo.setAge(22);
        demo.showDetails();     // OK: protected method, called through the subclass

        Person person = new Person();
        person.setName("Bob");
        // person.showDetails();   // does not compile: protected, and this is a plain Person from another package
        System.out.println("Plain Person: " + person.getName());
    }
}
