package blackjack;

import game.Gambler;
import game.Turn;

import java.util.List;

public interface GamblerTurn extends Turn {
    NextTurnStatus nextTurn(List<Gambler> gamblers);
}
