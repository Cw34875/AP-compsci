import java.util.Random;

public class DieRollGenerator {

   private Random randGen;
   private BarChart6 bars;

   public DieRollGenerator ( ) {
      randGen = new Random ( );
      bars = new BarChart6();
   }

   public int nextRoll( ) {
      // You supply this code.

   }
	
   public void tester (int howMany) {
	  int k;
	  k = 0;
	  while (k < howMany) {
	     bars.add(nextRoll ( ));
	     k = k + 1;
	     bars.updateUI();    // tells the bar chart to update its picture
	     sleep(5);

	  }

   }

   // This will make the program hesitate for a certain number of milliseconds.
   private void sleep(long milliseconds) {
      try {
         java.lang.Thread.sleep (milliseconds);
     } catch (Exception e) {
     }
   }
  
}
