 
import java.awt.Color;
import TurtleGraphics.*;

public class Turtle {

    private Pen myTurtle; 
    private MyPad myPad;
    private double totalDistance = 0;

    // constructor (
    public Turtle() {
        myPad = new MyPad(300, 300,  new SketchPad());
        myTurtle = new StandardPen(myPad);

    }

    // constructor (with size)
    public Turtle(int width, int height) {
        myPad = new MyPad(width, height, new SketchPad());  
        myTurtle = new StandardPen(myPad);
    }
    
    
    ///// pen methods

    // Raises the turtle's pen, so no drawing will occur with movement
    public void penUp() {
        myTurtle.up();
    }

    // Lowers the turtle's pen, so that it will leave a trail as it moves
    public void penDown() {
        myTurtle.down();
    }

    // Sets the color of the turtle's pen
    public void penColor(Color c) {
        myTurtle.setColor(c);
    }

    // Sets the width (in pixels) of the turtle's pen
    public void penWidth(int width) {
        myTurtle.setWidth(width);
    }

    
    ///// movement methods
    
    // Turns the turtle a certainly number of degrees. Positive numbers turn
    //  the turtle to the right; negative turn it to the left.
    public void turn(double degrees) {
        myTurtle.turn(degrees);
    }

    // Sets the turtles heading to an absolute point on the compass
    public void setDirection(double degrees) {
        myTurtle.setDirection(degrees);
    }
    
    // Moves the turtle forward a certain amount.  If the pen is down, 
    // drawing will occur
    public void move(double distance) {
        myTurtle.move(distance);
        totalDistance += distance;
    }

    // Moves the turtle to a particular pixel in the drawing area
    public void moveTo(int x, int y) {
        myTurtle.move(x, y);
    }
    
    
    ///// miscellaneous methods

    // Sends the turtle back to the starting position, with no drawing
    public void reset() {
        myTurtle.home();
    }

    // Erases the graphics, and removes the record of all the commands sent to the turtle
    public void erase() {
        myPad.reset();
        myTurtle = new StandardPen(myPad);
    }
    
    // Returns the total distance that the turtle has covered
    public double totalDistance() {
        return totalDistance;
    }
    
    
    // stops the program for a set amount of milliseconds (one thousanths of a second).
    // Don't worry about the implementation of this.
    public void delay(int millsecs) {
        try {
            Thread.sleep(millsecs);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    
    // This is an "inner class": this class (MyPad) is available only to 
    //  the class Turtle.  We could have included a separate file, but that
    //  would have cluttered things up.
    public class MyPad extends SketchPadWindow {
    
    	public MyPad(int width, int height) {
    		super(width, height);
    	}
    	
    	
    	public MyPad(int width, int height, SketchPad pad) {
    		super(width, height, pad);
    	}
    	
    	public void reset() {
    		this.remove(pad);
    		this.pad = new SketchPad();
    		this.addPanel(pad, 1, 1, 1, 1);
    		validate();
    	}
    } // close MyPad class
    

} // close Turtle class
