package card;

import java.util.List;

public class BlackJackHandImpl extends HandImpl implements BlackJackHand{
    protected int count;

    @Override
    public int count() {
        int count = 0;
        int aCount = 0;

        List<Card> cards = super.cards;

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

        return this.count = count;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public boolean countAndVerifyBJ() {
        count();
        return isBlackJack();
    }

    @Override
    public boolean isBlackJack() {
        return count == 21;
    }

    @Override
    public boolean isBusted() {
        return count() > 21;
    }
}
