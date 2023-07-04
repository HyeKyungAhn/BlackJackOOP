package blackjack;

import blackjack.NextTurnStatus;
import game.Playable;
import game.Turn;

import java.util.List;

public interface LazySettlingTurn extends Turn {
    static NextTurnStatus nextTurn(List<Playable> players) {
        System.out.println("최종 계산");
        return NextTurnStatus.FINISH_TURN;
    }
}
