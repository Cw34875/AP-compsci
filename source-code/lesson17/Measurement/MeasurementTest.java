import java.util.ArrayList;

public class MeasurementTest {

   public MeasurementTest ( ) {
      ArrayList<Measurement> a = new ArrayList<Measurement> ( );
      a.add (new Measurement (3, 5));
      a.add (new Measurement (10, 9));
      a.add (new Measurement (6, 3));
      Measurement m1 = new Measurement (2, 7);
      a.add (m1);
      
      if (a.contains (m1)) {
         System.out.println ("a contains m1");
      } else {
         System.out.println ("a doesn't contain m1");
      }
      
      if (a.contains (new Measurement (2, 7))) {
         System.out.println ("a contains 2'7''");
      } else {
         System.out.println ("a doesn't contain 2'7''");
      }
   }
}