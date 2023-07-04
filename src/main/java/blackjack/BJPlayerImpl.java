package blackjack;

import card.BJPlayerHand;
import card.Hand;
import game.Gambler;
import game.PlayableImpl;
import money.VirtualWallet;

public class BJPlayerImpl extends PlayableImpl implements BJPlayer, Gambler {
    VirtualWallet wallet;
    long bettingAmount;

    BJPlayerImpl(){
        super();
    }

    BJPlayerImpl(Hand hand, VirtualWallet wallet){
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
        ((BJPlayerHand)super.getHand()).setInsured(true);
        return true;
    }

    @Override
    public boolean isBroke() {
        return false;
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
}
