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
            giveWinnings(player, 2.0, false);

            Viewer.printInfo(ViewerStatus.PLAYER_WIN);
            Viewer.printInfo(ViewerStatus.DOUBLE_WINNING);
        }

        if(playerHand.isBusted()){
            if(playerHand.isInsured()){
                giveWinnings(player, 1.0, false);

                Viewer.printInfo(ViewerStatus.GET_INSURANCE);
            } else {
                Viewer.printInfo(ViewerStatus.PLAYER_BUSTED);
            }
        }

        if (dealerHand.isBusted()) {
            Viewer.printInfo(ViewerStatus.PLAYER_WIN);

            if(playerHand.isInsured()){
                giveWinnings(player, 2.0, true);

                Viewer.printInfo(ViewerStatus.TAKE_INSURANCE);
                Viewer.printInfo(ViewerStatus.DOUBLE_WINNING);
            }

            giveWinnings(player, 2.0, false);
            Viewer.printInfo(ViewerStatus.PLAYER_WIN);
        }

        initHands(player, dealer);

        return NextTurnStatus.FINISH_TURN;
    }

    private void initHands(BJPlayer player, BJDealer dealer){
        player.initValues();
        dealer.initValues();
    }

    private void giveWinnings(BJPlayer player, double rate, boolean isInsured){
        if(isInsured){
            long insurance = player.getBettingAmount()/2;
            long winnings = (long) (player.getBettingAmount() * rate - insurance);

            player.getWallet().getWinning(winnings);
        } else {
            long winnings = (long) (player.getBettingAmount() * rate);

            player.getWallet().getWinning(winnings);
        }
    }
}
