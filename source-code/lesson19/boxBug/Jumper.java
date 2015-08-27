import info.gridworld.actor.Bug;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

public class Jumper extends Bug
{
    // instance variables - replace the example below with your own
    public Jumper()
    {
    }
    
    public void move(){
        Grid<Actor> gr = getGrid();
        if(gr == null)
            return;
        Location loc = getLocation();
        Location oneAway = loc.getAdjacentLocation(getDirection());
        Location twoAway = oneAway.getAdjacentLocation(getDirection());
        if(gr.isValid(twoAway))
            moveTo(twoAway);
        else    
            super.move();
    }
    
    public boolean canMove(){
        Grid<Actor> gr = getGrid();
        if(gr == null)
            return false;
        Location loc = getLocation();
        Location oneAway = loc.getAdjacentLocation(getDirection());
        Location twoAway = oneAway.getAdjacentLocation(getDirection());
        if(!gr.isValid(twoAway) && !gr.isValid(oneAway))
            return false;
        
        Actor twoAct = gr.get(twoAway);
        if(twoAct == null || twoAct instanceof Flower)
            return true;
        else
            return super.canMove();
            
    }
    
}
