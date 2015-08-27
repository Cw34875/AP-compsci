/**
 * BLUEJ EXERCISE
 * ASSERTION:A1:allBalanced:Make sure braces and parentheses are balanced!
 * TESTSCHEME:__TEST
 * For string_3.html
 * 
 * Modify the length method to count only the nonblank characters in its string argument.
 * 
 */

 public class StringMachine{


//TEXT BOX:A1:0:70:11
private int length (String s) {                                                 
   int charCount;                                                               
   charCount = 0;                                                               
   while (!s.equals("")) {                                                      
      charCount = charCount + 1;                                                
      s = s.substring (1);                                                      
      // new s has everything but the first character of old s                  
   }                                                                            
                                                                                
                                                                                
}                                                                               
                                                                                
 
//HIDDEN CODE START
 public Object[] __TEST(){
 Object test[]= new Object[2];
 StringMachine s = new StringMachine();
 String s1 = "";
 String s2 = "abc ";
 String s3 = " abc";
 String s4 = "hi there";
 if(s1.replaceAll("\\s+", "").equals(s.length(s1))) {
   test[0] = "true";
 } else {
   test[0] = "false";
 }
 
 return test; 
 
 }
//HIDDEN CODE END
 
 
 }