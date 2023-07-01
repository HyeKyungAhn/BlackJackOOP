package blackjack;

import card.Deck;
import game.Playable;
import game.Turn;

import java.util.List;
import java.util.Scanner;

public class PlayerTurn implements Turn {
    public static NextTurnStatus nextTurn(List<Playable> players, Deck deck, Scanner scanner) {
        System.out.println("플레이어 완료");
        return NextTurnStatus.DEALER_TURN;
    }
}
