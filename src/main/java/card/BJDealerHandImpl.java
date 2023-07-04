package card;

import java.util.List;

public class BJDealerHandImpl extends BlackJackHandImpl implements BJDealerHand{
    protected Card hiddenCard;

    @Override
    public void addCard(Card card){
        if(hiddenCard==null){
            hiddenCard = card;

            super.addCard(new Card("비밀카드", ""));
            return;
        }

        super.addCard(card);
    }

    @Override
    public void openHiddenCard(){
        super.getCards().set(0, hiddenCard);
        hiddenCard = null;
    }

    @Override
    public boolean hasACard() {
        List<Card> cards = super.getCards();
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
