package blackjack;

import card.BJDealerHand;
import card.Deck;
import game.Playable;
import game.Turn;

public class BJDealerTurn implements Turn {
    BJDealer dealer;
    Deck deck;

    BJDealerTurn(){}

    BJDealerTurn(BJDealer dealer, Deck deck) {
        this.dealer = dealer;
        this.deck = deck;
    }

    @Override
    public NextTurnStatus nextTurn() {
        BJDealerHand dealerHand = (BJDealerHand) dealer.getHand();

        Viewer.showCards(dealer.totalOpen());

        repeatHitTo17(dealerHand);

        // dealer isBusted 확인 안해도 될 듯

        return NextTurnStatus.LAZY_SETTLING_TURN;
    }

    private void repeatHitTo17(BJDealerHand dealerHand){
        do {
            int count = dealerHand.getCount();

            if (isUnder17(count)) {
                dealer.hit(deck.giveOneCard());
                Viewer.showCards(dealer.open());
                continue;
            }
            break;
        } while (true);
    }

    private boolean isUnder17(int count){
        return count < 17;
    }
}
