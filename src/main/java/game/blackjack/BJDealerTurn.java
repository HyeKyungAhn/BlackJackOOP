package game.blackjack;

import card.Card;
import card.blackjack.BJDealerHand;
import card.Deck;
import game.Playable;
import viewer.Viewer;
import viewer.ViewerStatus;

import java.util.List;

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

        repeatHitTo17();

        if(dealerHand.countAndVerifyBusted()){
            return NextTurnStatus.EARLY_SETTLING_TURN;
        }
        return NextTurnStatus.LAZY_SETTLING_TURN;
    }

    private void repeatHitTo17() {
        BJDealerHand dealerHand = (BJDealerHand) dealer.getHand();

        do {
            List<Card> cards = dealer.totalOpen();
            Viewer.printInfo(ViewerStatus.DEALER_HAND);
            Viewer.showCards(cards);

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
