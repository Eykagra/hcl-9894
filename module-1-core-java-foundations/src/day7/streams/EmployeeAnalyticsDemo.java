package day7.streams;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Day 7 - Streams practice: 12 questions on a list of employees.
 */
public class EmployeeAnalyticsDemo {

    public static void main(String[] args) {
        List<Employee> employees = List.of(
                employee(1, "Asha", "Engineering", 90000, "2019-03-01", "Java", "SQL"),
                employee(2, "Ravi", "Engineering", 120000, "2016-07-15", "Java", "Spring", "AWS"),
                employee(3, "Meera", "HR", 60000, "2020-01-10", "Recruiting"),
                employee(4, "John", "Sales", 70000, "2018-11-20", "Negotiation", "CRM"),
                employee(5, "Priya", "Engineering", 105000, "2021-05-05", "Java", "React"),
                employee(6, "Karan", "Sales", 65000, "2022-02-14", "CRM"),
                employee(7, "Sara", "HR", 62000, "2017-09-09", "Recruiting", "Payroll"),
                employee(8, "Vikram", "Finance", 80000, "2015-04-30", "Excel", "SQL"));

        Comparator<Employee> bySalary = Comparator.comparing(Employee::salary);
        Comparator<Employee> bySalaryHighestFirst = bySalary.reversed();

        // Q1 Engineering employees, highest salary first
        List<String> q1 = employees.stream()
                .filter(e -> e.department().equals("Engineering"))
                .sorted(bySalaryHighestFirst)
                .map(Employee::name)
                .toList();
        System.out.println("Q1  Engineering, highest salary first: " + q1);                 // [Ravi, Priya, Asha]

        // Q2 Top 3 earners
        List<String> q2 = employees.stream()
                .sorted(bySalaryHighestFirst)
                .limit(3)
                .map(Employee::name)
                .toList();
        System.out.println("Q2  Top 3 earners: " + q2);                                     // [Ravi, Priya, Asha]

        // Q3 Names grouped by department
        Map<String, List<String>> q3 = employees.stream()
                .collect(Collectors.groupingBy(Employee::department, TreeMap::new,
                        Collectors.mapping(Employee::name, Collectors.toList())));
        System.out.println("Q3  Names by department: " + q3);
                                                    // {Engineering=[Asha, Ravi, Priya], Finance=[Vikram], HR=[Meera, Sara], Sales=[John, Karan]}

        // Q4 Number of employees in each department
        Map<String, Long> q4 = employees.stream()
                .collect(Collectors.groupingBy(Employee::department, TreeMap::new, Collectors.counting()));
        System.out.println("Q4  Employees per department: " + q4);                          // {Engineering=3, Finance=1, HR=2, Sales=2}

        // Q5 Average salary in each department
        Map<String, Double> q5 = employees.stream()
                .collect(Collectors.groupingBy(Employee::department, TreeMap::new,
                        Collectors.averagingDouble(e -> e.salary().doubleValue())));
        System.out.println("Q5  Average salary per department: " + q5);
                                                    // {Engineering=105000.0, Finance=80000.0, HR=61000.0, Sales=67500.0}

        // Q6 Highest paid employee in each department
        Map<String, String> q6 = employees.stream()
                .collect(Collectors.groupingBy(Employee::department, TreeMap::new,
                        Collectors.collectingAndThen(Collectors.maxBy(bySalary), best -> best.get().name())));
        System.out.println("Q6  Highest paid per department: " + q6);                       // {Engineering=Ravi, Finance=Vikram, HR=Sara, Sales=John}

        // Q7 Total salary of all employees
        BigDecimal q7 = employees.stream()
                .map(Employee::salary)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Q7  Total salary: " + q7);                                      // 652000

        // Q8 All different skills, sorted
        List<String> q8 = employees.stream()
                .flatMap(e -> e.skills().stream())
                .distinct()
                .sorted()
                .toList();
        System.out.println("Q8  All skills: " + q8);
                                                    // [AWS, CRM, Excel, Java, Negotiation, Payroll, React, Recruiting, SQL, Spring]

        // Q9 Employees who know Java
        List<String> q9 = employees.stream()
                .filter(e -> e.skills().contains("Java"))
                .map(Employee::name)
                .toList();
        System.out.println("Q9  Know Java: " + q9);                                         // [Asha, Ravi, Priya]

        // Q10 Employees hired before 2019, earliest hire first
        List<String> q10 = employees.stream()
                .filter(e -> e.hireDate().isBefore(LocalDate.of(2019, 1, 1)))
                .sorted(Comparator.comparing(Employee::hireDate))
                .map(Employee::name)
                .toList();
        System.out.println("Q10 Hired before 2019: " + q10);                                // [Vikram, Ravi, Sara, John]

        // Q11 Split into salary >= 90000 and the rest
        Map<Boolean, List<String>> q11 = employees.stream()
                .collect(Collectors.partitioningBy(e -> e.salary().compareTo(BigDecimal.valueOf(90000)) >= 0,
                        Collectors.mapping(Employee::name, Collectors.toList())));
        System.out.println("Q11 Salary >= 90000: " + q11);                                  // {false=[Meera, John, Karan, Sara, Vikram], true=[Asha, Ravi, Priya]}

        // Q12 Second highest paid employee
        String q12 = employees.stream()
                .sorted(bySalaryHighestFirst)
                .skip(1)
                .findFirst()
                .map(Employee::name)
                .orElse("N/A");
        System.out.println("Q12 Second highest paid: " + q12);                              // Priya
    }

    // small helper to keep the data above short
    private static Employee employee(int id, String name, String department, int salary, String hireDate, String... skills) {
        return new Employee(id, name, department, BigDecimal.valueOf(salary), LocalDate.parse(hireDate), Set.of(skills));
    }
}
