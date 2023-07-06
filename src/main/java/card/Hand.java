package card;

import card.Card;

import java.util.List;

public interface Hand {
    void addCard(Card card);
    List<Card> getCards();
    void initValues();
}
