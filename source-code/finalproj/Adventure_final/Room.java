import java.util.ArrayList;

public class Room{

    // protected because subclasses can get to it...
    protected String name;
    protected String description;

    private ArrayList<Path> exits;
    private ArrayList<Item> items;

    // constructor.
    public Room(String name, String desc) {
        this.name = name;
        this.description = desc;
        exits = new ArrayList<Path>();
        items = new ArrayList<Item>();
    }

    // setters and getters
        
    public void setDescription(String newDesc) {
        this.description = newDesc;
    }
    
    // adds a path from this room
    public void addExit(Room target, String dir) {
        Path path = new Path(this, target, dir);
        exits.add(path);
    }


    public void printDescription() {
        System.out.println(description);
        printItems();
        printExits();
    }

    
    ////// Movement
    //////
    
    // method that gets called when you first enter a room; returns a boolean
    // indicating whether entry was sucessful.
    // This might get overridden by subclasses if something fancy should happen.
    public boolean enterRoom() {
        printDescription();
        return true;
    }
    
    // Returns a boolean indicating a successful exit.  This version
    //  does nothing, but subclasses might override this.
    public boolean exitRoom() {
        return true;
    }

    // When the player tries to move, it calls its room's tryToMove method.
    // This method returns null if unsuccessful. Otherwise, it returns the
    // room that the player has moved to.
    public Room tryToExit(String direction) {
        //check if this is a valid direction
        Path path = getExit(direction);
        if (path != null) {
            if (exitRoom())  {            
                return path.travelDestination();
            }
        }
        return null;
    }

    
    // returns the path object if the argument is a direction to that path.
    //  if no such direction exists, returns null.
    public Path getExit (String possibleExit) {
        for (Path p: exits) {
            if (p.getDirection().equalsIgnoreCase(possibleExit)) {
                return p;
            }
        }
        return null;
    }
 
    public void printExits() {
        if (exits.size() == 0) {
            System.out.println("There are no exits, and therefore no way out of here!");
        } else {
            int i = 0;
            System.out.print("The exits are ");
            for (Path p: exits) {
                System.out.print(p.getDirection());
                i++;
                if (i != exits.size()) {
                    System.out.print(", ");
                } else {
                    System.out.print(".");
                }
                if (i == (exits.size() - 1)) {
                    System.out.print("and ");
                }
            }
            System.out.println();
        }
    }
 
    

    
    
    //////  Items
    //////
    
    public void addItem(Item item) {
        items.add(item);
    }

    public Item getItem(String possibleName) {
        for (Item item: items) {
            if (item.isReference(possibleName)) {
                return item;
            }
        }
        return null;
    }

    public void removeItem(Item itemToRemove) {
        int i;
        for (i = 0; i < items.size(); i++) {
            if (items.get(i).equal(itemToRemove)) {
                items.remove(i);
                break;
            }
        }
    }

    
    // this prints a nice comma separated list of items.  
    protected void printItems() {
        if (items.size() == 0) return;
        int i;
        System.out.print("There is ");
        // do the first set of items up to the second to last
        for (i=0; i < (items.size()-1); i++) {
            System.out.print("a ");
            items.get(i).printInventoryItem();
            System.out.print(", ");
        }
        if (items.size() >=2) {
            System.out.print("and ");
        }
        System.out.print("a ");
        items.get(i).printInventoryItem();
        System.out.println(" here.");
    }

    
    


} // end Room
