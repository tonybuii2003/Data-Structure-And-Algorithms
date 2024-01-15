
// Name: Phi Long Bui, ID: 114555975, R-30
import java.util.Scanner;

/**
 * This class contains a main method that provides a menu with the following
 * options to interact with the program and update the store inventory
 * 
 * @author Phi Long Bui
 */
public class DepartmentStore {
    /**
     * The main class
     * 
     * @param args
     *             The command line arguments.
     */
    public static void main(String[] args) {
        ItemList items = new ItemList();
        boolean isQuited = false;
        Scanner inp = new Scanner(System.in);
        System.out.println("Welcome!");
        while (!isQuited) {
            try {
                System.out.print(
                        "\nC - Clean store\n" +
                                "I - Insert an item into the list\n" +
                                "L - List by location\n" +
                                "M - Move an item in the store\n" +
                                "O - Checkout\n" +
                                "P - Print all items in store\n" +
                                "R - Print by RFID tag number\n" +
                                "U - Update inventory system\n" +
                                "Q - Exit the program. \n");
                System.out.print("\nPlease select an option: ");
                String input = inp.nextLine().toLowerCase();
                switch (input) {
                    case "c": {
                        try {
                            items.cleanStore();
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                        break;
                    case "i": {
                        try {
                            System.out.print("Enter the name: ");
                            String name = inp.nextLine();
                            System.out.print("Enter the RFID: ");
                            String rfid = inp.nextLine();
                            if (!rfid.matches("[0-9A-Z]{9}")) {
                                throw new IllegalArgumentException("Invalid Rfid length");
                            }
                            System.out.print("Enter the original location: ");
                            String location = inp.nextLine();
                            if (!location.matches("s[0-9]{5}")) {
                                throw new Exception("Invalid format please try again!");
                            }
                            System.out.print("Enter the price: ");
                            double price = inp.nextDouble();
                            inp.nextLine();
                            if (price < 0)
                                throw new IllegalArgumentException("Invalid price, please try again.");
                            items.insertInfo(name, rfid, price, location);
                            System.out.printf("%s is Inserted\n", name);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                        break;
                    case "l": {
                        try {
                            System.out.print("Enter the location: ");
                            String location = inp.nextLine();
                            if (!location.matches("s[0-9]{5}") && !location.matches("c[0-9]{3}")
                                    && !location.equals("out")) {
                                throw new Exception("Invalid format please try again!");
                            }
                            items.printByLocation(location);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                        break;
                    case "m": {
                        try {
                            System.out.print("Enter the RFID: ");
                            String rfid = inp.nextLine();
                            if (!rfid.matches("[0-9A-Z]{9}")) {
                                throw new IllegalArgumentException("Invalid Rfid length.");
                            }
                            System.out.print("Enter the original location: ");
                            String location = inp.nextLine();
                            if (!location.matches("s[0-9]{5}") && !location.matches("c[0-9]{3}")) {
                                throw new Exception("Invalid format please try again!");
                            }
                            System.out.print("Enter the new location: ");
                            String newLocation = inp.nextLine();
                            if (!newLocation.matches("c[0-9]{3}") && !newLocation.matches("s[0-9]{5}")) {
                                throw new Exception("Invalid format please try again!");
                            }
                            if (!items.moveItem(rfid, location, newLocation)) {
                                throw new Exception("Item is not found");
                            }
                            System.out.printf("\nItem with ID %s has been changed from %s to %s\n", rfid, location,
                                    newLocation);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                        break;
                    case "o": {
                        try {
                            System.out.print("Enter the cart number: ");
                            String cartNumber = inp.nextLine();
                            if (!cartNumber.matches("c[0-9]{3}"))
                                throw new Exception("Invalid format please try again!");
                            System.out.printf("\nThe total cost for all merchandise in cart %s was $%.2f\n",
                                    cartNumber.substring(1, cartNumber.length()), items.checkOut(cartNumber));
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                        break;
                    case "p": {
                        try {
                            items.printAll();
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                        break;
                    case "r": {
                        System.out.print("Enter the RFID: ");
                        try {
                            String rfid = inp.nextLine();
                            if (!rfid.matches("[0-9A-Z]{9}")) {
                                throw new IllegalArgumentException("Invalid Rfid length.");
                            }
                            items.printByRfid(rfid);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                        break;
                    case "u": {
                        try {
                            items.removeAllPurchased();
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                        break;
                    case "q": {
                        System.out.println("Goodbye!");
                        isQuited = true;
                    }
                        break;
                    default: {
                        System.out.println("Invalid, please try again!");
                    }
                }

            } catch (Exception e) {
                System.out.println("Invalid Please Try Again");
            }
        }
        inp.close();
    }
}
