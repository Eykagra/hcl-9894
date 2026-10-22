# hcl-9894 — HCL Java Training

Code from the HCL Java training, pushed daily. One folder per module, one folder per day, one package per topic.

## Module 1 — Core Java & Foundations

| Day | Topics | Code |
| --- | --- | --- |
| 1 | How Java runs (source → bytecode → JVM), packages and imports | [day1](module-1-core-java-foundations/src/day1) · [notes](module-1-core-java-foundations/notes/day1-how-java-runs.md) |
| 2 | OOP: constructors, inheritance, overloading and overriding, encapsulation, access modifiers | [day2](module-1-core-java-foundations/src/day2) |
| 3 | Abstraction, interfaces, collections (LinkedList, ArrayList, Stack) | [day3](module-1-core-java-foundations/src/day3) |
| 4 | Queue, Deque and Stack with ArrayDeque, HashMap, Comparable and toString (sorting Student objects) | [day4](module-1-core-java-foundations/src/day4) |
| 5 | Wrapper classes and autoboxing, generics, Comparable vs Comparator, functional interfaces and lambda expressions | [day5](module-1-core-java-foundations/src/day5) · [notes](module-1-core-java-foundations/notes/day5-comparable-vs-comparator.md) |
| 6 | Exception handling (try, catch, finally, throw, throws), checked vs unchecked, custom exceptions, streams introduction (filter, forEach, collect) | [day6](module-1-core-java-foundations/src/day6) |
| 7 | Stream methods (map, sorted, groupingBy, reduce, ...), records, sealed classes and interfaces, switch expressions | [day7](module-1-core-java-foundations/src/day7) |
| 8 | Singleton design pattern (eager and lazy) | [day8](module-1-core-java-foundations/src/day8) |

## Module 2

| Project | Topics |
| --- | --- |
| [SpringCoreFundamentals](module-2/SpringCoreFundamentals) | Spring container, `@Configuration` and `@Bean`, constructor / setter / field injection, `@Component`, `@ComponentScan`, `@Autowired`, `@Primary`, `@PostConstruct` and `@PreDestroy` |
| [hrms](module-2/hrms) | First Spring Boot web app: `@RestController`, `@GetMapping`, controller → model → repository |
| [hclhrms](module-2/hclhrms) | Employee REST API with controller, service and repository layers (in-memory list): add, bulk add, get all, get by id |

## Layout

```
module-1-core-java-foundations/
├── notes/              theory notes (no code)
└── src/
    ├── day1/ … day8/   one folder (and package) per day
    │   └── <topic>/    one package per topic (constructors, inheritance, collections, …)
module-2/
├── SpringCoreFundamentals/   Spring Core (pom.xml)
├── hrms/                     Spring Boot (pom.xml)
└── hclhrms/                  Spring Boot (pom.xml)
```

Package names match the folders, e.g. `day4.comparable.Student` lives in `src/day4/comparable/Student.java`.
Every class ending in `Demo` or `App` has a `main` method you can run.

## Compile and run

From inside `module-1-core-java-foundations`:

```
javac -d out -sourcepath src src/day4/comparable/StudentSortDemo.java
java -cp out day4.comparable.StudentSortDemo
```

`-sourcepath src` also compiles anything the class imports. Change the two paths/names to run another demo.

To compile everything at once (macOS / Linux / Git Bash): `javac -d out $(find src -name "*.java")`

Day 7 uses records, sealed classes and pattern matching in switch, so it needs Java 21 or newer.

In an IDE, open `module-1-core-java-foundations` as the project and mark `src` as the source folder.

Each project in Module 2 is a separate Maven project, so open its `pom.xml` as a Maven project. For the Spring Boot ones, run the `*Application` class (the endpoints are on `localhost:8080`). SpringCoreFundamentals has a `Main` class in each package.
