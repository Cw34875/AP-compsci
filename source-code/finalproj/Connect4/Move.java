
public class Move {

    private int column;
    private Player player;
    
    /**
     * Constructor for objects of class Move
     */
    public Move(int column, Player player) {
        this.column = column;
        this.player = player;
    }

    public int getColumn() {
        return this.column;
       
    }

    public Player getPlayer() {
        return this.player;
       
    }
}
