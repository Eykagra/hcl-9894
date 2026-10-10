# hcl-9894 — HCL Java Training

Code from the HCL Java training, pushed daily. One folder per module, one folder per day, one package per topic.

## Module 1 — Core Java & Foundations

| Day | Topics | Code |
| --- | --- | --- |
| 1 | How Java runs (source → bytecode → JVM), packages and imports | [day1](module-1-core-java-foundations/src/day1) · [notes](module-1-core-java-foundations/notes/day1-how-java-runs.md) |
| 2 | OOP: constructors, inheritance, overloading and overriding, encapsulation, access modifiers | [day2](module-1-core-java-foundations/src/day2) |
| 3 | Abstraction, interfaces, collections (LinkedList, ArrayList, Stack) | [day3](module-1-core-java-foundations/src/day3) |
| 4 | Queue, Deque and Stack with ArrayDeque, HashMap, Comparable and toString (sorting Student objects) | [day4](module-1-core-java-foundations/src/day4) |

## Layout

```
module-1-core-java-foundations/
├── notes/              theory notes (no code)
└── src/
    ├── day1/ … day4/   one folder (and package) per day
    │   └── <topic>/    one package per topic (constructors, inheritance, collections, …)
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

In an IDE, open `module-1-core-java-foundations` as the project and mark `src` as the source folder.
