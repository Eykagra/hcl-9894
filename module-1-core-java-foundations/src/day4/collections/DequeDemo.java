package day4.collections;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Day 4 - Deque (double-ended queue): add and remove at both ends.
 */
public class DequeDemo {

    public static void main(String[] args) {
        Deque<Integer> deque = new ArrayDeque<>();

        deque.addFirst(10);
        deque.addLast(20);
        deque.addFirst(5);
        deque.addLast(30);
        System.out.println("Deque: " + deque);                       // [5, 10, 20, 30]

        System.out.println("First: " + deque.peekFirst());           // 5
        System.out.println("Last: " + deque.peekLast());             // 30

        System.out.println("Removed first: " + deque.pollFirst());   // 5
        System.out.println("Removed last: " + deque.pollLast());     // 30
        System.out.println("Deque now: " + deque);                   // [10, 20]
    }
}
