package blackjack;

import game.Playable;
import game.Turn;

import java.util.List;
import java.util.Scanner;

public class LazySettlingTurn implements Turn {

    public static NextTurnStatus nextTurn(List<Playable> players, Scanner scanner) {
        System.out.println("최종 계산");
        return NextTurnStatus.FINISH_TURN;
    }
}
