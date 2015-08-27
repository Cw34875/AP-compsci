public class Path {
    
	// A Path is a connection between two rooms, a source Room and a target Room 
	// Each path has a direction that it exits the source room.  The direction
	// is simply a string, such as "east", "up", or "the hole in the wall".
	
    private Room source;
    private Room target;
    private String direction;
    private String description;
    
    public Path(Room source, Room target, String dir) {
        setSource(source);
        setTarget(target);
        setDirection(dir);
    }

    // source Room setter
    public void setSource(Room source) {
        this.source = source;
    }

    // source Room getter
    public Room getSource() {
        return source;
    }

    // target Room setter
    public void setTarget(Room target) {
        this.target = target;
    }

    // target room getter
    public Room getTarget() {
        return target;
    }

    // direction setter
    public void setDirection(String direction) {
        this.direction = direction;
    }

    // direction getter
    public String getDirection() {
        return direction;
    }

    // returns the Room that the player traveling this path arrives at
    public Room travelDestination() {
        return target;
    }
    
}  // end class Path
