package blackjack;

import game.InputProcessor;

import java.util.Scanner;
import java.util.regex.Pattern;

public class BJInputProcessor implements InputProcessor {
    private static final int YES = 1;
    private static final int NO = 2;

    public static boolean getBooleanAnswer(Scanner scanner) {
        while (true) {
            try {
                String input = scanner.nextLine();

                validateYesOrNo(input);

                return input.equals("1");
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } finally {
                Viewer.printInfo(ViewerStatus.INVALID_INPUT);
            }
        }
    }
    public static void validateYesOrNo(String input) throws IllegalStateException{
        if(!Pattern.matches("^[1|2]$",input)){
            throw new IllegalStateException();
        }
    }
}
