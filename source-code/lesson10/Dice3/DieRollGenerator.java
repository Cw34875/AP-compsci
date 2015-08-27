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