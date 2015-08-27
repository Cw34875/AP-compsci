/*
 * Here's an example of a program that uses the Random class. Compile and create an instance of it, then run the methods to see how it works. 
 */


import java.util.Random;

public class CoinflipGenerator {

   private Random randGen;

   public CoinflipGenerator ( ) {
      randGen = new Random ( );
   }

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