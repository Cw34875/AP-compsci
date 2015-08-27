import java.util.*;
/**
 * this should be a BLUEJ EXERCISE
 * 
 * Complete the maxInRank method.
 */


public class Hand{

   public ArrayList<Card> myCards;

       //this constructor calls the helper method fillHand to form valid random Hand of 5 cards 
    //It is not dependent on a Deck and could not be used for a card game 
    public Hand(){
        myCards = new ArrayList<Card>();
        fillHand();
    }
    
    
   public int maxInRank() {

      int maxRankCount = 0;
      for (int r=2;r<=14;r++){
         int countNumberOfR=0;    
         //Your code counts the number of times r occurs in myCards and sets the value to countNumberOfR .
         
         for(int x =0; x < myCards.size(); x++)
            if(myCards.get(x).rank() == r)
                countNumberOfR++;
          
         if (countNumberOfR > maxRankCount) {
                maxRankCount = countNumberOfR;
         }
      }
      return maxRankCount;
   }
   
   public void testMaxInRank()
   {
        for(int testNum = 1; testNum < 5; testNum++) {
            myCards = new ArrayList<Card>();
            for(int numSame=0; numSame < testNum; numSame++){
                myCards.add(new Card(2,0)); //add bogus rank so 
            }
            
            for(int x = 0; x < 5 - testNum; x++){
                int suit = 0; //suit can ignored
                int rank = 4 + x; //rank is always over 2 so 2 doesn't repeat 
                 myCards.add(new Card(rank,suit)); 
            }
            System.out.println("This test should show " + testNum + " card rank the same :" + maxInRank()); 
        }
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