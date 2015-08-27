import java.util.*;

public class Deck {

    // This class represents a deck of cards.

    // The set of cards in the Deck.  Each element in the ArrayList is of type Card.
    private ArrayList<Card> myCards;
    // Used to generate random numbers
    private Random randGen;

    //Constructor(s) go here
    public Deck(){
     
        this.resetDeck();

    }

    public void resetDeck(){
        myCards = new ArrayList<Card>();
        for(int suit = 0; suit < 4; suit++ )
            for(int rank = 0; rank < 13; rank++ )
                myCards.add(new Card(rank, suit));
    }
    
    public void shuffle()
    {
        Random randGen = new Random();
        ArrayList<Card> shuffled = new ArrayList<Card> ( );

        while (myCards.size() != 0) {
            int r = randGen.nextInt (myCards.size());
            shuffled.add (myCards.remove(r));
          
        }
        myCards = shuffled;
    }

    public Card dealTopCard(){
        if(myCards.size() > 0){
            return myCards.remove(0);
        }
        return null;
    }

    public int getNumCardsLeftInDeck()
    {
        return myCards.size();
    }
    public void print ( ) {
        for (Card c : myCards) {
            System.out.println (c);
        }
    }

}