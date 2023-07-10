package game.blackjack;

import card.blackjack.BJPlayerHand;
import card.Hand;
import fund.Wallet;
import game.Gambler;
import game.InputProcessor;
import game.PlayableImpl;
import viewer.Viewer;
import viewer.ViewerStatus;

public class BJPlayerImpl extends PlayableImpl implements BJPlayer, Gambler {
    protected Wallet wallet;
    protected long bettingAmount;

    public BJPlayerImpl(){
        super();
    }

    public BJPlayerImpl(Hand hand, Wallet wallet){
        super(hand);
        this.wallet = wallet;
    }

    @Override
    public Wallet getWallet() {
        return wallet;
    }

    @Override
    public boolean setBettingAmount(long bettingAmount) {
        // double down 하면 배팅 최대 금액의 2배까지 베팅 가능
        if (BettingTurn.BET_MIN <= bettingAmount
                && bettingAmount <= BettingTurn.BET_MAX * 2) {

            this.bettingAmount = bettingAmount;
            return true;
        }
        return false;
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
    public boolean bet(InputProcessor inputProcessor) {
        Viewer.printInfo(ViewerStatus.BETTING_INFO);
        long amount = inputProcessor.getLongValue();

        return bet(amount);
    }

    private boolean bet(long amount){
        if (wallet.getBalance() < amount) {
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

        wallet.subtract(amount);
        bettingAmount = amount;

        return true;
    }
}
