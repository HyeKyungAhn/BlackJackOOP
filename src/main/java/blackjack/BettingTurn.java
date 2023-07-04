package blackjack;

import blackjack.NextTurnStatus;
import game.Gambler;
import game.Turn;

import java.util.List;

public interface BettingTurn extends Turn {
    long BET_MIN = 100L;
    long BET_MAX = 100_000_000_000L;

    static NextTurnStatus nextTurn(List<Gambler> players) {
        for (Gambler gambler : players) {
            gambler.bet();
        }

        return NextTurnStatus.DEALING_TURN;
    }
}
