package blackjack;

public enum NextTurnStatus {
    INITIAL,
    BETTING_TURN,
    DEALING_TURN,
    PLAYER_TURN,
    DEALER_TURN,
    EARLY_SETTLING_TURN,
    LAZY_SETTLING_TURN,
    FINISH_TURN,
}
