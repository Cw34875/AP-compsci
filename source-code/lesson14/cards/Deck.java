import java.util.*;

public class Deck {

    // This class represents a deck of cards.
    
    // The set of cards in the Deck.  Each element in the ArrayList is of type Card.

    private ArrayList<Card> myCards;
    
    // Used to generate random numbers.
    private Random randGen;
    
	//Constructor(s) go here
    //Constructor(s) go here
    public Deck(){
         reset();
     }
    
    //write your code for reset here
    public void reset()
    {
        
    }
    
    //write the code for perfect here
    public void perfectShuffle(){
        
        
    }
    
    //write your code for shuffle here
    public void shuffle(){
        
    }
    
    //write your selection sort here
    public void selectionSort(){
        
    }
    
    //write your insertion sort
    public void insertionSort(){
        
    }
    

	// Sort myCards using a comparison counting sort:
	//	element 0 is the one that isn't greater than any of the others;
	//	element 1 is the one that's greater than exactly one of the others;
	//	element 2 is the one that's greater than exactly two of the others;
	//		...
	public void countingSort ( ) {
		ArrayList<Card> sorted = new ArrayList<Card> ( );
		for (int k=0; k<myCards.size ( ); k++) {
			int numLessThan = 0;
			for (int j=0; j<myCards.size ( ); j++) {
				if (myCards.get(k).outranks(myCards.get(j))) {
					numLessThan++;
				}
			}
			sorted.set (numLessThan, myCards.get(k));
		}
		myCards = sorted;
	}
   
    
    public void print ( ) {
        for (Card c : myCards) {
            System.out.println (c);
        }
    }
    

}
