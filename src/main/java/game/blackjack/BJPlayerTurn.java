package game.blackjack;

import card.blackjack.BJDealerHand;
import card.blackjack.BJPlayerHand;
import card.Deck;
import game.InputProcessor;
import viewer.Viewer;
import viewer.ViewerStatus;

/**
 * BJPlayerTurn controls player's choice
 *
 * In some cases, player can choose whether to insure, double down
 * , bet even money or hit additionally
 * **/

public class BJPlayerTurn implements TurnWithPlayerAndDealer {
    Deck deck;
    InputProcessor inputProcessor;

    BJPlayerTurn(){}

    BJPlayerTurn(Deck deck, InputProcessor inputProcessor){
        this.deck = deck;
        this.inputProcessor = inputProcessor;
    }

    @Override
    public NextTurnStatus nextTurn(BJPlayer player, BJDealer dealer) {
        BJDealerHand dealerHand = (BJDealerHand) dealer.getHand();
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

        if(!inputProcessor.getBooleanAnswer()) return;

        if(player.insure()){
            Viewer.printInfo(ViewerStatus.COMPLETE_INSURANCE_PAYMENT);
        } else {
            Viewer.printInfo(ViewerStatus.NO_MONEY_TO_INSURE);
        }
    }

    private NextTurnStatus evenMoney(BJPlayer player) { //메서드 이름 변경 필요
        Viewer.printInfo(ViewerStatus.CONFIRM_EVEN_MONEY);

        if(!inputProcessor.getBooleanAnswer()) return NextTurnStatus.EARLY_SETTLING_TURN;

        player.betEvenMoney();

        return NextTurnStatus.DEALER_TURN;
    }

    private boolean confirmDoubleDown(){
        Viewer.printInfo(ViewerStatus.CONFIRM_DOUBLE_DOWN);
        return inputProcessor.getBooleanAnswer();
    }

    private NextTurnStatus doubleDown(BJPlayer player, Deck deck) {
        player.hit(deck.giveOneCard());
        Viewer.showCards(player.open());

        BJPlayerHand playerHand = (BJPlayerHand) player.getHand();

        if(playerHand.countAndVerifyBusted()){
            return NextTurnStatus.EARLY_SETTLING_TURN;
        }

        return NextTurnStatus.DEALER_TURN;
    }

    private NextTurnStatus otherHits(BJPlayer player, Deck deck){
        while(true){
            Viewer.printInfo(ViewerStatus.CONFIRM_OTHER_HITS);

            if(!inputProcessor.getBooleanAnswer()) {
                return NextTurnStatus.DEALER_TURN;
            }

            player.hit(deck.giveOneCard());

            Viewer.printInfo(ViewerStatus.PLAYER_HAND);
            Viewer.showCards(player.open());

            BJPlayerHand playerHand = (BJPlayerHand) player.getHand();

            if(playerHand.countAndVerifyBusted()){
                return NextTurnStatus.EARLY_SETTLING_TURN;
            }

            if(playerHand.isBlackJack()){
                return NextTurnStatus.DEALER_TURN;
            }
        }
    }
}
