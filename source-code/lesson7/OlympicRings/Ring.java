import java.awt.*;
import javax.swing.*;

public class Ring extends JPanel
{
    // instance variables - replace the example below with your own
    private int myWindowWidth = 300;
    private int myWindowHeight = 400;


    public Ring()
    {
        // initialise instance variables
        JFrame easel = new JFrame();
        easel.setSize(myWindowWidth, myWindowHeight);
        easel.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        easel.add(this);
        easel.setVisible(true);
        
    }

    public void paintComponent(Graphics g)
    {
        
        
    }
}
