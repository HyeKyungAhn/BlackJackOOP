package game.blackjack;

import card.Card;
import game.Playable;

public interface HandForTest {
    static void getBlackJackHand(Playable playable) {
        playable.hit(new Card("Heart", "A"));
        playable.hit(new Card("King", "K"));
    }

    static void getBustedHand(Playable playable) {
        playable.hit(new Card("Heart", "7"));
        playable.hit(new Card("King", "6"));
        playable.hit(new Card("Queen", "K"));
    }

    static void getNoBustedNNoBlackJackHand(Playable playable) {
        playable.hit(new Card("Heart", "A"));
        playable.hit(new Card("King", "3"));
    }

    static void getDealerHandWithA(Playable playable) {
        playable.hit(new Card("King", "3"));
        playable.hit(new Card("Heart", "A"));
    }

    static void getDealerHandWithNotOpenedACard(Playable playable) {
        playable.hit(new Card("Heart", "Q"));
        playable.hit(new Card("King", "3"));
    }

    static void get20CountHand(Playable playable) {
        playable.hit(new Card("Heart", "Q"));
        playable.hit(new Card("King", "K"));
    }

    static void get10CountHand(Playable playable) {
        playable.hit(new Card("Heart", "4"));
        playable.hit(new Card("King", "6"));
    }

    static void get17CountHand(Playable playable) {
        playable.hit(new Card("Heart", "K"));
        playable.hit(new Card("King", "7"));
    }
}
