import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;



public class AceUp extends JPanel {

    private Deck myDeck;
    private JFrame frame;
    private JPanel cardPanel;

    private static final int width = 1000;
    private static final int height = 500;
    
    public AceUp() {
        super();
        myDeck = new Deck();
        setUp();
    }


    public void setUp() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.white);
        frame.setSize(width, height);

        this.setPreferredSize(new Dimension(width, height));
        frame.add(this);
        frame.setVisible(true);
    }

    
    public void play() {
        // doesn't do anything right now
    }


   
    


    public void paintComponent(Graphics g) {
        // currently just draws all the cards...
        drawAllCards(g);
    }

    public void drawAllCards(Graphics g) {
        myDeck.refill();
        for (int j = 1; j <= 4; j++) {
            for (int i = 1; i <= 13; i++ ) {
                Card c = myDeck.drawOne();
                int width = c.getImage().getWidth(this);
                int height = c.getImage().getHeight(this);
                c.drawImage(this, g, (i-1)*width, (j-1)*height);
                //g.drawImage(img, 0, 0, this);
                //g.drawImage(img,(i-1)*img.getWidth(this),(j-1)*img.getHeight(this),this);
            }
        }        
    }


    public static void main(String[] args) {
        AceUp game = new AceUp();
    }

}
