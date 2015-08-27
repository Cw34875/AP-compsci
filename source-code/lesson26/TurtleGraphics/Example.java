
public class Example {

   public Example() {
       Turtle turtle = new Turtle(400, 400);
       
       turtle.penWidth(1);
       turtle.penUp();
       turtle.moveTo(-100, -100);
       turtle.penDown();
       turtle.setDirection(0);
       drawFractal(5, turtle, 200);
       
   }
   
   private static void drawFractal(int depth, Turtle turtle, double length){
      if (depth <= 1) {
          turtle.delay(10);
          turtle.move(length);
      } else{
         drawFractal(depth - 1, turtle, length / 3);
         turtle.turn(60);
         drawFractal(depth - 1, turtle, length / 3);
         turtle.turn(-120);
         drawFractal(depth - 1, turtle, length / 3);
         turtle.turn(60);
         drawFractal(depth - 1, turtle, length / 3);
      }
   } 
}


