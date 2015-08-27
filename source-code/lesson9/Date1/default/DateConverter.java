/**
 * this should be a BLUEJ EXERCISE
 * For exercise_8.html
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
		//TEXT BOX:A1:6:70:3
		
		
		

        if (myMonthNumber == 2) {
          daysInMonth = 29;
        } else if (myMonthNumber == 4 || myMonthNumber == 6 
		|| myMonthNumber == 9 || myMonthNumber == 11) {
          daysInMonth = 30;
        } else {
          daysInMonth = 31;
        }

        // *** Here is one possible place to put assignment statements.
		//TEXT BOX:A1:6:70:3
		
		
		
		
      }

      myDateInMonth = dayOfYear;
	  
	  //HIDDEN CODE START
	  public string __TEST(){
		String[] test = new String[2];
		return test;
		
	  
	  
	  }
	  
	  
	  
	  //HIDDEN CODE END
	  
	  
	  
   }
}