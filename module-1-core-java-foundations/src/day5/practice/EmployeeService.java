package day5.practice;
import java.util.*;

public class EmployeeService {
    private ArrayList<Employee> employees = new ArrayList<>();

    // CREATE
    public void addEmployee(Employee e) {
        employees.add(e);
        System.out.println("Employee added successfully.");
    }

    // READ
    public void displayEmployees() {
        for (Employee e : employees) {
            e.display();
        }
    }

    // UPDATE
    public void updateEmployee(int id, String name, double salary) {
        for (Employee e : employees) {
            if (e.getId() == id) {
                e.setName(name);
                e.setSalary(salary);
                System.out.println("Employee updated.");
                return;
            }
        }

        System.out.println("Employee not found.");
    }

    // DELETE using Iterator
    public void deleteEmployee(int id) {
        Iterator<Employee> it = employees.iterator();

        while (it.hasNext()) {
            Employee e = it.next();

            if (e.getId() == id) {
                it.remove();
                System.out.println("Employee deleted.");
                return;
            }
        }

        System.out.println("Employee not found.");
    }

    // ITERATOR traversal
    public void traverseIterator() {
        System.out.println("\nUsing Iterator:");

        Iterator<Employee> it = employees.iterator();

        while (it.hasNext()) {
            it.next().display();
        }
    }

    // LIST ITERATOR forward and backward
    public void traverseListIterator() {
        ListIterator<Employee> it = employees.listIterator();

        System.out.println("\nForward Traversal:");
        while (it.hasNext()) {
            it.next().display();
        }

        System.out.println("\nBackward Traversal:");
        while (it.hasPrevious()) {
            it.previous().display();
        }
    }

    // INCREASING ORDER
    public void increasingOrder() {
        employees.sort(Comparator.comparingInt(Employee::getId));

        System.out.println("\nIncreasing Order of ID:");
        displayEmployees();
    }

    // DECREASING ORDER
    public void decreasingOrder() {
        employees.sort(
                Comparator.comparingInt(Employee::getId).reversed()
        );

        System.out.println("\nDecreasing Order of ID:");
        displayEmployees();
    }
}
