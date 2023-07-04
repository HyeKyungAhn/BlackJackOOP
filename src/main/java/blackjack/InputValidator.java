package blackjack;

import java.util.regex.Pattern;

public class InputValidator {
    public static boolean isOneOrTwo(String input) throws IllegalStateException{
        return Pattern.matches("^[1|2]$",input);
    }

    public static boolean isLong(String input) throws NumberFormatException{
        return Pattern.matches("^[1-9][0-9]*$",input);
    }
}
