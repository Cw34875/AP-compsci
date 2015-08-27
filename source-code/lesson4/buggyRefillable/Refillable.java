/**
 * 
 * Here's a version of Refillable that doesn't work. 
 * It's bug is the result of substituting exactly one symbol for another.
 * 
 * Experiment and figure out what's wrong with it.
 *
 */

public class Refillable {

   private int myAmount;
   private int myUseUpCallCount;
   private int myTotalUse;

   // Initialize a Refillable object having initialAmount units.
   public Refillable (int initialAmount) {                                      

      myAmount = initialAmount;
      myUseUpCallCount = 0;
      myTotalUse = 0;
   }

   // Use amount units.
   public void useUp (int amount) {
      if (amount > 0){
         myAmount = myAmount - amount;
         myUseUpCallCount = myUseUpCallCount + 1;
         myTotalUse = myTotalUse + amount;
      }
   }

   // Add amount to the current amount.
   public void refill (int amount) {
      if (amount < 0){
         myAmount = myAmount + amount;
      }
   }

   // Return the current amount.
   public int currentAmount ( ) {
      return myAmount;
   }

   public int averageUse ( ) {
      if  (myUseUpCallCount > 0){
         return myTotalUse / myUseUpCallCount;
      } else {
         return 0;
      }
   }
}
