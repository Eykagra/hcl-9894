package day2.encapsulation;

/**
 * Day 2 - Encapsulation: the fields are private and can only be reached
 * through public getters and setters, so the class controls its own data.
 */
public class Person {

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age > 0) {
            this.age = age;
        } else {
            System.out.println("Invalid age ignored: " + age);
        }
    }

    // protected: visible in this package and in subclasses (even in other packages)
    protected void showDetails() {
        System.out.println("Name: " + name + ", Age: " + age);
    }
}
