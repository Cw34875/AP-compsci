import java.awt.Color;

/**
 * Abstract class ComputerPlayer
 * This class represents a player that can calculate its own move.  
 * This needs to be subclasses (and a getMove() method written) in order
 * to be used
 */
public abstract class ComputerPlayer extends Player {

    public ComputerPlayer(String name, Color color) {
        super(name, color);
    }

    
    // to be overridden
    public abstract Move getMove(Board board) ;

}
