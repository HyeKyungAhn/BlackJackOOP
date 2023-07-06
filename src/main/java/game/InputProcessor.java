package game;

import java.util.Scanner;

public interface InputProcessor {
    String YES = "1";

    boolean getBooleanAnswer();
    long getLongValue();
}
