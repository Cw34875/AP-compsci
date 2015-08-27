
/**
 * This is a move to place a bomb.  There is no added functionality beyond Move.
 */
public class BombMove extends Move {

    // We need to specify this, since the constructor takes an argument
    public BombMove (int column, Player player) {
        super(column, player);
    }
}
