import java.awt.*;
import javax.swing.*;
import java.util.Random;
import java.util.ArrayList;

public class DotRaceArrayList extends JPanel {

   private JFrame myFrame;
   private Random randGen = new Random();

   // the length in pixels of the track
   int trackLength = 300;
   int startLine = 50;
   // number of milliseconds (one thousanth of a second) to pause between each cycle
   int pauseTime = 15;
   Dot winnaWinnaWinna; // who has won (or null if nobody)
   int winnaEffectDiameter; // for the special effects at end
   int defaultDiameter = 70; // default diameter for the dot

   int topBorder = 10; // allow for the menu bar at the top of the frame
   int border = 10; // the border around the dots
   int sideBorder = startLine; // space after the finishLine
   int windowWidth = trackLength + startLine + 50;
   int windowHeight; // this gets set in the constructor

   // This ArrayList holds the Dot objects in the race
   ArrayList<Dot> dots = new ArrayList<Dot>();

   // constructor
   public DotRaceArrayList() {
      initializeRacers();
      windowHeight = topBorder + (dots.size() * (defaultDiameter + border));
      
      myFrame = new JFrame("Dot Racing!");
      myFrame.add(this);
      myFrame.setSize(windowWidth, windowHeight);
      myFrame.setVisible(true);
      runRace();
   }
   
   
   // this method should initialize the "dots" ArrayList
   public void initializeRacers() {
      int racerCount = 7; // play around with this, trying different numbers.
                          // Or, try making this a random number...
      int k = 0;
      while (k < racerCount) {
         Dot aDotObject = new Dot(randomName(), k, randomColor(), defaultDiameter);
         // FINISH THIS -- insert the Dot object to the dots ArrayList
         dots.add(aDotObject);
         k = k + 1;
      }
   }

   public void runRace() {
      Dot mover;
      int distance;

      winnaWinnaWinna = null;
      while (winnaWinnaWinna == null) {
         mover = whoMoves();
         distance = howFar();
         mover.moved(distance);
         if (mover.getDistance() + mover.getDiameter() == trackLength + border) {
            winnaWinnaWinna = mover;
         }
         repaint();
         delay(pauseTime);
      }
      System.out.println("And the winner is " + winnaWinnaWinna.getName() + "! . You won by : " + distanceWon(winnaWinnaWinna));
   }

   public double distanceWon(Dot winner){
       //loop over dots list...
       //find the second place Dot
       // subtract the winner distance from second place      
   
   }
   
   // This method returns the 'Dot' whose turn it is to move.
   private Dot whoMoves() {
      int d = randGen.nextInt(dots.size()); // a number between 0 and one less than the number of dots
      // FINISH THIS: return the Dot pointed to by 'd'
      return dots.get(d);
   }
   
   
   // This method returns the number of pixels that the dot moves, kind of
   // non-linearly
   private int howFar() {
      int r = (randGen.nextInt(6));
      if (r < 5) {
         return 1;
      } else if (r == 5) {
         return 3;
      } else {
         return 8;
      }
   }
   
   
   // Draw the dots in raceTrack.
   // We keep track of who just moved to give an animation effect.
   // Also when there's a winner, we circle the dot that won.
   public void paintComponent(Graphics g) {
      super.repaint(); // don't worry about this -- it 'clears the screen'

      g.setColor(Color.white);
      g.fillRect(0, 0, windowWidth, windowHeight); // draw a nice white background
      g.setColor(Color.black);
      g.drawLine(startLine, 0, startLine, windowHeight); // draw a starting line
      g.drawLine(startLine + trackLength, 0, startLine + trackLength, windowHeight); // finish
      drawRacers(g);

   }

   // Draw all of the Dot objects
   public void drawRacers(Graphics g) {
      // FINISH THIS -- call drawRacer() for each Dot in dots
      
   }

   public void drawRacer(Dot racer, Graphics g) {
      int topX = racer.getDistance() + border + startLine;
      int topY = topBorder + (racer.getRacerNumber() * defaultDiameter)
            + border;
      int drawDiam = racer.getDiameter() - (2 * border);
      g.setColor(racer.getColor());
      g.fillOval(topX, topY, drawDiam, drawDiam);
      g.setColor(Color.black);
      g.drawOval(topX, topY, drawDiam, drawDiam);
   }

   // ///////////////////////////

   ArrayList<String> someNames = new ArrayList<String>() {
      {
         // this is a simple way of initializing an ArrayList in Java, but not
         // something you need to know for this course.
         // These are names of real race horses, as reported by 'the internet'.
         add("dulcify");
         add("whirlaway");
         add("gumshoe");
         add("skipteaser");
         add("kissed by a fish");
         add("rakatack");
         add("viscosity");
         add("flattermeforever");
         add("sonneteer");
         add("cigar starter");
         add("flat out fast");
         add("gothic soldier");
         add("sexy librarian");
         add("fishy advice");
         add("motel princess");
         add("lunatique");
         add("panicking petunia");
         add("wingspan");
         add("southern missle");
         add("excessive moves");
         add("paul bunyans axe");
         add("a storm it is");
         add("dawn of war");
      }
   };

   // returns a random element from the someNames ArrayList
   public String randomName() {
      return someNames.get(randGen.nextInt(someNames.size()));
   }

   ArrayList<Color> someColors = new ArrayList<Color>() {
      {
         add(Color.black);
         add(Color.blue);
         add(Color.cyan);
         add(Color.gray);
         add(Color.green);
         add(Color.magenta);
         add(Color.yellow);
         add(Color.orange);
         add(Color.red);
         add(Color.pink);
      }
   };

   // returns a random element from the someColors ArrayList
   public Color randomColor() {
      return someColors.get(randGen.nextInt(someColors.size()));
   }

   
   // This method causes your program to pause for a certain number of
   // milliseconds
   // Don't worry about the details of the class Thread, the weird 'try/catch'
   // keywords,
   // we won't cover them in this course.
   private void delay(int ms) {
      try {
         Thread.sleep(ms);
      } catch (Exception ex) {
         ex.printStackTrace();
      }
   }

} // end DotRace
