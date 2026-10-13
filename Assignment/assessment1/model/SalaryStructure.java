package assessment1.model;

import java.math.BigDecimal;

/** Salary parts of an employee: basic, HRA, TA and special allowance. */
public class SalaryStructure {

    private final BigDecimal basic;
    private final BigDecimal hra;
    private final BigDecimal travelAllowance;
    private final BigDecimal specialAllowance;

    public SalaryStructure(BigDecimal basic, BigDecimal hra, BigDecimal travelAllowance, BigDecimal specialAllowance) {
        this.basic = requireNotNegative(basic, "Basic");
        this.hra = requireNotNegative(hra, "HRA");
        this.travelAllowance = requireNotNegative(travelAllowance, "Travel allowance");
        this.specialAllowance = requireNotNegative(specialAllowance, "Special allowance");
    }

    // the bonus is calculated only on basic + HRA
    public BigDecimal getBonusBase() {
        return basic.add(hra);
    }

    public BigDecimal getTotalSalary() {
        return basic.add(hra).add(travelAllowance).add(specialAllowance);
    }

    public BigDecimal getBasic() {
        return basic;
    }

    public BigDecimal getHra() {
        return hra;
    }

    public BigDecimal getTravelAllowance() {
        return travelAllowance;
    }

    public BigDecimal getSpecialAllowance() {
        return specialAllowance;
    }

    private static BigDecimal requireNotNegative(BigDecimal amount, String componentName) {
        if (amount == null) {
            throw new IllegalArgumentException(componentName + " must not be null");
        }
        if (amount.signum() < 0) {
            throw new IllegalArgumentException(componentName + " must not be negative");
        }
        return amount;
    }
}
