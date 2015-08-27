import java.util.Random;

public class DiceRoll
{
    private int roll1;
    private int roll2;
    private Random randGen;
    
    public DiceRoll ( ) {
        randGen = new Random ( );
    }
    
    public void roll ( ) {
        roll1 = 1 + randGen.nextInt (6);
        roll2 = 1 + randGen.nextInt (6);
    }
    
    public int total ( ) {
        return roll1 + roll2;
    }
}
