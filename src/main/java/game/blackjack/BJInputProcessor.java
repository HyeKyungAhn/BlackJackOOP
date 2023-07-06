package game.blackjack;

import game.InputProcessor;
import viewer.Viewer;
import viewer.ViewerStatus;

import java.util.Scanner;

public class BJInputProcessor implements InputProcessor {
    Scanner scanner;

    BJInputProcessor(){}

    BJInputProcessor(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public boolean getBooleanAnswer() {
        while (true) {
            String input = scanner.nextLine();

            if (!InputValidator.isOneOrTwo(input)) {
                Viewer.printInfo(ViewerStatus.INVALID_INPUT);
                continue;
            }

            return input.equals(YES);
        }
    }

    @Override
    public long getLongValue() {
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
