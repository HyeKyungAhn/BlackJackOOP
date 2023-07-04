package game;

import card.Card;
import card.Hand;

import java.util.List;

public interface Playable {
    void hit(Card card);
    List<Card> open();
    Hand getHand();
}
