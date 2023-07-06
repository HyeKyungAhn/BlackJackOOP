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
            givePayout(player, 2.0);

            Viewer.printInfo(ViewerStatus.PLAYER_WIN);
            Viewer.printInfo(ViewerStatus.DOUBLE_PAYOUT);
        }

        if(playerHand.isBusted()){
            Viewer.printInfo(ViewerStatus.PLAYER_BUSTED);
            givePayout(player, 1.0);

            if(playerHand.isInsured()){
                Viewer.printInfo(ViewerStatus.GIVE_INSURANCE_WHEN_LOSE);
            } else {
                Viewer.printInfo(ViewerStatus.LOSE_BETTING_AMOUNT);
            }
        }

        if (dealerHand.isBusted()) {
            Viewer.printInfo(ViewerStatus.DEALER_BUSTED);
            Viewer.printInfo(ViewerStatus.PLAYER_WIN);

            givePayout(player, 2.0);

            if(playerHand.isInsured()){
                Viewer.printInfo(ViewerStatus.TAKE_INSURANCE);
                Viewer.printInfo(ViewerStatus.DOUBLE_PAYOUT);
            }

            Viewer.printInfo(ViewerStatus.DOUBLE_PAYOUT);
        }

        initHands(player, dealer);

        return NextTurnStatus.FINISH_TURN;
    }

    private void initHands(BJPlayer player, BJDealer dealer){
        player.initValues();
        dealer.initValues();
    }

    private void givePayout(BJPlayer player, double rate){
        long winnings = (long) (player.getBettingAmount() * rate);

        player.getWallet().getWinning(winnings);
    }
}
