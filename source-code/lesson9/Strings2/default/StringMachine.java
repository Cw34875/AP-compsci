/**
 * BLUEJ EXERCISE
 * ASSERTION:A1:allBalanced:Make sure braces and parentheses are balanced!
 * TESTSCHEME:__TEST
 * Write the method copies that returns a string formed from concatenating n copies of s
 * 
 */

public class StringMachine{

	public String copies (String s, int n) {
   //TEXT BOX:A1:8:70:5
	                                                                               
	                                                                               
	                                                                               
	                                                                               
	                                                                               
	}

 
 //HIDDEN CODE START
	public Object[] __TEST(){
		Object test[]= new Object[2];
		StringMachine machine = new StringMachine();
		
		//test cases from web
		test[0] = "false";
		if(!machine.copies("", 5).equals("")) {
		  test[1] = "What happens if the string is empty? (s = \"\")";
		} else if(!machine.copies("abc", 0).equals("")) {
		  test[1] = "What happens if n = 0?";
		} else {
			if(machine.copies("xy", 3).equals("xyxyxy") && machine.copies("hi", 1).equals("hi")) {
				test[0] = "true";
				test[1] = "Good job!";
			} else {
				test[0] = "false";
				test[1] = "Hmm something isn't working correctly...";
			}
		}
		return test; 
	}
 //HIDDEN CODE END
 
 
 }