
// Name: Phi Long Bui, ID: 114555975, R-01
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * /**
 * This is the main class for the apps that contains the LIRR Simulator
 * 
 * @author Name: Phi Long Bui
 */
public class LIRRSimulator {
    static int time = 0; // Current Time

    /**
     * This main method which will print the UI include the the probability for each
     * station,
     * the number of trains, class capacity, and last arrival time of passengers.
     * Prints the simulation after the UI.
     * 
     * @param args
     *             The command line arguments.
     */
    public static void main(String[] args) {
        int firstClassCapacity = 0;
        int secondClassCapacity = 0;
        int trainNum = 0;
        int lastArrivalTime = 0;
        int idCount = 1;
        boolean isDone = false;
        String[] stationsName = { "Mineola", "Hicksville", "Syosset", "Huntington" };
        Station[] stations = new Station[stationsName.length];
        double[] pFirstClass = new double[stations.length];
        double[] pSecondClass = new double[stations.length];
        System.out.println("Welcome to the LIRR Simulator, Leaving Irate Riders Regularly");
        Scanner inp = new Scanner(System.in);
        while (!isDone) {
            try {
                for (int i = 0; i < stations.length; i++) {
                    System.out.printf("\n%s:\n", stationsName[i]);
                    System.out.print("Please enter first class arrival probability: ");
                    pFirstClass[i] = inp.nextDouble();
                    inp.nextLine();
                    if (pFirstClass[i] < 0.0 || pFirstClass[i] > 1.0)
                        throw new IllegalArgumentException();
                    System.out.print("Please enter second class arrival probability: ");
                    pSecondClass[i] = inp.nextDouble();
                    inp.nextLine();
                    if (pSecondClass[i] < 0.0 || pSecondClass[i] > 1.0)
                        throw new IllegalArgumentException();
                    stations[i] = new Station(pFirstClass[i], pSecondClass[i], stationsName[i]);
                }

                System.out.print("Please enter first class capacity: ");
                firstClassCapacity = inp.nextInt();
                inp.nextLine();
                if (firstClassCapacity < 0)
                    throw new IllegalArgumentException();
                System.out.print("Please enter second class capacity: ");
                secondClassCapacity = inp.nextInt();
                inp.nextLine();
                if (secondClassCapacity < 0)
                    throw new IllegalArgumentException();
                System.out.print("Please enter number of trains: ");
                trainNum = inp.nextInt();
                inp.nextLine();
                if (trainNum <= 0)
                    throw new IllegalArgumentException();
                System.out.print("Please enter last arrival time of passengers: ");
                lastArrivalTime = inp.nextInt();
                inp.nextLine();
                if (lastArrivalTime <= 0 || lastArrivalTime > (15 + (5 * (trainNum - 1))))
                    throw new IllegalArgumentException();
                isDone = true;
                inp.close();

            } catch (IllegalArgumentException e) {
                System.out.println("Invalid, Please try again.");
            } catch (InputMismatchException e) {
                System.out.println("Invalid, Please try again.");
                inp.nextLine();
            } catch (Exception e) {
                System.out.println("Invalid, Please try again.");
            }
        }
        try {
            Train[] trains = new Train[trainNum];

            System.out.println("Begin Simulation:");
            System.out.println("---------------------------------------------");
            int timeUntilNextArrival = 0;
            for (int train = 0; train < trains.length; train++) {
                trains[train] = new Train(firstClassCapacity, secondClassCapacity);
                trains[train].setTimeUntilNextArrival(timeUntilNextArrival);
                trains[train].setStation(stations[stations.length - 1]);
                trains[train].setCurrentStation(stations.length - 1);
                timeUntilNextArrival += 5;
            }
            timeUntilNextArrival -= 5;
            while (time <= (timeUntilNextArrival + 15)) {
                System.out.printf("\nTime %d:\n\n", time);
                System.out.println("Station overview:");
                for (int station = 0; station < stations.length; station++) {
                    System.out.printf("\n%s:\n", stations[station].getStationName());
                    if (time <= lastArrivalTime) {
                        if (stations[station].getFirstArrival().occurs()) {
                            Passenger passengerFirstClass = new Passenger(idCount, time, true);
                            idCount++;
                            stations[station].getFirstClass().enqueue(passengerFirstClass);
                            stations[station].setFirstClassOccurs(true);
                        } else
                            stations[station].setFirstClassOccurs(false);
                        if (stations[station].getSecondArrival().occurs()) {
                            Passenger passengerSecondClass = new Passenger(idCount, time, false);
                            idCount++;
                            stations[station].getSecondClass().enqueue(passengerSecondClass);
                            stations[station].setSecondClassOccurs(true);
                        } else
                            stations[station].setSecondClassOccurs(false);
                    } else {
                        stations[station].setFirstClassOccurs(false);
                        stations[station].setSecondClassOccurs(false);
                    }
                    stations[station].simulateTimestep();
                    System.out.println("Queue:");
                    System.out.printf("First %s\n", stations[station].getFirstClass());
                    System.out.printf("Second %s\n", stations[station].getSecondClass());
                }
                System.out.printf("\nTrains:\n");
                for (int train = 0; train < trains.length; train++) {
                    if (trains[train].getCurrentStation() >= 0) {
                        if (trains[train].getTimeUntilNextArrival() == 0) {
                            System.out.printf(
                                    "\nTrain %d arrives at %s, There are %d passengers in first class and %d in second class.\n",
                                    train + 1, trains[train].getStation().getStationName(),
                                    firstClassCapacity - trains[train].getFirstCapacity(),
                                    secondClassCapacity - trains[train].getSecondCapacity());
                            trains[train].simulateTimeStep();
                            trains[train].setTimeUntilNextArrival(trains[train].getTimeUntilNextArrival() + 5);
                            trains[train].setCurrentStation(trains[train].getCurrentStation() - 1);
                            if (trains[train].getCurrentStation() >= 0)
                                trains[train].setStation(stations[trains[train].getCurrentStation()]);

                        } else {
                            System.out.printf("\nTrain %d will arrive at %s in %d minutes.\n", train + 1,
                                    trains[train].getStation().getStationName(),
                                    trains[train].getTimeUntilNextArrival());
                        }
                        trains[train].setTimeUntilNextArrival(trains[train].getTimeUntilNextArrival() - 1);
                    } else {
                        System.out.printf("\nTrain %s has stopped picking up passengers.\n", train + 1);
                    }
                }
                System.out.printf("\n-------\n");
                time++;
            }
            System.out.printf("\nAt the end of the simulation:\n");
            int totalNoSeatFirstClass = 0;
            int totalNoSeatSecondClass = 0;
            int totalServed = 0;
            for (int station = 0; station < stations.length; station++) {
                totalNoSeatFirstClass += stations[station].getFirstClass().size();
                totalNoSeatSecondClass += stations[station].getSecondClass().size();
            }
            totalServed = idCount - 1 - totalNoSeatFirstClass - totalNoSeatSecondClass;
            System.out.printf(
                    "\nA total of %d passengers were served, %d first class passengers were left without a seat, %d second class passengers were left\nwithout a seat.\n",
                    totalServed, totalNoSeatFirstClass, totalNoSeatSecondClass);
            for (int station = 0; station < stations.length; station++) {
                System.out.printf(
                        "\nAt %s %d first class passengers were served with an average wait time of %d min, %d second class passengers were served\nwith an average wait time of %d min. %d first class passengers and %d second class passengers were left without a seat.\n",
                        stations[station].getStationName(), stations[station].getFirstCount(),
                        (int) (((double) stations[station].getFirstClassTotalWaitTime()
                                / stations[station].getFirstCount()) + 0.5),
                        stations[station].getSecondCount(),
                        (int) (((double) stations[station].getSecondClassTotalWaitTime()
                                / stations[station].getSecondCount()) + 0.5),
                        stations[station].getFirstClass().size(), stations[station].getSecondClass().size());
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
