// Name: Phi Long Bui, ID: 114555975, R-01
/**
 * This class repersents which contains an integer id,
 * the arrival time, and a boolean for isFirstClass.
 * 
 * @author Phi Long Bui
 */
public class Passenger {
    private int id, arrivalTime;
    private boolean isFirstClass;

    /**
     * This Constructor used to create a new Passenger object
     * 
     * @param id
     *                     The passenger's ID
     * @param arrivalTime
     *                     The passenger's arrival time.
     * @param isFirstClass
     *                     a boolean to identify If the passenger is first class or
     *                     not.
     */
    public Passenger(int id, int arrivalTime, boolean isFirstClass) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.isFirstClass = isFirstClass;
    }

    /**
     * This method returns the passenger's ID.
     * 
     * @return
     *         The passenger's ID
     */
    public int getId() {
        return id;
    }

    /**
     * This method returns the passenger's arrival time.
     * 
     * @return
     *         The passenger's arrival time.
     */
    public int getArrivalTime() {
        return arrivalTime;
    }

    /**
     * This method returns a boolean to check whether the passenger is first class
     * or not.
     * 
     * @return
     *         true if the passenger is first class, false for second class.
     */
    public boolean getIsFirstClass() {
        return isFirstClass;
    }

    /**
     * This method sets the passenger's ID to a given ID.
     * 
     * @param id
     *           The given ID.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * This method sets the passenger's arrival time to a given arrival time.
     * 
     * @param arrivalTime
     *                    The given arrival time
     */
    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    /**
     * This method sets the passenger's boolean first class to a
     * given boolean first class
     * 
     * @param isFirstClass
     *                     The given boolean first class
     */
    public void setIsFirstClass(boolean isFirstClass) {
        this.isFirstClass = isFirstClass;
    }

    /**
     * This is a toString method returns the String for each passenger.
     * 
     * @return
     *         The String for each passenger
     */
    public String toString() {
        return String.format("P%d@T%d", id, arrivalTime);
    }
}
