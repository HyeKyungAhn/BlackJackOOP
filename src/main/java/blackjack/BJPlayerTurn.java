package blackjack;

import card.BJDealerHand;
import card.BJPlayerHand;
import card.Deck;
import game.Playable;

import java.util.List;

/**
 * BJPlayerTurn controls player's choice
 *
 * In some cases, player can choose whether to insure, double down,
 * bet even money or hit additionally
 * **/
public class BJPlayerTurn implements TurnWithDeck {
    @Override
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

        assert dealer != null;
        BJDealerHand dealerHand = (BJDealerHand) dealer.getHand();
        assert player != null;
        BJPlayerHand playerHand = (BJPlayerHand) player.getHand();

        boolean hasDealerACard = dealerHand.hasACard();
        boolean isPlayerBJ = playerHand.countAndVerifyBJ();

        if(hasDealerACard && isPlayerBJ){
            return evenMoney(player);
        }

        if(hasDealerACard){
            insure(player);
        }

        if(isPlayerBJ){
            return NextTurnStatus.DEALER_TURN;
        }

        if(confirmDoubleDown()){
            return doubleDown(player, deck);
        }

        return otherHits(player, deck);
    }

    private void insure(BJPlayer player) { //메서드 이름 변경 필요
        Viewer.printInfo(ViewerStatus.CONFIRM_INSURANCE);

        if(!BJInputProcessor.getBooleanAnswer()) return;

        if(player.insure()){
            Viewer.printInfo(ViewerStatus.COMPLETE_INSURANCE_PAYMENT);
        } else {
            Viewer.printInfo(ViewerStatus.NO_MONEY_TO_INSURE);
        }
    }

    private NextTurnStatus evenMoney(BJPlayer player) { //메서드 이름 변경 필요
        Viewer.printInfo(ViewerStatus.CONFIRM_EVEN_MONEY);

        if(!BJInputProcessor.getBooleanAnswer()) return NextTurnStatus.EARLY_SETTLING_TURN;

        player.betEvenMoney();

        return NextTurnStatus.DEALER_TURN;
    }

    private boolean confirmDoubleDown(){
        Viewer.printInfo(ViewerStatus.CONFIRM_DOUBLE_DOWN);
        return BJInputProcessor.getBooleanAnswer();
    }

    private NextTurnStatus doubleDown(BJPlayer player, Deck deck) {
        player.hit(deck.giveOneCard());
        Viewer.showCards(player.open());

        BJPlayerHand playerHand = (BJPlayerHand) player.getHand();

        if(playerHand.isBusted()){
            return NextTurnStatus.EARLY_SETTLING_TURN;
        }

        return NextTurnStatus.DEALER_TURN;
    }

    private NextTurnStatus otherHits(BJPlayer player, Deck deck){
        while(true){
            Viewer.printInfo(ViewerStatus.CONFIRM_OTHER_HITS);

            if(!BJInputProcessor.getBooleanAnswer()) {
                return NextTurnStatus.DEALER_TURN;
            }

            player.hit(deck.giveOneCard());
            Viewer.showCards(player.open());

            BJPlayerHand playerHand = (BJPlayerHand) player.getHand();

            if(playerHand.isBusted()){
                return NextTurnStatus.EARLY_SETTLING_TURN;
            }

            if(playerHand.isBlackJack()){
                return NextTurnStatus.DEALER_TURN;
            }
        }
    }
}
