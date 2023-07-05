package blackjack;

import game.Playable;
import game.Turn;

import java.util.List;

public class BJLazySettlingTurn implements Turn {
    List<Playable> players;

    BJLazySettlingTurn(){}

    BJLazySettlingTurn(List<Playable> players) {
        this.players = players;
    }

    public NextTurnStatus nextTurn() {
        System.out.println("최종 계산");
        return NextTurnStatus.FINISH_TURN;
    }
}
