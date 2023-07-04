package blackjack;

import card.Card;
import card.Deck;
import game.Playable;

import java.util.List;

public interface BJDealer extends Playable {
    List<Card> totalOpen();
    boolean repeatHitTo17(Deck deck);
}
