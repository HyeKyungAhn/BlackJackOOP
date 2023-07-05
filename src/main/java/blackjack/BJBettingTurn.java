package blackjack;

import game.Gambler;
import game.Turn;

import java.util.List;
import java.util.Scanner;

public class BJBettingTurn implements Turn {
    public static final long BET_MIN = 100L;
    public static final long BET_MAX = 100_000_000_000L;

    List<Gambler> gamblers;
    Scanner scanner;

    BJBettingTurn(){}
    BJBettingTurn(List<Gambler> gamblers, Scanner scanner){
        this.gamblers = gamblers;
        this.scanner = scanner;
    }

    @Override
    public NextTurnStatus nextTurn() {
        for (Gambler gambler : gamblers) {
            gambler.bet(scanner);
        }

        return NextTurnStatus.DEALING_TURN;
    }
}
