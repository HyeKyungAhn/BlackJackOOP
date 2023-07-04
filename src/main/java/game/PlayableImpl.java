package game;

import card.Card;
import card.Hand;

import java.util.List;

public class PlayableImpl implements Playable{
    protected Hand hand;

    protected PlayableImpl(){}

    protected PlayableImpl(Hand hand){
        this.hand = hand;
    }

    @Override
    public void hit(Card card) {
        hand.addCard(card);
    }

    @Override
    public List<Card> open() {
        return hand.getCards();
    }

    @Override
    public Hand getHand() {
        return hand;
    }
}
