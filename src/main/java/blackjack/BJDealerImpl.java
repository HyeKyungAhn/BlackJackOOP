package blackjack;

import card.*;
import game.Playable;
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
        ((BJDealerHand) super.hand).openHiddenCard();
        return open();
    }

    public boolean repeatHitTo17(Deck deck){
        boolean didHit = false;
        do {
            int count = ((BJDealerHand) hand).getCount();

            if (isUnder17(count)) {
                didHit = true;
                continue;
            }
            break;
        } while (true);

        return didHit;
    }

    private boolean isUnder17(int count){
        return count < 17;
    }
}
