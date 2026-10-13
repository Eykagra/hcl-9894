package assessment1.app;

import assessment1.model.Accountant;
import assessment1.model.Admin;
import assessment1.model.Employee;
import assessment1.model.Engineer;
import assessment1.model.Manager;
import assessment1.model.SalaryStructure;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/** Employee data used by the application. Salary order: basic, HRA, TA, special allowance. */
public final class SampleEmployees {

    private SampleEmployees() {
    }

    public static List<Employee> create() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Manager(101, "Rahul", salary("40000", "16000", "3000", "5000")));
        employees.add(new Engineer(102, "Priya", salary("35000", "14000", "2500", "4000")));
        employees.add(new Admin(103, "Amit", salary("30000", "12000", "2000", "3000")));
        employees.add(new Accountant(104, "Sneha", salary("32000", "12800", "2000", "3500")));
        employees.add(new Engineer(105, "Karan", salary("35000", "14000", "2500", "6000")));
        employees.add(new Manager(106, "Meera", salary("52000", "20800", "3500", "7000")));
        employees.add(new Accountant(107, "Vikram", salary("45000.50", "18000.25", "2500", "4500")));
        employees.add(new Admin(108, "Anita", salary("28000", "11200", "1800", "2500")));
        return employees;
    }

    private static SalaryStructure salary(String basic, String hra, String travelAllowance, String specialAllowance) {
        return new SalaryStructure(
                new BigDecimal(basic),
                new BigDecimal(hra),
                new BigDecimal(travelAllowance),
                new BigDecimal(specialAllowance));
    }
}
