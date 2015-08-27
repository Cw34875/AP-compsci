import java.util.Random;

public class StPeters
{
    private Random r = new Random();
    private double entryFee;
    private int pot = 1;
    
    public StPeters(double entryFee)
    {
        this.entryFee = entryFee;
    }

    public void tester(int howMany){
        int counter = 0;
        int countBreakEvenOrWin=0;
        while(counter < howMany)
        {
            pot = 1;
            if(runGame())
                countBreakEvenOrWin++;
            counter++;
        }
        System.out.println(countBreakEvenOrWin + " broke even or won out of " + howMany + " tries. Chance of winning: " + countBreakEvenOrWin * 1.0 / howMany); 
    }
    
    private boolean runGame(){
           while(this.flip() == 0)
           {
                pot *= 2; //pot = pot * 2;
           }
           return pot >= entryFee;
     }
    
    
    private int flip(){
        //code to return a 0 or 1 simulating a flip
        return r.nextInt(2);
    }
   
            
    
    
}
