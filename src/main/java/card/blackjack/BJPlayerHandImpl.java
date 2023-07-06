package card.blackjack;

public class BJPlayerHandImpl extends BlackJackHandImpl implements BJPlayerHand {
    protected boolean insured = false;
    protected boolean evenMoney;
    protected boolean firstTurnBJ;
    protected boolean blackJack;

    @Override
    public boolean isInsured() {
        return insured;
    }

    @Override
    public void setInsured(boolean insured) {
        this.insured = insured;
    }

    @Override
    public boolean isEvenMoney() {
        return evenMoney;
    }

    @Override
    public void setEvenMoney(boolean evenMoney) {
        this.evenMoney = evenMoney;
    }

    @Override
    public boolean isFirstTurnBJ() {
        return firstTurnBJ;
    }

    @Override
    public void setFirstTurnBJ(boolean firstTurnBJ) {
        this.firstTurnBJ = firstTurnBJ;
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
