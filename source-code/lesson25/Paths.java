public class Paths{

    public Paths(int n, int k){
        int one = this.pathCount1(n,k);
        int two = this.pathCount2(n,k);
        System.out.println(one + " : " + two);
    }
    
   public int pathCount1 (int n, int k) {
    if (n==0 || k==0) {
        return 1;
    } else if (n<0 || k<0) {
        return 0;
    } else {
      return pathCount1(n-1,k)  + pathCount1(n,k-1);
   }
}

public int pathCount2 (int n, int k) {
   if (n==0 && k==0) {
      return 1;
   } else if (n<0 || k<0) {
      return 0;
   } else {
      return pathCount2(n-1,k) + pathCount2(n,k-1);
   }
}
}
