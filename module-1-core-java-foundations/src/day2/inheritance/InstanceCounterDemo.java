package day2.inheritance;

/**
 * Day 2 - Counting instances. The static variable is shared by all objects
 * and is inherited by subclasses.
 */
public class InstanceCounterDemo extends InstanceCounter {

    public static void main(String[] args) {
        new InstanceCounter();
        new InstanceCounter();
        new InstanceCounter();
        new InstanceCounter();

        System.out.println("Objects created: " + count);   // 4 (count is inherited from InstanceCounter)
    }
}
