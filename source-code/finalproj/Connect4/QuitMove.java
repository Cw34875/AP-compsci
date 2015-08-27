
// this is the move the Player creates to admit that they are a quitting quitter.
public class QuitMove extends Move {

    // A place for the quitting quitter to get in one last taunt
    private String partingMessage;

    // constructor doesn't need a column, since they are quitting.
    public QuitMove(Player player, String message) {
        super(-1, player);
        this.partingMessage = message;
    }
}
