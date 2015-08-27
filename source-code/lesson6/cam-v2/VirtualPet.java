/* Virtual Pet, version 2
 * 
 * @author Ratchet
 * @author ?
 */
public class VirtualPet {
    
    VirtualPetFace face;
    int hunger = 0;   // how hungry the pet is.
    int tiredness = 0;
    
    // constructor
    public VirtualPet() {
        face = new VirtualPetFace();
        face.setImage("normal");
        face.setMessage("Hello.");
    }
    
    public void feed() {
        if (hunger > 10) {
            hunger = hunger - 10;
        } else {
            hunger = 0;
        }
        tiredness = tiredness + 22;
        if (tiredness > 15) {
            face.setMessage("I'm... too... sleepy...");
            sleep();
        } else {
            face.setMessage("Yum, thanks");
            face.setImage("normal");
        }
    }
    
    public void exercise() {
        hunger = hunger + 3;
        tiredness = tiredness + 1;
        if (tiredness > 15) {
            face.setMessage("I'm... too... sleepy...");
            feed();
        }
        else if (hunger > 12) {
            face.setMessage("urk, I'm too hungry!");
            face.setImage("dead");
        } else {   
            face.setMessage("1, 2, 3, jump.  Whew.");
            face.setImage("tired");
        }
    }
    
    public void sleep() {
        hunger = hunger + 1;
        tiredness = 10;
        if (tiredness > 12) {
            face.setMessage("awww, I got too hungry!");
            face.setImage("dead");
        } else {
            face.setMessage("zzzzzzzzzz");
            face.setImage("asleep");
        }
    }


} // end Virtual Pet
