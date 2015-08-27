/**
 * this should be a BLUEJ EXERCISE
 * For random_9.html
 * 
 * Add a parameter to nextRoll that represents a forbidden roll. 
 * That is, nextRoll (5) should return a "random" integer chosen from 1, 2, 3, 4, and 6.
 * 
 */


import java.util.Random;

public class CoinFlipGenerator {

   private Random randGen;

   public CoinFlipGenerator ( ) {
      randGen = new Random ( );
   }

   //Modify the code below!
   public String nextFlip ( ) {
      if (randGen.nextInt (2) == 0) {
	     return "heads";
      } else {
         return "tails";
      }
   }
	
   public void tester (int howMany) {
	  int k;
	  k = 0;
	  while (k < howMany) {
	     System.out.println (nextFlip ( ));
	     k = k + 1;
	  }
   }



}