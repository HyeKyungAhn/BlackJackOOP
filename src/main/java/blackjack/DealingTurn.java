package blackjack;

import blackjack.NextTurnStatus;
import game.Playable;
import game.Turn;

import java.util.List;

public interface DealingTurn extends Turn {
    public NextTurnStatus nextTurn(List<Playable> playersAndDealer);
}
