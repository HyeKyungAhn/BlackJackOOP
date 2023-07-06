package card;

public interface BlackJackHand extends Hand{
    int count();
    int getCount();
    boolean countAndVerifyBJ();
    boolean isBlackJack();
    boolean countAndVerifyBusted();
    boolean isBusted();
}
