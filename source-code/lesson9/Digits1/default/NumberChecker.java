/**
 * BLUEJ EXERCISE
 * ASSERTION:A1:allBalanced:Make sure braces and parentheses are balanced!
 * TESTSCHEME: __TEST
 * For exercise_4.html
 * 
 * Returns the sum of all of the digits 
 * 
 */

 
 public class NumberChecker{
 
	public int digitSum(int myNumber){
  //TEXT BOX:A1:6:70:12
                                                                                
                                                                                
                                                                                
                                                                                
                                                                                
                                                                                
                                                                                
                                                                                
                                                                                
                                                                                
                                                                                
}                                                                               
 
 
 
 
  //HIDDEN CODE START
 
 public Object[] __TEST(){
 
 //test[1] is a BOOLEAN, tells if tests passed or failed, test[2] is the corresponding error string
 Object test[]= new Object[2];
 NumberChecker testChecker = new NumberChecker;
 int sumof1234=testChecker.digitSum(1234);
 if (sumof1234 == 10){
	test[1]=true;
	test[2]="Correct! Your digitSum correctly added the digits of 1234 to obtain 10";
 }else{
	test[1]=false;
	test[2]="Test failed! Your digitSum did not add the digits of 1234 correctly. Your answer was" + sumof1234;
}
 return test; 
 
 
 
 }
 //HIDDEN CODE END
 
 }