package lesson10.GraphGuesser;
import java.util.Random;

public class Generator {

   private Random randGen;
   private BarChart12 bars;

   public Generator ( ) {
      randGen = new Random ( );
      bars = new BarChart12();
   }

   
   public int generator1( ) {
      // You supply this code.
      return 0;
   }
    
   
   public int generator2( ) {
      // You supply this code.
      return 0;
   }
   
   
   public int generator3( ) {
      // You supply this code.
      return 0;
   }
   
   
   public int generator4( ) {
      // You supply this code.
      return 0;
   }
   
   
   public void tester (int whichTest, int howMany) {
      int k;
      k = 0;
      bars.clear();        // clear the graph
      while (k < howMany) {
         if (whichTest == 1) {
            bars.add(generator1 ( ));
         } else if (whichTest == 2) {
            bars.add(generator2( ));
         } else if (whichTest == 3) {
            bars.add(generator3( ));
         } else if (whichTest == 4) {
            bars.add(generator4( ));
         } else {
            System.out.println("I don't have such a test!");
            break;
         };
         k = k + 1;
      }
      bars.updateUI();     // tells the bar chart to update its picture
   }

   
}
