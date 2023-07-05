package blackjack;

import card.Deck;
import game.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BlackJackRound implements Round {
    BJPlayer player; //player
    BJDealer dealer; //dealer
    List<Playable> playerAndDealer; //player + dealer
    Deck deck; //추상화 필요

    Scanner scanner;

    BettingTurn bettingTurn;
    DealingTurn dealingTurn;
    TurnWithPlayerAndDealer playerTurn;
    DealerTurn dealerTurn;
    TurnWithPlayerAndDealer lazySettlingTurn;
    TurnWithPlayerAndDealer earlySettlingTurn;

    NextTurnStatus nextTurn = NextTurnStatus.INITIAL;
    boolean isFinal = false;

    BlackJackRound(){}
    BlackJackRound(List<Playable> players, BJDealer dealer, Scanner scanner){

    }

    BlackJackRound(BJPlayer player, BJDealer dealer, Scanner scanner){
        this.player = player;
        this.dealer = dealer;

        this.playerAndDealer = new ArrayList<>();
        playerAndDealer.add(player);
        playerAndDealer.add(dealer);

        this.scanner = scanner;
        this.deck = new Deck();

        initTurns();
    }

    private void initTurns() {
        bettingTurn = new BettingTurnImpl(scanner);
        dealingTurn = new BJDealingTurn(deck);
        playerTurn = new BJPlayerTurn(deck, scanner);
        dealerTurn = new BJDealerTurn(dealer, deck);
        lazySettlingTurn = new BJLazySettlingTurn();
        earlySettlingTurn = new BJEarlySettlingTurn();
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
                nextTurn = bettingTurn.nextTurn(player);
            } break;

            case DEALING_TURN: {
                nextTurn = dealingTurn.nextTurn(playerAndDealer);
            } break;

            case PLAYER_TURN: {
                nextTurn = playerTurn.nextTurn(player, dealer);
            } break;

            case DEALER_TURN: {
                nextTurn = dealerTurn.nextTurn();
            } break;

            case EARLY_SETTLING_TURN: {
                nextTurn = earlySettlingTurn.nextTurn(player, dealer);
            } break;

            case LAZY_SETTLING_TURN: {
                nextTurn = lazySettlingTurn.nextTurn(player, dealer);
            } break;
        }

    }

}
