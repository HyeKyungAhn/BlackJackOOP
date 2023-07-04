package game;

import card.Card;

import java.util.List;

public interface Playable {
    void hit(Card card);
    List<Card> open();
//    List<Card> receiveCards();
}
