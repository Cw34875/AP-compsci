public class Measurement implements Comparable<Measurement>{

   // how many inches long this Measurement is
   private int myInches;
     
   // Constructor: no arguments, so make a measurement of 0 length. 
   public Measurement () {
     myInches = 0;
   }

   // Constructor: create a measurement of ft feet. 
   public Measurement (int ft) {
      // convert to inches
      myInches = 12 * ft;
   }

   // Constructor: create a measurement of ft feet, in inches. 
   public Measurement (int ft, int in) {
      // convert to inches
      myInches = 12*ft + in;
   }

   // returns a new Measurement with the summed length of this and m
   public Measurement plus (Measurement m) {
      return new Measurement (0, myInches + m.myInches);
   }

   // returns a new Measurement with length of current - m, assuming m is less than this.myInches. 
   public Measurement minus (Measurement m) {
      return new Measurement (0, myInches - m.myInches);
   }

   // returns a new Measurement representing n times current. 
   public Measurement multiple (int n) {
      return new Measurement (0, myInches * n);
   }
   
   // represent a measurement in feet and inches notation
   public String toString () {
      return "" + (myInches/12) + "'" + (myInches%12) + "''";
   }

   
   
   public boolean equals (Object m) {
      System.out.println("I am in here");
      return this.toString( ).equals(m.toString ( ));
   }
   
   public int compareTo(Measurement m){
       if(this.myInches < m.myInches)
            return 1;
       else if(this.myInches > m.myInches)
            return -1;
       return 0;
   }

}