package blackjack;

import card.Deck;
import game.Gambler;
import game.Playable;
import game.Round;

import java.util.ArrayList;
import java.util.List;

public class BlackJackRound implements Round {
    List<Gambler> gamblers; //플레이어
    Dealer dealer; //딜러
    List<Playable> players; //플레이어 + 딜러
    Deck deck;

    NextTurnStatus nextTurn = NextTurnStatus.INITIAL;
    boolean isFinal = false;

    BlackJackRound(){}

    BlackJackRound(List<Playable> players){
        this.players = players;
        this.gamblers = new ArrayList<>();
        this.deck = new Deck();

        players.forEach(player -> {
            if(player instanceof Gambler) {
                gamblers.add((Gambler) player);
            } else {
                dealer = (Dealer) player;
            }
        });
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
                nextTurn = BettingTurn.nextTurn(gamblers);
            } break;

            case DEALING_TURN: {
                nextTurn = DealingTurn.nextTurn(players, deck);
            } break;

            case PLAYER_TURN: {
                nextTurn = PlayerTurn.nextTurn(players, deck);
            } break;

            case DEALER_TURN: {
                nextTurn = DealerTurn.nextTurn(dealer, deck);
            } break;

            case EARLY_SETTLING_TURN: {
                nextTurn = EarlySettlingTurn.nextTurn(players);
            } break;

            case LAZY_SETTLING_TURN: {
                nextTurn = LazySettlingTurn.nextTurn(players);
            } break;
        }

    }

}
