public class MontySim {

    private int myGameCount, myWinTotal;
    
    public MontySim (int gameCount) {
        myGameCount = gameCount;
    }
    
    //implement this method
    public void runSimulation ( ) {
         Monty m = new Monty();
         int countRuns = 0;
         myWinTotal = 0;
         while(countRuns < myGameCount){
             int result = m.playGame();
             myWinTotal = myWinTotal + result;
             countRuns++;
         }
         System.out.println((myWinTotal * 1.0)/myGameCount);
    }
}      