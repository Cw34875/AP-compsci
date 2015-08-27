public class TreasureHunt {

   int [ ] myClues = {0, 3, 99, 8, 2, 7, 9, 9, 4, 5};

   public TreasureHunt( ) {
    boolean isAWin = this.huntSucceeds(7);
    System.out.println("Did we win: " + isAWin);
   }
   
   // Plays the Treasure Hunt game, returning true
   // if we win and false if we lose..
   public boolean huntSucceeds (int start) {
     int limit = myClues.length;
     int nextSpot = myClues [start];
     while (nextSpot < limit){
         if (nextSpot == -1){
             return false;
         }
         myClues[start] = -1;
         start = nextSpot;
         nextSpot = myClues [start];
     }
     return true;
   
   } 
   
}