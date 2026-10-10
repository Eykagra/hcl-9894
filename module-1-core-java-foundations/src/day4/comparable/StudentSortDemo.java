package day4.comparable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Day 4 - Sorting Student objects with Comparable: by age, then by marks when the ages are equal.
 */
public class StudentSortDemo {

    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student(101, "Asha", 20, 78));
        students.add(new Student(102, "Ravi", 19, 85));
        students.add(new Student(103, "Meera", 20, 65));
        students.add(new Student(104, "John", 21, 90));
        students.add(new Student(105, "Kiran", 19, 70));

        System.out.println("Before sorting:");
        for (Student student : students) {
            System.out.println(student);        // uses toString()
        }

        Collections.sort(students);             // uses compareTo()

        System.out.println();
        System.out.println("After sorting (age, then marks):");
        for (Student student : students) {
            System.out.println(student);
        }
    }
}
