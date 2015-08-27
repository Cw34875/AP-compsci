/**
 * this should be a BLUEJ EXERCISE exercise_8.html
 * 
 * DateConverter exercise
 * 
 */


public class DateConverter {

   private int myMonthNumber, myDayInMonth;

   public DateConverter (int dayOfYear) {
      myMonthNumber = 1;
      daysInMonth = 31;
      while (dayOfYear > daysInMonth) {

         // *** Here is one possible place to put assignment statements.

         if (myMonthNumber == 2) {
            daysInMonth = 29;
         } else if (myMonthNumber == 4 || myMonthNumber == 6 
               || myMonthNumber == 9 || myMonthNumber == 11) {
            daysInMonth = 30;
         } else {
            daysInMonth = 31;
         }

         // *** Here is one possible place to put assignment statements.


      }

      myDateInMonth = dayOfYear;

   }
}