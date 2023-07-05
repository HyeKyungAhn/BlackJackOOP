package blackjack;

import game.Turn;

public interface BettingTurn extends Turn {
    long BET_MIN = 100L;
    long BET_MAX = 100_000_000_000L;

    NextTurnStatus nextTurn(BJPlayer player);
}
