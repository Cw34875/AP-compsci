/**
 * Complete the setLeapYear method to properly detect leap years.
 */

public class LeapYear{

   int year;
   int leapYearCount;

   public LeapYear() {
      leapYearCount = 0;
   }

   // write this
   // Sets leapYearCount to 1 if year is divisible by 4, unless it is 
   //  also divisible by 100 and not divisible by 400. 
   // Otherwise leapDayCount is set to 0.
   public void setLeapYear() {
      
      
      
      
      
      
      
      
      
      
   }
//HIDDEN CODE START
   public static String[] __TEST(String studentInputs) {
      LeapYear obj=new LeapYear();
      org.uccp.apcsa.BlueJExerciseTest t = new org.uccp.apcsa.BlueJExerciseTest(studentInputs);

      // 0,1,4,16, 17, 100, 400, 500, 800
      obj.__claimIt(1, 0, "", t);
      obj.__claimIt(4, 1, "", t);
      obj.__claimIt(16, 1, "", t);
      obj.__claimIt(17, 0, "", t);
      obj.__claimIt(100, 0, "", t);
      obj.__claimIt(400, 1, "", t);
      obj.__claimIt(500, 0, "", t);
      obj.__claimIt(800, 1, "", t);

      return (t.report());
   }
   
   // y = year, actual = whether it really is a leap year or not
   // explain = possible additional explanation
   private void __claimIt(int y, int actual, String explain, org.uccp.apcsa.BlueJExerciseTest t) {
      year = y;
      setLeapYear();
      t.claim(leapYearCount == actual, __sayIt(explain));
   }

   private String __sayIt(String exp) {
      if (leapYearCount == 0) {
         // should have been a leap year
         return "Hmmm.  Your code thought that the year " + year + " is not a leap year, but really it is. " + exp;
      } else {
         // shouldn't have been a leap year
         return "No, the year " + year + " is <i>not</i> a leap year, but your code thinks it is. " + exp;
      }
   }
   
   private String __sayIt() {
      return __sayIt("");
   }
   /* solution
    
    if (year % 400 == 0) {
        leapDayCount = 1;
    } else if (year % 100 == 0) {
        leapDayCount = 0;
    } else if (year % 4 == 0) {
        leapDayCount = 1;
    } else {
        leapDayCount = 0;
    }

    */
//HIDDEN CODE END
}
/**
 * BLUEJ EXERCISE
 * TEXTBOX:A1:19:10
 * ASSERTION:A1:parenBalanced:Your parentheses aren't balanced; this won't compile!
 * ASSERTION:A1:braceBalanced:Your braces aren't balanced; this won't compile!
 * TESTSCHEME:__TEST
 */