/**
 * UNUSED
 * 
 * Create an instance of this class and provide two string arguments that you believe satisfy the requirements
 * of the current step. Use the equalStrings() method to see if you are correct! 
 *
 */
public class StringComparator
{
    // instance variables - replace the example below with your own
    private String string1;
    private String string2;

    /**
     * Constructor for objects of class StringComparator
     */
    public StringComparator(String string1, String string2)
    {
        this.string1=string1;
        this.string2=string2;
    }

	public StringComparator(){}
    public boolean equalStrings(){
        if (string1==string2){
            return true;
        }
        else{
            return false;
        }
    }
}
