package game.blackjack;

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
    @DisplayName("이븐머니 선택 시 배팅액 1배 상금 얻는지 테스트")
    void testNoEvenMoney(){
        //GIVEN
        long playerBalance = 1000L;
        long playerBettingAmount = 1000L;
        long expectedBalance = playerBalance + playerBettingAmount * 2;

        BJPlayerHand playerHand = new BJPlayerHandImpl();
        playerHand.setEvenMoney(true);
        BJPlayer player = new BJPlayerImpl(playerHand, new VirtualWallet(playerBalance));
        HandForTest.getBlackJackHand(player);

        BJDealerHand dealerHand = new BJDealerHandImpl();
        BJDealer dealer = new BJDealerImpl(dealerHand);
        HandForTest.getDealerHandWithA(dealer);

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
        long insurance = oldPlayerBalance / 2;
        long playerBettingAmount = 1000L;
        long expectedBalance = oldPlayerBalance + insurance * 3;

        BJPlayerHand playerHand = new BJPlayerHandImpl();
        playerHand.setInsured(true);

        BJPlayer player = new BJPlayerImpl(playerHand, new VirtualWallet(oldPlayerBalance));
        player.setBettingAmount(playerBettingAmount);
        HandForTest.getBustedHand(player);

        playerHand.count();

        BJDealerHand dealerHand = new BJDealerHandImpl();
        BJDealer dealer = new BJDealerImpl(dealerHand);
        HandForTest.getBlackJackHand(dealer);

        initSettlingTurn(dealer);

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
        BJPlayer player = new BJPlayerImpl(playerHand, new VirtualWallet(oldPlayerBalance));
        HandForTest.getBustedHand(player);

        player.setBettingAmount(playerBettingAmount);
        playerHand.setInsured(true);
        playerHand.count();

        BJDealerHand dealerHand = new BJDealerHandImpl();
        BJDealer dealer = new BJDealerImpl(dealerHand);
        HandForTest.getNoBustedNNoBlackJackHand(dealer);

        initSettlingTurn(dealer);

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
        BJPlayer player = new BJPlayerImpl(playerHand, new VirtualWallet(oldPlayerBalance));
        HandForTest.getBustedHand(player);

        player.setBettingAmount(playerBettingAmount);

        playerHand.count();

        BJDealerHand dealerHand = new BJDealerHandImpl();
        BJDealer dealer = new BJDealerImpl(dealerHand);
        HandForTest.getNoBustedNNoBlackJackHand(dealer);

        initSettlingTurn(dealer);

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
        BJPlayer player = new BJPlayerImpl(playerHand, new VirtualWallet(oldPlayerBalance));
        HandForTest.getNoBustedNNoBlackJackHand(player);

        player.setBettingAmount(playerBettingAmount);

        playerHand.setInsured(true);
        playerHand.count();

        BJDealerHand dealerHand = new BJDealerHandImpl();
        BJDealer dealer = new BJDealerImpl(dealerHand);
        HandForTest.getBustedHand(dealer);

        dealer.totalOpen();
        dealerHand.count();

        initSettlingTurn(dealer);

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
        playerHand.count();

        BJPlayer player = new BJPlayerImpl(playerHand, new VirtualWallet(oldPlayerBalance));
        HandForTest.getNoBustedNNoBlackJackHand(player);

        player.setBettingAmount(playerBettingAmount);

        BJDealerHand dealerHand = new BJDealerHandImpl();
        BJDealer dealer = new BJDealerImpl(dealerHand);
        HandForTest.getBustedHand(dealer);

        dealer.totalOpen();
        dealerHand.count();

        initSettlingTurn(dealer);

        //WHEN
        earlySettlingTurn.nextTurn(player);
        long actualPlayerBalance = player.getWallet().getBalance();

        //THEN
        assertThat(actualPlayerBalance).isEqualTo(expectedPlayerBalance);
    }
}