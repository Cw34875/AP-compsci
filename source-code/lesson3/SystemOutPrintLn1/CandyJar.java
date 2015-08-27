/* Assignment to understand System.out.println. 
 * Change the CandyJar method to to print how many candies remain after each eat invocation.
 * Add a line to the Refillable class to print "Refilled!" after each call to refill.
 * 
 * To test your work, create a new CandyJar object, add some candy to and try calling
 * your methods with various arguments.  You should see printing to the console after
 * calling the refill and candyCount methods!
 */
public class CandyJar
{
	private int myCandyCount;
	// Constructor, empty.
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
	// Print out the number of pieces of candy in the jar after adding the amount
	public void refill (int amount) {
		myCandyCount = myCandyCount + amount;
	}
	
	// Return the number of pieces of candy currently in the jar.
	// Add a feature that prints out the number of pieces of candy
	public int candyCount ( ) {
		return myCandyCount;
	}

}