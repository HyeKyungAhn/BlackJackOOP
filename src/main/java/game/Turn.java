package game;

import blackjack.NextTurnStatus;

public interface Turn {
    NextTurnStatus nextTurn();
}
