package blackjack;

import card.*;
import game.PlayableImpl;

import java.util.List;

public class BJDealerImpl extends PlayableImpl implements BJDealer {

    BJDealerImpl(){
    }

    BJDealerImpl(Hand hand) {
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
