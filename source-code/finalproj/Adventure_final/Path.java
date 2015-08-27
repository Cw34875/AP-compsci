public class Path {
    
    private Room source;
    private Room target;
    private String direction;
    private String description;
    
    public Path(Room source, Room target, String dir) {
        setSource(source);
        setTarget(target);
        setDirection(dir);
    }

    public void setSource(Room source) {
        this.source = source;
    }

    public Room getSource() {
        return source;
    }

    public void setTarget(Room target) {
        this.target = target;
    }

    public Room getTarget() {
        return target;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getDirection() {
        return direction;
    }

    // returns the Room that the player traveling this path arrives at
    public Room travelDestination() {
        return target;
    }
    
}  // end class Path
