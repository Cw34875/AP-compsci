import java.util.Scanner;
import java.util.ArrayList;

/* General class to gather responses from the player.  Can be specialized for 
   specific contexts
 */

public class InputGather {

    //initializes the one Scanner object for all Response objects (i.e., it's 'static').  
    // This will be reused for every user input.
    protected static Scanner rawInput = new Scanner(System.in);
    
    //a scanner object to process the particular input string
    Scanner stringProcessor;

    // This contains the full user entry, as a string or arraylist of strings (words).
    // Subclasses can access these.
    protected String inputString;
    protected ArrayList<String> inputWords;

    // Constructor: prints a prompt, and gets the input, saving it into "inputString"
    public InputGather() {
        printPrompt();
        gatherInput();
    }


    // this set inputString and inputWords
    public void gatherInput() {
        inputString = InputGather.rawInput.nextLine();  // get the line of user input
        stringProcessor = new Scanner(inputString);   // a new Scanner object to work on the string  
        inputWords = new ArrayList<String>();
        // populate the arraylist of input words
        int i = 0;
        while (stringProcessor.hasNext()) {
            inputWords.add(i, stringProcessor.next());
            i++;
        }
    }

    
    public void printPrompt() {
        System.out.println();
        System.out.print("> ");
    }
    
    
    public String getInputString() {
        return inputString;
    }

    public ArrayList<String> getInputWords() {
        return inputWords;
    }
    
} // end InputGather
