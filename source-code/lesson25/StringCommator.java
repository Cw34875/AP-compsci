
public class StringCommator
{
   public static String withCommas (int n) {
      if (n == 0) {
         return "0";
      } else {
         return withCommasHelper (n);
      }
   }
   
   // n is greater than 0.  
   // Return its printed representation with commas.
   private static String withCommasHelper (int n) {
      if( n < 1000 )   
        return n + "";
      else
        return withCommasHelper(n/1000) + "," + padded(n%1000);
   }
   
   // Return the result of converting the argument, a nonnegative integer,
   // to a string with three digits (leading zeroes are added to the front
   // if necessary).
   private static String padded (int n) {
      if (n < 10) {
         return "00" + n;
      } else if (n < 100) {
         return "0" + n;
      } else {
         return "" + n;
      }
   }

}
