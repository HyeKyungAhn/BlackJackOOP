package blackjack;

import card.BJDealerHand;
import card.BJPlayerHand;

public class BJEarlySettlingTurn implements TurnWithPlayerAndDealer {

    BJEarlySettlingTurn(){}

    public NextTurnStatus nextTurn(BJPlayer player, BJDealer dealer) {
        BJPlayerHand playerHand = (BJPlayerHand) player.getHand();
        BJDealerHand dealerHand = (BJDealerHand) dealer.getHand();
        dealer.totalOpen();

        if(playerHand.isBlackJack()){ //no even money
            giveWinnings(player, 2.0);

            Viewer.printInfo(ViewerStatus.PLAYER_WIN);
            Viewer.printInfo(ViewerStatus.DOUBLE_WINNING);
        }

        if(playerHand.isBusted()){
            Viewer.printInfo(ViewerStatus.PLAYER_BUSTED);
            giveWinnings(player, 1.0);

            if(playerHand.isInsured()){
                Viewer.printInfo(ViewerStatus.GET_INSURANCE);
            } else {
                Viewer.printInfo(ViewerStatus.LOSE_BETTING_AMOUNT);
            }
        }

        if (dealerHand.isBusted()) {
            Viewer.printInfo(ViewerStatus.DEALER_BUSTED);
            Viewer.printInfo(ViewerStatus.PLAYER_WIN);

            giveWinnings(player, 2.0);

            if(playerHand.isInsured()){
                Viewer.printInfo(ViewerStatus.TAKE_INSURANCE);
                Viewer.printInfo(ViewerStatus.DOUBLE_WINNING);
            }

            Viewer.printInfo(ViewerStatus.DOUBLE_WINNING);
        }

        initHands(player, dealer);

        return NextTurnStatus.FINISH_TURN;
    }

    private void initHands(BJPlayer player, BJDealer dealer){
        player.initValues();
        dealer.initValues();
    }

    private void giveWinnings(BJPlayer player, double rate){
        long winnings = (long) (player.getBettingAmount() * rate);

        player.getWallet().getWinning(winnings);
    }
}
