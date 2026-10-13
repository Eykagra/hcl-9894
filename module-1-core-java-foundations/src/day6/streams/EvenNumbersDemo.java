package day6.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Day 6 - Streams (introduction): a stream is a pipeline that processes the elements of a collection.
 *
 *   source (list.stream())  ->  intermediate operations (filter, ...)  ->  terminal operation (forEach, collect, ...)
 *
 * A stream does not change the list it comes from, and one stream can be used only once.
 */
public class EvenNumbersDemo {

    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>(Arrays.asList(10, 15, 20, 25, 30, 35, 40));

        // filter keeps the elements for which the condition is true, forEach does something with every element
        System.out.println("Even numbers:");
        numbers.stream()
                .filter(n -> n % 2 == 0)
                .forEach(n -> System.out.println("  " + n));              // 10 20 30 40

        // collect gathers the result into a new list
        List<Integer> evenNumbers = numbers.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        System.out.println("Even list: " + evenNumbers);                   // [10, 20, 30, 40]
        System.out.println("Original list: " + numbers);                   // [10, 15, 20, 25, 30, 35, 40] - unchanged

        // a stream can be used only once
        Stream<Integer> stream = numbers.stream();
        stream.forEach(n -> { });
        try {
            stream.forEach(n -> { });
        } catch (IllegalStateException e) {
            System.out.println("Second use: " + e.getMessage());           // stream has already been operated upon or closed
        }
    }
}
