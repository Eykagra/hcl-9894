package day4.comparable;

/**
 * Day 4 - Comparable and toString().
 *
 * Implementing Comparable<Student> gives Student a "natural order" that Collections.sort() uses.
 * compareTo() orders by age first; if two students have the same age, it orders by marks.
 */
public class Student implements Comparable<Student> {

    private final int rollNo;
    private final String name;
    private final int age;
    private final int marks;

    public Student(int rollNo, String name, int age, int marks) {
        this.rollNo = rollNo;
        this.name = name;
        this.age = age;
        this.marks = marks;
    }

    public int getRollNo() {
        return rollNo;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getMarks() {
        return marks;
    }

    // negative: this comes before other, zero: same position, positive: this comes after other
    @Override
    public int compareTo(Student other) {
        if (this.age != other.age) {
            return Integer.compare(this.age, other.age);    // youngest first
        }
        return Integer.compare(this.marks, other.marks);    // same age: lowest marks first
    }

    // without this, printing a Student shows something like Student@1b6d3586
    @Override
    public String toString() {
        return "Student{rollNo=" + rollNo + ", name='" + name + "', age=" + age + ", marks=" + marks + "}";
    }
}
