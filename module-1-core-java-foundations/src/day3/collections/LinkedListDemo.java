package day3.collections;

import java.util.LinkedList;

/**
 * Day 3 - Collections: LinkedList.
 * Insert a new element right after an existing one using indexOf() and add(index, element).
 */
public class LinkedListDemo {

    public static void main(String[] args) {
        LinkedList<String> fruits = new LinkedList<>();

        fruits.add("mango");
        fruits.add("banana");
        fruits.add("apple");
        fruits.add("grapes");
        fruits.add("orange");
        System.out.println("Before: " + fruits);

        int index = fruits.indexOf("apple");
        fruits.add(index + 1, "guava");          // insert right after "apple"
        System.out.println("After:  " + fruits);
    }
}
