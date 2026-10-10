package day3.collections;

import java.util.Stack;

/**
 * Day 3 - Collections: the Stack class (LIFO: last in, first out).
 * search() returns the 1-based position from the top, or -1 if the element is not present.
 */
public class StackDemo {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();

        stack.push(1);
        stack.push(33);
        stack.push(35);
        stack.push(38);
        System.out.println("Stack: " + stack);

        System.out.println("Popped: " + stack.pop());               // 38
        System.out.println("Top (peek): " + stack.peek());          // 35

        System.out.println("search(33): " + stack.search(33));      // 2 (second from the top)
        System.out.println("search(2): " + stack.search(2));        // -1 (not in the stack)
    }
}
