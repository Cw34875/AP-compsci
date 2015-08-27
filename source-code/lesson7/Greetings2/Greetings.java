
import java.awt.*;
import javax.swing.*;

public class Greetings extends JPanel {
	
   private int myWindowWidth = 300;
   private int myWindowHeight = 400;
	
   public Greetings() {
      JFrame easel = new JFrame();		
      easel.setSize (myWindowWidth, myWindowHeight);
      easel.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
      easel.add (this);
      easel.setVisible (true);
   }
   
   public void paintComponent (Graphics g) {
      g.drawString ("Hi there!", 150, 200);	
   }
   
   
   // Step 25 Write newDrawRect2 here, which calls drawLine four times.
   public void newDrawRect(int tlx, int tly, int width, int height) {
      
   }
   
   // Step 26 This method takes top-left x, top-left y, bottom-right x, bottom-right y to define
   //  a rectangle.  It should take drawRect.
   public void newDrawRect(int tlx, int tly, int brx, int bry) {
      
   }

   
}
