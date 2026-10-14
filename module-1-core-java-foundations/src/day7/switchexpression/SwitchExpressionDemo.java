package day7.switchexpression;

/**
 * Day 7 - Switch expression (Java 14+): the arrow form gives back a value,
 * needs no break and has no fall-through.
 */
public class SwitchExpressionDemo {

    enum Size { SMALL, MEDIUM, LARGE }

    public static void main(String[] args) {
        System.out.println(dayName(3));                         // Wednesday
        System.out.println(dayName(9));                         // Invalid day!

        // several labels in one case
        for (String day : new String[]{"Monday", "Saturday", "Funday"}) {
            System.out.println(day + " -> " + dayType(day));    // Weekday, Weekend, Invalid day!
        }

        for (int marks : new int[]{95, 72, 58, 45, 20}) {
            System.out.println(marks + " -> grade " + grade(marks));    // A, B, C, D, F
        }
        System.out.println(price(Size.MEDIUM));                 // 120
    }

    // the whole switch is one value, so it can be returned or assigned to a variable
    private static String dayName(int day) {
        return switch (day) {
            case 1 -> "Monday";
            case 2 -> "Tuesday";
            case 3 -> "Wednesday";
            case 4 -> "Thursday";
            case 5 -> "Friday";
            case 6 -> "Saturday";
            case 7 -> "Sunday";
            default -> "Invalid day!";
        };
    }

    private static String dayType(String day) {
        return switch (day) {
            case "Saturday", "Sunday" -> "Weekend";
            case "Monday", "Tuesday", "Wednesday", "Thursday", "Friday" -> "Weekday";
            default -> "Invalid day!";
        };
    }

    // a case with more than one statement uses a block, and yield gives back the value
    private static String grade(int marks) {
        return switch (marks / 10) {
            case 10, 9 -> "A";
            case 8, 7 -> "B";
            case 6, 5 -> "C";
            default -> {
                if (marks >= 40) {
                    yield "D";
                }
                yield "F";
            }
        };
    }

    // for an enum, listing every constant is enough: no default needed
    private static int price(Size size) {
        return switch (size) {
            case SMALL -> 80;
            case MEDIUM -> 120;
            case LARGE -> 160;
        };
    }
}
