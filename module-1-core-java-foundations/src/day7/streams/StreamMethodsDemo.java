package day7.streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Day 7 - Stream methods.
 *
 *   intermediate  return a new stream and run only when a terminal operation is called:
 *                 filter, map, sorted, distinct, limit, skip, flatMap
 *   terminal      produce the result and end the stream:
 *                 forEach, collect, toList, count, reduce, min, max, findFirst, anyMatch, allMatch, noneMatch
 *
 * toList() (Java 16+) is a shortcut for collect(Collectors.toList()).
 */
public class StreamMethodsDemo {

    public static void main(String[] args) {
        intermediateOperations();
        terminalOperations();
        collectors();
    }

    private static void intermediateOperations() {
        List<Integer> numbers = Arrays.asList(5, 3, 8, 3, 9, 1, 8, 6);
        List<String> names = Arrays.asList("meera", "asha", "ravi", "john");

        System.out.println("map:      " + names.stream().map(String::toUpperCase).toList());     // [MEERA, ASHA, RAVI, JOHN]
        System.out.println("sorted:   " + numbers.stream().sorted().toList());                   // [1, 3, 3, 5, 6, 8, 8, 9]
        System.out.println("sorted by length, then name: "
                + names.stream().sorted(Comparator.comparing(String::length).thenComparing(Comparator.naturalOrder())).toList());
                                                                                                  // [asha, john, ravi, meera]
        System.out.println("distinct: " + numbers.stream().distinct().toList());                 // [5, 3, 8, 9, 1, 6]
        System.out.println("limit 3:  " + numbers.stream().limit(3).toList());                   // [5, 3, 8]
        System.out.println("skip 5:   " + numbers.stream().skip(5).toList());                    // [1, 8, 6]

        // flatMap turns many small lists into one stream
        List<List<Integer>> groups = Arrays.asList(Arrays.asList(1, 2), Arrays.asList(3, 4), Arrays.asList(5));
        System.out.println("flatMap:  " + groups.stream().flatMap(List::stream).toList());       // [1, 2, 3, 4, 5]
        System.out.println();
    }

    private static void terminalOperations() {
        List<Integer> numbers = Arrays.asList(5, 3, 8, 3, 9, 1, 8, 6);

        System.out.println("count of numbers above 4: " + numbers.stream().filter(n -> n > 4).count());    // 5
        System.out.println("sum with reduce:          " + numbers.stream().reduce(0, Integer::sum));       // 43
        System.out.println("sum with mapToInt:        " + numbers.stream().mapToInt(Integer::intValue).sum());    // 43

        // min, max and findFirst return an Optional, because the stream can be empty
        System.out.println("max:                      " + numbers.stream().max(Integer::compare).get());   // 9
        System.out.println("min:                      " + numbers.stream().min(Integer::compare).get());   // 1
        System.out.println("first number above 5:     " + numbers.stream().filter(n -> n > 5).findFirst().orElse(-1));    // 8
        System.out.println("first number above 50:    " + numbers.stream().filter(n -> n > 50).findFirst().orElse(-1));   // -1

        System.out.println("anyMatch  (n > 8):  " + numbers.stream().anyMatch(n -> n > 8));      // true
        System.out.println("allMatch  (n > 0):  " + numbers.stream().allMatch(n -> n > 0));      // true
        System.out.println("noneMatch (n > 10): " + numbers.stream().noneMatch(n -> n > 10));    // true
        System.out.println();
    }

    // collect(...) with the ready-made Collectors
    private static void collectors() {
        List<String> fruits = Arrays.asList("apple", "banana", "avocado", "blueberry", "cherry");

        System.out.println("joining:        " + fruits.stream().collect(Collectors.joining(", ", "[", "]")));
                                                                            // [apple, banana, avocado, blueberry, cherry]
        Map<Character, List<String>> byFirstLetter = fruits.stream()
                .collect(Collectors.groupingBy(fruit -> fruit.charAt(0), TreeMap::new, Collectors.toList()));
        System.out.println("groupingBy:     " + byFirstLetter);             // {a=[apple, avocado], b=[banana, blueberry], c=[cherry]}

        Map<Character, Long> countByFirstLetter = fruits.stream()
                .collect(Collectors.groupingBy(fruit -> fruit.charAt(0), TreeMap::new, Collectors.counting()));
        System.out.println("counting:       " + countByFirstLetter);        // {a=2, b=2, c=1}

        Map<Boolean, List<String>> byLength = fruits.stream()
                .collect(Collectors.partitioningBy(fruit -> fruit.length() > 5));
        System.out.println("partitioningBy: " + byLength);                  // {false=[apple], true=[banana, avocado, blueberry, cherry]}
    }
}
