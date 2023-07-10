package game.blackjack;

import card.blackjack.BJPlayerHand;
import fund.Wallet;
import game.InputProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BJPlayerImplTest extends IOTest{
    private BJPlayer player;

    @Mock
    private BJPlayerHand playerHand;
    @Mock
    private Wallet wallet;

    @Mock
    InputProcessor inputProcessor;



    @BeforeEach
    void setUp() {
        long amount = 1000;
        initPlayerBalance(amount);
    }

    void initPlayerBalance(long amount){
        lenient().when(wallet.getBalance()).thenReturn(amount);
        player = new BJPlayerImpl(playerHand, wallet);
    }

    @Test
    @DisplayName("최소 베팅 금액보다 적은 베팅 금액 입력 실패 테스트")
    void testSetBettingAmount() {
        //GIVEN
        long bettingAmount = BettingTurn.BET_MIN - 1;

        //WHEN
        boolean result = player.setBettingAmount(bettingAmount);

        //THEN
        assertThat(player.getBettingAmount()).isZero();
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("최대 베팅 금액보다 많은 베팅 금액 입력 실패 테스트")
    void testSetBettingAmount1() {
        //GIVEN
        long bettingAmount = BettingTurn.BET_MAX * 2 + 1;

        //WHEN
        boolean result = player.setBettingAmount(bettingAmount);

        //THEN
        assertThat(player.getBettingAmount()).isZero();
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("정상범위 베팅 금액 입력 성공 테스트")
    void testSetBettingAmount2() {
        //GIVEN
        long bettingAmount = BettingTurn.BET_MIN + 1;

        //WHEN
        boolean result = player.setBettingAmount(bettingAmount);

        //THEN
        assertThat(player.getBettingAmount()).isEqualTo(bettingAmount);
        assertThat(result).isTrue();
    }


    @Test
    @DisplayName("정상범위 베팅 금액 입력 후 베팅금액 getter 호출 성공 테스트")
    void getBettingAmount() {
        //given
        long bettingAmount = 1000L;

        //when
        boolean resultOfSetBettingAmount = player.setBettingAmount(bettingAmount);

        long resultOfGetBettingAmount = player.getBettingAmount();

        //then
        assertThat(resultOfSetBettingAmount).isTrue();
        assertThat(resultOfGetBettingAmount).isEqualTo(bettingAmount);
    }

    @Test
    @DisplayName("비정상범위 베팅 금액 입력 후 베팅금액 getter 호출 실패 테스트")
    void getBettingAmount1() {
        //given
        long bettingAmount = 10L;

        //when
        boolean resultOfSetBettingAmount = player.setBettingAmount(bettingAmount);

        long resultOfGetBettingAmount = player.getBettingAmount();

        //then
        assertThat(resultOfSetBettingAmount).isFalse();
        assertThat(resultOfGetBettingAmount).isNotEqualTo(bettingAmount);
    }

    @Test
    @DisplayName("잔액부족으로 이슈어런스 지불 실패 테스트")
    void insure() {
        //given
        long playerBalance = 1000L;
        long bettingAmount = 900L;

        when(inputProcessor.getLongValue()).thenReturn(bettingAmount);

        when(wallet.getBalance())
                .thenReturn(playerBalance)
                .thenReturn(playerBalance - bettingAmount);

        //when
        boolean resultOfBetting = player.bet(inputProcessor);

        boolean resultOfInsure = player.insure();

        //then
        assertThat(resultOfBetting).isTrue();
        assertThat(resultOfInsure).isFalse();
    }

    @Test
    @DisplayName("이슈어런스 지불 성공 테스트")
    void insure2() {
        //given
        long playerBalance = 750L;
        long bettingAmount = 500L;

        when(inputProcessor.getLongValue()).thenReturn(bettingAmount);

        when(wallet.getBalance())
                .thenReturn(playerBalance)
                .thenReturn(playerBalance - bettingAmount);

        //when
        boolean resultOfBetting = player.bet(inputProcessor);

        boolean resultOfInsure = player.insure();

        //then
        assertThat(resultOfBetting).isTrue();
        assertThat(resultOfInsure).isTrue();
    }

    @Test
    @DisplayName("잔액부족으로 더블다운 실패 테스트")
    void testDoubleDownFailForNoMoney() {
        //given
        long playerBalance = 1000L;
        long bettingAmount = 600L;

        when(inputProcessor.getLongValue()).thenReturn(bettingAmount);

        when(wallet.getBalance())
                .thenReturn(playerBalance)
                .thenReturn(playerBalance - bettingAmount);

        //when
        boolean resultOfBetting = player.bet(inputProcessor);

        boolean resultOfDoubleDown = player.doubleDown();

        //then
        assertThat(resultOfBetting).isTrue();
        assertThat(resultOfDoubleDown).isFalse();
    }

    @Test
    @DisplayName("더블다운 성공 테스트")
    void testDoubleDownSuccess() {
        //given
        long playerBalance = 1200L;
        long bettingAmount = 600L;

        when(inputProcessor.getLongValue()).thenReturn(bettingAmount);

        when(wallet.getBalance())
                .thenReturn(playerBalance)
                .thenReturn(playerBalance - bettingAmount);

        //when
        boolean resultOfBetting = player.bet(inputProcessor);

        boolean resultOfDoubleDown = player.doubleDown();

        //then
        assertThat(resultOfBetting).isTrue();
        assertThat(resultOfDoubleDown).isTrue();
    }

    @Test
    @DisplayName("최소 베팅 금액보다 작은 베팅 실패 테스트")
    void testBetLessThanMinBet(){
        //given
        long input = 10L;

        when(inputProcessor.getLongValue()).thenReturn(input);

        //when
        boolean result = player.bet(inputProcessor);

        //then
        assertThat(input).isLessThan(BettingTurn.BET_MIN);
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("최대 베팅 금액보다 큰 베팅 실패 테스트")
    void testBetExceedMaxBet() {
        //given
        long input = BettingTurn.BET_MAX + 1;

        long playerBalance = BettingTurn.BET_MAX + 100;
        initPlayerBalance(playerBalance); //플레이어에게 충분한 돈 지급

        //when
        when(inputProcessor.getLongValue()).thenReturn(input);

        boolean result = player.bet(inputProcessor);

        //then
        assertThat(input).isGreaterThan(BettingTurn.BET_MAX);
        assertThat(playerBalance).isGreaterThan(input);
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("보유금액을 초과한 배팅 실패 테스트")
    void testBetExceedHoldingMoney() {
        //given
        long input = 10000L;
        long playerBalance = 100L;
        initPlayerBalance(playerBalance);

        //when
        when(inputProcessor.getLongValue()).thenReturn(input);

        boolean result = player.bet(inputProcessor);

        //then
        assertThat(input).isGreaterThan(playerBalance);
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("100단위가 아닌 배팅 실패 테스트")
    void testBetNot100Unit() {
        //given
        long input = 101L;

        //when
        when(inputProcessor.getLongValue()).thenReturn(input);

        boolean result = player.bet(inputProcessor);

        //then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("배팅 성공 테스트")
    void testBetSuccess() {
        //given
        long input = 100L;

        //when
        when(inputProcessor.getLongValue()).thenReturn(input);

        boolean result = player.bet(inputProcessor);

        //then
        assertThat(result).isTrue();
    }
}