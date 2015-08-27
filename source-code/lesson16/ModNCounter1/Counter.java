
/*
 * An implementation of the Counter class.
 */

 public class Counter{
    
    //myValue keeps track of the Counter's current value.
	private int myValue;
	
	//adds 1 to Counter's value
	public void increment(){
	myValue=myValue+1;
	}
	
	//resets Counter's value to 0
	public void reset(){
	myValue=0;
	}
	
	//returns Counter's value
	public int value(){
	return myValue;
	}
	


 
 }