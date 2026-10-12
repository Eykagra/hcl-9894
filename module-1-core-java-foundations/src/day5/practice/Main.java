package day5.practice;


public class Main {
    public static void main(String[] args) {

        EmployeeService service = new EmployeeService();

        // CREATE
        service.addEmployee(new Employee(103, "Rahul", 30000));
        service.addEmployee(new Employee(101, "Aman", 25000));
        service.addEmployee(new Employee(104, "Priya", 40000));
        service.addEmployee(new Employee(102, "Neha", 35000));

        // READ
        System.out.println("\nAll Employees:");
        service.displayEmployees();

        // UPDATE
        service.updateEmployee(101, "Aman Sharma", 28000);

        // DELETE
        service.deleteEmployee(104);

        // ITERATOR
        service.traverseIterator();

        // LIST ITERATOR
        service.traverseListIterator();

        // SORTING
        service.increasingOrder();
        service.decreasingOrder();
    }
}

