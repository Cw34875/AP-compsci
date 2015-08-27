/**
 * Write a description of class GameBoard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import Helpers.*;
public class GameBoard  
{
    private BoardFrame myDisplay;

    public GameBoard() {
    	myDisplay = new BoardFrame();
    }
    
    public void showBoard() {
    	myDisplay.showBoard();
    }
    
    public void hideBoard() {
    	myDisplay.hideBoard();
    }
    
    public void addPiece(GamePiece newPiece, int row, int column){
    	myDisplay.addPiece(newPiece, row, column);
    }
    
    public void moveEveryPiece() {
    	myDisplay.moveEveryPiece();
    }
}


