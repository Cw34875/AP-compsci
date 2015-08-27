// Represent a person's name with three components, a first name,
// a middle name, and a last name.

public class Name {

   private String myFirst;
   private String myMiddle;
   private String myLast;
   
   // the constructor
   public Name (String first, String middle, String last) {
      myFirst = caseFixed (first);
      myMiddle = caseFixed (middle);
      myLast = caseFixed (last);
   }
   
   // Return the name, arranged as 'last, first middle'.
   public String lastFirst ( ) {
      return myLast + ", " + myFirst + " " + myMiddle;
   }
   
   // Return the name, arranged as 'first middle last'.
   public String fullName ( ) {
      return myFirst + " " + myMiddle + " " + myLast;
   }
   
   // Return the result of "fixing" the given part of the name,
   // that is, making sure it starts with a capital letter and
   // has only lower-case letters after the first.
   private String caseFixed (String namePart) {
      String temp;
      temp = namePart.toLowerCase().trim();
      return temp.substring(0,1).toUpperCase() + temp.substring(1);
   }
}