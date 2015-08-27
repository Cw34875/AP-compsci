/**
 * this should be a BLUEJ EXERCISE string_3.html
 * 
 * Modify the length method to count only the nonblank characters in its string argument.
 * 
 */

public class StringMachine{


   public int length (String s) {                                                 
      int charCount;                                                               
      charCount = 0;                                                               
      while (!s.equals("")) {                                                      
         charCount = charCount + 1;                                                
         s = s.substring (1);                                                      
         // new s has everything but the first character of old s                  
      }                                                                            


   }                                                                               



}
