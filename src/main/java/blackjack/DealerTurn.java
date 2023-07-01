package blackjack;

import card.Deck;
import game.Playable;
import game.Turn;

public class DealerTurn implements Turn {
    public static NextTurnStatus nextTurn(Playable playable, Deck deck) {
        System.out.println("딜러 17까지 hit");
        return NextTurnStatus.LAZY_SETTLING_TURN;
    }
}
