package card;

public interface BJDealerHand extends BlackJackHand{
    void openHiddenCard();
    boolean hasACard();
    Card getImitationCard();
}
