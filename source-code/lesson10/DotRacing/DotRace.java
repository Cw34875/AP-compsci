import java.awt.*;
import javax.swing.*;
import java.util.Random;

public class DotRace extends JPanel {

    private JFrame myFrame;
    private Random randGen;

    // the length in pixels of the track
    int trackLength = 300;
    int startLine = 50;
    // number of milliseconds (one thousanth of a second) to pause between each cycle
    // slower computers might lower this number...
    int pauseTime = 50;
    Dot winnaWinnaWinna;              // who has won (or null if nobody)
    int winnaEffectDiameter;          // for the special effects at end
    int defaultDiameter = 70;         // default diameter for the dot
    
        
    Dot racer0 = new Dot("Fred", 0, Color.red, defaultDiameter);
    Dot racer1 = new Dot("Maria", 1, Color.green, defaultDiameter);
    Dot racer2 = new Dot("Darren", 2, Color.orange, defaultDiameter);
    int racerCount = 3;
    

    int topBorder = 10;           // allow for the menu bar at the top of the frame
    int border = 10;              // the border around the dots
    int sideBorder = startLine;   // space after the finishLine
    int windowWidth = trackLength + startLine + 50;
    int windowHeight = topBorder + (racerCount * (defaultDiameter + border));
    
    
    // constructor
    public DotRace() {
        myFrame = new JFrame("Dot Racing!");
        myFrame.add(this);
        myFrame.setSize(windowWidth, windowHeight); 
        myFrame.setVisible(true);
        randGen = new Random();
        
        runRace();
    }

    
    
    public void runRace ( ) {
        Dot mover;
        int distance;

        winnaWinnaWinna = null;
        while (winnaWinnaWinna == null) {
            mover = whoMoves();
            distance = howFar();
            mover.moved(distance);
            if (mover.getDistance() + mover.getDiameter() == trackLength + border) {
                winnaWinnaWinna = mover;
            }
            repaint();
            delay(pauseTime);
        }
    }

    
    // This method returns the 'Dot' (either racer0, racer1, or racer2)
    // whose turn it is to move.
    // finish this
    private Dot whoMoves() {
           
    }
    
    
    //  This method returns the number of pixels that the dot moves
    // finish this
    private int howFar() {

    }
    

    
    
    // Draw the dots in raceTrack.
    // We keep track of who just moved to give an animation effect.
    // Also when there's a winner, we circle the dot that won.
    public void paintComponent(Graphics g) {
        super.repaint();    // don't worry about this right now -- it 'clears the screen', though.
        
        g.setColor(Color.white);
        g.fillRect(0,0,windowWidth,windowHeight);              // draw a nice white background
        g.setColor(Color.black);
        g.drawLine(startLine, 0, startLine, windowHeight);     // draw a starting line
        g.drawLine(startLine+trackLength, 0, startLine+trackLength, windowHeight); // finish

        drawRacer(racer0, g);
        drawRacer(racer1, g);
        drawRacer(racer2, g);
    }
 
    
    public void drawRacer(Dot racer, Graphics g) {
        int topX = racer.getDistance() + border + startLine;
        int topY = topBorder + ( racer.getRacerNumber() * defaultDiameter ) + border;
        int drawDiam = racer.getDiameter() - (2 * border);
        g.setColor(racer.getColor());
        g.fillOval(topX, topY, drawDiam, drawDiam);
        g.setColor(Color.black);
        g.drawOval(topX, topY, drawDiam, drawDiam);
    }



    
    
    // This method causes your program to pause for a certain number of milliseconds
    // Don't worry about the details of the class Thread, the weird 'try/catch' keywords,
    //  we won't cover them in this course.
    private void delay(int ms) {
        try {
            Thread.sleep (ms);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    
}  // end DotRace
