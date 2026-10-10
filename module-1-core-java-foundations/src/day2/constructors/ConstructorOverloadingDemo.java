package day2.constructors;

/**
 * Day 2 - Constructor overloading: same constructor name, different parameter lists.
 * The compiler picks the constructor that matches the arguments.
 */
public class ConstructorOverloadingDemo {

    private int age;
    private String name;

    public ConstructorOverloadingDemo(int age) {
        this.age = age;
        this.name = "Unknown";
    }

    public ConstructorOverloadingDemo(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public void display() {
        System.out.println(name + " is " + age + " years old");
    }

    public static void main(String[] args) {
        ConstructorOverloadingDemo alice = new ConstructorOverloadingDemo(22, "Alice");
        ConstructorOverloadingDemo anonymous = new ConstructorOverloadingDemo(30);

        alice.display();
        anonymous.display();
    }
}
