class Card {

	// This class represents a Card.
	
	// each card has a suit and a rank.  The values for these int are specified in the 
	//  toString method at the bottom of this class.
    private int mySuit, myRank;
    
    // constructor when a single index k is provided, between 0 and 51.
//     public Card (int k) {
//         mySuit = k / Deck.suitSize;
//         myRank = k % Deck.suitSize;
//     }
    
    // constructor when both rank and suit are provided
    public Card (int r, int s) {
        mySuit = s;
        myRank = r;
    }
    
    // returns the suit of the Card
    public int suit ( ) {
        return mySuit;
    }
    
    // returns the rank of the Card
    public int rank ( ) {
        return myRank;
    }
    

    // outranks goes here!
    public boolean outranks(){
        
        
    }
    
    public String toString ( ) {
        String rtn = "";
        switch (myRank) {
        case 0: 
            rtn = "ace";
            break;
        case 1: case 2: case 3: case 4: case 5: case 6: case 7: case 8: case 9:
            rtn = "" + (myRank+1);
            break;
        case 10:
            rtn = "jack";
            break;
        case 11:
            rtn = "queen";
            break;
        case 12:
            rtn = "king";
            break;
        }
        rtn = rtn + " ";
        switch (mySuit) {
        case 0:
            return rtn + "of clubs";
        case 1:
            return rtn + "of diamonds";
        case 2:
            return rtn + "of hearts";
        case 3:
            return rtn + "of spades";
        }
        return "";
    } 
}