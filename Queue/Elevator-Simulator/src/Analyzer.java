// Name: Phi Long Bui, ID: 114555975, R-30

import java.util.Scanner;

/**
 * This class containing a main method which prompts the user, on separate
 * lines,
 * for each of the 4 parameters required for the simulate method of the
 * Simulator class.
 * 
 * @author Phi Long Bui
 */
public class Analyzer {
    /**
     * This is the main method
     * 
     * @param args
     *             The command line arguments.
     */
    public static void main(String[] args) {
        boolean isQuited = false;
        Scanner inp = new Scanner(System.in);
        while (!isQuited) {
            try {
                System.out.println("Welcome to the Elevator simulator!");
                System.out.print("Please enter the probability of arrival for Requests: ");
                double probability = inp.nextDouble();
                inp.nextLine();
                if (probability > 1.0 || probability < 0.0) {
                    throw new Exception("Invalid format, please try again!");
                }
                System.out.print("Please enter the number of floors: ");
                int floors = inp.nextInt();
                inp.nextLine();
                if (floors <= 1) {
                    throw new Exception("Invalid format, please try again!");
                }
                System.out.print("Please enter the number of elevators: ");
                int elevators = inp.nextInt();
                inp.nextLine();
                if (elevators <= 0) {
                    throw new Exception("Invalid format, please try again!");
                }
                System.out.printf("Please enter the length of the simulation (in time units): ");
                int length = inp.nextInt();
                inp.nextLine();
                if (length < 0) {
                    throw new Exception("Invalid format, please try again!");
                }
                System.out.print("A) Simulator\n");
                System.out.print("B) Optimal Simulator (extra credit)\n");
                boolean isDone = false;
                while (!isDone) {
                    System.out.print("Please choose a simulator: ");
                    String input = inp.nextLine().toLowerCase();
                    switch (input) {
                        case "a": {
                            Simulator.simulate(probability, floors, elevators, length);
                            isDone = true;
                        }
                            break;
                        case "b": {
                            OptimalSimulator.simulate(probability, floors, elevators, length);
                            isDone = true;
                        }
                            break;
                        default: {
                            System.out.println("invalid please try again!");
                        }
                    }

                }

                isQuited = true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        inp.close();
    }
}
