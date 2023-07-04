package blackjack;

import card.Deck;
import game.Playable;

public class BJDealerTurn implements DealerTurn{
    @Override
    public NextTurnStatus nextTurn(Playable playable, Deck deck) {
        System.out.println("딜러 17까지 hit");
        return NextTurnStatus.LAZY_SETTLING_TURN;
    }
}
