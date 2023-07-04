package card;

public interface BJDealerHand extends BlackJackHand{
    int getCount();
    void setCount(int count);
    void openHiddenCard();
    boolean hasACard(Hand hand); // 파라미터의 타입의 추상화는 어느정도여야 하는가?
}
