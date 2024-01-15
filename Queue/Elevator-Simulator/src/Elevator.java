// Name: Phi Long Bui, ID: 114555975, R-30
/**
 * This class persent an elevator
 * 
 * @author Phi Long Bui
 */
public class Elevator {
    private int currentFloor;
    static final int IDLE = 1, TO_SOURCE = 2, TO_DESTINATION = 3;
    private int elevatorState;
    private Request request;
    private boolean hasPassenger;

    /**
     * This Contructor is for an Elevator
     * 
     */
    public Elevator() {
        this.request = null;
        this.currentFloor = 1;
        this.elevatorState = IDLE;
        this.hasPassenger = false;

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
    public Request getRequest() {
        return request;
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
     * This method sets the current request to the given request
     * 
     * @param request
     *                The given request
     */
    public void setRequest(Request request) {
        this.request = request;
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
        if (request != null) {
            requestStr = request.toString();
        } else {
            requestStr = "---";
        }
        String str = String.format("[Floor %d, %s, %s]", currentFloor, state, requestStr);
        return str;
    }
}
