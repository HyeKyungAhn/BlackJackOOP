package blackjack;

import card.Deck;
import game.Playable;
import game.Turn;

import java.util.List;

public class BJDealingTurn implements Turn {
    List<Playable> players;
    Deck deck;

    BJDealingTurn(){}

    BJDealingTurn(List<Playable> players, Deck deck) {
        this.players = players;
        this.deck = deck;
    }

    public NextTurnStatus nextTurn() {

        for (Playable playable : players) {
            playable.hit(deck.giveOneCard());
            playable.hit(deck.giveOneCard());
            openCards(playable);
        }

        return NextTurnStatus.PLAYER_TURN;
    }

    private void openCards(Playable playable) {
        if(playable instanceof BJDealer){
            Viewer.printInfo(ViewerStatus.DEALER_HAND);
        } else {
            Viewer.printInfo(ViewerStatus.PLAYER_HAND);
        }

        Viewer.showCards(playable.open());
    }
}
