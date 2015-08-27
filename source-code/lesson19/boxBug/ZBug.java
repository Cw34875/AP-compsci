import info.gridworld.actor.Bug;
import info.gridworld.grid.Location;

public class ZBug extends Bug
{
    private int steps;
    private int sideLength;
    private boolean drawingTop = true;
    private boolean drawingAngle = false;
    private boolean drawingBottom = false;
    private boolean done = false;
    
    public ZBug(int length)
    {
        super(); //optional.... why?
        sideLength = length;
        setDirection(Location.EAST);
    }
   
    public void switchSides(){
        if(drawingTop){
            drawingTop = false;
            drawingAngle = true;
        }else if(drawingAngle){
            drawingAngle = false;
            drawingBottom = true;
        }else if(drawingBottom){
            drawingBottom =false;
            done = true;
        }
    }
    
    public void setAngle(){
        if(drawingTop)
            setDirection(Location.EAST);
        else if(drawingAngle)
            setDirection(Location.SOUTHWEST);
        else            
            setDirection(Location.EAST);
    }
    
    public void act()
    {
        if (!done && steps < sideLength && canMove())
        {
            move();
            steps++;
        }else if(!canMove()){
            //do nothing
        }
        else
        {


            
            Location loc = new Location(5,4);
            int dir = loc.getDirectionToward(new Location(2,5));
            System.out.println(dir);
            switchSides();
            setAngle();
            steps = 0;
        }
    }

}
