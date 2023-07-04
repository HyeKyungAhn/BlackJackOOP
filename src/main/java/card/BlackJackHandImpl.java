package card;

import java.util.List;

public class BlackJackHandImpl extends HandImpl implements BlackJackHand{
    @Override
    public int count(List<Card> cards) {
        int count = 0;
        int aCount = 0;

        for(Card card : cards){
            char pipOrCourt = card.getPipOrCourt().charAt(0);
            if((int)pipOrCourt >= 65){ //courts(K,Q,J,A)
                if(pipOrCourt=='A'){
                    count += 11;
                    aCount++;
                    continue;
                }
                count += 10;
            } else { //pips(2,...,10)
                if((int)pipOrCourt == 49){ //1일 때 ('1'0)
                    count += 10;
                    continue;
                }
                count += ((int)pipOrCourt - 48);
            }
        }

        if(count>21){
            for(int i=0; i<aCount && count>21; i++){
                count -= 10;
            }
        }

        return count;
    }

    @Override
    public boolean isBlackJack(BlackJackHand hand) {
        int count = hand.count(hand.getCards());
        return isBlackJack(count);
    }

    @Override
    public boolean isBlackJack(int count) {
        return count == 21;
    }

    @Override
    public boolean isBusted(BlackJackHand hand) {
        int count = hand.count(hand.getCards());
        return isBusted(count);
    }

    @Override
    public boolean isBusted(int count) {
        return count > 21;
    }
}
