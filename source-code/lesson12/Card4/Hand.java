import java.util.*;
/**
 * this should be a BLUEJ EXERCISE
 * 
 * Fill in the insertInOrder method.
 * 
 */

 
 public class Hand{
 
    public ArrayList<Card> myCards;
    
         //this constructor calls the helper method fillHand to form valid random Hand of 5 cards 
    //It is not dependent on a Deck and could not be used for a card game 
    public Hand(){
        myCards = new ArrayList<Card>();
        fillHand();
    }
    
    
    private void fillHand(){
        Random r = new Random();
        while(myCards.size() < 5){
            //create random card
            int suit = r.nextInt(4) + 1; //rank is 1 - 4
            int rank = r.nextInt(13) + 2; //rank is 2 - 14 -- Ace High 

            if(!cardExistsInHand(suit,rank)){
                Card c = new Card(rank,suit);
                insertInOrder(c);  //<-- NOTE : This changed from previous versions in order to put cards in order
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
    
    //Complete the following method.
    public void insertInOrder(Card newCard){
      if(myCards.size() == 0)
        myCards.add(newCard);
      else
      {
        for(int x = 0; x < myCards.size(); x++){
            if(newCard.rank() < myCards.get(x).rank())
            {
               myCards.add(x, newCard);
               return;
            }
        }
        myCards.add(newCard);
    }
    }   
    
    //You should print out the hand to verify it is in order
    public void testInsertInOrder(){
        for(int y = 0; y < myCards.size();y++)
        {
            int s = myCards.get(y).suit();
            int r = myCards.get(y).rank();
            System.out.println(r + " : " + s);        
        }
    }
 
 
}