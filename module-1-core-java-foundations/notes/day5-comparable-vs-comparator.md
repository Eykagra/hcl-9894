# Day 5 — Comparable vs Comparator, and functional interfaces

## Comparable vs Comparator

Both answer one question: **which of two objects comes first?** The difference is *who* answers it.

- **Comparable**: the class answers for itself, "this is how I compare with another one of me". It gives **one** built-in order (the *natural order*), written inside the class.
- **Comparator**: someone *outside* the class answers, "this is how to compare two of them". You can write **as many orders as you like** and the class stays untouched.

| | Comparable | Comparator |
| --- | --- | --- |
| Package | `java.lang` | `java.util` |
| Method | `int compareTo(T other)` | `int compare(T first, T second)` |
| Written | inside the class being sorted | outside: separate class, anonymous class or lambda |
| Orders | one (natural order) | as many as you want |
| Class must be edited | yes | no |
| Used with | `Collections.sort(list)`, `TreeSet`, `TreeMap` | `Collections.sort(list, comparator)`, `list.sort(comparator)`, `new TreeSet<>(comparator)` |

In this repo (`day5/comparator`): `Student implements Comparable<Student>`, so its natural order (age, then marks) lives inside `Student.compareTo`. `NameComparator` and the lambdas sort the same `Student` by name, marks and so on without changing `Student`. Run `ComparableDemo`, then `ComparatorDemo`, then `ComparatorLambdaDemo`.

**When to use which:** Comparable when the class has one obvious natural order. Comparator for every other order, or when you cannot edit the class.

### The return value (same rule for both)

| Result | Meaning |
| --- | --- |
| negative | first comes **before** second |
| `0` | same position (a tie) |
| positive | first comes **after** second |

- Descending order: swap the arguments (`compare(second, first)`) or call `.reversed()`.
- Use `Integer.compare(a, b)` instead of `a - b`; subtraction can overflow.
- `String` and the wrapper classes (`Integer`, `Double`, ...) are already Comparable, which is why `Collections.sort(listOfStrings)` just works.

## Functional interface

A **functional interface** is an interface with exactly **one abstract method**. `default` and `static` methods do not count. `@FunctionalInterface` makes the compiler check the rule.

`Comparator` is one (its single abstract method is `compare`), so a **lambda** can replace the class. The same sort, four ways:

| Style | Code |
| --- | --- |
| Class | `new NameComparator()` |
| Anonymous class | `new Comparator<Student>() { public int compare(...) { ... } }` |
| Lambda | `(a, b) -> a.getName().compareTo(b.getName())` |
| `Comparator.comparing` | `Comparator.comparing(Student::getName)` |

Lambda syntax: `(parameters) -> expression` or `(parameters) -> { statements; }`

Common built-in functional interfaces (`java.util.function`):

| Interface | Method | Takes → returns | Example |
| --- | --- | --- | --- |
| `Predicate<T>` | `test` | T → boolean | `n -> n > 0` |
| `Function<T, R>` | `apply` | T → R | `s -> s.length()` |
| `Consumer<T>` | `accept` | T → nothing | `s -> System.out.println(s)` |
| `Supplier<T>` | `get` | nothing → T | `() -> "Java"` |

`Runnable` (`run`: no input, no output) is a functional interface too.

Code: `day5/comparator`, `day5/functionalinterfaces`, `day5/lambdas`.
