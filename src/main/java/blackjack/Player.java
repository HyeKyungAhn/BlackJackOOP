package blackjack;

import card.*;
import game.*;
import money.VirtualWallet;

import java.util.List;

public class Player implements Playable, Gambler {
    BJPlayerHand hand;
    VirtualWallet wallet;
    long bettingAmount;

    Player(){
        hand = new BJPlayerHandImpl();
        wallet = new VirtualWallet(1000);
    }

    @Override
    public void bet() {
            Viewer.printInfo(ViewerStatus.BETTING_INFO);

        do {
            long amount = BJInputProcessor.getLongValue();
            if (wallet.getBalance() <= amount) {
                Viewer.printInfo(ViewerStatus.NO_MONEY_TO_BET);
                continue;
            }

            if(amount < BJBettingTurn.BET_MIN || BJBettingTurn.BET_MAX < amount){
                Viewer.printInfo(ViewerStatus.OUT_OF_BET_LIMIT);
                continue;
            }

            if(amount % 100 != 0){
                Viewer.printInfo(ViewerStatus.INVALID_BETTING_INPUT);
                continue;
            }

            bettingAmount = wallet.subtract(amount);
            break;
        } while(true);
    }

    @Override
    public void hit(Card card) {
        hand.addCard(card);
    }

    @Override
    public List<Card> open() {
        return hand.getCards();
    }

    public boolean isBroke() {
        return wallet.getBalance() <= BJBettingTurn.BET_MIN;
    }

    public BJPlayerHand getHand() {
        return hand;
    }

    public VirtualWallet getWallet() {
        return wallet;
    }

    public long getBettingAmount() {
        return bettingAmount;
    }

    public void setBettingAmount(long bettingAmount) {
        this.bettingAmount = bettingAmount;
    }
}
