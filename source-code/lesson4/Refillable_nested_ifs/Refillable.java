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
         myAmount = myAmount - amount; 
        }  
       else {
          myAmount = myAmount - 0;
          System.out.println ("not enough left");
      
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
   
}