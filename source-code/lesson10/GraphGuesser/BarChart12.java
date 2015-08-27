 // Draws a Bar Chart with 12 bins

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;

public class BarChart12 extends JPanel {

   private int bar0, bar1, bar2, bar3, bar4, bar5, bar6, bar7, bar8, bar9, bar10, bar11, bar12, max;

   public BarChart12() {
      JFrame easel = new JFrame();
      this.setPreferredSize(new Dimension(myWindowWidth, myWindowHeight));   
      easel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      easel.add(this);
      easel.pack();   // this will set the size of the window depending on the Jpanel it contains...
      easel.setVisible(true);
      
      clear();  // initialize the instance variables
   }

   public void clear() {
      bar0 = 0;
      bar1 = 0;
      bar2 = 0;
      bar3 = 0;
      bar4 = 0;
      bar5 = 0;
      bar6 = 0;
      bar7 = 0;
      bar8 = 0;
      bar9 = 0;
      bar10 = 0;
      bar11 = 0;
      bar12 = 0;
      max = 0;
   }
   
   public void add(int entry) {
      // Doesn't do anything if entry is less than 0 or greater than 12
      if (entry > 12) {
         return;
      } else if (entry > 11) {
         bar12 = bar12 + 1;
         max = Math.max(max, bar12);
      } else if (entry > 10) {
         bar11 = bar11 + 1;
         max = Math.max(max, bar11);
      } else if (entry > 9) {
         bar10 = bar10 + 1;
         max = Math.max(max, bar10);
      } else if (entry > 8) {
         bar9 = bar9 + 1;
         max = Math.max(max, bar9);
      } else if (entry > 7) {
         bar8 = bar8 + 1;
         max = Math.max(max, bar8);
      } else if (entry > 6) {
         bar7 = bar7 + 1;
         max = Math.max(max, bar7);
      } else if (entry > 5) {
         bar6 = bar6 + 1;
         max = Math.max(max, bar6);
      } else if (entry > 4) {
         bar5 = bar5 + 1;
         max = Math.max(max, bar5);
      } else if (entry > 3) {
         bar4 = bar4 + 1;
         max = Math.max(max, bar4);
      } else if (entry > 2) {
         bar3 = bar3 + 1;
         max = Math.max(max, bar3);
      } else if (entry > 1) {
         bar2 = bar2 + 1;
         max = Math.max(max, bar2);
      } else if (entry > 0) {
         bar1 = bar1 + 1;
         max = Math.max(max, bar1);
      } else if (entry == 0) {
         bar0 = bar0 + 1;
         max = Math.max(max, bar0);
      } else {
         return;
      }
   }



   //// Drawing details
   ////
   private int myWindowWidth = 500;
   private int myWindowHeight = 300;
   private int nBars = 13; // number of bars
   private int border = 5;
   private int textHeight = 10; // height of the text
   private int axisOffset = 20; // space for each axis
   private int barWidth = (myWindowWidth - (border * 2) - axisOffset) / nBars;

   public void paintComponent(Graphics g) {
      g.setColor(Color.white);
      g.fillRect(0, 0, myWindowWidth, myWindowHeight);
      drawBars(g);
      drawAxes(g);
   }

   private void drawAxes(Graphics g) {
      // draws the left and bottom axes and labels.
      g.setColor(Color.black);
      // y axis
      g.drawLine(border + axisOffset, border, border + axisOffset,
            myWindowHeight - (axisOffset + border));
      g.drawString("0", border, myWindowHeight - (axisOffset + border));
      g.drawString(max + "", border, border + textHeight);
      // x axis
      g.drawLine(border + axisOffset, myWindowHeight - (axisOffset + border),
            myWindowWidth - border, myWindowHeight - (axisOffset + border));
      int k = 0;
      while (k <= 12) {
         drawXLabel(g, k);
         k = k + 1;
      }
   }

   private void drawXLabel(Graphics g, int bar) {
      g.drawString(bar + "", border + axisOffset
            + ((barWidth * bar) + (barWidth / 2)), myWindowHeight - border);
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
      g.setColor(Color.blue);
      drawBar(g, 7, bar7);
      g.setColor(Color.cyan);
      drawBar(g, 8, bar8);
      g.setColor(Color.blue);
      drawBar(g, 9, bar9);
      g.setColor(Color.cyan);
      drawBar(g, 10, bar10);
      g.setColor(Color.blue);
      drawBar(g, 11, bar11);
      g.setColor(Color.cyan);
      drawBar(g, 12, bar12);
   }

   private void drawBar(Graphics g, int bar, int count) {
      double ratio = ((double) (myWindowHeight - (2 * (axisOffset + border))))
            / ((double) max); // pixels per item
      int height = (int) (count * ratio);
      g.fillRect((barWidth * bar) + axisOffset + border, myWindowHeight
            - (axisOffset + border + height), barWidth, height);
   }
}
