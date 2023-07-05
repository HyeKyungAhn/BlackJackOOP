package blackjack;

import java.util.Scanner;

public class BettingTurnImpl implements BettingTurn {
    private Scanner scanner;

    protected BettingTurnImpl(){}

    protected BettingTurnImpl(Scanner scanner){
        this.scanner = scanner;
    }

    @Override
    public NextTurnStatus nextTurn(BJPlayer player) {
        player.bet(scanner);
        return NextTurnStatus.DEALING_TURN;
    }
}
