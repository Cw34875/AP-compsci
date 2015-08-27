import java.awt.*;

public class Eye {

    // if you have a lot of instance variables of the same type, you can declare them all at
    //  once by using commas.  You can't set default values, though!
    private int eyeX, eyeY, eyeWidth, eyeHeight, eyeCenterX, eyeCenterY, pupilX, pupilY;
    private int eyeballBorder = 3;
    private int pupilRadius = 10;
    private double horDist, vertDist;


    public Eye(int x, int y, int width, int height) {
        // top left of this eye
        this.eyeX = x;
        this.eyeY = y;
        // dimensions of this eye
        this.eyeWidth = width;
        this.eyeHeight = height;
        // first calculate the position of the center of the eye...
        int centerX = (int) (x + (0.5 * width));
        int centerY = (int) (y + (0.5 * height));
        // these stay as the center of the eye
        this.eyeCenterX = centerX;
        this.eyeCenterY = centerY;
        // these keep the current position of the pupil
        this.pupilX = centerX;
        this.pupilY = centerY;
        
        this.pupilRadius = (int) 1 + ((width + height) / 12);
        
        // these stay as the maximum distance the pupil will be positioned 
        // from the eye center.  This can put the pupil out near the rim of the eye
        // (by subtracting pupilRadius, the pupil can't go quite as far out).
        horDist = ( ((double) width) / 2 ) - pupilRadius;
        vertDist = ( ((double) height) / 2) - pupilRadius;
        
     
    }

    
    public void draw (Graphics g) {
        
        g.setColor(Color.black);
        g.fillOval (eyeX, eyeY, eyeWidth, eyeHeight);

        g.setColor(Color.white);
        g.fillOval (eyeX + eyeballBorder, eyeY + eyeballBorder, 
                    eyeWidth - (2 * eyeballBorder), eyeHeight - (2 * eyeballBorder));
                    
        g.setColor(Color.black);
        g.fillOval(pupilX - pupilRadius, pupilY - pupilRadius, 2 * pupilRadius, 2 * pupilRadius);
    }



    //////
    // MouseListener events
    //////
    
    
    // This sets pupilX and pupilY to the new pupil position, using actual mathematics
    //  learned in high school...
    public void movePupil(int clickX, int clickY) {
        // distance from click to eye center
        int deltaX = clickX - eyeCenterX;
        int deltaY = clickY - eyeCenterY;

        if (deltaX == 0) {
            //first check if the click was exactly aligned vertically with eye center
            // why?  In general we need to calculate a slope, and perfect vertical has an infinite
            // slope, which will cause an error.  So, this is a special case.
            pupilX = eyeCenterX;
            if (deltaY < 0) {
                // remember, vertDist is the maximum vertical distance the pupil will be from the eye center
                pupilY = (int) (eyeCenterY - vertDist);
            } else {
                pupilY = (int) (eyeCenterY + vertDist);
            }
        } else {
            // this is for all other clicks.  We use doubles for more precision.
            // remember, slope will be positive if the click was in the upper right or lower left
            // of the eye center, or negative otherwise.
            double slope = (( (double) deltaY ) / ( (double) deltaX ));
            // findX will calculate the horizonal position of the pupil, at most horDist away from eye center
            double precisePupilX = findX(slope, deltaX);
            pupilX = (int) precisePupilX;
            // findY will calculate the vertical posiiton of the pupil, at most vertDist away from eye center 
            pupilY = (int) findY(precisePupilX, slope);
        }
    }
    
    // findX calculates the new horizonal position of the pupil, at most horDist away from eye center
    public double findX (double slope, int deltaX) {
        double h = horDist;
        double v = vertDist;
        // offsetX will be between 0 and h.  If slope is 0, offset will be h.  (see if you 
        // can calculate that...)  As slope becomes very large, the offset will approach 0.
        // This formula is derived from the equation of an oval.
        double offsetX = Math.sqrt ( h*h * v*v / (v*v + h*h*slope*slope) );
        if (deltaX < 0) {
            // the click was to the left of eye center, so flip the sign
            offsetX = 0 - offsetX;
        }
        return (offsetX + eyeCenterX);
    }
    
    // findY calculates the new vertical positon of the pupil, at most vertDist away from eye center 
    public double findY (double pupX, double slope) {
        // remember your geometry?  y = mx + b?  woot!
        return ( (slope * (pupX - eyeCenterX)) + eyeCenterY );
    }
    
    
    




}  //end class