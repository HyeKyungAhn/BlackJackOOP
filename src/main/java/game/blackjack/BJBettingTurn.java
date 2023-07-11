package game.blackjack;

import game.InputProcessor;
import viewer.Viewer;

public class BJBettingTurn implements BettingTurn {
    private InputProcessor inputProcessor;

    protected BJBettingTurn(){}

    protected BJBettingTurn(InputProcessor inputProcessor){
        this.inputProcessor = inputProcessor;
    }

    @Override
    public NextTurnStatus nextTurn(BJPlayer player) {
        Viewer.showBalance(player.getWallet().getBalance());

        do {
            if(player.bet(inputProcessor)) break;
        } while(true);

        return NextTurnStatus.DEALING_TURN;
    }
}
