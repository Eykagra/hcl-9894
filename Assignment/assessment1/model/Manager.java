package assessment1.model;

import java.math.BigDecimal;

/** Manager: festival bonus is 12% of (basic + HRA). */
public class Manager extends Employee {

    private static final BigDecimal BONUS_PERCENTAGE = BigDecimal.valueOf(12);

    public Manager(int id, String name, SalaryStructure salary) {
        super(id, name, salary);
    }

    @Override
    protected BigDecimal getBonusPercentage() {
        return BONUS_PERCENTAGE;
    }
}
