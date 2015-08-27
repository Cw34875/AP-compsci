import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Random;

/**
 * Tower of Hanoi
 */
public class Board extends JPanel {

    private int numDisks = 7;

    private Peg right;
    private Peg middle;
    private Peg left;
    
    // Settings
    private Color background = Color.lightGray;
    private int height = 400;
    private int width = 600;
    private int border = 50;
    private int pegSpacing = 25;
    
    
    public Board()
    {
        initialize();
    }
    
    public Board(int numDisks) {
        this.numDisks = numDisks;
        initialize();
    }
    
    private void initialize() {
        int pegHeight = height - (2 * border);
        int pegWidth = (width - (2 * border) - (2 * pegSpacing)) / 3;
        left = new Peg(pegWidth, pegHeight, (border + (0 * (pegSpacing + pegWidth))), border, numDisks);
        middle = new Peg(pegWidth, pegHeight, (border + (1 * (pegSpacing + pegWidth))), border, numDisks);
        right = new Peg(pegWidth, pegHeight, (border + (2 * (pegSpacing + pegWidth))), border, numDisks);
        
        resetDisks();     
        
        JFrame myFrame = new JFrame("Tower of Hanoi");      
        myFrame.setSize(width, height);
        myFrame.add(this);
        myFrame.setVisible(true);
    }
    
    public void resetDisks() {
        left.removeAll();
        for (int i = numDisks; i > 0; i--) {
            left.place(new Disk(i, numDisks));
        }
        middle.removeAll();
        right.removeAll();
        this.repaint();
    }
    
    public void randomizeDisks() {
        left.removeAll();
        middle.removeAll();
        right.removeAll();
        Peg pegs[] = {left, middle, right};
        Random r = new Random();
        for (int i = numDisks; i > 0; i--) {
            Peg p = pegs[r.nextInt(3)];
            p.place(new Disk(i, numDisks));
        }
        this.repaint();
    }
    
    public void paintComponent(Graphics g) {
        g.setColor(background);
        g.fillRect(0,0,getWidth(), getHeight());
        left.draw(g);
        middle.draw(g);
        right.draw(g);
    }
    
    /////////////////
    
    public boolean canMoveDisk(Peg from, Peg to) {
        Disk d = from.getTop();
        return ((d != null) && (to.canPlace(d)));
    }
    
    
    public boolean moveDisk(Peg from, Peg to) {
        if (canMoveDisk(from, to)) {
            Disk d = from.removeTop();
            // gotta graphically show the slide!
            to.place(d);
            this.repaint();
            delay(10);   // in case we do lots of moves at once?
            return true;
        } else {
            return false;
        }
    }
    
    
    public Peg getPegWith(int size) {
        if (left.getTop().isSize(size)) { 
            return left;
        } else if (middle.getTop().isSize(size)) {
            return middle;
        } else if (right.getTop().isSize(size)) {
            return right;
        } else {
            return null;
        }
    }
    
    
    public void resetAndSolve() {
        solveRecursive(numDisks, left, right, middle);
    }
    
    private void solveRecursive(int num, Peg from, Peg to, Peg with) {
        if (num >= 1) {
            solveRecursive(num - 1, from, with, to);
            moveDisk(from, to);
            solveRecursive(num - 1, with, to, from);
        }
    }
    
    
    
    
    
    
    
    ///////////////
    
    
    private void delay(int ms) {
        try {
            Thread.sleep (ms);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
