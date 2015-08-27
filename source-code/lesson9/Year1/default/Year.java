/**
 * BLUEJ EXERCISE
 * ASSERTION:A1:allBalanced:Make sure braces and parentheses are balanced!
 * TESTSCHEME: __TEST
 * For exercise_3.html
 * 
 * Write a nextLeapYear method that, given an int year, returns the next highest leap year. For example, nextLeapYear(2005) would return 2008
 * 
 */

 public class Year{
 
 
 public int nextLeapYear(int year){
 //TEXT BOX:A1:6:70:12
 
 
 
 
 
 
 
 
 
 
 
 
 }
 
 //HIDDEN CODE START
 
 public Object[] __TEST(){
 
 //test[1] is a BOOLEAN, tells if tests passed or failed, test[2] is the corresponding error string
 Object test[]= new Object[2];
 
 Year yearChecker = new Year();
 int leapAfter2000 = yearChecker.nextLeapYear(2000);
 int leapAfter1992 = yearChecker.nextLeapYear(1992);
 int leapAfter2001 = yearChecker.nextLeapYear(2001); 
 
 if (leapAfter2000==2004){
	if (leapAfter1992 == 1996){
		if (leapAfter2001 == 2004){
			test[1]=true;
			test[2]="All tests passed!"
		}
		else{
			test[1]=false;
			test[2]="Test failed! You incorrectly said the leap year after 2001 is " + leapAfter2001
		}
	}
	else{
		test[1]=false;
		test[2]="Test failed! You incorrectly said the leap year after 1992 is " + leapAfter1992
	}
}
else{
	test[1]=false;
	test[2]="Test failed! You incorrectly said the leap year after 2000 is " + leapAfter2000
}
 
 return test; 
 
 
 
 }
 //HIDDEN CODE END
 
 
 
 
 }