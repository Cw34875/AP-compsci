public class Interval {

   private int myLeft;
   private int myRight;
   
   public Interval (int left, int right) {
      myLeft = left;
      myRight = right;
   }
   
   // Return the sum of the integers represented by this interval.
   public int sum ( ) {
      return sumHelper (myLeft, myRight);
   }
   
   private int sumHelper (int left, int right) {
      if (left > right) {
         return 0;
      } else {
         int mid = (left+right)/2;
         return sumHelper (left, mid-1) + mid + sumHelper (mid+1, right);
      }
   }
}