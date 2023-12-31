package viewer;

import card.Card;

import java.util.List;

public interface Viewer {
    static void printInfo(ViewerStatus status) {
        switch (status){
            case NEW_START:
                System.out.println("블랙잭 게임을 시작합니다!"); break;
            case GAME_END:
                System.out.println("게임을 종료합니다."); break;
            case ROUND_END:
                System.out.println("라운드가 종료됩니다.\n게임을 종료하시겠습니까? 네: 1, 아니요: 2"); break;
            case INVALID_INPUT:
                System.out.println("입력값 확인 후, 다시 입력해주세요."); break;
            case NO_MONEY:
                System.out.println("배팅 금액이 부족합니다."); break;
            case BETTING_INFO:
                System.out.println("얼마를 베팅하시겠습니까?" +
                        "\n배팅 금액은 100단위의 정수만 작성가능합니다. 예시) 1200(o), 1010(x), 0100(x)"); break;
            case INVALID_BETTING_INPUT:
                System.out.println("배팅 금액은 100단위의 정수만 작성가능합니다. 예시) 1200(o), 1010(x), 0100(x)"); break;
            case OUT_OF_BET_LIMIT:
                System.out.println("최소, 최대 베팅 금액을 다시 한 번 확인해주세요."); break;
            case NO_MONEY_TO_BET:
                System.out.println("베팅 금액이 부족합니다. 다시 작성해주세요."); break;
            case NEXT_ROUND:
                System.out.println("새 라운드를 시작합니다."); break;
            case DEALER_HAND:
                System.out.print("딜러 패 : "); break;
            case PLAYER_HAND:
                System.out.print("플레이어 패 : "); break;
            case CONFIRM_INSURANCE:
                System.out.println("딜러의 카드가 A 입니다. 인셔런스를 지불하시겠습니까? 네:1, 아니요:2"); break;
            case NO_MONEY_TO_INSURE:
                System.out.println("베팅머니가 부족해 인슈어런스를 지불할 수 없습니다.\n게임을 진행하겠습니다."); break;
            case COMPLETE_INSURANCE_PAYMENT:
                System.out.println("인슈어런스를 지불하였습니다."); break;
            case CONFIRM_EVEN_MONEY:
                System.out.println("이븐머니를 하시겠습니까? 네:1, 아니요:2 " +
                        "\n이븐머니 선택 시 배팅액 1배의 보상금을 받고 이번 라운드를 즉시 종료합니다"); break;
            case CONFIRM_DOUBLE_DOWN:
                System.out.println("더블다운을 하시겠습니까? 네:1, 아니요:2"); break;
            case CONFIRM_OTHER_HITS:
                System.out.println("한 장 더 받으시겠습니까? 네:1, 아니요:2"); break;
            case PLAYER_WIN:
                System.out.println("Winner winner chicken dinner! 게임에서 이겼습니다!"); break;
            case PLAYER_LOSE:
                System.out.println("게임에서 졌습니다."); break;
            case DEALER_BUSTED:
                System.out.println("딜러가 버스트(bust) 되었습니다!"); break;
            case PLAYER_BUSTED:
                System.out.println("버스트(bust) 되었습니다!"); break;
            case TIE:
                System.out.println("비겼습니다!"); break;
            case DEALER_BLACKJACK:
                System.out.println("딜러가 블랙잭입니다!"); break;
            case PLAYER_BLACKJACK:
                System.out.println("블랙잭입니다!"); break;
            case DOUBLE_PAYOUT:
                System.out.println("베팅액 2배를 상금으로 얻었습니다!"); break;
            case ONE_PAYOUT:
                System.out.println("베팅액 1배를 상금으로 얻었습니다!"); break;
            case DOUBLE_AND_HALF_PAYOUT:
                System.out.println("베팅액 2.5배를 상금으로 얻었습니다!"); break;
            case ONE_AND_A_HALF_PAYOUT:
                System.out.println("베팅액 1.5배를 상금으로 얻었습니다!"); break;
            case HALF_PAYOUT:
                System.out.println("베팅액 0.5배를 상금으로 얻었습니다!"); break;
            case LOSE_BETTING_AMOUNT:
                System.out.println("베팅액을 회수합니다."); break;
            case EXCLUDE_INSURANCE:
                System.out.print("보험금을 제외한 "); break;
            case INCLUDE_INSURANCE:
                System.out.print("보험금과 "); break;
            case GIVE_INSURANCE_WHEN_LOSE:
                System.out.println("다행히 인슈어런스로 원금을 지켰습니다."); break;
            case GIVE_INSURANCE_COMPENSATION:
                System.out.print("딜러가 블랙잭이므로 인슈어런스 수당을 포함한 "); break;
            case GIVE_PRINCIPAL:
                System.out.println("원금을 돌려드립니다.");
            case LOOSE_INSURANCE:
                System.out.println("딜러가 블랙잭이 아닙니다! 보험금으로 걸어둔 돈을 잃었습니다.");

        }
    }

    static void showCards(List<Card> cards){
        for (Card card : cards) {
            System.out.print(card + "  ");
        }
        System.out.println();
    }

    static void showBalance(long amount) {
        System.out.println("현재 잔액은 " + amount + "원 입니다.");
    }
}
