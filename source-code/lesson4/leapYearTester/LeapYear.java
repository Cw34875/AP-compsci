public class LeapYear{

    private int year;
    private boolean isLeapYear;

    public LeapYear(int y) {
        year = y;
        isLeapYear = false;
    }

    public void determineLeapYear() {
        if (year % 400 == 0) {
            isLeapYear = true;
        } else if (year % 100 == 0) {
            isLeapYear = false;
        } else if (year % 4 == 0) {
            isLeapYear = true;
        } else {
            isLeapYear = false;
        }
    }
    
    public boolean isLeapYear(){
        return isLeapYear;
    }

}