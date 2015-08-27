public class RemoveElements
{
    int[] myElements = new int[10];
    int myElemCount = 0;
    
    public RemoveElements(){
        for(int x = 0; x < myElements.length; x++)
        {
            myElemCount++;
            myElements[x] = x + 1;
        }
        System.out.println(this);
    }
    
    // Remove x from this set.
    public void remove (int x) {
        removeHelper (x, 0);
        System.out.println(this);
    }
    
    // Remove all occurrences of x from myElements[k] through 
    // myElements[myElemCount-1], decrementing myElemCount for each
    // value removed.
    private void removeHelper (int x, int k) {
        if (k >= myElemCount) {
            return;
        } else if (myElements[k] == x) {
            //move the last element to spot you
            //want to replace
            myElements[k] = myElements[myElemCount-1];
            //one less item in list
            myElemCount--;
            //recurse one more time!
            removeHelper(x, k);
        } else {
            removeHelper (x, k+1);
        }
    }
    
    public String toString(){
        String temp = "";
        for(int x = 0; x< myElements.length; x++)
        {
            temp += "," + myElements[x];
        }
        return temp;
    }
}
