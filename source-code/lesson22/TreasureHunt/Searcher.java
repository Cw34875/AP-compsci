public class Searcher {

   int [ ] values = {2, 4, 6, 8, 10, 12, 14, 16, 18, 20};
   
   public Searcher ( ) {
   }
   
   public boolean contains (int k) {
      return helper (k, 0, values.length-1);
   }
   
   private boolean helper (int x, int low, int high) {
      while (low < high) {
         int mid = (low + high) / 2;
         if (x > values[mid]) {
            low = mid + 1;
         } else {
            high = mid -1 ;
         }
      }
      return values[low] == x;
   }
}