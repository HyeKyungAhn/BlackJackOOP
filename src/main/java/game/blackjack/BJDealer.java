package game.blackjack;

import card.Card;
import game.Playable;

import java.util.List;

public interface BJDealer extends Playable {
    List<Card> totalOpen();
    void initValues();
}
