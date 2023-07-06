package game.blackjack;

import java.util.regex.Pattern;

public interface InputValidator {
    static boolean isOneOrTwo(String input) throws IllegalStateException{
        return Pattern.matches("^[1|2]$",input);
    }

    static boolean isLong(String input) throws NumberFormatException{
        return Pattern.matches("^[1-9][0-9]*$",input);
    }
}
