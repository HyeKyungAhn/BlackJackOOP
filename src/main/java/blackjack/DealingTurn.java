package blackjack;

import blackjack.NextTurnStatus;
import card.Deck;
import game.Playable;
import game.Turn;

import java.util.List;

public interface DealingTurn extends Turn {
    static NextTurnStatus nextTurn(List<Playable> players, Deck deck) {

        for (Playable playable : players) {
            playable.hit(deck.giveOneCard());
            playable.hit(deck.giveOneCard());
            openCards(playable);
        }

        return NextTurnStatus.PLAYER_TURN;
    }

    static void openCards(Playable playable) {
        if(playable instanceof Dealer){
            Viewer.printInfo(ViewerStatus.DEALER_HAND);
        } else {
            Viewer.printInfo(ViewerStatus.PLAYER_HAND);
        }

        Viewer.showCards(playable.open());
    }
}
