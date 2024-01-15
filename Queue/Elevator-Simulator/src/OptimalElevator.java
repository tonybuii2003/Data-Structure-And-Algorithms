import java.util.ArrayList;

// Name: Phi Long Bui, ID: 114555975, R-30
/**
 * This class persent an elevator
 * 
 * @author Phi Long Bui
 */
public class OptimalElevator {
    private int currentFloor;
    private boolean hitTopFloor;
    static final int IDLE = 1, TO_SOURCE = 2, TO_DESTINATION = 3;
    static final boolean UP = true, DOWN = false;
    private int elevatorState;
    private boolean upOrDown;
    private ArrayList<Request> requests;
    private boolean hasPassenger;

    /**
     * This Contructor is for an Elevator
     * 
     */
    public OptimalElevator() {
        this.requests = new ArrayList<Request>();
        this.currentFloor = 1;
        this.elevatorState = IDLE;
        this.upOrDown = UP;
        this.hasPassenger = false;
        this.hitTopFloor = false;
    }

    /**
     * This method gets the elevator current floor
     * 
     * @return
     *         the elevator current floor
     */
    public int getCurrentFloor() {
        return currentFloor;
    }

    /**
     * This method gets a boolean value to check if the the elevator has a passenger
     * 
     * @return
     *         A boolean value to check if the the elevator has a passenger
     */
    public boolean getHasPassenger() {
        return hasPassenger;
    }

    /**
     * This method returns
     * 
     * @return
     *         The request contain in the elevator
     */
    public ArrayList<Request> getRequests() {
        return requests;
    }

    /**
     * This method gets the current elevator state of the elevator
     * 
     * @return
     *         The current elevator state of the elevator
     */
    public int getElevatorState() {
        return elevatorState;
    }

    /**
     * This method gets the up or down state of the elevator
     * 
     * @return
     *         The up or down state of the elevator
     */
    public boolean getUpOrDown() {
        return upOrDown;
    }

    /**
     * This method gets the boolean value saying if the elevator is already at top
     * floor
     * 
     * @return
     */
    public boolean getHitTopFloor() {
        return hitTopFloor;
    }

    /**
     * This method sets the current floor to the given floor
     * 
     * @param currentFloor
     *                     The given floor
     */
    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    /**
     * This method sets the current elevator state to the given state
     * 
     * @param elevatorState
     *                      The given state
     */
    public void setElevatorState(int elevatorState) {
        this.elevatorState = elevatorState;
    }

    /**
     * This method sets the current hasPassenger to the given boolean
     * 
     * @param hasPassenger
     *                     The current boolean
     */
    public void setHasPassenger(boolean hasPassenger) {
        this.hasPassenger = hasPassenger;
    }

    /**
     * This method sets the up or down state of the elevator to the given state
     * 
     * @param upOrDown
     *                 The given up or down state
     */
    public void setUpOrDown(boolean upOrDown) {
        this.upOrDown = upOrDown;
    }

    /**
     * This method sets the current requests to the given request
     * 
     * @param request
     *                The given requests
     */
    public void setRequests(ArrayList<Request> request) {
        this.requests = request;
    }

    /**
     * This method set the current hitTopFloor to the given boolean
     * 
     * @param hitTopFloor
     *                    The given boolean
     */
    public void setHitTopFloor(boolean hitTopFloor) {
        this.hitTopFloor = hitTopFloor;
    }

    /**
     * This toString method returns a string contain the infomation from the
     * elevator including its current floor, state, request
     * 
     * @return
     *         A string contain the infomation from the
     *         elevator including its current floor, state, request
     */
    public String toString() {
        String state = "";
        String requestStr = "";
        String upOrDownStr = "";
        if (upOrDown) {
            upOrDownStr = "UP";
        } else {
            upOrDownStr = "DOWN";
        }
        switch (elevatorState) {
            case IDLE: {
                state = "IDLE";
            }
                break;
            case TO_SOURCE: {
                state = "TO_SOURCE";
            }
                break;
            case TO_DESTINATION: {
                state = "TO_DESTINATION";
            }
                break;
        }
        if (requests.size() != 0) {
            for (Request i : requests) {
                requestStr += i.toString() + ", ";
            }
            requestStr = requestStr.substring(0, requestStr.length() - 2);
        } else {
            requestStr = "---";
        }
        String str = String.format("[Floor %d, %s, %s, %s]", currentFloor, state, requestStr, upOrDownStr);
        return str;
    }
}
