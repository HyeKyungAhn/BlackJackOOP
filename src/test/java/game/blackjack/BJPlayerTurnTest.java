package game.blackjack;

import card.Card;
import card.Deck;
import card.blackjack.BJDealerHand;
import card.blackjack.BJDealerHandImpl;
import card.blackjack.BJPlayerHand;
import card.blackjack.BJPlayerHandImpl;
import fund.VirtualWallet;
import game.InputProcessor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

//기능 테스트
@ExtendWith(MockitoExtension.class)
class BJPlayerTurnTest{

    private TurnWithPlayerAndDealer playerTurn;

    @Mock
    private InputProcessor inputProcessor;

    private void initPlayerTurn(Deck deck) {
        playerTurn = new BJPlayerTurn(deck, inputProcessor);
    }

    private void initPlayerTurn() {
        playerTurn = new BJPlayerTurn(new Deck(), inputProcessor);
    }

    @Test
    @DisplayName("이븐머니 선택 결과 테스트")
    void testEvenMoney(){
        //GIVEN
        long playerBalance = 1000L;

        when(inputProcessor.getBooleanAnswer()).thenReturn(true);
        initPlayerTurn();

        BJPlayerHand playerHand = new BJPlayerHandImpl();
        BJPlayer player = new BJPlayerImpl(playerHand, new VirtualWallet(playerBalance));
        HandForTest.getBlackJackHand(player);

        BJDealerHand dealerHand = new BJDealerHandImpl();
        BJDealer dealer = new BJDealerImpl(dealerHand);
        HandForTest.getDealerHandWithA(dealer);

        //WHEN
        NextTurnStatus nextTurn = playerTurn.nextTurn(player, dealer);

        //THEN
        assertThat(dealerHand.hasACard()).isTrue();
        assertThat(playerHand.isBlackJack()).isTrue();
        assertThat(playerHand.isEvenMoney()).isTrue();
        assertThat(nextTurn).isEqualTo(NextTurnStatus.DEALER_TURN);
    }

    @Test
    @DisplayName("이븐머니 선택하지 않는 경우 테스트")
    void testNoEvenMoney(){
        //GIVEN
        long playerBalance = 1000L;

        when(inputProcessor.getBooleanAnswer()).thenReturn(false);
        initPlayerTurn();

        BJPlayerHand playerHand = new BJPlayerHandImpl();
        BJPlayer player = new BJPlayerImpl(playerHand, new VirtualWallet(playerBalance));
        HandForTest.getBlackJackHand(player);

        BJDealerHand dealerHand = new BJDealerHandImpl();
        BJDealer dealer = new BJDealerImpl(dealerHand);
        HandForTest.getDealerHandWithA(dealer);

        //WHEN
        NextTurnStatus nextTurn = playerTurn.nextTurn(player, dealer);

        //THEN
        assertThat(playerHand.isBlackJack()).isTrue();
        assertThat(dealerHand.hasACard()).isTrue();
        assertThat(playerHand.isEvenMoney()).isFalse();
        assertThat(nextTurn).isEqualTo(NextTurnStatus.EARLY_SETTLING_TURN);
    }

    @Test
    @DisplayName("인슈어런스 선택, 더블다운 버스트로 인슈어런스 값 테스트")
    void testInsurePlayerWithInsuranceAndDoubleDownBusted(@Mock Deck deck){
        //GIVEN
        long bettingMoney = 1000L;
        long playerBalance = 1000L;

        when(inputProcessor.getBooleanAnswer()).thenReturn(true).thenReturn(true); // 인슈어런스 선택

        when(deck.giveOneCard()).thenReturn(new Card("Spade", "K"));
        initPlayerTurn(deck);

        BJPlayerHand playerHand = new BJPlayerHandImpl();
        BJPlayer player = new BJPlayerImpl(playerHand, new VirtualWallet(playerBalance));
        HandForTest.get20CountHand(player);
        player.setBettingAmount(bettingMoney);

        BJDealerHand dealerHand = new BJDealerHandImpl();
        BJDealer dealer = new BJDealerImpl(dealerHand);
        HandForTest.getDealerHandWithA(dealer);

        //WHEN
        playerTurn.nextTurn(player, dealer);

        //THEN
        assertThat(playerHand.isBlackJack()).isFalse();
        assertThat(dealerHand.hasACard()).isTrue();
        assertThat(playerHand.isEvenMoney()).isFalse();
        assertThat(playerHand.isInsured()).isTrue();
    }

