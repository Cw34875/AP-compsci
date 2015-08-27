
public class WhileExample_1 {

    public WhileExample_1() {
        iLikeJava();
    }

    /* Make one change to one line in this method so that the 
     * output lines are numbered, starting at line 0:
     * 
     *    0: I like Java!
     *    1: I like Java!
     *     ...
     *    79: I like Java!
     */
    public void iLikeJava() {
        int count = 0;
        while (count < 30) {
           count = count + 1;
           System.out.println ("I like Java!");
        }   
    }

}