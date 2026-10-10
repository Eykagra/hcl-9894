package day4.collections;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Day 4 - Queue (FIFO: first in, first out) implemented with ArrayDeque.
 * enqueue = offer() at the tail, dequeue = poll() from the head.
 */
public class QueueDemo {

    public static void main(String[] args) {
        Queue<String> queue = new ArrayDeque<>();

        queue.offer("Asha");      // enqueue
        queue.offer("Ravi");
        queue.offer("Meera");
        System.out.println("Queue: " + queue);

        System.out.println("Front (peek): " + queue.peek());       // looks at the head, does not remove it
        System.out.println("Dequeued (poll): " + queue.poll());    // removes the head
        System.out.println("Queue now: " + queue);
        System.out.println("Size: " + queue.size());

        // On an empty queue poll()/peek() return null, while remove()/element() throw NoSuchElementException
        queue.clear();
        System.out.println("poll() on an empty queue: " + queue.poll());
    }
}
