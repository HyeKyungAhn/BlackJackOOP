package game.blackjack;

import game.Turn;

public interface DealerTurn extends Turn {
    NextTurnStatus nextTurn();
}
