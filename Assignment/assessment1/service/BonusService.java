package assessment1.service;

import assessment1.model.Employee;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/** Sorting logic for the bonus report. Works with Employee only, not with the roles. */
public class BonusService {

    // highest bonus first; if the bonus is equal, the lower employee id comes first
    private static final Comparator<Employee> HIGHEST_BONUS_FIRST =
            Comparator.comparing(Employee::calculateBonus).reversed()
                    .thenComparingInt(Employee::getId);

    // returns a new sorted list, the given list is not changed
    public List<Employee> sortByBonusDescending(List<? extends Employee> employees) {
        List<Employee> sortedEmployees = new ArrayList<>(employees);
        sortedEmployees.sort(HIGHEST_BONUS_FIRST);
        return sortedEmployees;
    }
}
