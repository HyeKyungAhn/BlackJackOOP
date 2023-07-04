package blackjack;

import game.InputProcessor;

import java.util.Scanner;
import java.util.regex.Pattern;

public class BJInputProcessor implements InputProcessor {
    private static final String YES = "1";

    public static boolean getBooleanAnswer() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                String input = scanner.nextLine();

                if (!InputValidator.isOneOrTwo(input)) {
                    Viewer.printInfo(ViewerStatus.INVALID_INPUT);
                    continue;
                }

                return input.equals(YES);
            }
        }
    }

    public static long getLongValue(){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();

            if (!InputValidator.isLong(input)) {
                Viewer.printInfo(ViewerStatus.INVALID_BETTING_INPUT);
                continue;
            }

            return Long.parseLong(input);
        }
    }
}
