package day6.streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Day 6 - Streams: filter a list of names and collect the result.
 */
public class FilterNamesDemo {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Rahul", "Amit", "Rohit", "Sneha", "Riya", "Karan");

        List<String> rNames = names.stream()
                .filter(name -> name.startsWith("R"))       // condition: starts with R
                .collect(Collectors.toList());              // gather the matching names in a new list

        rNames.forEach(System.out::println);                // Rahul, Rohit, Riya
        System.out.println("Count: " + rNames.size());      // 3
    }
}
