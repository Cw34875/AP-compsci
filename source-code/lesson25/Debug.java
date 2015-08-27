public class Debug {
  String myString;

  public Debug (String s) {
      myString = s;
  }

  // Return true when myString is the result of inserting
  // exactly one character into s, and return false otherwise.
  public boolean contains1MoreThan (String s) {
    if (myString.length ( ) == 0) {
      return s.length() == 1;
    } else if (s.length ( ) == 0) {
      return myString.length ( ) == 1;
    } else if (myString.substring(0,1).equals(s.substring(0,1))) {
      Debug remainder = new Debug (myString.substring(1));
      return remainder.contains1MoreThan (s.substring(1));
    } else {
      return myString.substring(1).equals(s) || s.substring(1).equals(myString);
    }
  }

  // Check the two strings to see if s1 is the result of inserting
  // a single character into s2.  For example,
  //   check ("abc", "def") should return false, and
  //   check ("abc2", "abc") should return true.
  public static void check (String s1, String s2) {
    Debug d = new Debug (s1);
    if (d.contains1MoreThan (s2)) {
      System.out.println(s1 + " is the result of adding a single character to "
                            + s2);
    } else {
      System.out.println(s1 + " is not the result of adding a single character to "
                            + s2);
    }
  }
}