    @Test
    @DisplayName("잔액 부족으로 인슈어런스 실패, 더블다운 버스트로 인슈어런스 값 테스트")
    void testInsuredPlayerWithNoEnoughMoneyNDoubleDownNBusted(@Mock Deck deck){
        //GIVEN
        long bettingMoney = 3000L;
        long playerBalance = 1000L;

        when(inputProcessor.getBooleanAnswer()).thenReturn(true).thenReturn(true); // 인슈어런스 선택

        when(deck.giveOneCard()).thenReturn(new Card("Spade", "K"));
        initPlayerTurn(deck);

        BJPlayerHand playerHand = new BJPlayerHandImpl();
        BJPlayer player = new BJPlayerImpl(playerHand, new VirtualWallet(playerBalance));
        HandForTest.get20CountHand(player);
        player.setBettingAmount(bettingMoney);

        BJDealerHand dealerHand = new BJDealerHandImpl();
        BJDealer dealer = new BJDealerImpl(dealerHand);
        HandForTest.getDealerHandWithA(dealer);

        //WHEN
        playerTurn.nextTurn(player, dealer);

        //THEN
        assertThat(playerHand.isBlackJack()).isFalse();
        assertThat(dealerHand.hasACard()).isTrue();
        assertThat(playerHand.isEvenMoney()).isFalse();
        assertThat(playerHand.isInsured()).isFalse();
    }

    @Test
    @DisplayName("인슈어런스 미 선택, 더블다운 버스트 테스트")
    void testDoubleNBustedWhenPlayerNotChooseInsure(@Mock Deck deck){
        //GIVEN
        long playerBalance = 1000L;

        when(inputProcessor.getBooleanAnswer()).thenReturn(false).thenReturn(true); // 인슈어런스 선택

        when(deck.giveOneCard()).thenReturn(new Card("Spade", "K"));
        initPlayerTurn(deck);

        BJPlayerHand playerHand = new BJPlayerHandImpl();
        BJPlayer player = new BJPlayerImpl(playerHand, new VirtualWallet(playerBalance));
        HandForTest.get20CountHand(player);

        BJDealerHand dealerHand = new BJDealerHandImpl();
        BJDealer dealer = new BJDealerImpl(dealerHand);
        HandForTest.getDealerHandWithA(dealer);

        //WHEN
        playerTurn.nextTurn(player, dealer);

        //THEN
        assertThat(playerHand.isBlackJack()).isFalse();
        assertThat(dealerHand.hasACard()).isTrue();
        assertThat(playerHand.isEvenMoney()).isFalse();
        assertThat(playerHand.isInsured()).isFalse();
    }

    @Test
    @DisplayName("플레이어 블랙잭 && 딜러 A 카드 미보유 테스트")
    void testPlayerWithBJAndDealerWithNoOpenedACard(){
        //GIVEN
        long playerBalance = 1000L;

        initPlayerTurn();

        BJPlayerHand playerHand = new BJPlayerHandImpl();
        BJPlayer player = new BJPlayerImpl(playerHand, new VirtualWallet(playerBalance));
        HandForTest.getBlackJackHand(player);

        BJDealerHand dealerHand = new BJDealerHandImpl();
        BJDealer dealer = new BJDealerImpl(dealerHand);
        HandForTest.getDealerHandWithNotOpenedACard(dealer);

        //WHEN
        NextTurnStatus nextTurn = playerTurn.nextTurn(player, dealer);

        //THEN
        assertThat(playerHand.isBlackJack()).as("playerBlackjack").isTrue();
        assertThat(dealerHand.hasACard()).as("dealer with no opened A").isFalse();
        assertThat(playerHand.isEvenMoney()).as("evenMoney").isFalse();
        assertThat(playerHand.isInsured()).as("insure").isFalse();
        assertThat(nextTurn).isEqualTo(NextTurnStatus.DEALER_TURN);
    }

    @Test
    @DisplayName("더블다운 & 버스트 테스트")
    void testPlayerWhoDoubleDownAndIsBusted(@Mock Deck deck){
        //GIVEN
        long playerBalance = 1000L;

        when(inputProcessor.getBooleanAnswer()).thenReturn(true);
        when(deck.giveOneCard()).thenReturn(new Card("Spade", "K"));
        initPlayerTurn(deck);

        BJPlayerHand playerHand = new BJPlayerHandImpl();
        BJPlayer player = new BJPlayerImpl(playerHand, new VirtualWallet(playerBalance));
        HandForTest.get20CountHand(player);

        BJDealerHand dealerHand = new BJDealerHandImpl();
        BJDealer dealer = new BJDealerImpl(dealerHand);
        HandForTest.getDealerHandWithNotOpenedACard(dealer);

        //WHEN
        NextTurnStatus nextTurn = playerTurn.nextTurn(player, dealer);

        //THEN
        assertThat(playerHand.isBlackJack()).as("playerBlackjack").isFalse();
        assertThat(dealerHand.hasACard()).as("dealer with no opened A").isFalse();
        assertThat(playerHand.isEvenMoney()).as("evenMoney").isFalse();
        assertThat(playerHand.isInsured()).as("insure").isFalse();
        assertThat(playerHand.isBusted()).as("bust").isTrue();
        assertThat(nextTurn).isEqualTo(NextTurnStatus.EARLY_SETTLING_TURN);
    }

