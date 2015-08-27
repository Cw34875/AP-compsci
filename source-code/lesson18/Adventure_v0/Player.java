import java.util.ArrayList;


public class Player {

    private World myWorld;
    private Room currentRoom;
    private String myName = "";
    
    public Player(World world) {
        myWorld = world;
    }
    
    public Player(String name, World world) {
        myName = name;
        myWorld = world;
    }

    public void setCurrentRoom(Room room) {
        currentRoom = room;
    }
    
    public Room getCurrentRoom() {
        return( currentRoom );
    }
    
    //////
    //////   Handle Commands
    //////
    
    public void dispatch(Command turn) {
        if (turn.isLookRoom()) {
            actionLookRoom();
            
        } else if (turn.isTravel(currentRoom)) {
            actionTravel(turn);

        } else {
            System.out.println("Huh?");
        }
    }


    
    public void actionTravel(Command turn) {
        String newDir = turn.getDirectionReference();
        Room newRoom = currentRoom.tryToExit(newDir);
        if (newRoom == null) {
            System.out.println("You can't go that direction from here.");
        } else {
            if (newRoom.enterRoom()) {
                currentRoom = newRoom;
            } else {
                System.out.println("You can't go there.");
            }
        }
    }


    public void actionLookRoom() {
        currentRoom.printDescription();
    }
    
    
    




}
