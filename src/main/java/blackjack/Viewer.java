package blackjack;

public class Viewer {
    public static void printInfo(ViewerStatus status) {
        switch (status){
            case NEW_START:
                System.out.println("블랙잭 게임을 시작합니다!"); break;
            case GAME_END:
                System.out.println("게임이 종료됩니다."); break;
            case ROUND_END:
                System.out.println("라운드가 종료됩니다.\n 게임을 종료하시겠습니까? 네: 1, 아니요: 2"); break;
            case INVALID_INPUT:
                System.out.println("입력값 확인 후, 다시 입력해주세요."); break;
        }
    }
}
