import java.util.Stack;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.*;

public class Peg
{
    private Color myColor = Color.red;
    private int width = 5; 

    private Stack<Disk> myDisks = new Stack<Disk>();
    private int boundingWidth, boundingHeight, boundingTop, boundingLeft;
    private int numDisks;

    public Peg(int width, int height, int left, int top, int numDisks) {
        this.numDisks = numDisks;
        setBounds(width, height, left, top);
    }

    public void setBounds(int width, int height, int left, int top) {
        boundingWidth = width;
        boundingHeight = height;
        boundingLeft = left;
        boundingTop = top; 
    }
    
    
    public boolean canPlace(Disk d) {
        return (myDisks.empty()) || d.canCover(myDisks.peek());
    }
    
    public void place(Disk d) {
        if (canPlace(d)) {
            myDisks.push(d);
        }
    }
    
    public Disk getTop() {
        if (myDisks.empty()) {
            return null;
        } else {
            return (myDisks.peek());
        }
    }
    
    public Disk removeTop() {
        if (!myDisks.empty()) {
            return(myDisks.pop());
        } else {
            return null;
        }
    }
    
    public void removeAll() {
        myDisks.clear();
    }
    
    
    public void draw(Graphics g) {
        // fancy new Graphics type, could do a lot more....
        g.setColor(myColor);
        g.fillRect((boundingLeft + (boundingWidth / 2) - width), boundingTop, width, boundingHeight);
        int diskHeight = boundingHeight / numDisks;
        int diskBoundTop = boundingTop + boundingHeight - diskHeight;
        for (Disk d : myDisks) {
            // bottom to top, right?
            d.draw(g, boundingLeft, diskBoundTop, boundingWidth, diskHeight);
            diskBoundTop = diskBoundTop - diskHeight;
        }
    }
}
