package game;

import blackjacktest.Card;

import java.util.List;

public interface Playable {
    void hit(Card card);
    void open();
    List<Card> receiveCards();
}
