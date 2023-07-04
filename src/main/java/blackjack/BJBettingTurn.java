package blackjack;

import game.Gambler;

import java.util.List;

public class BJBettingTurn implements GamblerTurn{
    public static final long BET_MIN = 100L;
    public static final long BET_MAX = 100_000_000_000L;

    @Override
    public NextTurnStatus nextTurn(List<Gambler> gamblers) {
        for (Gambler gambler : gamblers) {
            gambler.bet();
        }

        return NextTurnStatus.DEALING_TURN;
    }
}
