package blackjack;

import card.Deck;
import game.Gambler;
import game.Playable;
import game.Turn;

import java.util.List;

public interface TurnWithDeck extends Turn {
    NextTurnStatus nextTurn(List<Playable> playable, Deck deck);
}
