package day7.switchexpression;

/**
 * Day 7 - The classic switch statement: every case needs a break,
 * otherwise execution "falls through" into the next case.
 */
public class SwitchStatementDemo {

    public static void main(String[] args) {
        printDayName(3);
        printDayName(9);
        fallThrough();
    }

    private static void printDayName(int day) {
        switch (day) {
            case 1:
                System.out.println("Monday");
                break;
            case 2:
                System.out.println("Tuesday");
                break;
            case 3:
                System.out.println("Wednesday");        // Wednesday
                break;
            case 4:
                System.out.println("Thursday");
                break;
            case 5:
                System.out.println("Friday");
                break;
            case 6:
                System.out.println("Saturday");
                break;
            case 7:
                System.out.println("Sunday");
                break;
            default:
                System.out.println("Invalid day!");     // Invalid day!
        }
    }

    // the missing break after case 2 is the classic mistake: case 3 runs as well
    @SuppressWarnings("fallthrough")
    private static void fallThrough() {
        int level = 2;
        switch (level) {
            case 1:
                System.out.println("level 1");
            case 2:
                System.out.println("level 2");          // level 2
            case 3:
                System.out.println("level 3");          // level 3 (fall-through)
                break;
            default:
                System.out.println("unknown level");
        }
    }
}
