import java.awt.Color;

public class SpiralBug extends BoxBug
{
   public SpiralBug(int length)
   {
        super(length);
   }

   public SpiralBug(int length, Color c)
   {
        super(length, c);
   }
   
   public void incrementSideLength(){
        changeSideLengthByOne();
   }
    


}
