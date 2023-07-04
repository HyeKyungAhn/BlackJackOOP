package card;

import java.util.List;

public class BJDealerHandImpl extends BlackJackHandImpl implements BJDealerHand{
    private Card hiddenCard;
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void addCard(Card card){
        if(hiddenCard==null){
            hiddenCard = card;

            super.addCard(new Card("비밀카드", ""));
            return;
        }

        super.addCard(card);
    }

    public void openHiddenCard(){
        super.getCards().set(0, hiddenCard);
        hiddenCard = null;
    }

    public boolean hasACard(Hand hand) {
        List<Card> cards = hand.getCards();
        String pipOrCourt = cards.get(1).getPipOrCourt();
        return pipOrCourt.equals("A");
    }

    @Override
    public void initValues(){
        super.initValues();
        hiddenCard = null;
        count = 0;
    }
}
