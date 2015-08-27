// Draws a Bar Chart with 6 bins

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;

public class BarChart6 extends JPanel {

   private int bar0 = 0;
   private int bar1 = 0;
   private int bar2 = 0;
   private int bar3 = 0;
   private int bar4 = 0;
   private int bar5 = 0;
   private int bar6 = 0;
   private int max = 0;


   public BarChart6() {
      // initialise instance variables
      JFrame easel = new JFrame();
      easel.setSize(myWindowWidth, myWindowHeight);
      easel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      easel.add(this);
      easel.setVisible(true);
   }

   public void add(int entry) {
      // Doesn't do anything if entry is less than 0 or greater than 6
      if (entry > 6) {
         return;
      } else if (entry > 5) {
         bar6 = bar6 + 1;
      } else if (entry > 4) {
         bar5 = bar5 + 1;
      } else if (entry > 3) {
         bar4 = bar4 + 1;
      } else if (entry > 2) {
         bar3 = bar3 + 1;
      } else if (entry > 1) {
         bar2 = bar2 + 1;
      } else if (entry > 0) {
         bar1 = bar1 + 1;
      } else if (entry == 0) {
         bar0 = bar0 + 1;
      } else {
         return;
      }
      this.setMax();
   }

   private void setMax() {
      // update the maximum in any bar
      max = Math.max(bar0, 
               Math.max(bar1, 
                  Math.max(bar2, 
                     Math.max(bar3, 
                        Math.max(bar4, 
                           Math.max(bar5, bar6))))));
   }

   
   ////// Drawing details
   //////
   private int myWindowWidth = 400;
   private int myWindowHeight = 300;
   private int nBars = 7;  // number of bars
   private int border = 5;
   private int textHeight = 10;   // height of the text
   private int axisOffset = 20;   // space for each axis
   private int barWidth = (myWindowWidth-(border*2)-axisOffset)/nBars;
   
   
   public void paintComponent(Graphics g) {
      g.setColor(Color.white);
      g.fillRect(0,0,myWindowWidth, myWindowHeight);
      drawBars(g);
      drawAxes(g);
   }
   
   
   private void drawAxes(Graphics g) {
      // draws the left and bottom axes and labels.
      g.setColor(Color.black);
      // y axis
      g.drawLine(border+axisOffset, border, border+axisOffset, myWindowHeight-(axisOffset+border));
      g.drawString("0", border, myWindowHeight-(axisOffset+border));
      g.drawString(max + "", border, border + textHeight);
      // x axis
      g.drawLine(border+axisOffset, myWindowHeight-(axisOffset+border), myWindowWidth-border, myWindowHeight-(axisOffset+border));
      drawXLabel(g, 0);
      drawXLabel(g, 1);
      drawXLabel(g, 2);
      drawXLabel(g, 3);
      drawXLabel(g, 4);
      drawXLabel(g, 5);
      drawXLabel(g, 6);

   }
   
   private void drawXLabel(Graphics g, int bar) {
      g.drawString(bar + "", border+axisOffset+((barWidth*bar)+(barWidth/2)), myWindowHeight-border);
   }
   
   private void drawBars(Graphics g) {
      g.setColor(Color.cyan);
      drawBar(g, 0, bar0);
      g.setColor(Color.blue);
      drawBar(g, 1, bar1);
      g.setColor(Color.cyan);
      drawBar(g, 2, bar2);
      g.setColor(Color.blue);
      drawBar(g, 3, bar3);
      g.setColor(Color.cyan);
      drawBar(g, 4, bar4);
      g.setColor(Color.blue);
      drawBar(g, 5, bar5);
      g.setColor(Color.cyan);
      drawBar(g, 6, bar6);
   }
   
   private void drawBar(Graphics g, int bar, int count) {
      double ratio = ((double) (myWindowHeight - (2*(axisOffset+border)))) / ((double) max);  // pixels per item
      int height = (int) (count * ratio);
      g.fillRect((barWidth*bar)+axisOffset+border, myWindowHeight-(axisOffset+border+height), barWidth, height);
   }
}
