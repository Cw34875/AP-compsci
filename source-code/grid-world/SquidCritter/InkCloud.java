import info.gridworld.actor.Actor;
import info.gridworld.actor.Rock;

public class InkCloud extends Rock
{
  public void act()
  {
    removeSelfFromGrid();
  }
}