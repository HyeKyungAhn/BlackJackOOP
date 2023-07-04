package blackjack;

import blackjack.Dealer;
import blackjack.NextTurnStatus;
import blackjack.Player;
import card.Deck;
import game.Playable;
import game.Turn;

import java.util.List;

public interface PlayerTurn extends Turn {
    static NextTurnStatus nextTurn(List<Playable> players, Deck deck) {
        Player player;
        Dealer dealer;

        for(Playable playable : players){
            if (playable instanceof blackjack.Dealer) {
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
