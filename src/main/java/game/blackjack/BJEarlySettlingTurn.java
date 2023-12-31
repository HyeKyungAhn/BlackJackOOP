package game.blackjack;

import card.blackjack.BJDealerHand;
import card.blackjack.BJPlayerHand;
import viewer.Viewer;
import viewer.ViewerStatus;

public class BJEarlySettlingTurn implements TurnWithPlayerAndDealer {
    private BJDealer dealer;

    BJEarlySettlingTurn(){}

    BJEarlySettlingTurn(BJDealer dealer){
        this.dealer = dealer;
    }

    public NextTurnStatus nextTurn(BJPlayer player) {
        BJPlayerHand playerHand = (BJPlayerHand) player.getHand();
        BJDealerHand dealerHand = (BJDealerHand) dealer.getHand();

        dealer.totalOpen();

        System.out.println("----------------------------------------------");

        if(playerHand.isEvenMoney()){
            givePayout(player, 2.0);

            Viewer.printInfo(ViewerStatus.PLAYER_WIN);
            Viewer.printInfo(ViewerStatus.ONE_PAYOUT);
        }

        if(playerHand.isBusted()){
            Viewer.printInfo(ViewerStatus.PLAYER_BUSTED);

            if(playerHand.isInsured() && dealerHand.countAndVerifyBJ()){
                givePayout(player, 1.5);
                Viewer.printInfo(ViewerStatus.DEALER_BLACKJACK);
                Viewer.printInfo(ViewerStatus.GIVE_INSURANCE_WHEN_LOSE);
            } else {
                Viewer.printInfo(ViewerStatus.LOSE_BETTING_AMOUNT);
            }
        }

        if (dealerHand.isBusted()) {
            Viewer.printInfo(ViewerStatus.DEALER_BUSTED);
            Viewer.printInfo(ViewerStatus.PLAYER_WIN);

            if(playerHand.isBlackJack()){
                Viewer.printInfo(ViewerStatus.PLAYER_BLACKJACK);

                givePayout(player, 2.5);

                if(playerHand.isInsured()){
                    Viewer.printInfo(ViewerStatus.LOOSE_INSURANCE);
                }

                Viewer.printInfo(ViewerStatus.ONE_AND_A_HALF_PAYOUT);
            } else {
                givePayout(player, 2.0);

                if(playerHand.isInsured()){
                    Viewer.printInfo(ViewerStatus.LOOSE_INSURANCE);
                }

                Viewer.printInfo(ViewerStatus.ONE_PAYOUT);
            }


        }

        System.out.println("----------------------------------------------");

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
