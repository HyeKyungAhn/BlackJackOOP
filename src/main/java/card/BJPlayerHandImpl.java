package card;

import blackjack.Player;
import blackjack.BettingTurn;

public class BJPlayerHandImpl extends BlackJackHandImpl implements BJPlayerHand {
    private int count;
    private boolean insured = false;
    private boolean evenMoney;
    private boolean firstTurnBJ;
    private boolean blackJack;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isInsured() {
        return insured;
    }

    public void setInsured(boolean insured) {
        this.insured = insured;
    }

    public boolean isEvenMoney() {
        return evenMoney;
    }

    public void setEvenMoney(boolean evenMoney) {
        this.evenMoney = evenMoney;
    }

    public boolean isFirstTurnBJ() {
        return firstTurnBJ;
    }

    public void setFirstTurnBJ(boolean firstTurnBJ) {
        this.firstTurnBJ = firstTurnBJ;
    }

    public boolean isBlackJack() {
        return blackJack;
    }

    public void setBlackJack(boolean blackJack) {
        this.blackJack = blackJack;
    }

    public boolean canDoubleDown(Player player, BettingTurn bm){
        long betMoney = player.getBettingAmount();
        long playerMoney = player.getWallet().getBalance();
        return playerMoney >= betMoney;
    }

    @Override
    public void initValues(){
        super.initValues();
        count = 0;
        insured = false;
        evenMoney = false;
        firstTurnBJ = false;
        blackJack = false;
    }
}
