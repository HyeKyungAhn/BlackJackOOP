package game.blackjack;

import card.*;
import card.Hand;
import card.blackjack.BJDealerHand;
import game.PlayableImpl;

import java.util.List;

public class BJDealerImpl extends PlayableImpl implements BJDealer {

    public BJDealerImpl(){
    }

    public BJDealerImpl(Hand hand) {
        super(hand);
    }

    @Override
    public List<Card> totalOpen(){
        ((BJDealerHand) hand).openHiddenCard();
        return open();
    }

    @Override
    public void initValues() {
        hand.initValues();
    }
}
