package game.blackjack;

import card.Deck;
import game.Playable;
import viewer.Viewer;
import viewer.ViewerStatus;

import java.util.List;

public class BJDealingTurn implements DealingTurn {
    Deck deck;

    BJDealingTurn(){}

    BJDealingTurn(Deck deck) {
        this.deck = deck;
    }

    public NextTurnStatus nextTurn(List<Playable> playersAndDealer) {

        for (Playable playable : playersAndDealer) {
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
