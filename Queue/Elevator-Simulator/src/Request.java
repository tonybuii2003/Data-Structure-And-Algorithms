// Name: Phi Long Bui, ID: 114555975, R-30
/**
 * This class contains the source, destination, and the entered time of the
 * floor
 * 
 * @author Phi Long Bui
 */
public class Request {
    private int sourceFloor, destinationFloor, timeEntered;
    private int totalWaitTime = 0;

    /**
     * This Contructor is for the Request
     * 
     * @param sourceFloor
     *                         The source
     * @param destinationFloor
     *                         The destination
     */
    public Request(int sourceFloor, int destinationFloor) {
        this.sourceFloor = sourceFloor;
        this.destinationFloor = destinationFloor;
    }

    /**
     * This method gets the source of the floor
     * 
     * @return
     *         The source of the floor
     */
    public int getSourceFloor() {
        return sourceFloor;
    }

    /**
     * This method gets the destination of the floor
     * 
     * @return
     *         The destination of the floor
     */
    public int getDestinationFloor() {
        return destinationFloor;
    }

    /**
     * This method gets the time entered
     * 
     * @return
     *         The time entered
     */
    public int getTimeEntered() {
        return timeEntered;
    }

    /**
     * This method gets the total wait time of the request
     * 
     * @return
     *         The total wait time of the request
     */
    public int getTotalWaitTime() {
        return totalWaitTime;
    }

    /**
     * This method sets the source to the given source
     * 
     * @param source
     *               The given source
     */
    public void setSourceFloor(int source) {
        this.sourceFloor = source;
    }

    /**
     * This method set the destination to the given destination
     * 
     * @param destination
     *                    The given destination
     */
    public void setDestinationFloor(int destination) {
        this.destinationFloor = destination;

    }

    /**
     * This method sets the timeEntered to the given timeEntered
     * 
     * @param timeEntered
     *                    The given timeEntered
     */
    public void setTimeEntered(int timeEntered) {
        this.timeEntered = timeEntered;
    }

    /**
     * This method sets the current total wait time to the given time
     * 
     * @param totalWaitTime
     *                      The given total wait time
     */
    public void setTotalWaitTime(int totalWaitTime) {
        this.totalWaitTime = totalWaitTime;
    }

    /**
     * This toString method returns a string contain the source, destination and
     * timeEntered
     * 
     * @return
     *         A string contain the source, destination and timeEntered
     */
    public String toString() {
        String str = String.format("(%d, %d, %d)", sourceFloor, destinationFloor, timeEntered);
        return str;
    }

}
