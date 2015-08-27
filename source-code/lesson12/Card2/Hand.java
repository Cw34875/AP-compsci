import java.util.*;
/**
 * this should be a BLUEJ EXERCISE

 * Fill in the allSameSuit and the testAllSameSuit methods. Your allTheSameSuit method should use the process
 * every element pattern.
 */

public class Hand{

    public ArrayList<Card> myCards;

    //this constructor calls the helper method fillHand to form valid random Hand of 5 cards 
    //It is not dependent on a Deck and could not be used for a card game 
    public Hand(){
        myCards = new ArrayList<Card>();
        fillHand();
    }

    public boolean allSameSuit(){
        int checkSuit = myCards.get(0).suit();
 

        // fix the return statement
        return    ;
    }

    public void testAllSameSuit()
    {
       
		
		
		
    }
    
    
    private void fillHand(){
        Random r = new Random();
        while(myCards.size() < 5){
            //create random card
            int suit = r.nextInt(4) + 1; //rank is 1 - 4
            int rank = r.nextInt(12) + 2; //rank is 2 - 14 -- Ace High 

            if(!cardExistsInHand(suit,rank)){
                Card c = new Card(rank,suit);
                myCards.add(c);
            }

        }
    }

    private boolean cardExistsInHand(int suit, int rank)
    {
        //make sure not already in list
        boolean cardExists= false;
        for(int y = 0; y < myCards.size();y++)
        {
            int s = myCards.get(y).suit();
            int r = myCards.get(y).rank();
            if(s == suit && r == rank)
            {
                cardExists = true;
                break;
            }
        }
        return cardExists;
    }
}                                             

