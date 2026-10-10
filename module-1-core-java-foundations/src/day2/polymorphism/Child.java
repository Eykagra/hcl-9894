package day2.polymorphism;

public class Child extends Parent {

    @Override
    public void show() {
        super.show();   // call the parent version first
        System.out.println("Child: show()");
    }
}
