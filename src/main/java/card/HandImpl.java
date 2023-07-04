package card;

import java.util.ArrayList;
import java.util.List;

public class HandImpl implements Hand {
    private List<Card> cards;

    public HandImpl(){
        cards = new ArrayList<>();
    }

    public void addCard(Card card){
        cards.add(card);
    }

    public List<Card> getCards(){
        return cards;
    }

    public void initValues(){
        cards = new ArrayList<>();
    }
}
