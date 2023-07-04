package card;

import java.util.List;

public interface BlackJackHand extends Hand{
    int count(List<Card> cards);
    boolean isBlackJack(BlackJackHand hand);
    boolean isBlackJack(int count);
    boolean isBusted(BlackJackHand hand);
    boolean isBusted(int count);
}
