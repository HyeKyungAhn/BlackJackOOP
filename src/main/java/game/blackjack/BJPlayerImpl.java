package game.blackjack;

import card.blackjack.BJPlayerHand;
import card.Hand;
import game.Gambler;
import game.InputProcessor;
import game.PlayableImpl;
import fund.VirtualWallet;
import viewer.Viewer;
import viewer.ViewerStatus;

import java.util.Scanner;

public class BJPlayerImpl extends PlayableImpl implements BJPlayer, Gambler {
    protected VirtualWallet wallet;
    protected long bettingAmount;

    public BJPlayerImpl(){
        super();
    }

    public BJPlayerImpl(Hand hand, VirtualWallet wallet){
        super(hand);
        this.wallet = wallet;
    }

    @Override
    public VirtualWallet getWallet() {
        return wallet;
    }

    @Override
    public void setBettingAmount(long bettingAmount) {
        this.bettingAmount = bettingAmount;
    }

    @Override
    public long getBettingAmount() {
        return bettingAmount;
    }

    @Override
    public boolean insure() {
        long insurance = bettingAmount/2;

        if(insurance > wallet.getBalance()){
            return false;
        }

        wallet.subtract(insurance);
        ((BJPlayerHand)hand).setInsured(true);
        return true;
    }

    @Override
    public boolean isBroke() {
        return false;
    }

    @Override
    public void betEvenMoney() {
        BJPlayerHand playerHand = ((BJPlayerHand)hand);
        playerHand.setEvenMoney(true);
    }

    @Override
    public boolean doubleDown() {
        if(bet(bettingAmount)){
            bettingAmount *= 2;
            return true;
        }
        return false;
    }

    @Override
    public void initValues() {
        bettingAmount = 0;
        hand.initValues();
    }

    @Override
    public void bet(InputProcessor inputProcessor) {
        Viewer.printInfo(ViewerStatus.BETTING_INFO);

        do {
            long amount = inputProcessor.getLongValue();
            if(bet(amount)) { break; }
        } while(true);
    }

    private boolean bet(long amount){
        if (wallet.getBalance() <= amount) {
            Viewer.printInfo(ViewerStatus.NO_MONEY_TO_BET);
            return false;
        }

        if(amount < BettingTurn.BET_MIN || BettingTurn.BET_MAX < amount){
            Viewer.printInfo(ViewerStatus.OUT_OF_BET_LIMIT);
            return false;
        }

        if(amount % 100 != 0){
            Viewer.printInfo(ViewerStatus.INVALID_BETTING_INPUT);
            return false;
        }

        bettingAmount = wallet.subtract(amount);
        return true;
    }
}