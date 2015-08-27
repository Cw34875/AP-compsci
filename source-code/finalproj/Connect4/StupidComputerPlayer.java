import java.awt.Color;
import java.util.Random;

/*
 * Class StupidComputerPlayer
 * This player generates its moves randomly
 */ 

public class StupidComputerPlayer extends ComputerPlayer {

    public StupidComputerPlayer(String name, Color color) {
        super(name, color);
    }
    
    private Random randGen = new Random();
    
    public Move getMove(Board board) {
        // right now, the computer just chooses randomly.
        return( new Move(randGen.nextInt(board.getCols()), this) );
    }
    

}
