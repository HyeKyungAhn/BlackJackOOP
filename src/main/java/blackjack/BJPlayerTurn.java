package blackjack;

import card.BJDealerHand;
import card.BJPlayerHand;
import card.Deck;
import game.Playable;
import game.Turn;

import java.util.List;

public class BJPlayerTurn implements TurnWithDeck {
    public NextTurnStatus nextTurn(List<Playable> players, Deck deck) {
        BJPlayer player = null;
        BJDealer dealer = null;

        for(Playable playable : players){
            if (playable instanceof BJDealer) {
                dealer = (BJDealer) playable;
            } else {
                player = (BJPlayer) playable;
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
