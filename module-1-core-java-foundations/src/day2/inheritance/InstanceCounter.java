package day2.inheritance;

/**
 * Day 2 - static members: a counter shared by every object of the class.
 * Each time a constructor runs, the same static variable is incremented.
 */
public class InstanceCounter {

    protected static int count = 0;   // one copy for the whole class, not one per object

    public InstanceCounter() {
        count++;
    }
}
