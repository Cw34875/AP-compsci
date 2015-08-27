import Helpers.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Random;


public class GamePiece implements Piece
{
    private int row;
    private int col;
    private BoardPanel panel;
    private BufferedImage img;
    private int direction;
    
    private static final int UP = 0;
    private static final int DOWN = 1;
    private static final int LEFT = 2;
    private static final int RIGHT = 3;

    public GamePiece()
    {
        row = 0;
        col = 0;
        createImage();
    }
  
    public int getRow()
    {
        return row;
    }
    
    public int getCol()
    {
        return col;
    }
    

    public void moveTo(int row, int col)
    {
        this.row = row;
        this.col = col;
        panel.repaint();
    }
    
    public void moveLeft() {
        direction = LEFT;
        if (this.col > 0)
            col--;
      
        panel.repaint();
    }
    
    public void moveRight() {
        direction = RIGHT;
        if (this.col < panel.GRID-1)
            col++;
        panel.repaint();

    }
    
    public void moveUp() {
        direction = UP;
        if (this.row > 0)
            row--;
        panel.repaint();

    }
    
    public void moveDown() {
        direction = DOWN;
        if (this.row < panel.GRID-1)
            row++;
        panel.repaint();

    }
    
    public void addPanel(BoardPanel panel) {
        this.panel = panel;
    }

    public void move() {
        switch(direction) {
            case UP:  if (row > 0) {moveUp();}
                      else {moveRight();} 
                      break;

            case DOWN: if (row < panel.GRID-1) {moveDown();}
                        else {moveLeft();}
                        break;
            case LEFT: if (col > 0 ) {moveLeft();}
                        else {moveUp();}
                        break;
            case RIGHT: if (col < panel.GRID-1) {moveRight();}
                        else {moveDown();}
                         break;
 
        }
    }
    
    public void moveRight(int x) {
        if (this.col + x <= panel.GRID)
            col += x;
        else 
            col = panel.GRID-1;
        panel.repaint();
    }
    
    public void moveLeft(int x) {
        if (this.col - x >= 0)
            col -= x;
        else 
            col = 0;
        panel.repaint();
    }
    
    public void moveUp(int y) {
        if (this.row - y >= 0)
            row -= y;
        else 
            row = 0;
        panel.repaint();
    }
    
    public void moveDown(int y) {
        if (this.row + y <= panel.GRID)
            row += y;
        else 
            row = panel.GRID;
        panel.repaint();
    }
    
    public BufferedImage getImage() {
        return img;
    }
    
    private void createImage() {
       Random r = new Random();
       int n = r.nextInt() % 3;
       try {
            img = ImageIO.read(new File("robot"+n+".png"));
       } catch (IOException e) {
       } 
    }
    
   
}