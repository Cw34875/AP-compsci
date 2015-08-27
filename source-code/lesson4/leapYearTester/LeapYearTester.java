
public class LeapYearTester {

   public LeapYearTester ( ) {
      // Testing whether year is correctly identified as a leap year
      int year = 2000;
      LeapYear myYear = new LeapYear(year);
      myYear.determineLeapYear();
      if (myYear.isLeapYear()) {
         System.out.println ("Fails for " + year + ", which is a leap year");
      }
      
      // your additional tests go here
      
      
      
      
   }
}
      