/**
 * BLUEJ EXERCISE
 * ASSERTION:A1:allBalanced:Make sure braces and parentheses are balanced!
 * TESTSCHEME:__TEST
 * For string_3.html
 * 
 * Write the zip method to return a string of interweaved s1 and s2
 * 
 */

 public class StringMachine{


public String zip(String s1, String s2) {
	//TEXT BOX:A1:8:70:5
	                                                                               
	                                                                               
	                                                                               
	                                                                               
	                                                                               
   
}

 
//HIDDEN CODE START
 public String[] __TEST(){
 
	String test[]= new String[2];
	StringMachine machine = new StringMachine();
	String s1 = "CLFRI";
	String s2 = "aiona";
	String output = "";
	for(int i=0; i<s1.length(); i++) {
		output += s1.substring(i,i+1) + s2.substring(i,i+1);
	}
	if(output.equals(machine.zip(s1, s2))) {
	  test[0] = "true";
	} else {
		test[0] = "false";
		test[1] = "When s1 = \"CLFRI\" and s2 = \"aiona\", the output currently is " + "\"" + machine.zip(s1, s2) + "\"";
	}
	return test; 
 }
//HIDDEN CODE END
 
 
 }