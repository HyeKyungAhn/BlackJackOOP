package card;

import blackjack.BettingTurn;
import blackjack.Player;

public class BJPlayerHand extends Hand{
    private int count;
    private boolean insured = false;
    private boolean evenMoney;
    private boolean firstTurnBJ;
    private boolean blackJack;

    public int getCount() {
        return count;
    }

    protected void setCount(int count) {
        this.count = count;
    }

    public boolean isInsured() {
        return insured;
    }

    protected void setInsured(boolean insured) {
        this.insured = insured;
    }

    public boolean isEvenMoney() {
        return evenMoney;
    }

    protected void setEvenMoney(boolean evenMoney) {
        this.evenMoney = evenMoney;
    }

    public boolean isFirstTurnBJ() {
        return firstTurnBJ;
    }

    protected void setFirstTurnBJ(boolean firstTurnBJ) {
        this.firstTurnBJ = firstTurnBJ;
    }

    public boolean isBlackJack() {
        return blackJack;
    }

    protected void setBlackJack(boolean blackJack) {
        this.blackJack = blackJack;
    }

    public boolean canDoubleDown(Player player, BettingTurn bm){
//        long betMoney = (bm).getPlayerBetMoney();
//        long playerMoney = player.getWallet().getBalance();
//        return playerMoney >= betMoney;
        return false;
    }

    @Override
    protected void initValues(){
        super.initValues();
        count = 0;
        insured = false;
        evenMoney = false;
        firstTurnBJ = false;
        blackJack = false;
    }
}
