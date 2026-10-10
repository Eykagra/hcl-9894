package day3.collections;

import java.util.ArrayList;
import java.util.List;

/**
 * Day 3 - Collections: ArrayList (a resizable array).
 * Covers add, get, set, remove, size and printing the list.
 */
public class ArrayListDemo {

    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            numbers.add(i * 10);                      // 10, 20, ... 100
        }
        System.out.println("List: " + numbers);

        numbers.set(2, 34);                           // replace the element at index 2
        numbers.set(5, 31);                           // replace the element at index 5
        System.out.println("After set: " + numbers);
        System.out.println("Element at index 2: " + numbers.get(2));
        System.out.println("Element at index 5: " + numbers.get(5));

        numbers.remove(0);                            // remove(int) removes by INDEX
        System.out.println("After remove(0): " + numbers);

        numbers.remove(Integer.valueOf(34));          // remove(Object) removes by VALUE
        System.out.println("After remove(34): " + numbers);

        System.out.println("Size: " + numbers.size());
    }
}
