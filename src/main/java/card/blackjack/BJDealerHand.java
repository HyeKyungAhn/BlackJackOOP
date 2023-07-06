package card.blackjack;

import card.Card;

public interface BJDealerHand extends BlackJackHand {
    void openHiddenCard();
    boolean hasACard();
    Card getImitationCard();
}
