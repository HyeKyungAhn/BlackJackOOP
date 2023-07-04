package blackjack;

import card.Deck;
import game.Playable;
import game.Turn;

import java.util.List;

public interface SettlingTurn extends Turn {
    NextTurnStatus nextTurn(List<Playable> playable);
}
