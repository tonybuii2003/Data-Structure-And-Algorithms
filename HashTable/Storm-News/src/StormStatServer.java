
// Name: Phi Long Bui, ID: 114555975, R-01
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This is the main class contains 3 static BookShelf and a main method.
 * 
 * @author Name: Phi Long Bui
 */
public class StormStatServer {
    private static HashMap<String, Storm> database = new HashMap<>();

    /**
     * This is the main method which will print the UI
     * The UI include the menu contains all commands, each command function
     * differently
     * 
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        String filename = "hurricane.ser";

        System.out.printf(
                "Welcome to the StormStatServer, we may not be able to make it rain, but we sure can tell you when it happened!\n");
        try {
            FileInputStream inputFile = new FileInputStream(filename);
            ObjectInputStream inStream = new ObjectInputStream(inputFile);
            database = (HashMap<String, Storm>) inStream.readObject();
            System.out.printf("%s was found and loaded.\n", filename);
            inStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("\nNo previous data found.");
        } catch (Exception e) {
            System.out.println("\nInvalid, please try again.");
        }
        Scanner inp = new Scanner(System.in);
        boolean isQuitted = false;

        System.out.print("\nMenu:\n"

                + "A) Add A Storm\n"

                + "L) Look Up A Storm\n"

                + "D) Delete A Storm\n"

                + "E) Edit Storm Data\n"

                + "R) Print Storms Sorted By Rainfall\n"

                + "W) W-Print Storms by Windspeed\n"

                + "X) Save and quit\n"

                + "Q) Quit and delete saved data\n");
        while (isQuitted == false) {
            try {
                System.out.print("\nPlease select an option: ");
                String input = inp.nextLine().toLowerCase();
                switch (input) {
                    case "a": {
                        System.out.print("Please enter name: ");
                        String name = inp.nextLine();
                        System.out.print("Please enter date: ");
                        String date = inp.nextLine();
                        if (!date.matches("\\d{4}-([1-9]|1[012])-([1-9]|[12][0-9]|3[01])"))
                            throw new Exception("Invalid date");
                        System.out.print("Please enter precipitation (cm): ");
                        double precipitation = inp.nextDouble();
                        inp.nextLine();
                        System.out.print("Please enter windspeed (km/h): ");
                        double windspeed = inp.nextDouble();
                        inp.nextLine();
                        Storm storm = new Storm(name, date, windspeed, precipitation);
                        database.put(name, storm);
                        System.out.printf("%s added\n", name);
                    }
                        break;
                    case "l": {
                        System.out.print("Please enter name: ");
                        String name = inp.nextLine();
                        if (database.get(name) != null) {
                            System.out.printf("Storm %s: Date %s, %s km/h winds, %s cm of rain\n",
                                    database.get(name).getName(), database.get(name).getDate(),
                                    database.get(name).getWindspeed(), database.get(name).getPrecipitation());
                        } else {
                            throw new Exception(String.format("Key not found."));
                        }
                    }
                        break;
                    case "d": {
                        System.out.print("Please enter name: ");
                        String name = inp.nextLine();
                        if (database.get(name) != null) {
                            database.remove(name);
                            System.out.printf("Storm %s has been deleted.\n", name);
                        } else {
                            throw new Exception(String.format("Key not found."));
                        }
                    }
                        break;
                    case "e": {
                        System.out.print("Please enter name: ");
                        String name = inp.nextLine();
                        if (database.get(name) != null) {
                            System.out.println("In Edit Mode, press enter without any input to leave data unchanged.");
                            System.out.print("Please enter date: ");
                            String date = inp.nextLine();

                            if (!date.equals("")) {
                                if (!date.matches("\\d{4}-([1-9]|1[012])-([1-9]|[12][0-9]|3[01])"))
                                    throw new Exception("Invalid date");
                                database.get(name).setDate(date);
                            }
                            System.out.print("Please enter precipitation (cm): ");
                            String precipatation = inp.nextLine();
                            if (!precipatation.equals("")) {
                                double tmp = Double.parseDouble(precipatation);
                                database.get(name).setPrecipitation(tmp);
                            }
                            System.out.print("Please enter windspeed (km/h): ");
                            String windspeed = inp.nextLine();
                            if (!windspeed.equals("")) {
                                double tmp = Double.parseDouble(windspeed);
                                database.get(name).setWindspeed(tmp);
                            }
                            System.out.printf("%s updated", name);
                        } else {
                            throw new Exception(String.format("Key not found."));
                        }

                    }
                        break;
                    case "r": {
                        System.out.printf("%-21s%-15s%-13s%-10s\n", "Name", "Windspeed", "Rainfall", "Date");
                        System.out.print("-----------------------------------------------------------\n");

                        ArrayList<Storm> storms = new ArrayList<Storm>();
                        for (Storm i : database.values()) {
                            storms.add(i);
                        }
                        Collections.sort(storms, new PrecipitationComparator());
                        for (Storm storm : storms) {
                            System.out.printf("%-21s%-15.1f%-13.1f%-10s\n", storm.getName(), storm.getWindspeed(),
                                    storm.getPrecipitation(), storm.getDate());
                        }
                    }
                        break;
                    case "w": {
                        System.out.printf("%-21s%-15s%-13s%-10s\n", "Name", "Windspeed", "Rainfall", "Date");
                        System.out.print("-----------------------------------------------------------\n");
                        ArrayList<Storm> storms = new ArrayList<Storm>();
                        for (Storm i : database.values()) {
                            storms.add(i);
                        }
                        Collections.sort(storms, new WindSpeedComparator());
                        for (Storm storm : storms) {
                            System.out.printf("%-21s%-15.1f%-13.1f%-10s\n", storm.getName(), storm.getWindspeed(),
                                    storm.getPrecipitation(), storm.getDate());
                        }

                    }
                        break;
                    case "x": {

                        FileOutputStream file = new FileOutputStream(filename);
                        ObjectOutputStream outStream = new ObjectOutputStream(file);
                        outStream.writeObject(database);
                        outStream.close();
                        System.out.printf(
                                "File saved to %s; feel free to use the weather channel in the meantime.\n", filename);
                        isQuitted = true;
                    }
                        break;
                    case "q": {
                        System.out.println(
                                "Goodbye, it's hard to hold an (electric) candle in the cold November (and April!) rain!.");
                        File deteleFile = new File(filename);
                        deteleFile.delete();
                        isQuitted = true;
                    }
                        break;
                    default: {
                        System.out.println("Invalid, please try again");
                    }
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid, please try again.");
            } catch (InputMismatchException e) {
                System.out.println("Invalid, please try again.");
                inp.nextLine();
            } catch (Exception e) {
                if (e.getMessage() == null)
                    System.out.println("Invalid, please try again.");
                else
                    System.out.println(e.getMessage());
            }
        }
        inp.close();
    }
}
