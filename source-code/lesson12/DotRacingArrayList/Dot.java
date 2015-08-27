import java.awt.*;

public class Dot {

    // how far this dot has run    
    private int distance;

    private int diameter;
    private int racerNumber;
    private Color color;
    private String name;
    
    public Dot(String name, int racerNumber, Color color, int diameter) {
        this.name = name;
        this.color = color;
        this.racerNumber = racerNumber;
        this.diameter = diameter;
        this.distance = 0;
    }

    // Note: all of this classes instance variables are private.  The only way for 
    //  other classes to get their value is to use the 'accessor' -- the method that
    //  returns that instance variables value.  
    
    public int getDistance () {
        return (distance);
    }
    
    public void moved(int n) {
        distance = distance + n;
    }
    
    public int getDiameter() {
        return (diameter);
    }
    
    public int getRacerNumber() {
        return(racerNumber);
    }
    
    public Color getColor() {
        return(color);
    }
    
    public String getName() {
        return(name);
    }




}