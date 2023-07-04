package blackjack;

import game.Playable;

import java.util.List;

public class BJLazySettlingTurn implements SettlingTurn {
    public NextTurnStatus nextTurn(List<Playable> players) {
        System.out.println("최종 계산");
        return NextTurnStatus.FINISH_TURN;
    }
}
