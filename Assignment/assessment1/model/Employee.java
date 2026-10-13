package assessment1.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

/** Base class of all employees. Each role only gives its own bonus percentage. */
public abstract class Employee {

    private static final BigDecimal ONE_HUNDRED = BigDecimal.valueOf(100);
    private static final int MONEY_DECIMAL_PLACES = 2;

    private final int id;
    private final String name;
    private final SalaryStructure salary;

    protected Employee(int id, String name, SalaryStructure salary) {
        if (id <= 0) {
            throw new IllegalArgumentException("Employee id must be a positive number, but was " + id);
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Employee name must not be empty");
        }
        if (salary == null) {
            throw new IllegalArgumentException("Salary structure must not be null");
        }
        this.id = id;
        this.name = name.trim();
        this.salary = salary;
    }

    // bonus percentage of the role, e.g. 12 means 12%
    protected abstract BigDecimal getBonusPercentage();

    // bonus = percentage of (basic + HRA), rounded to 2 decimal places
    public BigDecimal calculateBonus() {
        return salary.getBonusBase()
                .multiply(getBonusPercentage())
                .divide(ONE_HUNDRED, MONEY_DECIMAL_PLACES, RoundingMode.HALF_UP);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public SalaryStructure getSalary() {
        return salary;
    }
}
