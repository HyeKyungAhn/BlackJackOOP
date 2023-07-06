package game.blackjack;

import card.blackjack.BJDealerHand;
import card.Deck;
import game.Playable;
import viewer.Viewer;

public class BJDealerTurn implements DealerTurn {
    BJDealer dealer;
    Deck deck;

    BJDealerTurn(){}

    BJDealerTurn(Playable dealer, Deck deck) {
        this.dealer = (BJDealer) dealer;
        this.deck = deck;
    }

    @Override
    public NextTurnStatus nextTurn() {
        BJDealerHand dealerHand = (BJDealerHand) dealer.getHand();

        repeatHitTo17(dealer);

        if(dealerHand.countAndVerifyBusted()){
            return NextTurnStatus.EARLY_SETTLING_TURN;
        }
        return NextTurnStatus.LAZY_SETTLING_TURN;
    }

    private void repeatHitTo17(BJDealer dealer){
        BJDealerHand dealerHand = (BJDealerHand) dealer.getHand();

        do {
            Viewer.showCards(dealer.totalOpen());

            if (isUnder17(dealerHand.count())) {
                dealer.hit(deck.giveOneCard());
                continue;
            }

            break;
        } while (true);
    }

    private boolean isUnder17(int count){
        return count < 17;
    }
}
