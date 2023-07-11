package game.blackjack;

import card.Card;
import card.blackjack.*;
import fund.VirtualWallet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BJEarlySettlingTurnTest extends IOTest {
    private TurnWithPlayerAndDealer earlySettlingTurn;

    void initSettlingTurn(BJDealer dealer){
        earlySettlingTurn = new BJEarlySettlingTurn(dealer);
    }

    @Test
    @DisplayName("이븐머니 포기 시 상금 두 배 테스트")
    void testNoEvenMoney(){ //player who has blackjack at first dealing but doesn't choose even money
        //GIVEN
        long playerBalance = 1000L;
        long playerBettingAmount = 1000L;
        long expectedBalance = playerBalance + playerBettingAmount * 2;

        BJPlayerHand playerHand = new BJPlayerHandImpl();
        getBlackJackHand(playerHand);

        BJDealerHand dealerHand = new BJDealerHandImpl();
        getBlackJackHand(dealerHand);

        BJPlayer player = new BJPlayerImpl(playerHand, new VirtualWallet(playerBalance));
        BJDealer dealer = new BJDealerImpl(dealerHand);

        initSettlingTurn(dealer);

        player.setBettingAmount(playerBettingAmount);

        playerHand.count();

        //WHEN
        earlySettlingTurn.nextTurn(player);

        long actualPlayerBalance = player.getWallet().getBalance();

        //THEN
        assertThat(actualPlayerBalance).isEqualTo(expectedBalance);
    }

    @Test
    @DisplayName("블랙잭 딜러, 버스트&인슈어런스 플레이어 테스트")
    void testBustedPlayerWithInsuranceAndDealerWithBlackJack(){
        //GIVEN
        long oldPlayerBalance = 1000L;
        long playerBettingAmount = 1000L;
        long expectedBalance = oldPlayerBalance + playerBettingAmount;

        BJPlayerHand playerHand = new BJPlayerHandImpl();
        getBustedHand(playerHand);

        BJDealerHand dealerHand = new BJDealerHandImpl();
        getBlackJackHand(dealerHand);

        BJPlayer player = new BJPlayerImpl(playerHand, new VirtualWallet(oldPlayerBalance));
        BJDealer dealer = new BJDealerImpl(dealerHand);

        initSettlingTurn(dealer);

        player.setBettingAmount(playerBettingAmount);

        playerHand.setInsured(true);

        playerHand.count();

        //WHEN
        earlySettlingTurn.nextTurn(player);
        long actualPlayerBalance = player.getWallet().getBalance();

        //THEN
        assertThat(actualPlayerBalance).isEqualTo(expectedBalance);
    }

    @Test
    @DisplayName("블랙잭이 아닌 딜러, 버스트&인슈어런스 플레이어 테스트")
    void testBustedPlayerWithInsurance(){
        //GIVEN
        long oldPlayerBalance = 1000L;
        long playerBettingAmount = 1000L;

        BJPlayerHand playerHand = new BJPlayerHandImpl();
        getBustedHand(playerHand);

        BJDealerHand dealerHand = new BJDealerHandImpl();
        getNoBustedNNoBlackJackHand(dealerHand);

        BJPlayer player = new BJPlayerImpl(playerHand, new VirtualWallet(oldPlayerBalance));
        BJDealer dealer = new BJDealerImpl(dealerHand);

        initSettlingTurn(dealer);

        player.setBettingAmount(playerBettingAmount);

        playerHand.setInsured(true);

        playerHand.count();

        //WHEN
        earlySettlingTurn.nextTurn(player);
        long actualPlayerBalance = player.getWallet().getBalance();

        //THEN
        assertThat(actualPlayerBalance).isEqualTo(oldPlayerBalance);
    }

    @Test
    @DisplayName("버스트 플레이어 테스트")
    void testBustedPlayer(){
        //GIVEN
        long oldPlayerBalance = 1000L;
        long playerBettingAmount = 1000L;

        BJPlayerHand playerHand = new BJPlayerHandImpl();
        getBustedHand(playerHand);

        BJDealerHand dealerHand = new BJDealerHandImpl();
        getNoBustedNNoBlackJackHand(dealerHand);

        BJPlayer player = new BJPlayerImpl(playerHand, new VirtualWallet(oldPlayerBalance));
        BJDealer dealer = new BJDealerImpl(dealerHand);

        initSettlingTurn(dealer);

        player.setBettingAmount(playerBettingAmount);

        playerHand.count();

        //WHEN
        earlySettlingTurn.nextTurn(player);
        long actualPlayerBalance = player.getWallet().getBalance();

        //THEN
        assertThat(actualPlayerBalance).isEqualTo(oldPlayerBalance);
    }

    @Test
    @DisplayName("버스트 딜러 테스트 & 인슈어런스 플레이어 테스트")
    void testBustedDealerAndPlayerWithInsurance(){
        //GIVEN
        long oldPlayerBalance = 1000L;
        long playerBettingAmount = 1000L;
        long expectedPlayerBalance = oldPlayerBalance + playerBettingAmount * 2;

        BJPlayerHand playerHand = new BJPlayerHandImpl();
        getNoBustedNNoBlackJackHand(playerHand);

        BJDealerHand dealerHand = new BJDealerHandImpl();
        getBustedHand(dealerHand);

        BJPlayer player = new BJPlayerImpl(playerHand, new VirtualWallet(oldPlayerBalance));
        BJDealer dealer = new BJDealerImpl(dealerHand);

        initSettlingTurn(dealer);

        player.setBettingAmount(playerBettingAmount);
        playerHand.setInsured(true);

        playerHand.count();

        dealer.totalOpen();
        dealerHand.count();

        //WHEN
        earlySettlingTurn.nextTurn(player);
        long actualPlayerBalance = player.getWallet().getBalance();

        //THEN
        assertThat(actualPlayerBalance).isEqualTo(expectedPlayerBalance);
    }

    @Test
    @DisplayName("버스트 딜러 테스트")
    void testBustedDealer(){
        //GIVEN
        long oldPlayerBalance = 1000L;
        long playerBettingAmount = 1000L;
        long expectedPlayerBalance = oldPlayerBalance + playerBettingAmount * 2;

        BJPlayerHand playerHand = new BJPlayerHandImpl();
        getNoBustedNNoBlackJackHand(playerHand);

        BJDealerHand dealerHand = new BJDealerHandImpl();
        getBustedHand(dealerHand);

        BJPlayer player = new BJPlayerImpl(playerHand, new VirtualWallet(oldPlayerBalance));
        BJDealer dealer = new BJDealerImpl(dealerHand);

        initSettlingTurn(dealer);

        player.setBettingAmount(playerBettingAmount);

        playerHand.count();

        dealer.totalOpen();
        dealerHand.count();

        //WHEN
        earlySettlingTurn.nextTurn(player);
        long actualPlayerBalance = player.getWallet().getBalance();

        //THEN
        assertThat(actualPlayerBalance).isEqualTo(expectedPlayerBalance);
    }

    private void getBlackJackHand(BlackJackHand hand) {
        hand.addCard(new Card("Heart", "A"));
        hand.addCard(new Card("King", "K"));
    }

    private void getBustedHand(BlackJackHand hand) {
        hand.addCard(new Card("Heart", "7"));
        hand.addCard(new Card("King", "6"));
        hand.addCard(new Card("Queen", "K"));
    }

    private void getNoBustedNNoBlackJackHand(BlackJackHand hand) {
        hand.addCard(new Card("Heart", "A"));
        hand.addCard(new Card("King", "3"));
    }
}