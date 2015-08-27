/**
 * this should be a BLUEJ EXERCISE
 * For random_7.html
 * 
 * Add a parameter to nextRoll that represents a forbidden roll. 
 * That is, nextRoll (5) should return a "random" integer chosen from 1, 2, 3, 4, and 6.
 * 
 */


import java.util.Random;

public class DieRollGenerator {

    private int myRoll;
    private Random randGen;
    
    public DieRollGenerator ( ) {
        randGen = new Random ( );
    }
    
	
	//Don't forget to add a parameter here!
    public int nextRoll( ) {
      // You supply this code.

	  
	  
   }
    
   public void tester (int howMany) {
	  int k;
	  k = 0;
	  while (k < howMany) {
	     System.out.println (nextRoll ( ));
	     k = k + 1;
	  }
   }
   

}