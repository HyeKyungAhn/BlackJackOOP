package game.blackjack;

import card.blackjack.BJDealerHand;
import card.blackjack.BJPlayerHand;
import viewer.Viewer;
import viewer.ViewerStatus;

public class BJLazySettlingTurn implements TurnWithPlayerAndDealer {
    BJLazySettlingTurn(){}

    public NextTurnStatus nextTurn(BJPlayer player, BJDealer dealer) {
        BJPlayerHand playerHand = (BJPlayerHand) player.getHand();
        BJDealerHand dealerHand = (BJDealerHand) dealer.getHand();

        System.out.println("----------------------------------------------");
        if (dealerHand.isBlackJack()) {
            handleDealerBlackjack(player);
        } else {
            if (playerHand.isBlackJack()) {
                handlePlayerBlackjack(player);
            } else if (isPlayerWin(playerHand, dealerHand)) {
                handlePlayerWin(player);
            } else if (isPlayerTie(playerHand, dealerHand)) {
                handlePlayerTie(player);
            } else {
                handlePlayerLose(playerHand);
            }
        }
        System.out.println("----------------------------------------------");

        initHands(player, dealer);

        return NextTurnStatus.FINISH_TURN;
    }

    private void handlePlayerLose(BJPlayerHand playerHand) {
        Viewer.printInfo(ViewerStatus.PLAYER_LOSE);

        if(playerHand.isInsured()){
            Viewer.printInfo(ViewerStatus.INCLUDE_INSURANCE);
        }

        Viewer.printInfo(ViewerStatus.LOSE_BETTING_AMOUNT);
    }

    private void handlePlayerTie(BJPlayer player) {
        BJPlayerHand playerHand = (BJPlayerHand) player.getHand();

        Viewer.printInfo(ViewerStatus.TIE);
        givePayout(player, 1.0);

        if (playerHand.isInsured()) {
            Viewer.printInfo(ViewerStatus.EXCLUDE_INSURANCE);
            Viewer.printInfo(ViewerStatus.GIVE_PRINCIPAL);
        } else {
            Viewer.printInfo(ViewerStatus.GIVE_PRINCIPAL);
        }
    }

    private void handlePlayerWin(BJPlayer player) {
        BJPlayerHand playerHand = (BJPlayerHand) player.getHand();

        Viewer.printInfo(ViewerStatus.PLAYER_WIN);

        givePayout(player, 2.0);

        if (playerHand.isInsured()) {
            Viewer.printInfo(ViewerStatus.EXCLUDE_INSURANCE);
            Viewer.printInfo(ViewerStatus.DOUBLE_PAYOUT);
        } else {
            Viewer.printInfo(ViewerStatus.ONE_PAYOUT);
        }
    }

    private void handlePlayerBlackjack(BJPlayer player) {
        Viewer.printInfo(ViewerStatus.PLAYER_BLACKJACK);

        givePayout(player, 2.5);

        Viewer.printInfo(ViewerStatus.ONE_AND_A_HALF_PAYOUT);
    }

    private void handleDealerBlackjack(BJPlayer player) {
        BJPlayerHand playerHand = (BJPlayerHand) player.getHand();

        Viewer.printInfo(ViewerStatus.DEALER_BLACKJACK);

        if (playerHand.isBlackJack()) {
            Viewer.printInfo(ViewerStatus.PLAYER_BLACKJACK);
            givePayout(player, 1.0);
            Viewer.printInfo(ViewerStatus.GIVE_PRINCIPAL);
        } else {
            Viewer.printInfo(ViewerStatus.PLAYER_LOSE);

            if (playerHand.isInsured()) {
                givePayout(player, 1.5);
                Viewer.printInfo(ViewerStatus.GIVE_INSURANCE_WHEN_LOSE);
            } else {
                Viewer.printInfo(ViewerStatus.LOSE_BETTING_AMOUNT);
            }
        }
    }

    private boolean isPlayerTie(BJPlayerHand playerHand, BJDealerHand dealerHand) {

        return playerHand.getCount() == dealerHand.getCount();
    }

    private boolean isPlayerWin(BJPlayerHand playerHand, BJDealerHand dealerHand) {

        return playerHand.getCount() > dealerHand.getCount();
    }

    private void givePayout(BJPlayer player, double rate){
        long payout = (long) (player.getBettingAmount() * rate);

        player.getWallet().getWinning(payout);
    }

    private void initHands(BJPlayer player, BJDealer dealer){
        player.initValues();
        dealer.initValues();
    }
}
