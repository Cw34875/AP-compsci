/* Refillable -- using nested ifs
 * 
 * Fill in the UseUp method such that it prints the error "not enough left"
 * if it is called with an amount greater than myAmount.  Otherwise, it 
 * should assign a new value to myAmount.
 */
public class Refillable {

   private int myAmount;

   // Initialize a Refillable object having initialAmount units.
   public Refillable(int initialAmount) {
      myAmount = initialAmount;
   }

   // Use amount units.
   public void useUp(int amount) {
      if (myAmount > amount) {
         
         
         
      } else {
         
         
         
      }
   }

   // Add amount to the current amount.
   public void refill(int amount) {
      myAmount = myAmount + amount;
   }

   // Return the current amount.
   public int currentAmount() {
      return myAmount;
   }
   
//HIDDEN CODE START 
   
   //simple tests, won't catch exceptions...
   public static String[] __TEST(String studentInputs) {
      Refillable obj = new Refillable(0);
      org.uccp.apcsa.BlueJExerciseTest t = new org.uccp.apcsa.BlueJExerciseTest(studentInputs);
   
      // catch the print output... 
      // (functionality should go into BlueJExerciseTest, perhaps?)
      java.io.ByteArrayOutputStream b = new java.io.ByteArrayOutputStream();
      java.io.PrintStream ps = new java.io.PrintStream(b, true);
      System.setOut(ps);
      // I guess just stick with the default encoding, eh?
      // this will all fail if there is an uptight security manager... could check for this first...
      // check println output with b.toString()...  
      // return the standard output with System.setOut(System.out);
      
      obj.myAmount = 52;
      obj.useUp(51);
      t.claim(!obj.__printed(b), "When myAmount is bigger than the input to useUp, you printed something to the console...  That isn't right.");
      //obj.debug(b);
      t.claim(obj.myAmount != 52, "When myAmount is bigger than the input to useUp, you failed to update myAmount." );
      t.claim(obj.myAmount == 1, "When myAmount is bigger than the input to useUp, you changed myAmount to the wrong value.  You should subtract the input from myAmount!");

      obj.myAmount = 64;
      obj.useUp(64);
      //obj.debug(b);
      t.claim(!obj.__printed(b), "When called with a input equal to myAmount, useUp printed something to the console...  It shouldn't do that.");
      t.claim(obj.myAmount != 64, "When called with a input equal to myAmount, useUp failed to update myAmount at all!" );
      t.claim(obj.myAmount == 0, "When called with a input equal to myAmount, useUp changed myAmount to the wrong value.  When using up an amount equal to myAmount, there should be 0 left!");

      
      obj.myAmount = 36;
      obj.useUp(37);
      t.claim(obj.__printed(b), "When called with a input greater than myAmount -- that is, an bad input -- useUp didn't printed anything to the console...  It should print \"not enough left\" in this situation.");
      t.claim(obj.myAmount == 36, "When called with a input greater than myAmount -- that is, an bad input -- useUp changed myAmount.  It shouldn't change when there is a bad input!" );
      
      if (t.hasFailures()) {
         return (t.report());  // report the first error.
      } else if (b.toString().equals("not enough left")) {
         return(t.reportSuccess());
      } else {
         return(t.reportSuccess("Good job! (Although you were supposed to say \"not enough left\" with bad input, not \"" + b.toString() +"\".)"));
      }
         
   }

//   private void debug(java.io.ByteArrayOutputStream b) {
//      System.err.println("start...");
//      System.err.println(b.toString());
//      System.err.println(b.toString().length());
//      System.err.println("myamount is " + myAmount);
//   }
   
   private boolean __printed(java.io.ByteArrayOutputStream b) {
      return(b.toString().length() != 0);
   }

//HIDDEN CODE END
}
/**
 * BLUEJ EXERCISE
 * TEXTBOX:A1:19:3
 * ASSERTION:A1:parenBalanced:Your parentheses aren't balanced; this won't compile!
 * ASSERTION:A1:braceBalanced:Your braces aren't balanced; this won't compile!
 * TEXTBOX:A2:23:3
 * ASSERTION:A2:parenBalanced:Your parentheses aren't balanced; this won't compile!
 * ASSERTION:A2:braceBalanced:Your braces aren't balanced; this won't compile!
 * TESTSCHEME:__TEST
 */