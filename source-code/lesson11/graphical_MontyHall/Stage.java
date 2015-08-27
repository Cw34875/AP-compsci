// This class keeps track of what's behind each door, and which doors are open.

public class Stage {
	
	private boolean door1isClosed;
	private boolean door2isClosed;
	private boolean door3isClosed;
	private int doorWithCar;
	private Controller control;
	
	public Stage (int door, Controller c) {
		control = c;
		door1isClosed = true;
		control.close (1);
		door2isClosed = true;
		control.close (2);
		door3isClosed = true;
		control.close (3);
		doorWithCar = door;
	}

	// Return true if the given door is closed; return false if it's open.
	public boolean isClosed (int doorNumber) {
		if (doorNumber == 1) {
			return door1isClosed;
		} else if (doorNumber == 2) {
			return door2isClosed;
		} else if (doorNumber == 3) {	
			return door3isClosed;
		}
		return false;
	}
	
	// Open the given door.  Currently has no effect if the door is already closed.
	public void open (int doorNumber) {
		if (doorNumber == 1) {
			door1isClosed = false;
		} else if (doorNumber == 2) {
			door2isClosed = false;
		} else if (doorNumber == 3) {
			door3isClosed = false;
		}
		control.open (doorNumber, doorWithCar != doorNumber);
	}
}
