# Day 1 — How Java code runs

## From source to output

```
Hello.java  --javac-->  Hello.class (bytecode)  --JVM-->  machine code  -->  output
```

1. **Write** the program in a `.java` file.
2. **Compile** with `javac`. The compiler checks syntax and types and produces **bytecode** in a `.class` file (not machine code).
3. **Run** with `java`. The **JVM** loads the class, verifies the bytecode, then executes it: it interprets the bytecode and JIT-compiles frequently used parts to native code.

Bytecode is platform independent, so the same `.class` file runs on any OS that has a JVM ("write once, run anywhere").

## JVM, JRE, JDK

| Term | What it is |
| --- | --- |
| JVM | Runs the bytecode |
| JRE | JVM + standard class libraries (enough to *run* Java) |
| JDK | JRE + development tools such as `javac` (needed to *write and compile* Java) |

## Packages in a nutshell

- A package groups related classes: `package day1.calculator;`
- The folder structure must match the package name: `src/day1/calculator/Calculator.java`
- Use `import` to use a class from another package. The class and the members you use must be `public`.
- Compile with `javac -d out ...` and run with the fully qualified class name:
  `java -cp out day1.app.CalculatorApp`
