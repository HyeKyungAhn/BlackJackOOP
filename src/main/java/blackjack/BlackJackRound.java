package blackjack;

import card.Deck;
import game.Gambler;
import game.Playable;
import game.Round;
import game.Turn;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BlackJackRound implements Round {
    List<Gambler> gamblers; //player (can bet)
    BJDealer dealer; //dealer
    List<Playable> players; //player + dealer
    Deck deck; //추상화 필요

    Scanner scanner;

    Turn bettingTurn;
    Turn dealingTurn;
    Turn playerTurn;
    Turn dealerTurn;
    Turn lazySettlingTurn;
    Turn earlySettlingTurn;

    NextTurnStatus nextTurn = NextTurnStatus.INITIAL;
    boolean isFinal = false;

    BlackJackRound(){}

    BlackJackRound(List<Playable> players, Scanner scanner){
        this.players = players;
        this.scanner = scanner;
        this.gamblers = new ArrayList<>();
        this.deck = new Deck();

        initTurns();

        players.forEach(player -> {
            if(player instanceof Gambler) {
                gamblers.add((Gambler) player);
            } else {
                dealer = (BJDealer) player;
            }
        });
    }

    private void initTurns() {
        bettingTurn = new BJBettingTurn(gamblers, scanner);
        dealingTurn = new BJDealingTurn(players, deck);
        playerTurn = new BJPlayerTurn(players, deck, scanner);
        dealerTurn = new BJDealerTurn(dealer, deck);
        lazySettlingTurn = new BJLazySettlingTurn(players);
        earlySettlingTurn = new BJEarlySettlingTurn(players);
    }

    @Override
    public void next() {

        do {
            process();
        } while (nextTurn != NextTurnStatus.FINISH_TURN);

        Viewer.printInfo(ViewerStatus.ROUND_END);

        if(!(isFinal = BJInputProcessor.getBooleanAnswer(scanner))){
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
                nextTurn = bettingTurn.nextTurn();
            } break;

            case DEALING_TURN: {
                nextTurn = dealingTurn.nextTurn();
            } break;

            case PLAYER_TURN: {
                nextTurn = playerTurn.nextTurn();
            } break;

            case DEALER_TURN: {
                nextTurn = dealerTurn.nextTurn();
            } break;

            case EARLY_SETTLING_TURN: {
                nextTurn = earlySettlingTurn.nextTurn();
            } break;

            case LAZY_SETTLING_TURN: {
                nextTurn = lazySettlingTurn.nextTurn();
            } break;
        }

    }

}
