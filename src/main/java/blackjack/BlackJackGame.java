package blackjack;

import card.BJDealerHandImpl;
import card.BJPlayerHandImpl;
import game.*;
import money.VirtualWallet;

import java.util.Scanner;

public class BlackJackGame implements Game {

    @Override
    public void play() {
        BJPlayer player = new BJPlayerImpl(new BJPlayerHandImpl(), new VirtualWallet(1000));
        BJDealer dealer = new BJDealerImpl(new BJDealerHandImpl());

//        List<Playable> players = new ArrayList<>(Arrays.asList(player));
        BlackJackRound round = new BlackJackRound(player, dealer, new Scanner(System.in));
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
