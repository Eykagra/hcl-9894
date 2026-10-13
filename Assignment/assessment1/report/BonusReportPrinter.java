package assessment1.report;

import assessment1.model.Employee;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/** Prints the bonus report in the required format. */
public class BonusReportPrinter {

    private static final String SEPARATOR = "---------------------------------------------";
    private static final String CURRENCY_PREFIX = "Rs. ";
    private static final int DECIMAL_PLACES = 2;

    private final PrintStream out;

    public BonusReportPrinter(PrintStream out) {
        this.out = out;
    }

    public void print(List<? extends Employee> employees) {
        for (Employee employee : employees) {
            out.println(SEPARATOR);
            out.println("Emp_id: " + employee.getId());
            out.println("Name: " + employee.getName());
            out.println("Bonus: " + CURRENCY_PREFIX + formatAmount(employee.calculateBonus()));
        }
    }

    // whole amounts without decimals (6700), other amounts with two (5040.06)
    private static String formatAmount(BigDecimal amount) {
        boolean isWholeNumber = amount.stripTrailingZeros().scale() <= 0;
        if (isWholeNumber) {
            return amount.setScale(0, RoundingMode.UNNECESSARY).toPlainString();
        }
        return amount.setScale(DECIMAL_PLACES, RoundingMode.HALF_UP).toPlainString();
    }
}