    @Test
    @DisplayName("더블다운 & not bust 테스트")
    void testPlayerWhoDoubldDownAndIsNotBusted(){
        //GIVEN
        long playerBalance = 1000L;

        when(inputProcessor.getBooleanAnswer()).thenReturn(true);
        initPlayerTurn();

        BJPlayerHand playerHand = new BJPlayerHandImpl();
        BJPlayer player = new BJPlayerImpl(playerHand, new VirtualWallet(playerBalance));
        HandForTest.get10CountHand(player);

        BJDealerHand dealerHand = new BJDealerHandImpl();
        BJDealer dealer = new BJDealerImpl(dealerHand);
        HandForTest.getDealerHandWithNotOpenedACard(dealer);

        //WHEN
        NextTurnStatus nextTurn = playerTurn.nextTurn(player, dealer);

        //THEN
        assertThat(playerHand.isBlackJack()).as("playerBlackjack").isFalse();
        assertThat(playerHand.isEvenMoney()).as("evenMoney").isFalse();
        assertThat(playerHand.isBusted()).as("bust").isFalse();
        assertThat(nextTurn).isEqualTo(NextTurnStatus.DEALER_TURN);
    }

    @Test
    @DisplayName("추가 hit 이후 21 이하의 점수일 때 DealerTurn 으로 넘어가는지 테스트")
    void testNextTurnStatusWhenPlayerHitAnotherHitAndGetCountUnder21(){
        //GIVEN
        long oldBalance = 1000L;

        when(inputProcessor.getBooleanAnswer()).thenReturn(true);
        initPlayerTurn();

        BJPlayerHand playerHand = new BJPlayerHandImpl();
        BJPlayer player = new BJPlayerImpl(playerHand, new VirtualWallet(oldBalance));
        HandForTest.get10CountHand(player);

        BJDealerHand dealerHand = new BJDealerHandImpl();
        BJDealer dealer = new BJDealerImpl(dealerHand);
        HandForTest.getDealerHandWithNotOpenedACard(dealer);

        //WHEN
        NextTurnStatus nextTurn = playerTurn.nextTurn(player, dealer);

        //THEN
        assertThat(playerHand.isBlackJack()).as("playerBlackjack").isFalse();
        assertThat(playerHand.isEvenMoney()).as("evenMoney").isFalse();
        assertThat(player.getWallet().getBalance()).as("double down?").isEqualTo(oldBalance);
        assertThat(playerHand.isBusted()).as("bust").isFalse();
        assertThat(nextTurn).isEqualTo(NextTurnStatus.DEALER_TURN);
    }

    @Test
    @DisplayName("카드 한 장 더 받기 후 bust 테스트")
    void testBustSuccessWhenAnotherHit(@Mock Deck deck){
        //GIVEN
        long oldBalance = 1000L;

        when(inputProcessor.getBooleanAnswer()).thenReturn(true);
        when(deck.giveOneCard()).thenReturn(new Card("Spade", "K"));
        initPlayerTurn(deck);

        BJPlayerHand playerHand = new BJPlayerHandImpl();
        BJPlayer player = new BJPlayerImpl(playerHand, new VirtualWallet(oldBalance));
        HandForTest.get20CountHand(player);

        BJDealerHand dealerHand = new BJDealerHandImpl();
        BJDealer dealer = new BJDealerImpl(dealerHand);
        HandForTest.getDealerHandWithNotOpenedACard(dealer);

        //WHEN
        NextTurnStatus nextTurn = playerTurn.nextTurn(player, dealer);

        //THEN
        assertThat(playerHand.isEvenMoney()).as("evenMoney").isFalse();
        assertThat(player.getWallet().getBalance()).as("double down?").isEqualTo(oldBalance);
        assertThat(playerHand.isBusted()).as("bust").isTrue();
        assertThat(nextTurn).isEqualTo(NextTurnStatus.EARLY_SETTLING_TURN);
    }

    @Test
    @DisplayName("카드 한 장 더 받기 후 blackjack 일 때  테스트")
    void testWhenPlayerHitOneMoreAndGetBJ(@Mock Deck deck){
        //GIVEN
        long oldBalance = 1000L;

        when(inputProcessor.getBooleanAnswer()).thenReturn(false).thenReturn(true);
        when(deck.giveOneCard()).thenReturn(new Card("Clover", "A"));
        initPlayerTurn(deck);

        BJPlayerHand playerHand = new BJPlayerHandImpl();
        BJPlayer player = new BJPlayerImpl(playerHand, new VirtualWallet(oldBalance));
        HandForTest.get20CountHand(player);

        BJDealerHand dealerHand = new BJDealerHandImpl();
        BJDealer dealer = new BJDealerImpl(dealerHand);
        HandForTest.getDealerHandWithNotOpenedACard(dealer);

        //WHEN
        NextTurnStatus nextTurn = playerTurn.nextTurn(player, dealer);

        //THEN
        assertThat(playerHand.isBlackJack()).as("playerBlackjack").isTrue();
        assertThat(playerHand.isEvenMoney()).as("evenMoney").isFalse();
        assertThat(player.getWallet().getBalance()).as("double down?").isEqualTo(oldBalance);
        assertThat(playerHand.isBusted()).as("bust").isFalse();
        assertThat(nextTurn).isEqualTo(NextTurnStatus.DEALER_TURN);
    }
}