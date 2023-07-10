package game.blackjack;

import card.blackjack.BJDealerHandImpl;
import card.blackjack.BJPlayerHandImpl;
import game.Game;
import fund.VirtualWallet;
import game.InputProcessor;
import viewer.Viewer;
import viewer.ViewerStatus;

import java.util.Scanner;

public class BlackJackGame implements Game {

    @Override
    public void play() {
        BJPlayer player = new BJPlayerImpl(new BJPlayerHandImpl(), new VirtualWallet(1000));
        BJDealer dealer = new BJDealerImpl(new BJDealerHandImpl());
        InputProcessor inputProcessor = new BJInputProcessor(new Scanner(System.in));

        BlackJackRound round = new BlackJackRound(player, dealer, inputProcessor);
        boolean hasNext;

        Viewer.printInfo(ViewerStatus.NEW_START);

        do {
            round.next();

            hasNext = !player.isBroke() && !round.isFinal;
        } while (hasNext);

        if(player.isBroke()){
            Viewer.printInfo(ViewerStatus.NO_MONEY);
        }

        Viewer.printInfo(ViewerStatus.GAME_END);
    }
}
