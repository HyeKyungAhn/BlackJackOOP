package game.blackjack;

import game.InputProcessor;
import viewer.Viewer;
import viewer.ViewerStatus;

import java.util.Scanner;

public class BJInputProcessor implements InputProcessor {
    private static final String YES = "1";

    public static boolean getBooleanAnswer(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine();

            if (!InputValidator.isOneOrTwo(input)) {
                Viewer.printInfo(ViewerStatus.INVALID_INPUT);
                continue;
            }

            return input.equals(YES);
        }
    }

    public static long getLongValue(Scanner scanner){
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
