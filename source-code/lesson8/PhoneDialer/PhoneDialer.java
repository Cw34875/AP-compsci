// A series of errors have been introduced into the code. Please fix it
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class PhoneDialer extends JPanel implements MouseListener {
    
   private int myWindowWidth = 200;
   private int myWindowHeight = 330;
   private String msg = "";
   
   public PhoneDialer () {
      JFrame myFrame = new JFrame("Phone Dialer");      
      myFrame.setSize(myWindowWidth, myWindowHeight);
      myFrame.add(this);
      myFrame.setVisible(true);
      
      
   }
    
   public void mouseClicked (MouseEvent e) {
       getNumber(e.getX(),e.getY());
       
   }
    
    public void paintComponent (Graphics g) {
        g.setColor(Color.yellow);
        g.fillRect(0, 0, myWindowWidth, myWindowHeight);
        drawButton(g, 30, 30, "1");
        drawButton(g, 80, 30, "2"); 
        drawButton(g, 130, 30, "3");
        drawButton(g, 30, 80, "4");
        drawButton(g, 80, 80, "5"); 
        drawButton(g, 130, 80, "6");
        drawButton(g, 30, 130, "7");
        drawButton(g, 80, 130, "8"); 
        drawButton(g, 130, 130, "9");
        drawButton(g, 30, 180, "#");
        drawButton(g, 80, 180, "0"); 
        drawButton(g, 130, 180, "*");
        g.setColor(Color.black);
        g.drawLine(30, 230, myWindowWidth-40, 230);
        g.drawString(msg, 80, 250);
   }
    
   private void drawButton(Graphics g, int x, int y, String txt) {
       g.setColor(Color.white);
       g.fillRect(x, y, 30, 30);
       g.setColor(Color.black);
       g.drawRect(x, y, 30, 30);
       g.drawString(txt, x+18, y+18);
    }
   
   private void getNumber(int x, int y){
       if(x >= 30 && x <= 60 && y >= 30 && y <= 60)
            msg += "1";
       else if(x >= 80 && x <= 110 && y >= 30 && y <= 60)
            msg += "2";
       else if(x >= 130 && x <= 160 && y >= 30 && y <= 60)
            msg += "3";
       else if(x >= 30 && x <= 60 && y >= 80 && y <= 110)
            msg += "4";
       else if(x >= 80 && x <= 110 && y >= 80 && y <= 110)
            msg += "5";
       else if(x >= 130 && x <= 160 && y >= 80 && y <= 110)
            msg += "6";
       else if(x >= 80 && x <= 110 && y >= 130 && y <= 160)
            msg += "7";
       else if(x >= 80 && x <= 110 && y >= 130 && y <= 160)
            msg += "8";
       else if(x >= 130 && x <= 160 && y >= 130 && y <= 160)
            msg += "9";
       else if(x >= 80 && x <= 110 && y >= 180 && y <= 210)
            msg += "0";
       else if(x >= 30 && x <= 60 && y >= 180 && y <= 210){
            msg = "#"; 
            
        }
   }
    
   public void mouseReleased (MouseEvent event) {
   }
    
   public void mousePressed (MouseEvent event) {
   }
    
   public void mouseEntered (MouseEvent event) {
   }
    
   public void mouseExited (MouseEvent event) {
   }
   
} // end PhoneDialer
