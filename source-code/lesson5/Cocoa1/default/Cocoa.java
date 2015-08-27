/**
 * Fill in the blank in the cocoaTaste() method so that cocoaTaste 
 * is correct.  Note: chocolate is good if the percentage of Cocoa is between
 * 60 and 75 percent inclusive.
 */

public class Cocoa {

   public String cocoaTaste;
   public int cocoaPercentage;

   public void setCocoaTaste(){
      if (                                              ) {                  
         cocoaTaste = "good";
      } else {
         cocoaTaste = "bad";
      }
   }


   //HIDDEN CODE START
   
   public static String[] __TEST(String studentInputs) {
      Cocoa obj = new Cocoa();
      org.uccp.apcsa.BlueJExerciseTest t = new org.uccp.apcsa.BlueJExerciseTest(studentInputs);


      t.claim(obj.__checkIt(59, "bad"), "Oops.  You have a problem at the low end of the bitterness range...");
      t.claim(obj.__checkIt(60, "good"), "Oops.  You have a problem when the cocoaPercentage is exactly on the lower border...");
      t.claim(obj.__checkIt(61, "good"), "Oops.  You have a problem at the low end of the bitterness range...");       
      t.claim(obj.__checkIt(69, "good"), "Oops.  You have a problem in the middle range of bitterness...");
      t.claim(obj.__checkIt(74, "good"), "Oops.  You have a problem at the upper end of the bitterness range...");
      t.claim(obj.__checkIt(75, "good"), "Oops.  You have a problem when the cocoaPercentage is exactly on the upper border...");
      t.claim(obj.__checkIt(76, "bad"), "Oops.  You have a problem at the low end of the bitterness range...");

      return(t.reportAll());
   }
   
   public boolean __checkIt(int pct, String expected) {
      Cocoa c = new Cocoa();
      c.cocoaPercentage = pct;
      c.setCocoaTaste();
      return c.cocoaTaste.equals(expected);
    }
   
   /* answer

          cocoaPercentage >= 60 && cocoaPercentage <= 75 

    */

   //HIDDEN CODE END
}
/**
 * BLUEJ EXERCISE
 * TEXTBOX:A1:13:1:12:55
 * ASSERTION:A1:parenBalanced:Your parentheses aren't balanced; this won't compile!
 * ASSERTION:A1:braceBalanced:Your braces aren't balanced; this won't compile!
 * TESTSCHEME:__TEST
 */