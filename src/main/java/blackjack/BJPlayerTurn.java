package blackjack;

import card.BJDealerHand;
import card.Deck;
import game.Playable;
import game.Turn;

import java.util.List;

public class BJPlayerTurn implements TurnWithDeck {
    public NextTurnStatus nextTurn(List<Playable> players, Deck deck) {
        Player player = null;
        Dealer dealer = null;

        for(Playable playable : players){
            if (playable instanceof Dealer) {
                dealer = (Dealer) playable;
            } else {
                player = (Player) playable;
            }
        }

//        // checking first card is A
//        if(evaluator.isDealerCardA(dealer)){
//            // checking insurance
//            Viewer.printInfo(ViewerStatus.INSURANCE);
//            if(isYES(scan)){
//                player.insure();
//            }
//        }

        return NextTurnStatus.DEALER_TURN;
    }
}
