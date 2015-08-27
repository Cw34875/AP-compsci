import java.util.*;

public class Monty {

    private Stage stage;
    private Random randoms;
    private Contestant player;
    private Controller control;
    
    public Monty ( ) {
        Calendar c = Calendar.getInstance ( );
        randoms = new Random (c.getTimeInMillis ( ));
        control = new Controller ( );
        player = new Contestant (control, randoms);
    }
    
    // Play the game, returning 1 if the contestant wins and 0 if not.
    public int playGame ( ) {
        int doorWithCar;
        int firstChoice, secondChoice;
        int goatDoor;
        
        doorWithCar = gameStart ( );
        firstChoice = playerGuess ( );
        goatDoor = tempt (doorWithCar, firstChoice);
        secondChoice = newGuess (goatDoor, firstChoice);
        return gameResult (doorWithCar, goatDoor, secondChoice);
    }
    
    // Set up the game, returning the position of the car (chosen randomly).
    private int gameStart ( ) {
        int doorWithCar = randoms.nextInt (3) + 1;
        stage = new Stage (doorWithCar, control);
        control.picture (control.MONTY_IDLE, 0);
        control.picture (control.CONTESTANT_THINKING, 3000);
        return doorWithCar;
    }

    // Return the contestant's first guess.
    private int playerGuess ( ) {
        control.say ("Please choose a door.", 0);
        return player.guess1 ( );
    }
    
    // Tempt the contestant by revealing a door with a goat.  
    // Return the number of the newly opened door.
    private int tempt (int doorWithCar, int firstChoice) {
        int doorToOpen;
        control.say ("You picked door " + firstChoice + ", eh?", 3000);
        doorToOpen = goatDoorToShow (doorWithCar, firstChoice);
        control.say ("To make things interesting, I'll reveal a goat in door #" + doorToOpen + ".", 0);
        stage.open (doorToOpen);
        control.picture (control.MONTY_REVEALING_GOAT, 2000);
        return doorToOpen;
    }

    // This is called by Monty after the contestant makes his/her first guess.
    // It returns the position of a door behind which is a goat.
    private int goatDoorToShow (int doorWithCar, int chosenDoor) {
        int doorToOpen;
        if (chosenDoor == doorWithCar) {
            // The contestant has chosen the door with the car.
            // Return the result of a random choice between the two goat doors.
            int r = randoms.nextInt (2);
            if (chosenDoor == 1) {
                doorToOpen = r+2;
            } else if (chosenDoor == 2) {
                doorToOpen = r*2+1;
            } else {
                doorToOpen = r+1;
            }
        } else {
            // The contestant has chosen a goat door.
            // Open the door that contains the other goat.
            doorToOpen = 6 - (chosenDoor+doorWithCar);
        }
        return doorToOpen;
    }
    
    // The contestant decides on a second guess, aided (?) by knowing that the given
    // door has a goat.  Return the updated guess.
    private int newGuess (int goatDoor, int firstChoice) {
        int guess;
        control.say ("Would you like to stick with your guess, or switch? Please choose a door.", 0);
        control.picture (control.MONTY_HANDLING_CHOICE2, 0);
        control.picture (control.CONTESTANT_THINKING, 5000);
        guess = player.guess2 (goatDoor);
        if (guess == firstChoice) {
            control.say ("Sticking with your guess of " + guess + " ...", 2000);
        } else {
            control.say ("You decided to switch to door " + guess + " ...", 2000);
        }
        return guess;
    }
    
    // The contestant has made a final guess (the value in secondChoice).
    // Open the contestant's chosen door, along with the door hiding the car
    // if the contestant guessed incorrectly.
    // Then reveal the result.  Return 1 if win, 0 if loss.
    private int gameResult (int doorWithCar, int goatDoor, int secondChoice) {
        control.say ("Let's see what's behind the door you chose, door #" + secondChoice + ".", 3000);
        if (secondChoice == doorWithCar) {
            stage.open (secondChoice);
            control.say ("You win!", 0);
            control.picture (control.MONTY_WIN, 1000);
            control.picture (control.CONTESTANT_WIN, 0);
            return 1;
        } else {
            stage.open (secondChoice);
            control.say ("Oh, no!", 3000);
            control.say ("Here is the car behind door number " + doorWithCar + ".", 3000);
            stage.open (doorWithCar);
            control.say ("Sorry, you lose.", 0);
            control.picture (control.MONTY_LOSE, 1000);
            control.picture (control.CONTESTANT_LOSE, 0);
            return 0;
        }
    }
}
