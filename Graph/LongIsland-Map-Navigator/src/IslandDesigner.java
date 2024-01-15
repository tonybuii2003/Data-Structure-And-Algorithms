
// Name: Phi Long Bui, ID: 114555975, R-01
import java.util.List;
import java.util.Scanner;

/**
 * This is the main class contains a main method that
 * allows the user to run Depth First Searches from any City on the Island to
 * any other City on the Island,
 * find the maximum network flow from any City on the Island to any other City
 * on the Island,
 * and shows the shortest path from any city on the Island to any other City on
 * the Island.
 * 
 * @author Name: Phi Long Bui
 */
public class IslandDesigner {
    /**
     * This is the main method which will print the UI
     * The UI include the menu contains all commands, each command function
     * differently
     * 
     * @param args The command line arguments.
     */
    public static void main(String[] args) {

        boolean quitted = false;
        boolean loaded = false;
        IslandNetwork network = new IslandNetwork(null);
        Scanner inp = new Scanner(System.in);
        System.out.println(
                "Welcome to the Island Designer, because, when you're trying to stay above water, Seas get degrees!");
        while (loaded == false) {
            try {
                System.out.print("\nPlease enter an url: ");
                String url = inp.nextLine();
                network = IslandNetwork.loadFromFile(url);
                loaded = true;

            } catch (big.data.DataSourceException e) {
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.print("\nMenu:\n"
                + "D) Destinations reachable (Depth First Search)\n"
                + "F) Maximum Flow\n"
                + "S) Shortest Path (Extra Credit)\n"
                + "Q) Quit\n");
        try {
            while (quitted == false) {
                System.out.print("\nPlease select an option: ");
                String command = inp.nextLine().toLowerCase();
                switch (command) {
                    case "d": {
                        try {
                            System.out.print("\nPlease enter a starting city: ");
                            String startingCity = inp.nextLine();
                            if (!network.getGraph().containsKey(startingCity)) {
                                throw new Exception("City not found.");
                            }
                            List<String> cities = network.dfs(startingCity);
                            System.out.printf("\nDFS Starting From %s:\n", startingCity);
                            String path = "";
                            for (String i : cities) {
                                path += String.format("%s, ", i);
                            }
                            System.out.printf("%s\n", path.substring(0, path.length() - 2));
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                        break;
                    case "f": {
                        try {
                            System.out.printf("Please enter a starting city: ");
                            String startingCity = inp.nextLine();
                            if (!network.getGraph().containsKey(startingCity)) {
                                throw new Exception("City not found.");
                            }
                            System.out.printf("Please enter a destination: ");
                            String destination = inp.nextLine();
                            if (!network.getGraph().containsKey(destination)) {
                                throw new Exception("City not found.");
                            }
                            network.maxFlow(startingCity, destination);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                        break;
                    case "s": {
                        try {
                            System.out.printf("Please enter a starting city: ");
                            String startingCity = inp.nextLine();
                            if (!network.getGraph().containsKey(startingCity)) {
                                throw new Exception("City not found.");
                            }
                            System.out.printf("Please enter a destination: ");
                            String destination = inp.nextLine();
                            if (!network.getGraph().containsKey(destination)) {
                                throw new Exception("City not found.");
                            }
                            network.djikstra(startingCity, destination);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                        break;
                    case "q": {
                        System.out.println("You can go your own way! Goodbye!");
                        quitted = true;
                    }
                        break;
                    default: {
                        System.out.println("\nInvalid, please try again.");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        inp.close();
    }
}