import java.util.*;

public class MeasurementTest2 {

   public MeasurementTest2 ( ) {
      ArrayList<Measurement> a = new ArrayList<Measurement> ( );
      a.add (new Measurement (3, 5));
      a.add (new Measurement (10, 9));
      a.add (new Measurement (6, 3));
      Measurement m1 = new Measurement (2, 7);
      a.add (m1);
      System.out.println (a);
      Collections.sort (a);
      System.out.println (a);
   }
}