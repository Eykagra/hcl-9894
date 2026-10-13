package assessment1.model;

import java.math.BigDecimal;

/** Admin: festival bonus is 10% of (basic + HRA). */
public class Admin extends Employee {

    private static final BigDecimal BONUS_PERCENTAGE = BigDecimal.valueOf(10);

    public Admin(int id, String name, SalaryStructure salary) {
        super(id, name, salary);
    }

    @Override
    protected BigDecimal getBonusPercentage() {
        return BONUS_PERCENTAGE;
    }
}
