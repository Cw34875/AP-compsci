import java.util.*;

public class Contestant {
    
    private int myInitialGuess;
    private boolean stay;
    private Controller control;
    private Random myRandoms;
    
    public Contestant (Controller c, Random randoms) {
        control = c;
        myRandoms = randoms;
    }
    
    // The contestant makes his/her first guess.
    public int guess1 ( ) {
        myInitialGuess = myRandoms.nextInt (3) + 1;
        control.picture (control.CONTESTANT_CHOICE, myInitialGuess, 0);
        return myInitialGuess;
    }
    
    // The contestant sticks with his/her first guess.
    public int guess2 (int openedDoor) {
        return myInitialGuess;
        //return 6 - openedDoor - myInitialGuess;
    }
}
