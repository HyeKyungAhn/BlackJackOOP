package blackjack;

import card.Deck;
import game.Gambler;
import game.Playable;

import java.util.List;

public class BJDealingTurn implements TurnWithDeck {
    public NextTurnStatus nextTurn(List<Playable> players, Deck deck) {

        for (Playable playable : players) {
            playable.hit(deck.giveOneCard());
            playable.hit(deck.giveOneCard());
            openCards(playable);
        }

        return NextTurnStatus.PLAYER_TURN;
    }

    private void openCards(Playable playable) {
        if(playable instanceof Dealer){
            Viewer.printInfo(ViewerStatus.DEALER_HAND);
        } else {
            Viewer.printInfo(ViewerStatus.PLAYER_HAND);
        }

        Viewer.showCards(playable.open());
    }
}
