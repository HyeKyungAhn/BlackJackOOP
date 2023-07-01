package card;

public class BJDealerHand extends Hand{
    private Card hiddenCard;
    private int count;

    protected int getCount() {
        return count;
    }

    protected void setCount(int count) {
        this.count = count;
    }

    protected void addCard(Card card){
        if(hiddenCard==null){
            hiddenCard = card;

            super.addCard(new Card("비밀카드", ""));
            return;
        }

        super.addCard(card);
    }

    @Override
    protected void initValues(){
        super.initValues();
        hiddenCard = null;
        count = 0;
    }

    protected void openHiddenCard(){
        super.getCards().set(0, hiddenCard);
        hiddenCard = null;
    }
}
