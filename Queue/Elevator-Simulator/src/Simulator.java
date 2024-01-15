// Name: Phi Long Bui, ID: 114555975, R-30
/**
 * This class will be used to actually carry out the simulation
 * 
 * @author Phi Long Bui
 */
public class Simulator {
    private static int totalRequest, totalWaitTime = 0;

    /**
     * This method accepts the following four parameters, carries out the
     * simulation, and prints the results
     * 
     * @param probability
     *                         The probability
     * @param numbersOfFloor
     *                         The number of floor
     * @param numberOfElevator
     *                         The number of elevators
     * @param length
     *                         The length
     */
    public static void simulate(double probability, int numbersOfFloor, int numberOfElevator, int length) {
        BooleanSource requestBool = new BooleanSource(probability);
        RequestQueue queue = new RequestQueue();
        Elevator[] elevators = new Elevator[numberOfElevator];
        for (int i = 0; i < elevators.length; i++) {
            elevators[i] = new Elevator();
        }
        for (int i = 1; i <= length; i++) {
            System.out.printf("Step %d: ", i);
            if (requestBool.requestArrived()) {
                int sourceFloor = (int) (Math.random() * numbersOfFloor) + 1;
                int destinationFloor = (int) (Math.random() * numbersOfFloor) + 1;
                Request request = new Request(sourceFloor, destinationFloor);
                request.setTimeEntered(i);
                queue.enqueue(request);
                totalRequest += 1;

                System.out.printf("A request arrives from Floor %d to Floor %d\n", request.getSourceFloor(),
                        request.getDestinationFloor());
            } else {
                System.out.printf("Nothing Arrives\n");
            }

            System.out.print("Requests: \n");
            System.out.print("Elevators: ");
            String str = "";
            for (Elevator elevator : elevators) {
                if (elevator.getRequest() == null && !queue.isEmpty()) {
                    elevator.setRequest(queue.dequeue());

                }

                if (elevator.getRequest() != null) {
                    if (elevator.getRequest().getSourceFloor() == elevator.getCurrentFloor()
                            && elevator.getRequest().getDestinationFloor() == elevator.getRequest()
                                    .getSourceFloor()) {
                        elevator.setElevatorState(Elevator.IDLE);
                        elevator.setHasPassenger(false);
                        totalWaitTime += elevator.getRequest().getTotalWaitTime();
                        elevator.setRequest(null);
                        str += (elevator.toString() + ", ");
                        continue;
                    }
                    if (elevator.getCurrentFloor() == elevator.getRequest().getSourceFloor()) {
                        elevator.getRequest().setTotalWaitTime(Math
                                .abs(elevator.getRequest().getTimeEntered() - i));
                        elevator.setElevatorState(Elevator.TO_DESTINATION);

                        elevator.setHasPassenger(true);
                    }
                    if (elevator.getCurrentFloor() != elevator.getRequest().getSourceFloor()
                            && elevator.getElevatorState() == Elevator.IDLE) {
                        elevator.setElevatorState(Elevator.TO_SOURCE);
                    }
                    if (elevator.getCurrentFloor() == elevator.getRequest().getSourceFloor()) {
                        elevator.setHasPassenger(true);
                    }
                    switch (elevator.getElevatorState()) {
                        case Elevator.TO_SOURCE: {
                            if (elevator.getRequest().getSourceFloor() < elevator.getCurrentFloor()) {
                                elevator.setCurrentFloor(elevator.getCurrentFloor() - 1);
                            } else {
                                elevator.setCurrentFloor(elevator.getCurrentFloor() + 1);
                            }
                        }
                            break;
                        case Elevator.TO_DESTINATION: {
                            if (elevator.getRequest().getDestinationFloor() < elevator.getCurrentFloor()) {
                                elevator.setCurrentFloor(elevator.getCurrentFloor() - 1);
                            } else {
                                elevator.setCurrentFloor(elevator.getCurrentFloor() + 1);
                            }
                        }

                    }
                    if ((elevator.getCurrentFloor() == elevator.getRequest().getDestinationFloor()
                            && elevator.getHasPassenger())) {
                        elevator.setElevatorState(Elevator.IDLE);
                        elevator.setHasPassenger(false);
                        totalWaitTime += elevator.getRequest().getTotalWaitTime();
                        elevator.setRequest(null);

                    }
                }

                str += (elevator.toString() + ", ");
            }
            System.out.print(str.substring(0, str.length() - 2));

            System.out.print("\n");

        }
        System.out.println("END OF SIMULATION");
        System.out.printf("Total Wait Time: %d\n", totalWaitTime);
        System.out.printf("Total Requests: %d\n", totalRequest);
        System.out.printf("Average Wait Time: %.2f", (double) totalWaitTime / totalRequest);
    }
}
