package blackjack;

public enum ViewerStatus {
    NEW_START,
    NEXT_ROUND,
    NO_MONEY,
    GAME_END,
    ROUND_END,
    INVALID_INPUT,
    BETTING_INFO,
    INVALID_BETTING_INPUT,
    BET_MONEY_UNIT,
    OUT_OF_BET_LIMIT,
    NO_MONEY_TO_BET,
    DEALER_HAND,
    PLAYER_HAND,
    CONFIRM_INSURANCE,
    NO_MONEY_TO_INSURE,
    COMPLETE_INSURANCE_PAYMENT,
    CONFIRM_EVEN_MONEY,
    CONFIRM_DOUBLE_DOWN,
    CONFIRM_OTHER_HITS,
    PLAYER_WIN,
    DOUBLE_WINNING,
    GET_PRINCIPLE,
    PLAYER_BUSTED,
    DEALER_BUSTED,
    GET_INSURANCE,
    TAKE_INSURANCE,
    LOSE_BETTING_AMOUNT,
}
