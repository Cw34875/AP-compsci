import java.util.ArrayList;


/* Class to gather general commands from the user.  Right now, it gets up to two word commands, such as 
   "look" and "take stapler".
 */
public class Command extends InputGather {

    // these two are just conveniences.  Could use inputWords from parent class..
    private String firstWord = null;
    private String secondWord = null;

    // The constructor
    public Command() {
        super();
        processInput();
    }
    
    // processes the input string, setting firstWord and secondWord, or trying again if things are empty
    public void processInput() {
        // check to see if there was input, set firstWord, etc.
        if (inputWords.size() == 0) {
            // its empty, they should try again...
            printPrompt();
            gatherInput();
            processInput();    // recursion!
        }
        if (inputWords.size() >= 1) {
            firstWord = inputWords.get(0);
        } 
        if (inputWords.size() >=2) {
            secondWord = inputWords.get(1);
        }
    }


    
    //////// Action predicates
    //////// 
    
    // Methods whose names start with "is" report whether a certain type of command
    //  has been given -- that is, what the 'action' of the command is.  For instance, 
    //  "isQuit" reports whether the user's command is to quit.
    
    ///  Administrative 
    
    public boolean isHelp() {
        return firstWordIn( HELP_words ) ;
    }
    
    public boolean isQuit() {
        return firstWordIn( QUIT_words ) ;
    }

    private static String[] QUIT_words = {"quit", "exit", "die", "uncle", "q"};
    private static String[] HELP_words = {"help", "?"};
    
    
    /// player
       
    public boolean isInventory() {
        return  firstWordIn( INVENTORY_words);
    }
    
    public boolean isLookRoom() {
        return ( firstWordIn(LOOK_words) &&
                 secondWord == null );
    }
    
    private static String[] INVENTORY_words = {"inventory", "inv", "i"};
    private static String[] LOOK_words = {"look", "gaze", "l"};
    
   
    /// travel
    
    // this takes the Room so it can check the exits
    public boolean isTravel(Room room) {
        if ( firstWordIn( TRAVEL_words ) )  {
            return true;
        }
        if ( secondWord == null  &&  (room.getExit(firstWord) != null) ) {
            secondWord = firstWord;
            return true;
        }
        return false;      
    }
    
    public String getDirectionReference() {
        return secondWord;
    }
    
    private static String[] TRAVEL_words = {"go", "travel", "venture", "walk", "amble", "enter"};
    

    

    
    //// Some utility methods
    
    
    private boolean firstWordIn(String[] words) {
        for (String word : words) {
            if (firstWord.equalsIgnoreCase(word)) return true;
        }
        return false;
    }

    private boolean secondWordIn(String[] words) {
        for (String word : words) {
            if (secondWord.equalsIgnoreCase(word)) return true;
        }
        return false;
    }


    private boolean memberOf(String s, String[] words) {
        for (String word : words) {
            if (s.equalsIgnoreCase(word)) return true;
        }
        return false;
    }



} // end class
