package blackjack;

import blackjack.NextTurnStatus;
import card.Deck;
import game.Playable;
import game.Turn;

public interface DealerTurn extends Turn {
    NextTurnStatus nextTurn(Playable playable, Deck deck);
}
