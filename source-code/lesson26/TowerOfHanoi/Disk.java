import java.awt.Graphics;
import java.awt.Color;

public class Disk
{
    // instance variables - replace the example below with your own
    private double proportion;
    private double maxSize;
    
    private Color myColor = Color.yellow;
    private int minWidth = 10;
    private int verticalSpacing = 2;
    private int rounding = 3;

    /**
     * Constructor for objects of class Disk
     */
    public Disk(int size, int maxSize) {
        this.maxSize = maxSize;
        this.proportion = (double)size / (double)maxSize;
    }
    
    public boolean canCover(Disk d) {
        return this.proportion < d.proportion;
    }
    
    public boolean isSize(int size) {
        return this.proportion == (double) size / maxSize;
    }
    
    public void draw(Graphics g, int boundingLeft, int boundingTop, int boundingWidth, int boundingHeight) {
        int diskWidth = (int) ((boundingWidth - minWidth) * proportion) + minWidth;
        if (diskWidth < minWidth) {
            diskWidth = minWidth;
        }
        int diskLeft = boundingLeft + ((boundingWidth - (int) diskWidth) / 2);
        g.setColor(myColor);
        g.fillRoundRect(diskLeft, boundingTop - verticalSpacing, diskWidth, boundingHeight - (2 * verticalSpacing), rounding, rounding);
    }
}
