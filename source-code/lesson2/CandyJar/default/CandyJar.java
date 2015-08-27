/**
 * Complete the definition of the method doubleCandyAmount.
 */

public class CandyJar {

   private int myCandyCount;
   
   public CandyJar (){}
   
   // Initialize a CandyJar object having startingCount pieces of candy.
   public CandyJar (int startingCount) {
      myCandyCount = startingCount;
   }
   
   // Eat amount pieces of candy.
   public void eat (int amount) {
      myCandyCount = myCandyCount - amount;
   }
   
   // Add amount to the candy currently in the jar.
   public void refill (int amount) {
      myCandyCount = myCandyCount + amount;
   }
   
   // Return the number of pieces of candy currently in the jar.
   public int candyCount ( ) {
      return myCandyCount;
   }
   
   // Double the amount of candy currently in the jar
   public void doubleCandyAmount(){
      //Your code goes here
    
    
    
    
    
    
   }
   
//HIDDEN CODE START 

   	// simple TEST method, because no need to check for exceptions...
    public static String[] __TEST(String studentInputs) {
    	CandyJar c;
		org.uccp.apcsa.BlueJExerciseTest t = new org.uccp.apcsa.BlueJExerciseTest(studentInputs);

		// try it with a random number between 3 and 13 (maybe!) pieces
		int origCount = (int) (3 + (java.lang.Math.random() * 10));
    	c = new CandyJar(origCount);
    	c.doubleCandyAmount();
    	t.claim(c.myCandyCount == (origCount * 2), "After calling your method on a CandyJar with " + origCount + " pieces, it had " 
    			+ c.myCandyCount + " pieces instead of " + (origCount * 2) + ".");

    	// try it with 0 pieces
    	c = new CandyJar(0);
    	c.doubleCandyAmount();
    	t.claim(c.myCandyCount == 0, "After calling your method on a CandyJar with no candy in it, the jar had " + c.myCandyCount + " pieces.  It should have had 0 still!");

    	return t.reportAll();  // report both problems if they both failed.  Could do t.report(), to just report the first.
    }

	//HIDDEN CODE END
}
/**
 * BLUEJ EXERCISE
 * TEXTBOX:A1:34:6
 * ASSERTION:A1:parenBalanced:Your parentheses are not balanced; this won't compile.
 * TESTSCHEME:__TEST
 */