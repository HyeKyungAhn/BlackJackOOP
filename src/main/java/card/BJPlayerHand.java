package card;

import blackjack.BettingTurn;
import blackjack.Player;

public interface BJPlayerHand extends BlackJackHand{
    int getCount();
    void setCount(int count);
    boolean isInsured();
    void setInsured(boolean insured);
    boolean isEvenMoney();
    void setEvenMoney(boolean evenMoney);
    boolean isFirstTurnBJ();
    void setFirstTurnBJ(boolean firstTurnBJ);
    boolean isBlackJack();
    void setBlackJack(boolean blackJack);
    boolean canDoubleDown(Player player, BettingTurn bm);
}
