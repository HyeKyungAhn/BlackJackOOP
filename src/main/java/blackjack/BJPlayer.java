package blackjack;

import game.Gambler;
import game.Playable;
import money.VirtualWallet;

public interface BJPlayer extends Playable, Gambler {
    VirtualWallet getWallet();
    void setBettingAmount(long bettingAmount);
    long getBettingAmount();
    boolean insure();
    boolean isBroke();
}
