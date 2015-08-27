

/*
* Test the following code by compiling the class and calling blanksRemoved with various arguments.
* 
*
*/


public class StringMachine{

//This is buggy!

public String blanksRemoved (String s) {
   int pos = 0;
   while (pos < s.length()) {
      // Examine character at position pos.
      if (s.substring (pos,pos+1).equals(" ")) {
         // There's a blank at position pos.
         // Create a string that doesn't have the blank.
         s = s.substring (0,pos) + s.substring (pos+1);
      }
      // Go on to the next character.
      pos = pos + 1;
   }
   return s;
}
 
}