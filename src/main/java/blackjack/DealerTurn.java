package blackjack;

import blackjack.NextTurnStatus;
import game.Turn;

public interface DealerTurn extends Turn {
    NextTurnStatus nextTurn();
}
