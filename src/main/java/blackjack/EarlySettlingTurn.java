package blackjack;

import game.Playable;
import game.Turn;

import java.util.List;
import java.util.Scanner;

public class EarlySettlingTurn implements Turn {
    public static NextTurnStatus nextTurn(List<Playable> players, Scanner scanner) {
        System.out.println("이른 판정");
        return NextTurnStatus.FINISH_TURN;
    }
}
