package game.blackjack;

import game.Gambler;
import game.Playable;
import fund.VirtualWallet;

public interface BJPlayer extends Playable, Gambler {
    VirtualWallet getWallet();
    void setBettingAmount(long bettingAmount);
    long getBettingAmount();
    boolean insure();
    boolean isBroke();
    void betEvenMoney();
    boolean doubleDown();
    void initValues();
}
