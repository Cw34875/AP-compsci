import java.util.ArrayList;


public class Deck {

    private ArrayList<Card> myCards;
    private int[] suits = {Card.CLUBS, Card.SPADES, Card.HEARTS, Card.DIAMONDS };
    
    public Deck()  {
        myCards = new ArrayList<Card>();
        refill();
    }

    // repopulates the deck, and shuffles
    public void refill() {
        for (int suit : suits) {
            for (int rank = 1; rank <= 13;  rank++) {
                myCards.add(new Card(suit, rank));
            }
        }
        shuffle();
    }
    
    
    public int numberOfCards() {
        return myCards.size();
    }
    
    
    public Card drawOne() {
        return myCards.remove(0);
    }
    
    
    public void shuffle() {
        // doesn't do anything right now...
    }
    
}
