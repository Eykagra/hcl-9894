package assessment1.model;

import java.math.BigDecimal;

/** Accountant: festival bonus is 8% of (basic + HRA). */
public class Accountant extends Employee {

    private static final BigDecimal BONUS_PERCENTAGE = BigDecimal.valueOf(8);

    public Accountant(int id, String name, SalaryStructure salary) {
        super(id, name, salary);
    }

    @Override
    protected BigDecimal getBonusPercentage() {
        return BONUS_PERCENTAGE;
    }
}
