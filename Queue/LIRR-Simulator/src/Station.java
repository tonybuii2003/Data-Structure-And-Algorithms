// Name: Phi Long Bui, ID: 114555975, R-01
/**
 * This class repersents a Station that ontain two queues, two boolean source
 * instances,
 * the staion name, a boolean to keep track of source instances, every class
 * total wait time,
 * and count for the passenger that got in the train.
 * 
 * @author Phi Long Bui
 */
public class Station {
    private PassengerQueue firstClass;
    private PassengerQueue secondClass;
    private BooleanSource firstArrival;
    private BooleanSource secondArrival;
    private String stationName;
    private boolean firstClassOccurs;
    private boolean secondClassOccurs;
    private int firstClassTotalWaitTime, secondClassTotalWaitTime;
    private int firstCount, secondCount;

    /**
     * The Contructor creates a new Staion object
     * 
     * @param firstClassProbability
     *                               The probability of the first class.
     * @param secondClassProbability
     *                               The probability of the second class
     * @param stationName
     *                               The station name
     * @throws IllegalArgumentException
     *                                  when probabality is not valid.
     */
    public Station(double firstClassProbability, double secondClassProbability, String stationName)
            throws IllegalArgumentException {
        this.firstClass = new PassengerQueue();
        this.secondClass = new PassengerQueue();
        this.firstArrival = new BooleanSource(firstClassProbability);
        this.secondArrival = new BooleanSource(secondClassProbability);
        this.stationName = stationName;
        this.firstClassOccurs = false;
        this.secondClassOccurs = false;
        this.firstClassTotalWaitTime = 0;
        this.firstCount = 0;
        this.secondCount = 0;
    }

    /**
     * This method returns the total waiting time for the first class
     * 
     * @return
     *         The total waiting time for the first class
     */
    public int getFirstClassTotalWaitTime() {
        return firstClassTotalWaitTime;
    }

    /**
     * This method returns the total waiting time for the second class
     * 
     * @return
     *         The total waiting time for the second class
     */
    public int getSecondClassTotalWaitTime() {
        return secondClassTotalWaitTime;
    }

    /**
     * This method returns the boolean source for first class
     * 
     * @return
     *         The boolean source for first class
     */
    public BooleanSource getFirstArrival() {
        return firstArrival;
    }

    /**
     * This method returns the boolean source for second class
     * 
     * @return
     *         The boolean source for second class
     */
    public BooleanSource getSecondArrival() {
        return secondArrival;
    }

    /**
     * This method return the PassengerQueue for the first class
     * 
     * @return
     *         The PassengerQueue for the first class
     */
    public PassengerQueue getFirstClass() {
        return firstClass;
    }

    /**
     * This method return the PassengerQueue for the second class
     * 
     * @return
     *         The PassengerQueue for the second class
     */
    public PassengerQueue getSecondClass() {
        return secondClass;
    }

    /**
     * This method returns the station name
     * 
     * @return
     *         The station name
     */
    public String getStationName() {
        return stationName;
    }

    /**
     * This method returns the amount of served passenger for first class
     * 
     * @return
     *         The amount of served passenger for first class
     */
    public int getFirstCount() {
        return firstCount;
    }

    /**
     * This method returns the amount of served passenger for second class
     * 
     * @return
     *         The amount of served passenger for second class
     */
    public int getSecondCount() {
        return secondCount;
    }

    /**
     * This method set occurs to a given boolean occurs for first class
     * 
     * @param occurs
     *               The given boolean occurs.
     * 
     */
    public void setFirstClassOccurs(boolean occurs) {
        this.firstClassOccurs = occurs;
    }

    /**
     * This method set occurs to a given boolean occurs for second class.
     * 
     * @param occurs
     *               The given boolean occurs.
     * 
     */
    public void setSecondClassOccurs(boolean occurs) {
        this.secondClassOccurs = occurs;
    }

    /**
     * This method set the count to a given count for the first class.
     * 
     * @param count
     *              The given count.
     */
    public void setFirstCount(int count) {
        this.firstCount = count;
    }

    /**
     * This method set the count to a given count for the second class.
     * 
     * @param count
     *              The given count.
     */
    public void setSecondCount(int count) {
        this.secondCount = count;
    }

    /**
     * This method sets first class total wait time to a given time
     * 
     * @param time
     *             The given time
     */
    public void setFirstClassTotalWaitTime(int time) {
        this.firstClassTotalWaitTime = time;
    }

    /**
     * This method sets second class total wait time to a given time
     * 
     * @param time
     *             The given time
     */
    public void setSecondClassTotalWaitTime(int time) {
        this.secondClassTotalWaitTime = time;
    }

    /**
     * This method simulate the Times step for the station.
     */
    public void simulateTimestep() {
        if (firstClassOccurs) {
            System.out.printf("First class passenger ID %d arrives\n", firstClass.getReverse().peek().getId());
        } else {
            System.out.println("No first class passenger arrives");
        }
        if (secondClassOccurs) {
            System.out.printf("Second class passenger ID %d arrives\n", secondClass.getReverse().peek().getId());
        } else {
            System.out.println("No second class passenger arrives");
        }

    }

}
