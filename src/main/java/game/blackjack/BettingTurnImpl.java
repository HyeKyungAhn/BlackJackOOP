package game.blackjack;

import game.InputProcessor;

public class BettingTurnImpl implements BettingTurn {
    private InputProcessor inputProcessor;

    protected BettingTurnImpl(){}

    protected BettingTurnImpl(InputProcessor inputProcessor){
        this.inputProcessor = inputProcessor;
    }

    @Override
    public NextTurnStatus nextTurn(BJPlayer player) {
        player.bet(inputProcessor);
        return NextTurnStatus.DEALING_TURN;
    }
}
