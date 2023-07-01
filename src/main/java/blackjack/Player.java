package blackjack;

import blackjacktest.Card;
import card.Hand;
import game.Gambler;
import game.Playable;
import money.VirtualWallet;

import java.util.List;

public class Player implements Playable, Gambler {
    Hand hand;
    VirtualWallet wallet;

    @Override
    public boolean bet(int amount) {
        return false;
    }

    @Override
    public void hit(Card card) {

    }

    @Override
    public void open() {

    }

    @Override
    public List<Card> receiveCards() {
        return null;
    }

    public boolean isBroke() {
        return wallet.getBalance() <= 0;
    }

    public Hand getHand() {
        return hand;
    }

    public VirtualWallet getWallet() {
        return wallet;
    }
}
