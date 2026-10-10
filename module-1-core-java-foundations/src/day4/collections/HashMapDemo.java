package day4.collections;

import java.util.HashMap;
import java.util.Map;

/**
 * Day 4 - HashMap: stores key-value pairs. Keys are unique; HashMap does not guarantee any order.
 */
public class HashMapDemo {

    public static void main(String[] args) {
        Map<Integer, String> students = new HashMap<>();   // roll number -> name

        students.put(101, "Asha");
        students.put(102, "Ravi");
        students.put(103, "Meera");
        students.put(102, "Rahul");      // same key: the old value is replaced
        System.out.println("Map: " + students);

        System.out.println("get(101): " + students.get(101));
        System.out.println("containsKey(104): " + students.containsKey(104));
        System.out.println("getOrDefault(104): " + students.getOrDefault(104, "Not found"));

        students.remove(103);
        System.out.println("After remove(103): " + students);

        for (Map.Entry<Integer, String> entry : students.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
        System.out.println("Keys: " + students.keySet());
        System.out.println("Values: " + students.values());
    }
}
