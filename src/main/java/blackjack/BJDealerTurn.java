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
}
