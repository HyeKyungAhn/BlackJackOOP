package game.blackjack;

import game.Turn;

public interface TurnWithPlayerAndDealer extends Turn {
    NextTurnStatus nextTurn(BJPlayer player, BJDealer dealer);
}
