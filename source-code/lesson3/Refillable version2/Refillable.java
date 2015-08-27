// Refillable version 2
//
public class Refillable {

	private int myAmount;
	private int myUseUpCallCount;// NEW
	private int myTotalUse;// NEW 
	
	// Initialize a Refillable object having initialAmount units.
	public Refillable (int initialAmount) {
		myAmount = initialAmount;
		myUseUpCallCount = 0; // NEW
		myTotalUse = 0; // NEW
	}
	
	// Use amount units.
	public void useUp (int amount) {
		myAmount = myAmount - amount;
		myUseUpCallCount = myUseUpCallCount + 1; // NEW
		myTotalUse = myTotalUse + amount; // NEW
	}
	
	// Add amount to the current amount.
	public void refill (int amount) {
		myAmount = myAmount + amount;
	}
	
	// Return the current amount.
	public int currentAmount ( ) {
		return myAmount;
	}

	public int averageUse ( ) { // NEW
		return myTotalUse / myUseUpCallCount; // NEW
	} // NEW
}
