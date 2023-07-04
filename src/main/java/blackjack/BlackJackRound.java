package blackjack;

import card.Deck;
import game.Gambler;
import game.Playable;
import game.Round;

import java.util.ArrayList;
import java.util.List;

public class BlackJackRound implements Round {
    List<Gambler> gamblers; //player (can bet)
    Playable dealer; //dealer
    List<Playable> players; //player + dealer
    Deck deck; //추상화 필요

    GamblerTurn bettingTurn;
    TurnWithDeck dealingTurn;
    TurnWithDeck playerTurn;
    DealerTurn dealerTurn;
    SettlingTurn lazySettlingTurn;
    SettlingTurn earlySettlingTurn;

    NextTurnStatus nextTurn = NextTurnStatus.INITIAL;
    boolean isFinal = false;

    BlackJackRound(){}

    BlackJackRound(List<Playable> players){
        initTurns();

        this.players = players;
        this.gamblers = new ArrayList<>();
        this.deck = new Deck();

        players.forEach(player -> {
            if(player instanceof Gambler) {
                gamblers.add((Gambler) player);
            } else {
                dealer = player;
            }
        });
    }

    private void initTurns() {
        bettingTurn = new BJBettingTurn();
        dealingTurn = new BJDealingTurn();
        playerTurn = new BJPlayerTurn();
        dealerTurn = new BJDealerTurn();
        lazySettlingTurn = new BJLazySettlingTurn();
        earlySettlingTurn = new BJEarlySettlingTurn();
    }

    @Override
    public void next() {

        do {
            process();
        } while (nextTurn != NextTurnStatus.FINISH_TURN);

        Viewer.printInfo(ViewerStatus.ROUND_END);
        isFinal = BJInputProcessor.getBooleanAnswer();
    }

    @Override
    public void process() {
        switch (nextTurn) {
            case INITIAL: {
                nextTurn = NextTurnStatus.BETTING_TURN;
            } break;

            case BETTING_TURN: {
                nextTurn = bettingTurn.nextTurn(gamblers);
            } break;

            case DEALING_TURN: {
                nextTurn = dealingTurn.nextTurn(players, deck);
            } break;

            case PLAYER_TURN: {
                nextTurn = playerTurn.nextTurn(players, deck);
            } break;

            case DEALER_TURN: {
                nextTurn = dealerTurn.nextTurn(dealer, deck);
            } break;

            case EARLY_SETTLING_TURN: {
                nextTurn = earlySettlingTurn.nextTurn(players);
            } break;

            case LAZY_SETTLING_TURN: {
                nextTurn = lazySettlingTurn.nextTurn(players);
            } break;
        }

    }

}
