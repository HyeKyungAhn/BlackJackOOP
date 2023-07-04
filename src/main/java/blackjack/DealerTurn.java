package blackjack;

import blackjack.NextTurnStatus;
import card.Deck;
import game.Playable;
import game.Turn;

public interface DealerTurn extends Turn {
    static NextTurnStatus nextTurn(Playable playable, Deck deck) {
        System.out.println("딜러 17까지 hit");
        return NextTurnStatus.LAZY_SETTLING_TURN;
    }
}
