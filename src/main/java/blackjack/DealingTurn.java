package blackjack;

import card.Deck;
import game.Turn;
import game.Playable;

import java.util.List;

public class DealingTurn implements Turn {

    public static NextTurnStatus nextTurn(List<Playable> players, Deck deck) {
        //dealing
        System.out.println("카드 받음");
        return NextTurnStatus.PLAYER_TURN;
    }
}
