package blackjack;

public class BJLazySettlingTurn implements TurnWithPlayerAndDealer {
    BJLazySettlingTurn(){}

    public NextTurnStatus nextTurn(BJPlayer player, BJDealer dealer) {
        System.out.println("최종 계산");
        return NextTurnStatus.FINISH_TURN;
    }
}
