package assessment2.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.Scanner;
import java.util.function.Function;

/** Reads text and numbers from the keyboard. The number readers ask again until the input is valid. */
public class ConsoleInput {

    private final Scanner scanner;
    private final PrintStream out;

    public ConsoleInput(InputStream in, PrintStream out) {
        this.scanner = new Scanner(in);
        this.out = out;
    }

    // shows the prompt and returns the typed line without leading/trailing spaces
    public String readLine(String prompt) {
        out.print(prompt);
        out.flush();
        return scanner.nextLine().trim();
    }

    // asks until a whole number is typed
    public int readInt(String prompt) {
        return readParsed(prompt, Integer::parseInt, "a whole number", false);
    }

    // like readInt, but an empty answer is allowed and returns null (= no change)
    public Integer readOptionalInt(String prompt) {
        return readParsed(prompt, Integer::parseInt, "a whole number", true);
    }

    // asks until a valid amount like 499 or 499.50 is typed
    public BigDecimal readAmount(String prompt) {
        return readParsed(prompt, BigDecimal::new, "a valid amount", false);
    }

    // like readAmount, but an empty answer is allowed and returns null (= no change)
    public BigDecimal readOptionalAmount(String prompt) {
        return readParsed(prompt, BigDecimal::new, "a valid amount", true);
    }

    // one loop for all number types: the parser returns the value or throws NumberFormatException
    private <T> T readParsed(String prompt, Function<String, T> parser, String expectedKind, boolean emptyAllowed) {
        while (true) {
            String text = readLine(prompt);
            if (text.isEmpty() && emptyAllowed) {
                return null;
            }
            try {
                return parser.apply(text);
            } catch (NumberFormatException e) {
                out.println("'" + text + "' is not " + expectedKind + ". Please try again.");
            }
        }
    }
}
