
/**
 * Write a description of class LBug here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LBug extends BoxBug
{
    private int direction = 1; //used in the act method
    
    public LBug(int sideLength){super(sideLength);}
    
    public void act()
    {
        if (steps < sideLength && canMove())
        {
            move();
            steps++;
        }
        else
        {
            setDirection(getDirection() + (direction * 90));
            direction = direction * -1;
            steps = 0;

        }
    }
}

   

