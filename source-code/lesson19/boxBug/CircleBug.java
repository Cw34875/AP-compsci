import java.awt.Color;

public class CircleBug extends BoxBug
{
    public CircleBug(int length)
    {
        super(length);
    }

    public CircleBug(int length, Color c)
    {
        super(length, c);
    }
    
    public void doTurn(){
        turn();
    }   
}
