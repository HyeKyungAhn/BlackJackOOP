package game.blackjack;

import fund.Wallet;
import game.Gambler;
import game.Playable;

public interface BJPlayer extends Playable, Gambler {
    Wallet getWallet();
    boolean setBettingAmount(long bettingAmount);
    long getBettingAmount();
    boolean insure();
    boolean isBroke();
    void betEvenMoney();
    boolean doubleDown();
    void initValues();
}
