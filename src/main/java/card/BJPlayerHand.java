package card;

public interface BJPlayerHand extends BlackJackHand{
    boolean isInsured();
    void setInsured(boolean insured);
    boolean isEvenMoney();
    void setEvenMoney(boolean evenMoney);
    boolean isFirstTurnBJ();
    void setFirstTurnBJ(boolean firstTurnBJ);
}
