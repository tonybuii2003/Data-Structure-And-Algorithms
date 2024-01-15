// Name: Phi Long Bui, ID: 114555975, R-01
/**
 * This class repersents a train which contains firstCapacity, secondCapacity,
 * timeUntilNextArrival, staion, and current station
 */
public class Train {
    private int firstCapacity;
    private int secondCapacity;
    private int timeUntilNextArrival;
    private int currentStation;
    private Station station;

    /**
     * This Contructor creates a Train Object
     * 
     * @param firstCapacity
     *                       The capacity of the train's first class
     * @param secondCapacity
     *                       The capacity of the train's second class
     */
    public Train(int firstCapacity, int secondCapacity) {
        this.firstCapacity = firstCapacity;
        this.secondCapacity = secondCapacity;
        this.timeUntilNextArrival = 0;
        this.currentStation = 0;
    }

    /**
     * This method return the capacity of the second class
     * 
     * @return
     *         The capacity of the second class
     */
    public int getSecondCapacity() {
        return secondCapacity;
    }

    /**
     * This method return the capacity of the first class
     * 
     * @return
     *         The capacity of the first class
     */
    public int getFirstCapacity() {
        return firstCapacity;
    }

    /**
     * This method return the train's current staion
     * 
     * @return
     *         the train's current staion
     */
    public Station getStation() {
        return station;
    }

    /**
     * This method returns the index of current station
     * 
     * @return
     *         The index of current station
     */
    public int getCurrentStation() {
        return currentStation;
    }

    /**
     * This method set the current station to a given station
     * 
     * @param station
     *                The given station
     */
    public void setStation(Station station) {
        this.station = station;
    }

    /**
     * This method sets the current station index to a given index
     * 
     * @param currentStation
     *                       The given index
     */
    public void setCurrentStation(int currentStation) {
        this.currentStation = currentStation;
    }

    /**
     * This method sets the first class cabacity to a given cabacity
     * 
     * @param capacity
     *                 The given capacity
     */
    public void setSecondCapacity(int capacity) {
        this.secondCapacity = capacity;
    }

    /**
     * This method sets the second class cabacity to a given cabacity
     * 
     * @param capacity
     *                 The given capacity
     */
    public void setFirstCapacity(int capacity) {
        this.firstCapacity = capacity;
    }

    /**
     * This method sets the time until next arrival to a given time
     * 
     * @param time
     *             The given time
     */
    public void setTimeUntilNextArrival(int time) {
        this.timeUntilNextArrival = time;
    }

    /**
     * This method returns the time until the next arrival
     * 
     * @return
     *         The time until the next arrival
     */
    public int getTimeUntilNextArrival() {
        return timeUntilNextArrival;
    }

    /**
     * This method simulate the time step for the train
     */
    public void simulateTimeStep() {
        String embarkFirstClass = "";
        String embarkSecondClass = "";
        Passenger removedPassenger;
        while (!station.getFirstClass().isEmpty() && firstCapacity != 0) {
            removedPassenger = station.getFirstClass().dequeue();
            station.setFirstClassTotalWaitTime(
                    station.getFirstClassTotalWaitTime() + (LIRRSimulator.time - removedPassenger.getArrivalTime()));
            station.setFirstCount(station.getFirstCount() + 1);
            embarkFirstClass += String.format("P%d, ", removedPassenger.getId());
            firstCapacity--;
        }
        while (!station.getFirstClass().isEmpty() && firstCapacity == 0 && secondCapacity != 0) {
            removedPassenger = station.getFirstClass().dequeue();
            station.setFirstClassTotalWaitTime(
                    station.getFirstClassTotalWaitTime() + (LIRRSimulator.time - removedPassenger.getArrivalTime()));
            station.setFirstCount(station.getFirstCount() + 1);
            embarkSecondClass += String.format("P%d, ", removedPassenger.getId());
            secondCapacity--;
        }
        while (!station.getSecondClass().isEmpty() && secondCapacity != 0) {
            removedPassenger = station.getSecondClass().dequeue();
            station.setSecondClassTotalWaitTime(
                    station.getSecondClassTotalWaitTime() + (LIRRSimulator.time - removedPassenger.getArrivalTime()));
            station.setSecondCount(station.getSecondCount() + 1);
            embarkSecondClass += String.format("P%d, ", removedPassenger.getId());
            secondCapacity--;
        }
        if (embarkFirstClass.equals(""))
            embarkFirstClass = "none";
        else
            embarkFirstClass = embarkFirstClass.substring(0, embarkFirstClass.length() - 2)
                    + embarkFirstClass.substring(embarkFirstClass.length() - 1);

        if (embarkSecondClass.equals(""))
            embarkSecondClass = "none";
        else
            embarkSecondClass = embarkSecondClass.substring(0, embarkSecondClass.length() - 2)
                    + embarkSecondClass.substring(embarkSecondClass.length() - 1);

        System.out.printf("Passengers embarking in first class: %s\n", embarkFirstClass);

        System.out.printf("Passengers embarking in second class: %s\n", embarkSecondClass);
    }
}
