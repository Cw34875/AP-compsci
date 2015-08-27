import java.util.*;
public class Test
{
    public Test()
    {
        ArrayList<Measurable> a = new ArrayList<Measurable>();
        a.add(new Bed());
        a.add(new Desk());
        
        for(Measurable m : a){
            System.out.println(m.length());
        }
    }

}
