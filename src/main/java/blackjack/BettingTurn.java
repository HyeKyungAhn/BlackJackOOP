package blackjack;

import game.Gambler;
import game.Turn;

import java.util.List;

public class BettingTurn implements Turn {
    private static final long BET_MAX = 100_000_000_000L;

    public static NextTurnStatus nextTurn(List<Gambler> players) {

        System.out.println("배팅 완료");
        //실패시
//        return NextTurnStatus.DEALING_TURN;

        return NextTurnStatus.DEALING_TURN;
    }

}
