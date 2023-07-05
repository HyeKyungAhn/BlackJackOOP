package blackjack;

import blackjack.BJPlayer;
import blackjack.NextTurnStatus;
import blackjack.BJDealer;
import game.Turn;

public interface TurnWithPlayerAndDealer extends Turn {
    NextTurnStatus nextTurn(BJPlayer player, BJDealer dealer);
}
