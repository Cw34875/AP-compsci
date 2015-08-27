
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

import java.awt.Color;

public class SquidRunner
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        world.add(new Location(7, 8), new SharkCritter());
        world.add(new Location(3, 3), new SharkCritter());
        world.add(new Location(3, 8), new SharkCritter());
        world.add(new Location(4, 4), new Critter());
        world.add(new Location(4, 5), new Critter());
        world.add(new Location(4, 6), new Critter());
        world.add(new Location(5, 4), new Critter());
        world.add(new Location(5, 6), new Critter());
        world.add(new Location(5, 5), new SquidCritter());
        world.add(new Location(5, 8), new Critter());
        world.add(new Location(4, 8), new SquidCritter());
        world.show();
    }
}