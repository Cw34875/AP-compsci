import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.util.ArrayList;
import java.awt.Color;

public class SquidCritter extends Critter
{
  private boolean danger;

  public SquidCritter()
  {
    setColor(Color.pink);
    danger = false;
  }

  public ArrayList<Actor> getActors()
  {
    ArrayList<Actor> actors = new ArrayList<Actor>();

    int dir = getDirection();
    Actor nbr = getActorInDir(dir);
    if(nbr != null && !(nbr instanceof Rock) && !(nbr instanceof Flower))
      actors.add(nbr);

    nbr = getActorInDir(dir + Location.HALF_RIGHT);
    if(nbr != null && !(nbr instanceof Rock) && !(nbr instanceof Flower))
      actors.add(nbr);

    nbr = getActorInDir(dir + Location.HALF_LEFT);
    if(nbr != null && !(nbr instanceof Rock) && !(nbr instanceof Flower))
      actors.add(nbr);

    return actors;
  }

  private Actor getActorInDir(int dir)
  {
    Grid<Actor> grd = getGrid();
    Location loc = getLocation().getAdjacentLocation(dir);
    if(grd.isValid(loc))
      return grd.get(loc);
    else
      return null;
  }

  public void processActors(ArrayList<Actor> actors)
  {
    for(Actor it: actors)
      if(it instanceof SharkCritter)
      {
        danger = true;
        return;
      }

    if(actors.size() > 0)
    {
      int choice = (int)(actors.size() * Math.random());
      actors.get(choice).removeSelfFromGrid();
    }
  }

  public ArrayList<Location> getMoveLocations()
  {
    Grid<Actor> grd = getGrid();
    ArrayList<Location> locs = new ArrayList<Location>();
    int dir = getDirection();

    if(danger)
      dir = dir + Location.HALF_CIRCLE;

    Location nbrLoc = getLocation().getAdjacentLocation(dir);
    if(grd.isValid(nbrLoc) && grd.get(nbrLoc) == null)
      locs.add(nbrLoc);

    nbrLoc = getLocation().getAdjacentLocation(dir+ Location.HALF_RIGHT);
    if(grd.isValid(nbrLoc) && grd.get(nbrLoc) == null)
      locs.add(nbrLoc);

    nbrLoc = getLocation().getAdjacentLocation(dir+ Location.HALF_LEFT);
    if(grd.isValid(nbrLoc) && grd.get(nbrLoc) == null)
      locs.add(nbrLoc);

    return locs;
  }

  public void makeMove(Location loc)
  {
    if(danger && loc != getLocation())
    {
      Location prevLoc = getLocation();
      super.makeMove(loc);
      InkCloud ink = new InkCloud();
      ink.putSelfInGrid(getGrid(), prevLoc);
      danger = false;
    }
    else if(loc != getLocation())
    {
      setDirection(getLocation().getDirectionToward(loc));
      super.makeMove(loc);
    }
  }

}

