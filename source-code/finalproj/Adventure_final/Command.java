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
    //  "isTake" reports whether the user's command is to take something.
    // Methods that start with "is" but end with "Action" report on the general class
    //  of action that is it: either administrative, player, or item commands.  This helps
    //  dispatch the command to the right objects in the World method "dispatch".
    // There are several methods whose names are of the form "getXXReference", where "XX"
    //  is something like "Take".  This returns the word that is the target, or object, of the command.
    //  For instance, with the input "take key", getTakeReference would return "key". 

    
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
    

        
    /// item
    
    public boolean isTake() {
        return  firstWordIn( TAKE_words );
    }
    
    // handles commands like "pick up key"
    public String getTakeReference() {
        if (firstWord == "pick") {
            if (secondWord == "up") {
                secondWord = inputWords.get(2);
            }
        }
        return secondWord;
    }
    
    public boolean isDrop() {
        return  firstWordIn( DROP_words );
    }
    
    public String getDropReference() {
        return secondWord;
    }
    
    public boolean isUse() {
        return  firstWordIn( USE_words );
    }
    
    public String getUseReference() {
        return secondWord;
    }

    
    private static String[] TAKE_words = {"take", "grab", "snag", "get", "pick"};
    private static String[] DROP_words = {"drop", "unload"};
    private static String[] USE_words = {"use", "operate"};

    
    
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
