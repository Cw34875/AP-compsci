public class Tester {

    public Tester ( ) {
        Refillable rtest = new Refillable (100);
        rtest.useUp (10);
        System.out.println ("current contents = "
                            + rtest.currentAmount ( )
                            + "; should be 90");
        rtest.useUp (20);
        System.out.println ("current contents = "
                            + rtest.currentAmount ( )
                            + "; should be 70");
        rtest.refill (40);
        System.out.println ("current contents = "
                            + rtest.currentAmount ( )
                            + "; should be 110");
        rtest.refill (5);
        System.out.println ("current contents = "
                            + rtest.currentAmount ( )
                            + "; should be 115");
    }            
}
