/** Age1
 * Ages between 13 and 19, inclusive, are teenage years.
 * 
 * Fill in the blank in the ageType() method so that ageType 
 * receives the correct results.
 */

public class Age {

   public String ageType;
   public int age;

   // teenagers are between 13 and 19 inclusive...
   public void ageType(){
      //TEXT BOX:A1:9:20:1
      if (                                 ) {
         ageType = "teenage";
      } else {
         ageType = "other";
      }

   }

   //HIDDEN CODE START

   // no exception check, oh well.
   public static String[] __TEST(String studentInputs) {
      Age obj=new Age();
      org.uccp.apcsa.BlueJExerciseTest t = new org.uccp.apcsa.BlueJExerciseTest(studentInputs);
      int a;
      String teenRes = "teenage";
      String otherRes = "other";

         // check teenager
         a = obj.__getAge(14,18);
      t.claim(obj.__checkIt(a)==teenRes, "Your code doesn't handle an age of " + a + " correctly: ageType should be '" + teenRes + "'.");
      t.claim(obj.__checkIt(13)==teenRes, "Your code doesn't handle an age of 13 correctly: ageType should be " + teenRes + "'.");
      t.claim(obj.__checkIt(19)==teenRes, "Your code doesn't handle an age of 19 correctly: ageType should be '" + teenRes + "'.");

      // check other
      a = obj.__getAge(0,11);
      t.claim(obj.__checkIt(a)==otherRes, "Your code doesn't handle an age of " + a + " correctly: ageType should be '" + otherRes + "'.");
      a = obj.__getAge(21,100);
      t.claim(obj.__checkIt(a)==otherRes, "Your code doesn't handle an age of " + a + " correctly: ageType should be '" + otherRes + "'.");
      t.claim(obj.__checkIt(12)==otherRes, "Your code doesn't handle an age of 12 correctly: ageType should be '" + otherRes + "'.");
      t.claim(obj.__checkIt(20)==otherRes, "Your code doesn't handle an age of 20 correctly: ageType should be '" + otherRes + "'.");


      return (t.report());
   }


   public String __checkIt(int ageToSet) {
      String rtn;

      age = ageToSet;
      ageType();
      rtn = ageType;
      ageType = null;   // reset it in case something weird in their code...
      return rtn;
   }

   /** Return a random number in the range [min,max] inclusive */
   public int __getAge(int min, int max) {
      return min + (int)(Math.random() * ((max - min) + 1));
   }

   /* solution

          age <=13 && age >=19

    */

   //HIDDEN CODE END
}
/**
 * BLUEJ EXERCISE
 * TEXTBOX:A1:16:1:11:42
 * ASSERTION:A1:parenBalanced:Your parentheses aren't balanced; this won't compile!
 * ASSERTION:A1:braceBalanced:Your braces aren't balanced; this won't compile!
 * TESTSCHEME:__TEST
 */


