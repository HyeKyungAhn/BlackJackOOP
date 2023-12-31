package viewer;

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
    PLAYER_LOSE,
    DOUBLE_PAYOUT,
    ONE_PAYOUT,
    DOUBLE_AND_HALF_PAYOUT,
    ONE_AND_A_HALF_PAYOUT,
    HALF_PAYOUT,
    GET_PRINCIPLE,
    PLAYER_BUSTED,
    DEALER_BUSTED,
    GIVE_INSURANCE_WHEN_LOSE,
    GIVE_INSURANCE_COMPENSATION,
    LOSE_BETTING_AMOUNT,
    TIE,
    DEALER_BLACKJACK,
    PLAYER_BLACKJACK,
    GIVE_PRINCIPAL,
    EXCLUDE_INSURANCE,
    INCLUDE_INSURANCE,
    LOOSE_INSURANCE,
}
