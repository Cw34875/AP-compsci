
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

import java.awt.Color;

public class MeaslesRunner
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        world.add(new Location(7, 8), new MeaslesCritter());
        world.add(new Location(3, 3), new MeaslesCritter());
        world.add(new Location(4, 4), new MeaslesCritter());
        world.add(new Location(4, 5), new MeaslesCritter());
        world.add(new Location(4, 6), new MeaslesCritter());
        world.add(new Location(5, 4), new MeaslesCritter());
        world.add(new Location(5, 6), new MeaslesCritter(1));
        world.add(new Location(5, 5), new MeaslesCritter());
        world.add(new Location(5, 8), new MeaslesCritter());
        world.add(new Location(4, 8), new MeaslesCritter());
        world.show();
    }
}