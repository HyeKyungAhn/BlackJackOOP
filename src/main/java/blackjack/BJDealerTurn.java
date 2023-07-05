package blackjack;

import card.Deck;
import game.Playable;
import game.Turn;

public class BJDealerTurn implements Turn {
    Playable dealer;
    Deck deck;

    BJDealerTurn(){}

    BJDealerTurn(Playable dealer, Deck deck) {
        this.dealer = dealer;
        this.deck = deck;
    }

    @Override
    public NextTurnStatus nextTurn() {
        System.out.println("딜러 17까지 hit");
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
