package card;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> cards;

    Hand(){
        cards = new ArrayList<>();
    }

    protected void addCard(Card card){
        cards.add(card);
    }

    protected List<Card> getCards(){
        return cards;
    }

    protected void initValues(){
        cards = new ArrayList<>();
    }
}
