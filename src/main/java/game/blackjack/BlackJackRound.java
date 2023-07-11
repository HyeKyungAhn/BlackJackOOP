package game.blackjack;

import card.Deck;
import game.InputProcessor;
import game.Playable;
import game.Round;
import viewer.Viewer;
import viewer.ViewerStatus;

import java.util.ArrayList;
import java.util.List;

public class BlackJackRound implements Round {
    private BJPlayer player; //player
    private BJDealer dealer; //dealer
    private List<Playable> playerAndDealer; //player + dealer
    private Deck deck;

    private BettingTurn bettingTurn;
    private DealingTurn dealingTurn;
    private TurnWithPlayerAndDealer playerTurn;
    private DealerTurn dealerTurn;
    private TurnWithPlayerAndDealer lazySettlingTurn;
    private TurnWithPlayerAndDealer earlySettlingTurn;

    private InputProcessor inputProcessor;

    private NextTurnStatus nextTurn = NextTurnStatus.INITIAL;

    boolean isFinal = false;

    BlackJackRound(){}

    BlackJackRound(BJPlayer player, BJDealer dealer, InputProcessor inputProcessor){
        this.player = player;
        this.dealer = dealer;

        this.playerAndDealer = new ArrayList<>();
        playerAndDealer.add(player);
        playerAndDealer.add(dealer);

        this.inputProcessor = inputProcessor;
        this.deck = new Deck();

        initTurns();
    }

    private void initTurns() {
        bettingTurn = new BJBettingTurn(inputProcessor);
        dealingTurn = new BJDealingTurn(deck);
        playerTurn = new BJPlayerTurn(deck, inputProcessor, dealer);
        dealerTurn = new BJDealerTurn(dealer, deck);
        lazySettlingTurn = new BJLazySettlingTurn(dealer);
        earlySettlingTurn = new BJEarlySettlingTurn(dealer);
    }

    @Override
    public void next() {

        do {
            process();
        } while (nextTurn != NextTurnStatus.FINISH_TURN);

        Viewer.showBalance(player.getWallet().getBalance());
        Viewer.printInfo(ViewerStatus.ROUND_END);

        if(!(isFinal = inputProcessor.getBooleanAnswer())){
            nextTurn =  NextTurnStatus.INITIAL;
        }
    }

    @Override
    public void process() {
        switch (nextTurn) {
            case INITIAL: {
                nextTurn = NextTurnStatus.BETTING_TURN;
            } break;

            case BETTING_TURN: {
                nextTurn = bettingTurn.nextTurn(player);
            } break;

            case DEALING_TURN: {
                nextTurn = dealingTurn.nextTurn(playerAndDealer);
            } break;

            case PLAYER_TURN: {
                nextTurn = playerTurn.nextTurn(player);
            } break;

            case DEALER_TURN: {
                nextTurn = dealerTurn.nextTurn();
            } break;

            case EARLY_SETTLING_TURN: {
                nextTurn = earlySettlingTurn.nextTurn(player);
            } break;

            case LAZY_SETTLING_TURN: {
                nextTurn = lazySettlingTurn.nextTurn(player);
            } break;
        }
    }

}
