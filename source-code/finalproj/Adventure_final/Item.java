public class Item{

    private String name;
    private String description;
    private boolean canTake;
    
    public Item(String name, String description, boolean canTake) {
        this.name = name;
        this.description = description;
        this.canTake = canTake;
    }
    
    // getters and setters
    
    public String getDescription() {
        return description;
    }

    
    public String getName() {
        return name;
    }
    
    // returns boolean representing whether this item can be taken
    public boolean take() {
        if (canTake) {
            printItemTake();
        } else {
            printItemCantTake();
        }
        return canTake;     
    }

    // returns boolean on whether this item can be dropped
    public boolean drop() {
        printItemDrop(); 
        return true;
    }

    
    
    // boolean representing whether this string is a name for the item.
    public boolean isReference(String possibleName) {
        return (name.equalsIgnoreCase(possibleName));
    }
        
    // items are equal if they are the same object (don't compare field contents)
    public boolean equal(Item itemToCompare) {
        return (this == itemToCompare);   
    }
        
    // This method is likely to be overridden in sub-classes, since interesting things
    //  should often happen when an item is used.
    // returns true if item should be removed (from Player) after use -- i.e., it gets used up.
    public boolean use(Room currentRoom, Player player) {
        System.out.println("You use " + name + ".  Nothing much happens.");
        return false;
    }
    
    
    /////// Printing
    ///////
    // override these in subclasses to make things more interesting.
    
    public void printInventoryItem() {
        System.out.print(name);
    }
    
    private void printItemTake() {
        System.out.println("You pick up the " + name + ".");
    }
    
    private void printItemCantTake() {
        System.out.println("You can't pick up the " + name + "."); 
    }
        
    private void printItemDrop() {
        System.out.println("You drop the " + name + ".");
    }
    
}  // end Item
