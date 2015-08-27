/*
 * An implementation of the Card class.
 */

public class Card {
    int myRank;
    int mySuit;

    public Card(int myRank, int mySuit){
        this.myRank = myRank;
        this.mySuit = mySuit;
    }
    public int rank() {
        return myRank;
    }

    public int suit() {
        return mySuit;
    }

}