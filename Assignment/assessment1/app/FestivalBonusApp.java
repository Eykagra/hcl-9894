package assessment1.app;

import assessment1.model.Employee;
import assessment1.report.BonusReportPrinter;
import assessment1.service.BonusService;

import java.util.List;

/** Assessment 1: festival bonus report, highest bonus first. */
public class FestivalBonusApp {

    public static void main(String[] args) {
        try {
            List<Employee> employees = SampleEmployees.create();
            List<Employee> employeesByBonus = new BonusService().sortByBonusDescending(employees);
            new BonusReportPrinter(System.out).print(employeesByBonus);
        } catch (IllegalArgumentException e) {
            System.out.println("Cannot create the bonus report: " + e.getMessage());
        }
    }
}
