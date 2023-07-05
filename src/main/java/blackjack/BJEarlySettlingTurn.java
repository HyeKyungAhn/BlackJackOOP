package blackjack;

public class BJEarlySettlingTurn implements TurnWithPlayerAndDealer {

    BJEarlySettlingTurn(){}

    public NextTurnStatus nextTurn(BJPlayer player, BJDealer dealer) {
        //

        System.out.println("이른 판정");
        return NextTurnStatus.FINISH_TURN;
    }
}
