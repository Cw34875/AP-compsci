


import java.util.Random;

public class DiceRollGenerator {

    private int roll1;
    private int roll2;
    private Random randGen;
    
    public DiceRollGenerator ( ) {
        randGen = new Random ( );
    }
    
    public void roll ( ) {
        roll1 = 1 + randGen.nextInt (6);
        roll2 = 1 + randGen.nextInt (6);
    }
    
    public int total ( ) {
        return roll1 + roll2;
    }
    
    public void runTestSix(int howMany)
    {
                int count = 0,counter = 0;
                while(counter < howMany){
                    if(testSixInFourRolls())
                        count++;
                    counter++;
                }
                System.out.println(count + " one 6 in 4 rolls tried " + howMany + " times : " + count * 1.0 / howMany);

    }
    

    private boolean testSixInFourRolls(){
        int counter = 0;
        while(counter < 4)
        {
                this.roll();
                if(roll1 == 6)
                    return true;
                counter++;    
        }
        return false;
    }
    
    
    public void runTestTwelve(int howMany)
    {
          //your code here    
    }
    
    
     private boolean test12In24Rolls(){
    	 //your code here
       

    }
}