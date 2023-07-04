package blackjack;

import blackjack.NextTurnStatus;
import game.Playable;
import game.Turn;

import java.util.List;

public interface EarlySettlingTurn extends Turn {
    static NextTurnStatus nextTurn(List<Playable> players) {
        System.out.println("이른 판정");
        return NextTurnStatus.FINISH_TURN;
    }
}
