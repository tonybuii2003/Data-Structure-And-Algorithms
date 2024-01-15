// Name: Phi Long Bui, ID: 114555975, R-30
public class OptimalSimulator {
    private static int totalRequest, totalWaitTime = 0;

    /**
     * This method accepts the following four parameters, carries out the
     * simulation, and prints the results using the Elevator Algorithm
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
        RequestQueue[] queues = new RequestQueue[numbersOfFloor + 1];
        OptimalElevator[] elevators = new OptimalElevator[numberOfElevator];
        for (int i = 0; i < queues.length; i++) {
            queues[i] = new RequestQueue();
        }
        for (int i = 0; i < elevators.length; i++) {
            elevators[i] = new OptimalElevator();
        }
        for (int i = 1; i <= length; i++) {
            System.out.printf("Step %d: ", i);
            if (requestBool.requestArrived()) {
                int sourceFloor = (int) (Math.random() * numbersOfFloor) + 1;
                int destinationFloor = (int) (Math.random() * numbersOfFloor) + 1;
                Request request = new Request(sourceFloor, destinationFloor);
                request.setTimeEntered(i);
                queues[sourceFloor].enqueue(request);
                totalRequest += 1;
                System.out.printf("A request arrives from Floor %d to Floor %d\n", request.getSourceFloor(),
                        request.getDestinationFloor());
            } else {
                System.out.printf("Nothing Arrives\n");
            }
            int topFloorRequest = Integer.MIN_VALUE;
            for (int q = 0; q < queues.length; q++) {
                if (!queues[q].isEmpty()) {
                    if (q > topFloorRequest) {
                        topFloorRequest = q;
                    }
                }
            }
            System.out.print("Requests: \n");
            System.out.print("Elevators: ");
            String str = "";
            for (OptimalElevator elevator : elevators) {
                while (!queues[elevator.getCurrentFloor()].isEmpty()) {
                    Request tmp = queues[elevator.getCurrentFloor()].dequeue();

                    tmp.setTotalWaitTime(Math
                            .abs(tmp.getTimeEntered() - i));
                    elevator.getRequests().add(tmp);

                    if (tmp.getDestinationFloor() == tmp.getSourceFloor()
                            && tmp.getSourceFloor() == elevator.getCurrentFloor()) {
                        elevator.getRequests().remove(tmp);
                    }

                }
                for (Request p : elevator.getRequests()) {
                    if (p.getDestinationFloor() > topFloorRequest) {
                        topFloorRequest = p.getDestinationFloor();
                    }
                }
                if ((elevator.getCurrentFloor() == numbersOfFloor) || (elevator.getCurrentFloor() == topFloorRequest
                        && elevator.getUpOrDown() == OptimalElevator.UP)) {
                    elevator.setUpOrDown(OptimalElevator.DOWN);
                }
                if (!elevator.getUpOrDown() && elevator.getCurrentFloor() == 1 && !elevator.getRequests().isEmpty()) {
                    elevator.setUpOrDown(OptimalElevator.UP);
                }
                if (topFloorRequest > 0 && topFloorRequest != 1) {
                    if (elevator.getUpOrDown()) {
                        elevator.setCurrentFloor(elevator.getCurrentFloor() + 1);
                    } else {
                        elevator.setCurrentFloor(elevator.getCurrentFloor() - 1);
                    }
                } else if (topFloorRequest == 1) {

                }

                for (int pass = 0; pass < elevator.getRequests().size(); pass++) {
                    if (elevator.getRequests().get(pass).getDestinationFloor() == elevator.getCurrentFloor()) {
                        totalWaitTime += elevator.getRequests().get(pass).getTotalWaitTime();
                        elevator.getRequests().remove(pass);
                        pass -= 1;
                    }
                }
                str += (elevator.toString() + ", ");
            }
            System.out.print(str.substring(0, str.length() - 2) + "\n");
            System.out.println();
        }
        System.out.println("END OF SIMULATION");
        System.out.printf("Total Wait Time: %d\n", totalWaitTime);
        System.out.printf("Total Requests: %d\n", totalRequest);
        System.out.printf("Average Wait Time: %.2f", (double) totalWaitTime / totalRequest);
    }
}
