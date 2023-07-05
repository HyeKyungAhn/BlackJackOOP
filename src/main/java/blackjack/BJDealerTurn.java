package blackjack;

import card.BJDealerHand;
import card.Deck;
import game.Playable;

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

        Viewer.showCards(dealer.totalOpen());
        repeatHitTo17(dealerHand);

        // dealer isBusted 확인 안해도 될 듯

        return NextTurnStatus.LAZY_SETTLING_TURN;
    }

    private void repeatHitTo17(BJDealerHand dealerHand){
        int count;
        do {
            count = dealerHand.count();

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
