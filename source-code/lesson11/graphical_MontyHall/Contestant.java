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
        myInitialGuess = control.doorSelected ( );
        control.picture (control.CONTESTANT_CHOICE, myInitialGuess, 0);
        return myInitialGuess;
    }
    
    // The contestant makes his/her second guess.
    public int guess2 (int openedDoor) {
        int mySecondGuess = control.doorSelected ( );

        while (mySecondGuess == openedDoor) {
           // oops, they clicked on the door that Monty showed.  Try that again.
           control.say ("That door is already open!", 3000);
           mySecondGuess = control.doorSelected ( );
        }

        if (mySecondGuess == myInitialGuess) {
           stay = true;
           control.picture (control.CONTESTANT_STAY, 0);
        } else {
           stay = false;
           control.picture (control.CONTESTANT_SWITCH, 0);
        }
        
        return mySecondGuess;
    }
}
