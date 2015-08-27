/**
 * BLUEJ EXERCISE
 * ASSERTION:A1:allBalanced:Make sure braces and parentheses are balanced!
 * TESTSCHEME: __TEST
 * For exercise_5.html
 * 
 * Write the reversed method, which returns a given integer argument with its digits reversed
 * 
 */

 
 public class NumberChanger{
 
 

public int reversed(int myNumber){
  //TEXT BOX:A1:6:70:12
  
  
  
  
  
  
  
 
 
 
 
}
 
 
 
 
  //HIDDEN CODE START
 
 public Object[] __TEST(){
 
 //test[1] is a BOOLEAN, tells if tests passed or failed, test[2] is the corresponding error string
 Object test[]= new Object[2];
 NumberChanger testChanger = new NumberChanger();

 int reversed1234= testChanger.reversed(1234);
 
 if (reversed1234 == 4321){
	test[1]=true;
	test[2]="Test passed! You correctly reversed 1234 into 4321!";
	}
 else{
	test[1]=false;
	test[2]="Test failed! Your reversed did not correctly reverse 1234 into 4321. Instead, you got " + reversed1234;
 }

 return test; 
 
 
 
 }
 //HIDDEN CODE END
 
 }