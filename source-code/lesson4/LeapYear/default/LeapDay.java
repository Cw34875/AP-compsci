/**
 * Complete the setLeapDay method to properly detect leap days.
 */

 public class LeapDay {
 
    int monthNumber;
    int dateInMonth;
    int leapDayCount;
 
    public LeapDay (){
       leapDayCount = 0;
    }

    // write this
    // Sets leapDayCount to 1 if monthNumber is equal to 2 and dateInMonth 
    // equals 29. Otherwise leapDayCount is set to 0.
    public void setLeapDay() {
       
       
       
       
       
       
    }
    
//HIDDEN CODE START
    
    // really, should check for '=' versus '==' in the if clause, you know...  How would I do that, hm?
	
    // no exception check, oh well.
    public static String[] __TEST(String studentInputs) {
       LeapDay obj=new LeapDay();
       org.uccp.apcsa.BlueJExerciseTest t = new org.uccp.apcsa.BlueJExerciseTest(studentInputs);

       t.claim(obj.__checkIt(6,16)==0, "Hmm.  On National Holler' Contest Day (where monthNumber is 6 and dateInMonth is 16), your code failed to set leapDayCount to 0 (or to leave it at that value).");
       t.claim(obj.__checkIt(11,13)==0, "Hmm.  On National Indian Pudding Day (where monthNumber is 11 and dateInMonth is 13), your code failed to set leapDayCount to 0 (or to leave it at that value).");       
       t.claim(obj.__checkIt(1,29)==0, "Hmm.  On National Cornchip Day (where monthNumber is 1 and dateInMonth is 29), your code failed to set leapDayCount to 0 (or to leave it at that value).");       
       t.claim(obj.__checkIt(3,29)==0, "Hmm.  On Festival of Smoke and Mirrors Day (where monthNumber is 3 and dateInMonth is 29), your code failed to set leapDayCount to 0 (or to leave it at that value).");       
       t.claim(obj.__checkIt(2,29)==1, "Hmm.  On February 29th (where monthNumber is 2 and dateInMonth is 29), your code failed to set leapDayCount to 1.");
       t.claim(obj.__checkIt(2,28)==0, "Hmm.  On February 28th (where monthNumber is 2 and dateInMonth is 28), your code failed to set leapDayCount to 0 (or to leave it at that value).");
       t.claim(obj.__checkIt(2,30)==0, "Hmm.  On February 30th (which doesn't even exist!), your code failed to set leapDayCount to 0 (or to leave it at that value).");

       return (t.report());
    }
    
    private int __checkIt(int mn, int dim) {
       monthNumber = mn;
       dateInMonth = dim;
       leapDayCount=0;  //bummer to do this, but the constructor does it...
       setLeapDay();
       return(leapDayCount);
    }

    /* solution
     
        if (monthNumber == 2 && dateInMonth == 29) {
           leapDayCount=1;
        }

     */
    
    
//HIDDEN CODE END   
}
/**
 * BLUEJ EXERCISE
 * TEXTBOX:A1:19:6
 * ASSERTION:A1:parenBalanced:Your parentheses aren't balanced; this won't compile!
 * ASSERTION:A1:braceBalanced:Your braces aren't balanced; this won't compile!
 * TESTSCHEME:__TEST
 */