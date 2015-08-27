// THIS CLASS HAS ... BUGS

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Guesser extends JPanel implements MouseListener {

    private int myLow, myHigh, myGuess, myGuessCount;
    private String myMsg;
    private int myWindowWidth = 400;
    private int myWindowHeight = 500;
    
    public Guesser (int n) {
        myLow = 1;
        myHigh = n;
        myGuess = (myLow + myHigh) / 2;
        myGuessCount = 1;
        myMsg = "Pick a number between 1 and " + n;
        
        JFrame myFrame = new JFrame();      
        myFrame.setSize (myWindowWidth, myWindowHeight);
        myFrame.add (this);
        this.addMouseListener (this);
        myFrame.setVisible(true);
    }

    public void mouseClicked (MouseEvent e) {
        int x = e.getX ( );
        int y = e.getY ( );
        if (x>=10 && x<110 && y>=100 && y<130) {
            myHigh = myGuess;
            myGuess = (myLow + myHigh) / 2;
            myMsg = "too big";
        } else if (x>=110 && x<210 && y>=100 && y<130) {
            myMsg = "Great!";
        } else if (x>=210 && x<310 && y>=100 && y<130) {
            myLow = myGuess;
            myGuess = (myLow + myHigh) / 2;
            myMsg = "too small";
        } else {
            myMsg = "Click ignored.";
        }
        System.out.println ("Click pos = (" + x + "," + y + ")");
		repaint ( );
	}
	
	public void paintComponent (Graphics g) {
	    g.setColor (Color.white);
	    g.fillRect (0, 0, myWindowWidth, myWindowHeight);
	    g.setColor (Color.black);
	    g.drawString ("My guess #" + myGuessCount + " is " + myGuess, 10, 80);
	    g.drawRect (10, 100, 100, 30);
	    g.drawString ("too big", 15, 125);
	    g.drawRect (110, 100, 100, 30);
	    g.drawString ("got it!", 115, 125);
	    g.drawRect (210, 100, 100, 30);
	    g.drawString ("too small", 215, 125);
	    g.drawString (myMsg, 10, 350);
	}
	
	public void mouseReleased (MouseEvent event) {
	}
	
	public void mousePressed (MouseEvent event) {
	}
	
	public void mouseEntered (MouseEvent event) {
	}
	
	public void mouseExited (MouseEvent event) {
	}
	
}
