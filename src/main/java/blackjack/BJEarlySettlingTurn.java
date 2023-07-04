package blackjack;

import game.Playable;

import java.util.List;

public class BJEarlySettlingTurn implements SettlingTurn {
    public NextTurnStatus nextTurn(List<Playable> players) {
        System.out.println("이른 판정");
        return NextTurnStatus.FINISH_TURN;
    }
}
