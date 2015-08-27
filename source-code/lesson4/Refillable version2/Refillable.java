// Refillable version 2
//
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
		myAmount = myAmount - amount;
		myUseUpCallCount = myUseUpCallCount + 1; 
		myTotalUse = myTotalUse + amount; 
	}
	
	// Add amount to the current amount.
	public void refill (int amount) {
		myAmount = myAmount + amount;
	}
	
	// Return the current amount.
	public int currentAmount ( ) {
		return myAmount;
	}

	public int averageUse ( ) { 
		return myTotalUse / myUseUpCallCount; 
	} 
}
