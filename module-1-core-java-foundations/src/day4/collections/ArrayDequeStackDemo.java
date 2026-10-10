package day4.collections;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Day 4 - Stack (LIFO: last in, first out) using ArrayDeque.
 * ArrayDeque is the recommended replacement for the old java.util.Stack class.
 */
public class ArrayDequeStackDemo {

    public static void main(String[] args) {
        Deque<Integer> stack = new ArrayDeque<>();

        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println("Stack: " + stack);                   // [3, 2, 1] - the top is on the left
        System.out.println("Top (peek): " + stack.peek());       // 3
        System.out.println("Popped: " + stack.pop());            // 3
        System.out.println("Stack now: " + stack);               // [2, 1]
        System.out.println("Is empty? " + stack.isEmpty());      // false
    }
}
