package blackjack;

import game.*;

import java.util.Arrays;

public class BlackJackGame implements Game {

    @Override
    public void play() {
        Player player = new Player();
        Dealer dealer = new Dealer();
        BlackJackRound round = new BlackJackRound(Arrays.asList(player, dealer));
        boolean hasNext;

        Viewer.printInfo(ViewerStatus.NEW_START);

        do {
            round.next();

            hasNext = !player.isBroke() && !round.isFinal;
        } while (hasNext);

        if(player.isBroke()){
            Viewer.printInfo(ViewerStatus.NO_MONEY);
            return;
        }
        Viewer.printInfo(ViewerStatus.GAME_END);
    }
}
