import java.awt.Color;

/**
 * Player class.  
 * Represents players who get their input from a listener (i.e., a users click)
 */
public class Player {

	private String name;
	private Color color;
	private int numBombs = 2;
    
    
    public Player (String name, Color color) {
        this.name = name;
        this.color = color;
    }
    
    public String getName() {
        return name;
    }
    
    public Color getColor() {
        return color;
    }
    
    
    public boolean hasBomb () {
        return (numBombs != 0);
    }
    

    // this method will generate an error -- an exception! -- if there are no bombs.
    public void useBomb() throws Exception {
        if (numBombs == 0) {
            throw new Exception("Player " + name + " doesn't have any bombs to use!");
        } else {
            numBombs -= 1;
        }
    }

} // end Player class
