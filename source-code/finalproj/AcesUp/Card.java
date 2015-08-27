import java.awt.*;
import javax.swing.*;


public class Card {

    private Image image;
    private int suit;
    private int value;

    public static final int CLUBS = 1;
    public static final int SPADES = 2;
    public static final int HEARTS = 3;
    public static final int DIAMONDS = 4;

    public Card(int suit, int value) {
        this.suit = suit;
        this.value = value;

        String filename = "";
        switch(suit){

        case Card.CLUBS: filename += "c";
        break;
        case Card.SPADES: filename += "s";
        break;
        case Card.HEARTS: filename += "h";
        break;
        case Card.DIAMONDS: filename += "d";
        break;
        }

        switch(value) {
        case 13: filename += "k";
        break;
        case 12: filename += "q";
        break;
        case 11: filename += "j";
        break;
        default:
            filename += value+"";
        }
        String fileName = "cards/" + filename + ".gif";

        // This loads the image from filename.
        image = new ImageIcon(fileName).getImage();
    }

    public Image getImage() {
        return image;
    }
    
    public void drawImage(JPanel jp, Graphics g, int x, int y) {
        g.drawImage(image, x, y, jp);
    }

} // end Card classs
