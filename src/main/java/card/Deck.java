package card;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    //A,2~10,K,Q,J Heart/Spade/Clover/Diamond
    String[] suitSymbols = {"Heart", "Spade", "Clover", "Diamond"};
    String[] pipsAndCourts = {"A","2","3","4","5","6","7","8","9","10","K","Q","J"};// 0 == 10
    static final int NUMBER_OF_PACK = 4;
    List<Card> deck = new ArrayList<>(suitSymbols.length * pipsAndCourts.length * NUMBER_OF_PACK);

    public Deck(){
        generateDeck();
        shuffle();
    }

    //public -> private
    public void generateDeck(){
        for(int i=0; i<NUMBER_OF_PACK; i++){
            for (String suitSymbol : suitSymbols) {
                for (String pipsAndCourt : pipsAndCourts) {
                    Card card = new Card(suitSymbol, pipsAndCourt);
                    deck.add(card);
                }
            }
        }
    }

    public void shuffle(){
        int dectSize = deck.size();
        int ranNum;
        Card temp;

        for(int i=0; i < dectSize; i++){
            ranNum = (int)(Math.random() * deck.size());
            temp = deck.get(ranNum);
            deck.set(ranNum, deck.get(i));
            deck.set(i, temp);
        }
    }

    public Card giveOneCard(){
        if(deck.size()==0){
            generateDeck();
            shuffle();
        }

        return deck.remove(deck.size() - 1);
    }
}
