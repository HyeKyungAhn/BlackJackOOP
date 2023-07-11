package game.blackjack;

import card.blackjack.BJDealerHand;
import card.blackjack.BJDealerHandImpl;
import card.blackjack.BJPlayerHand;
import card.blackjack.BJPlayerHandImpl;
import fund.VirtualWallet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BJLazySettlingTurnTest {
    private TurnWithPlayerAndDealer lazySettlingTurn;

    BJLazySettlingTurnTest(){
        lazySettlingTurn = new BJLazySettlingTurn();
    }

    void initSettlingTurn(BJDealer dealer){
        lazySettlingTurn = new BJLazySettlingTurn(dealer);
    }

    @Test
    @DisplayName("딜러 블랙잭 & 플레이어 블랙잭 일 때 베팅머니 원금 반환 테스트")
    void testReturnPrincipalWhenDealerBJNPlayerBJ(){
        //GIVEN
        long bettingAmount = 1000L;
        long playerBalance = 0L;

        BJPlayerHand playerHand = new BJPlayerHandImpl();
        BJPlayer player = new BJPlayerImpl(playerHand, new VirtualWallet(playerBalance));
        HandForTest.getBlackJackHand(player);

        player.setBettingAmount(bettingAmount);

        playerHand.count();
        playerHand.setInsured(false);

        BJDealerHand dealerHand = new BJDealerHandImpl();
        BJDealer dealer = new BJDealerImpl(dealerHand);
        HandForTest.getBlackJackHand(dealer);

        initSettlingTurn(dealer);

        dealerHand.openHiddenCard();
        dealerHand.count();

        //WHEN
        lazySettlingTurn.nextTurn(player);

        long actualPlayerBalance = player.getWallet().getBalance();

        //THEN
        assertThat(actualPlayerBalance).isEqualTo(playerBalance + bettingAmount);
    }

    @Test
    @DisplayName("딜러 블랙잭 & 플레이어가 졌을 떄 베팅머니 회수 테스트")
    void testTakeBettingAmountWhenDealerGotBJAndPlayerLose(){
        //GIVEN
        long bettingAmount = 1000L;
        long playerBalance = 0L;

        BJPlayerHand playerHand = new BJPlayerHandImpl();
        BJPlayer player = new BJPlayerImpl(playerHand, new VirtualWallet(playerBalance));
        HandForTest.getNoBustedNNoBlackJackHand(player);

        player.setBettingAmount(bettingAmount);

        playerHand.count();
        playerHand.setInsured(false);

        BJDealerHand dealerHand = new BJDealerHandImpl();
        BJDealer dealer = new BJDealerImpl(dealerHand);
        HandForTest.getBlackJackHand(dealer);

        initSettlingTurn(dealer);

        dealerHand.openHiddenCard();
        dealerHand.count();

        //WHEN
        lazySettlingTurn.nextTurn(player);

        long actualPlayerBalance = player.getWallet().getBalance();

        //THEN
        assertThat(actualPlayerBalance).isEqualTo(playerBalance);
        assertThat(player.getBettingAmount()).isZero();
    }

    @Test
    @DisplayName("딜러 블랙잭 & 플레이어 짐 & 인슈어런스를 냈을 때 보험금 1.5배 반환 테스트")
    void testCaseWhenDealerGotBJAndPlayerWithInsuranceLose(){
        //GIVEN
        long bettingAmount = 1000L;
        long playerBalance = 0L;

        BJPlayerHand playerHand = new BJPlayerHandImpl();
        BJPlayer player = new BJPlayerImpl(playerHand, new VirtualWallet(playerBalance));
        HandForTest.getNoBustedNNoBlackJackHand(player);

        player.setBettingAmount(bettingAmount);

        playerHand.count();
        playerHand.setInsured(true);

        BJDealerHand dealerHand = new BJDealerHandImpl();
        BJDealer dealer = new BJDealerImpl(dealerHand);
        HandForTest.getBlackJackHand(dealer);

        initSettlingTurn(dealer);

        dealerHand.openHiddenCard();
        dealerHand.count();

        //WHEN
        lazySettlingTurn.nextTurn(player);

        long actualPlayerBalance = player.getWallet().getBalance();

        //THEN
        assertThat(actualPlayerBalance).isEqualTo(playerBalance + (bettingAmount / 2) * 3);
        assertThat(player.getBettingAmount()).isZero();
    }

    @Test
    @DisplayName("플레이어 블랙잭 & 이겼을 때 베팅액 2.5배 반환 테스트")
    void testReturnTwoAndHalfPayoutWhenPlayerWithBjWin(){
        //GIVEN
        long bettingAmount = 1000L;
        long playerBalance = 0L;

        BJPlayerHand playerHand = new BJPlayerHandImpl();
        BJPlayer player = new BJPlayerImpl(playerHand, new VirtualWallet(playerBalance));
        HandForTest.getBlackJackHand(player);

        player.setBettingAmount(bettingAmount);

        playerHand.count();

        BJDealerHand dealerHand = new BJDealerHandImpl();
        BJDealer dealer = new BJDealerImpl(dealerHand);
        HandForTest.get20CountHand(dealer);

        initSettlingTurn(dealer);

        dealerHand.openHiddenCard();
        dealerHand.count();

        //WHEN
        lazySettlingTurn.nextTurn(player);

        long actualPlayerBalance = player.getWallet().getBalance();

        //THEN
        assertThat(actualPlayerBalance).isEqualTo(playerBalance + (long)(bettingAmount * 2.5));
        assertThat(player.getBettingAmount()).isZero();
    }

    @Test
    @DisplayName("플레이어 이겼을 때 베팅액 2배 반환 테스트")
    void testReturnTwoTimesPayoutWhenPlayerWin(){
        //GIVEN
        long bettingAmount = 1000L;
        long playerBalance = 0L;

        BJPlayerHand playerHand = new BJPlayerHandImpl();
        BJPlayer player = new BJPlayerImpl(playerHand, new VirtualWallet(playerBalance));
        HandForTest.get20CountHand(player);

        player.setBettingAmount(bettingAmount);

        playerHand.count();

        BJDealerHand dealerHand = new BJDealerHandImpl();
        BJDealer dealer = new BJDealerImpl(dealerHand);
        HandForTest.get17CountHand(dealer);

        initSettlingTurn(dealer);

        dealerHand.openHiddenCard();
        dealerHand.count();

        //WHEN
        lazySettlingTurn.nextTurn(player);

        long actualPlayerBalance = player.getWallet().getBalance();

        //THEN
        assertThat(actualPlayerBalance).isEqualTo(playerBalance + bettingAmount * 2);
        assertThat(player.getBettingAmount()).isZero();
    }

    @Test
    @DisplayName("인슈어런스를 낸 플레이어가 이겼을 때 보험금 제외 한 베팅액 2배 반환 테스트")
    void testReturnOneAndAHalfPayoutWhenPlayerWithInsuranceWin(){
        //GIVEN
        long bettingAmount = 1000L;
        long playerBalance = 0L;

        BJPlayerHand playerHand = new BJPlayerHandImpl();
        BJPlayer player = new BJPlayerImpl(playerHand, new VirtualWallet(playerBalance));
        HandForTest.get20CountHand(player);

        player.setBettingAmount(bettingAmount);

        playerHand.setInsured(true);
        playerHand.count();

        BJDealerHand dealerHand = new BJDealerHandImpl();
        BJDealer dealer = new BJDealerImpl(dealerHand);
        HandForTest.get17CountHand(dealer);

        initSettlingTurn(dealer);

        dealerHand.openHiddenCard();
        dealerHand.count();

        //WHEN
        lazySettlingTurn.nextTurn(player);

        long actualPlayerBalance = player.getWallet().getBalance();

        //THEN
        assertThat(actualPlayerBalance).isEqualTo(playerBalance + bettingAmount * 2);
        assertThat(player.getBettingAmount()).isZero();
    }

    @Test
    @DisplayName("비겼을 때 원금 반환 테스트")
    void testReturnPrincipalWhenTie(){
        //GIVEN
        long bettingAmount = 1000L;
        long playerBalance = 0L;

        BJPlayerHand playerHand = new BJPlayerHandImpl();
        BJPlayer player = new BJPlayerImpl(playerHand, new VirtualWallet(playerBalance));
        HandForTest.get20CountHand(player);

        player.setBettingAmount(bettingAmount);

        playerHand.count();

        BJDealerHand dealerHand = new BJDealerHandImpl();
        BJDealer dealer = new BJDealerImpl(dealerHand);
        HandForTest.get20CountHand(dealer);

        initSettlingTurn(dealer);

        dealerHand.openHiddenCard();
        dealerHand.count();

        //WHEN
        lazySettlingTurn.nextTurn(player);

        long actualPlayerBalance = player.getWallet().getBalance();

        //THEN
        assertThat(actualPlayerBalance).isEqualTo(playerBalance + bettingAmount);
        assertThat(player.getBettingAmount()).isZero();
    }

    @Test
    @DisplayName("인슈어런스를 낸 플레이어가 비겼을 때 원금 반환 테스트")
    void testReturnPrincipalWhenPlayerWithInsuranceTieWithDealer(){
        //GIVEN
        long bettingAmount = 1000L;
        long playerBalance = 0L;

        BJPlayerHand playerHand = new BJPlayerHandImpl();
        BJPlayer player = new BJPlayerImpl(playerHand, new VirtualWallet(playerBalance));
        HandForTest.get20CountHand(player);

        player.setBettingAmount(bettingAmount);
        playerHand.setInsured(true);

        playerHand.count();

        BJDealerHand dealerHand = new BJDealerHandImpl();
        BJDealer dealer = new BJDealerImpl(dealerHand);
        HandForTest.get20CountHand(dealer);

        initSettlingTurn(dealer);

        dealerHand.openHiddenCard();
        dealerHand.count();

        //WHEN
        lazySettlingTurn.nextTurn(player);

        long actualPlayerBalance = player.getWallet().getBalance();

        //THEN
        assertThat(actualPlayerBalance).isEqualTo(playerBalance + bettingAmount);
        assertThat(player.getBettingAmount()).isZero();
    }

    @Test
    @DisplayName("패배 시 배팅머니 회수 테스트")
    void testTakeBettingAmountWhenLose(){
        //GIVEN
        long bettingAmount = 1000L;
        long playerBalance = 0L;

        BJPlayerHand playerHand = new BJPlayerHandImpl();
        BJPlayer player = new BJPlayerImpl(playerHand, new VirtualWallet(playerBalance));
        HandForTest.get17CountHand(player);

        player.setBettingAmount(bettingAmount);

        playerHand.count();

        BJDealerHand dealerHand = new BJDealerHandImpl();
        BJDealer dealer = new BJDealerImpl(dealerHand);
        HandForTest.get20CountHand(dealer);

        initSettlingTurn(dealer);

        dealerHand.openHiddenCard();
        dealerHand.count();

        //WHEN
        lazySettlingTurn.nextTurn(player);

        long actualPlayerBalance = player.getWallet().getBalance();

        //THEN
        assertThat(actualPlayerBalance).isEqualTo(playerBalance);
        assertThat(player.getBettingAmount()).isZero();
    }

    @Test
    @DisplayName("인슈어런스를 낸 플레이어가 패배 시 배팅머니 회수 테스트")
    void testTakeBettingAmountWhenPlayerWithInsuranceLose(){
        //GIVEN
        long bettingAmount = 1000L;
        long playerBalance = 0L;

        BJPlayerHand playerHand = new BJPlayerHandImpl();
        BJPlayer player = new BJPlayerImpl(playerHand, new VirtualWallet(playerBalance));
        HandForTest.get17CountHand(player);

        player.setBettingAmount(bettingAmount);

        playerHand.setInsured(true);
        playerHand.count();

        BJDealerHand dealerHand = new BJDealerHandImpl();
        BJDealer dealer = new BJDealerImpl(dealerHand);
        HandForTest.get20CountHand(dealer);

        initSettlingTurn(dealer);

        dealerHand.openHiddenCard();
        dealerHand.count();

        //WHEN
        lazySettlingTurn.nextTurn(player);

        long actualPlayerBalance = player.getWallet().getBalance();

        //THEN
        assertThat(actualPlayerBalance).isEqualTo(playerBalance);
        assertThat(player.getBettingAmount()).isZero();
    }
}