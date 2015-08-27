import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Controller extends JPanel {
    
    // If logging, information about click location is printed
    // on standard output.
    final boolean logging = false; 
    
    private Image carImage;
    private Image goatImage;
            
    private Image checkmarkImage;
    private Image questionmarkImage;
            
    private Image door1Image;
    private Image door2Image;
    private Image door3Image;
            
    private Image montyIdleImage;
    private Image montyRevealGoatImage;
    private Image montySecondChoiceImage;
    private Image montyWinImage;
    private Image montyLoseImage;

    private Image contestantThinkingImage;
    private Image contestantChoose1Image;
    private Image contestantChoose2Image;
    private Image contestantChoose3Image;
    private Image contestantStayImage;
    private Image contestantSwitchImage;
    private Image contestantWinImage;
    private Image contestantLoseImage;
    
    // Initialize the images displayed in the five image sections
    // of the display.
    private Image door1 = door1Image;
    private Image door2 = door2Image;
    private Image door3 = door3Image;
    private Image contestant = contestantThinkingImage;
    private Image monty = montyIdleImage;
    
    // These constants control what images are displayed.
    public final int CONTESTANT_THINKING = 1;
    public final int CONTESTANT_CHOICE = 2;
    public final int MONTY_REVEALING_GOAT = 3;
    public final int CONTESTANT_STAY = 4;
    public final int CONTESTANT_SWITCH = 5;
    public final int MONTY_HANDLING_CHOICE2 = 6;
    public final int CONTESTANT_WIN = 7;
    public final int MONTY_WIN = 8;
    public final int CONTESTANT_LOSE = 9;
    public final int MONTY_LOSE = 10;
    public final int MONTY_IDLE = 11;
    
    private String msg = "Let's play a game!";
    
    // Screen coordinates for the text area, the three doors,
    // and the two characters.
    private int msgX = 200;
    private int msgY = 100;
        
    private int door1X = 10;
    private int door1Y = 135;
    private int door2X = 250;
    private int door2Y = 135;
    private int door3X = 490;
    private int door3Y = 135;
    private int doorWidth = 240;
    private int doorHeight = 225;
    
    private int playerX = 10;
    private int playerY = 360;
    private int montyX = 370;
    private int montyY = 360;
    private int peopleWidth = 360;
    private int peopleHeight = 370;
    
    private int myWindowWidth = 730;
    private int myWindowHeight = 750;
    
    private ClickHandler inputSource;
    
    public Controller ( ) {
        // Load all the relevant images.
        loadImages ( );
        
        // Set up the window.
        JFrame easel = new JFrame();      
        easel.setSize (myWindowWidth, myWindowHeight);
        easel.add (this);
        easel.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        easel.setVisible (true);
      
        // Initialize the mouse listener.
        inputSource = new ClickHandler ( );
        inputSource.run ( );
        addMouseListener (inputSource);
        repaint ( );
    }
    
    // Draw the given string in the text area, then sleep for the given number
    // of milliseconds.
    public void say (String s, int delay) {
        msg = s;
        if (logging) {
            System.out.println (s);
        }
        repaint ( );
        goToSleep (delay);
    }

    // Draw the image represented by the given code in the Monty area
    // or the contestant area, then sleep for the given number of milliseconds.
    public void picture (int code, int delay) {
        if (code == CONTESTANT_THINKING) {
            contestant = contestantThinkingImage;
        } else if (code == MONTY_REVEALING_GOAT) {
            monty = montyRevealGoatImage;
        } else if (code == CONTESTANT_STAY) {
            contestant = contestantStayImage;
        } else if (code == CONTESTANT_SWITCH) {
            contestant = contestantSwitchImage;
        } else if (code == MONTY_HANDLING_CHOICE2) {
            monty = montySecondChoiceImage;
        } else if (code == CONTESTANT_WIN) {
            contestant = contestantWinImage;
        } else if (code == MONTY_WIN) {
            monty = montyWinImage;
        } else if (code == CONTESTANT_LOSE) {
            contestant = contestantLoseImage;
        } else if (code == MONTY_LOSE) {
            monty = montyLoseImage;
        } else if (code == MONTY_IDLE) {
            monty = montyIdleImage;
        }
        repaint ( );
        goToSleep (delay);
    }
    
    // Draw the image represented by the given codes in the contestant area,
    // then sleep for the given number of milliseconds.
    // (The reason for this is that there are different images representing
    // the choice of door 1, door 2, or door 3.)
    public void picture (int code1, int code2, int delay) {
        if (code1 == CONTESTANT_CHOICE) {
            if (code2 == 1) {
                contestant = contestantChoose1Image;
            } else if (code2 == 2) {
                contestant = contestantChoose2Image;
            } else if (code2 == 3) {
                contestant = contestantChoose3Image;
            }
        }
        repaint ( );
        goToSleep (delay);
    }
    
    // Wait for a mouse click, then translate it to the number 
    // of the clicked-on door and return that.
    public int doorSelected ( ) {
        int x, y;
        while (!inputSource.receivedClick) {
	    goToSleep (1000);
        }
        inputSource.receivedClick = false;
        x = inputSource.thingClickedOnX;
        y = inputSource.thingClickedOnY;
        int doorPos = -1;
        if (x < door2X) {
            doorPos = 1;
        } else if (x < door3X) {
            doorPos = 2;
        } else if (x < door3X+doorWidth) {
            doorPos = 3;
        }
        if (logging) {
            System.out.println ("returning " + doorPos);
        }
        return doorPos;
    }
    
    // Replace the image for the given door by the image displaying
    // the contents of the door (goat or car).  If the door is already
    // open, this method has no effect.
    public void open (int doorToOpen, boolean isGoat) {
        Image contents;
        if (isGoat) {
            contents = goatImage;
        } else {
            contents = carImage;
        }
        if (doorToOpen == 1) {
            door1 = contents;
        } else if (doorToOpen == 2) {
            door2 = contents;
        } else if (doorToOpen == 3) {
            door3 = contents;
        }
        repaint ( );
    }
    
    // Replace the image for the car or goat by the image displaying
    // the door.  If the door is already closed, this method has no effect.
    public void close (int doorToClose) {
        if (doorToClose == 1) {
            door1 = door1Image;
        } else if (doorToClose == 2) {
            door2 = door2Image;
        } else if (doorToClose == 3) {
            door3 = door3Image;
        }
        repaint ( );
    }   
    
    // Draw the display.  There is a text area at the top that we erase
    // before drawing the text.  Then we draw the images for the three doors.
    // Finally, we draw the images for Monty and the contestant.
    public void paintComponent (Graphics g) {
        g.setColor (Color.white);
        g.fillRect (door1X+5, door1X+5, myWindowWidth-10, door1Y-10);
        g.setColor (Color.black);
        g.drawString (msg, msgX, msgY);
        g.drawImage (door1, door1X, door1Y, doorWidth, doorHeight, this);
        g.drawImage (door2, door2X, door2Y, doorWidth, doorHeight, this);
        g.drawImage (door3, door3X, door3Y, doorWidth, doorHeight, this);
        g.drawImage (contestant, playerX, playerY, peopleWidth, peopleHeight, this);
        g.drawImage (monty, montyX, montyY, peopleWidth, peopleHeight, this);
    }
    
    // The remaining code involves Java constructs--image initialization, exceptions,
    // and threads--that either aren't in the AP CS Java subset (image methods and threads)
    // or will be covered later (exceptions).
    
    private void loadImages ( ) {
        carImage = new ImageIcon (getClass ( ).getClassLoader ( )
            .getResource ("images/Car.jpg")).getImage ( );
        goatImage = new ImageIcon (getClass ( ).getClassLoader ( )
            .getResource ("images/Goat2.jpg")).getImage ( );
            
        checkmarkImage = new ImageIcon (getClass ( )
            .getClassLoader ( ).getResource ("images/checkmark.jpg")).getImage ( );
        questionmarkImage = new ImageIcon (getClass ( )
            .getClassLoader ( ).getResource ("images/questionmark.jpg")).getImage ( );
            
        door1Image = new ImageIcon (getClass ( ).getClassLoader ( )
            .getResource ("images/Door1.jpg")).getImage ( );
        door2Image = new ImageIcon (getClass ( ).getClassLoader ( )
            .getResource ("images/Door2.jpg")).getImage ( );
        door3Image = new ImageIcon (getClass ( ).getClassLoader ( )
            .getResource ("images/Door3.jpg")).getImage ( );
            
        montyIdleImage = new ImageIcon (getClass ( )
            .getClassLoader ( ).getResource ("images/Monty_idle.jpg")).getImage ( );
        montyRevealGoatImage = new ImageIcon (getClass ( )
            .getClassLoader ( ).getResource ("images/Monty_revealGoat.jpg")).getImage ( );
        montySecondChoiceImage = new ImageIcon (getClass ( )
            .getClassLoader ( ).getResource ("images/Monty_secondChoice.jpg")).getImage ( );
        montyWinImage = new ImageIcon (getClass ( ).getClassLoader ( )
            .getResource ("images/Monty_win.jpg")).getImage ( );
        montyLoseImage = new ImageIcon (getClass ( )
            .getClassLoader ( ).getResource ("images/Monty_lose.jpg")).getImage ( );

        contestantThinkingImage = new ImageIcon (getClass ( )
            .getClassLoader ( ).getResource ("images/Contestant_thinking.jpg")).getImage ( );
        contestantChoose1Image = new ImageIcon (getClass ( )
            .getClassLoader ( ).getResource ("images/Contestant_choose1.jpg")).getImage ( );
        contestantChoose2Image = new ImageIcon (getClass ( )
            .getClassLoader ( ).getResource ("images/Contestant_choose2.jpg")).getImage ( );
        contestantChoose3Image = new ImageIcon (getClass ( )
            .getClassLoader ( ).getResource ("images/Contestant_choose3.jpg")).getImage ( );
        contestantStayImage = new ImageIcon (getClass ( )
            .getClassLoader ( ).getResource ("images/Contestant_stay.jpg")).getImage ( );
        contestantSwitchImage = new ImageIcon (getClass ( )
            .getClassLoader ( ).getResource ("images/Contestant_switch.jpg")).getImage ( );
        contestantWinImage = new ImageIcon (getClass ( )
            .getClassLoader ( ).getResource ("images/Contestant_win.jpg")).getImage ( );
        contestantLoseImage = new ImageIcon (getClass ( )
            .getClassLoader ( ).getResource ("images/Contestant_lose.jpg")).getImage ( );
    }
    
    private void goToSleep (int milliseconds) {
        try {
            Thread.sleep (milliseconds);
        } catch (Exception e) {
        }
    }
        
    // The communication between clicks and processing is that of a producer
    // and consumer.  When the mouse is clicked, the coordinates are saved
    // in thingClickedOnX and thingClickedOnY and receivedClick is set to true. 
    // When the program wants to read a door number, it first waits for
    // receivedClick to be true.  Once that happens, it copies the contents
    // of thingClickedOnX and thingClickedOnY and sets receivedClick to be
    // false. 
    private class ClickHandler implements Runnable, MouseListener {
        
        private int thingClickedOnX;
        private int thingClickedOnY;
        private boolean receivedClick = false;
        Thread t;
        
        public ClickHandler ( ) {
            t = new Thread (this);
        }
        
        public void start ( ) {
            t.start ( );
        }
    
        public void run ( ) {
        }
    
        public void mouseClicked (MouseEvent e) {
            thingClickedOnX = e.getX ( );
            thingClickedOnY = e.getY ( );
            if (logging) {
                System.out.println ("click at (" + thingClickedOnX + "," + thingClickedOnY + ")");
            }
            if (thingClickedOnX < door1X || thingClickedOnX > door3X+doorWidth
                    || thingClickedOnY < door1Y || thingClickedOnY > door1Y+doorHeight) {
                System.out.println ("Click ignored.");
            } else {
                receivedClick = true;
                repaint ( );
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
    }
}
