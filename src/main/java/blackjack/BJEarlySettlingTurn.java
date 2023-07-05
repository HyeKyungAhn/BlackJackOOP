package blackjack;

import game.Playable;
import game.Turn;

import java.util.List;

public class BJEarlySettlingTurn implements Turn {
    List<Playable> players;

    BJEarlySettlingTurn(){}

    BJEarlySettlingTurn(List<Playable> players) {
        this.players = players;
    }

    public NextTurnStatus nextTurn() {
        System.out.println("이른 판정");
        return NextTurnStatus.FINISH_TURN;
    }
}
