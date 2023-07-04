package blackjack;

import card.*;
import game.Playable;

import java.util.List;

public class Dealer implements Playable {
    HandImpl hand;

    Dealer(){
        hand = new BJDealerHandImpl();
    }

    @Override
    public void hit(Card card) {
        hand.addCard(card);
    }

    @Override
    public List<Card> open() {
        return hand.getCards();
    }

    public List<Card> totalOpen(){
        ((BJDealerHand) hand).openHiddenCard();
        return open();
    }

    public HandImpl getHand() {
        return hand;
    }

    public boolean repeatHitTo17(Deck deck){

//        do{
//            int count = evaluator.count(hand.getCards());
//
//            if(isUnder17(count)){
//                hit(deck.giveOneCard());
//                didHit = true;
//                continue;
//            }
//
//            hand.setCount(count);
//            break;
//        } while (true);
        return false;
    }

    private boolean isUnder17(int count){
        return count < 17;
    }

}
