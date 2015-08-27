// This is the batch version of the InputOutput class.

public class Controller {
    
    public Controller ( ) {
    }

    public final int CONTESTANT_THINKING = 1;
    public final int CONTESTANT_CHOICE = 2;
    public final int MONTY_REVEALING_GOAT = 3;
    public final int CONTESTANT_STAY = 4;
    public final int CONTESTANT_SWITCH = 5;
    public final int MONTY_HANDLING_CHOICE2 = 6;
    public final int CONTESTANT_WIN = 7;
    public final int MONTY_WIN = 8;
    public final int CONTESTANT_LOSE = 9;
    public final int MONTY_LOSE = 10;
    public final int MONTY_IDLE = 11;

    public void say (String s, int delay) {
    }

    public void picture (int code, int delay) {
    }
    
    public void picture (int code1, int code2, int delay) {
    }
    
    public int doorSelected ( ) {
        return 0;
    }
    
    public void open (int doorToOpen, boolean isGoat) {
    }
    
    public void close (int doorToClose) {
    }   
}
