import java.util.*;

public class Elevens {

    private Deck myDeck = new Deck();
    private ArrayList<Card> onTable = new ArrayList<Card>();
    private int numOnTable=2;

    public Elevens(){
        
    }
    
    // constructor when both rank and suit are provided
    public Elevens (int numOnTable) {
        this.numOnTable  = numOnTable;
    }

    //this method should return a 0 if they lose and 1 if they win
    public int play(){
       
    }

    private boolean removeJQK()
    {
        
    }

    private boolean removeSums11()
    {
        

    }
  

    //simulate should play the game howMany times for a range of setNumOnTable values
    //looking for the smallest setNumOnTable values that will win 25% of the time 
    public void simulate(int howMany){
       
    }

    
    // sort the cards using selection sort
    private void sortOnTable ( ) {
        
        ArrayList<Card> sorted = new ArrayList<Card> ( );
        while (onTable.size()>0) {
            // find smallest card in myCards
            Card minCard = onTable.get(0);
            for (Card c: onTable) {

                if (minCard.outranks(c)) {
                    minCard = c;
                }
            }
            sorted.add (minCard);
            onTable.remove (minCard);
        }
        onTable = sorted;
       
    }
}