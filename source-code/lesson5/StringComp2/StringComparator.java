/**
 * Find a pair of String objects s1 and s2 
 * for which s1 != s2 and s1.equals(s2) is true.
 * 
 * There are two constructors, each of which will test your code after it is called.
 * You can fill code into the constructor without any arguments if necessary.
 */

public class StringComparator {

   // instance variables - replace the example below with your own
   private String s1;
   private String s2;

    
   // Use this one if you need to create strings in specific ways.  
   public StringComparator() {
      // fill in code here...
      // Hint: try using the s1 = new String("something")' form...
      s1 = new String("Sean");
      // Now try some thing like this : s1 = "pizza"
      s2 = new String("Sean");
      test();
   }

   // Test whether s1 and s2 fullfill the constraints.  
   // Also, make sure you understand how the conditional in this test works correctly!
   public void test() {
      System.out.println("s1 is " + s1);
      System.out.println("s2 is " + s2);
      if (s1 != s2 && s1.equals(s2)){
         System.out.println("You got it!");
      } else if (s1 != s2) {
         System.out.println("Nope, 's1 == s2' is false, but s1.equals(s2) is also false.");
      } else if (s1.equals(s2)) {
         System.out.println("Nope, 's1.equals(s2)' is true, but so is 's1 == s2'.");
      } else {
         // when will this get invoked, hmm?
         System.out.println("Double nope, 's1.equals(s2)' is false but 's1==s2' is true.");
      }
   }
}
