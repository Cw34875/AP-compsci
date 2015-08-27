// This class gathers players input, and lets you match to it.
public class CompareCommand extends InputGather {

    // we'll just use the parent class' instance variables. 

    // The constructor
    public CompareCommand() {
        super();
    }
    
    // ignores case
    public boolean isInput(String possibleInput) {
        return (inputString.equalsIgnoreCase(possibleInput));
    }
    
    // ignores case
    public boolean contains(String possibleInput) {
        return (inputString.toLowerCase().contains(possibleInput.toLowerCase()));
    }
    
} // end class