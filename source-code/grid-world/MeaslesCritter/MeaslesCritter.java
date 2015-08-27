import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

import java.util.ArrayList;
import java.awt.Color;

public class MeaslesCritter extends Critter
{
  public int MAXTIMESICK = 5;

  private int timeSick;
  private boolean isImmune;

  public MeaslesCritter()
  {
    timeSick = 0;
    isImmune = false;
    setColor(Color.blue);
  }

  public MeaslesCritter(int time)
  {
    timeSick = time;
    isImmune = false;
    setColor(Color.blue);
    if(time > 0)
      setColor(Color.red);
  }

  public void processActors(ArrayList<Actor> actors)
  {
    if(timeSick > 0)
       for(Actor it: actors)
        if(it instanceof MeaslesCritter)
          ((MeaslesCritter)it).expose();
  }

  public void expose()
  { 
    if(!isImmune && timeSick == 0 && Math.random() < 0.5)  // probability 0.5 of getting measles when exposed
    {
      timeSick = 1;
      setColor(Color.red);
    }
  }

  public void makeMove(Location loc)
  {
    super.makeMove(loc);

    if(timeSick > 0)
      timeSick++;

    if(timeSick == MAXTIMESICK)
    {
      timeSick = 0;
      isImmune = true;
      setColor(Color.green);
    }
  }
}
