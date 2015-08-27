/** Card:  a class that models a playing card, 
 *    e.g., an ace of spades or a seven of diamonds.
 *    
 *    You need to complete the isLegalRank and isLegalSuit methods.
 */

public class Card {
   
   // the cards suit: either 0 (clubs), 1 (diamonds), 2 (hearts), or 3 (spades)
   private int mySuit;
   //myRank, a value between 2 and 14, inclusive
   // 11 represents a jack, 12 a queen, 13 a king, and 14 an ace (which is high).
   private int myRank;
   
   // the constructor
   public Card (int rank, int suit) {
      if (isLegalRank (rank)) {
         myRank = rank;
      } else {
         System.out.println ("Illegal rank: " + rank);
      }
      if (isLegalSuit (suit)) {
         mySuit = suit;
      } else {
         System.out.println ("Illegal suit: " + suit);
      }
   }
   
   // returns 'true' if the rank of this card is legal, or false otherwise.
   private boolean isLegalRank (int x) {
      // you fill this in
      
   }
   
   // returns 'true' if the suit of this card is legal, or false otherwise.
   private boolean isLegalSuit (int x) {
      // you fill this in
      
   }
   
   public int rank ( ) {
      return myRank;
   }
   
   public int suit ( ) {
      return mySuit;
   }
   
}