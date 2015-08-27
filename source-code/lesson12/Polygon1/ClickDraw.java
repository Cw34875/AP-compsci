import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

// This program accepts clicks and draws them connected by lines.
public class ClickDraw extends JPanel implements MouseListener {
    private ArrayList<Point> points = new ArrayList<Point>();
    private int myWindowWidth = 700;
    private int myWindowHeight = 500;
    // a) Declaration of an ArrayList of Point objects goes here.
    private int countClose = 0;

    public ClickDraw ( ) {
        // a) Initialization of an ArrayList of Point objects goes here.
        JFrame easel = new JFrame();      
        easel.setSize (myWindowWidth, myWindowHeight);
        easel.add (this);
        addMouseListener (this);
        easel.setVisible (true);
    }
    
    public void mouseClicked (MouseEvent event) {
        Point p = new Point (event.getX ( ), event.getY ( ));
        if(!clearPoints(p)){
        // b) Adding the point to the ArrayList goes here.
            points.add(p);
        }
        repaint ( );
    }
    
    private boolean closeTo (int x1, int y1, int x2, int y2) {
        return Math.abs (x1-x2) < 3 && Math.abs (y1-y2) < 3;
    }
       
    public void paintComponent (Graphics g) {
        // The following 3 lines erase the display, ...
        g.setColor (Color.white);
        g.fillRect (0, 0, myWindowWidth, myWindowHeight);
        g.setColor (Color.black);
        // c) then redraw it (code goes here).
        int x = 0;
        while(x < points.size()){
            Point p = points.get(x);
            g.fillOval (p.x-5, p.y-5, 10, 10);
            x++;
        }
    }
    
    private boolean clearPoints(Point p)
    {
        // d) This method should remove all the points from the ArrayList
        //    if the user clicks the same point(or closeTo) 3 times
        //    It should return true if the points were removed, otherwise false
        int x = 0;
        if(points.size() == 0)
            points.add(p);
        
        while(x < points.size())
        {
            Point temp = points.get(x);
            if(closeTo(temp.x, temp.y, p.x, p.y)){
                   this.countClose++;
                   if(this.countClose == 3){
                        points.clear();
                        return true;
                    }
            }
        }
        return false;
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
