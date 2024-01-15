
// Name: Phi Long Bui, ID: 114555975, R-01
import java.util.Scanner;

/**
 * This is the main class contains 2 static DeliveryList and the main method.
 * 
 * @author Name: Phi Long Bui
 */
public class DeliverDriver {
    private static DeliveryList deliveryListA = new DeliveryList();
    private static DeliveryList deliveryListB = new DeliveryList();

    /**
     * This is the main method which will print the UI
     * The UI include the menu contains all commands, each command function
     * differently
     * 
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        System.out.println("Welcome to the Delinquent Dollar Delivery Scheduler.");
        System.out.print("Menu:\n"
                + "     A) Add a Delivery After Cursor\n"
                + "     R) Remove Delivery At Cursor\n"
                + "     X) Cut Cursor\n"
                + "     V) Paste After Cursor\n"
                + "     H) Cursor to Head\n"
                + "     T) Cursor to Tail\n"
                + "     F) Cursor Forward\n"
                + "     B) Cursor Backward\n"
                + "     S) Switch Delivery Lists\n"
                + "     P) Print current list\n"
                + "     Q) Quit\n\n");
        DeliveryList currentList = deliveryListA;
        String[] delivers = { "Biz Billy", "Money Mike" };
        int curr = 0;
        Delivery tmp = new Delivery();
        boolean isEnded = false;
        Scanner inp = new Scanner(System.in);
        while (isEnded == false) {
            System.out.print("Please select an option: ");
            String userInput = inp.nextLine();
            userInput = userInput.toLowerCase();
            try {
                switch (userInput) {
                    case "a": {
                        System.out.print("Please enter a source: ");
                        String source = inp.nextLine();
                        System.out.print("Please enter a destination: ");
                        String destination = inp.nextLine();
                        System.out.print("Please enter any special instructions: ");
                        String instruction = inp.nextLine();
                        Delivery delivery = new Delivery();
                        delivery.setDest(destination);
                        delivery.setSource(source);
                        delivery.setIntruction(instruction);
                        try {
                            currentList.insertAfterCursor(delivery);
                            System.out.println("Order inserted.");
                        } catch (IllegalArgumentException e) {
                            System.out.println(e);
                        }
                    }
                        break;
                    case "h": {
                        currentList.resetCursorToHead();
                        System.out.println("Cursor is at head.");
                    }
                        break;
                    case "t": {
                        currentList.resetCursorToTail();
                        System.out.println("Cursor is at Tail.");
                    }
                        break;
                    case "f": {
                        try {
                            currentList.cursorForward();
                            System.out.println("Cursor moved forward.");
                        } catch (EndOfListException e) {
                            System.out.println(e);
                        }
                    }
                        break;
                    case "b": {
                        try {
                            currentList.cursorBackward();
                            System.out.println("Cursor moved backward.");
                        } catch (EndOfListException e) {
                            System.out.println(e);
                        }

                    }
                        break;
                    case "p": {
                        System.out.println(delivers[curr] + "'s Deliveries:");
                        System.out.print(currentList);
                    }
                        break;
                    case "r": {
                        try {
                            Delivery removedCurr = currentList.getCursor();
                            currentList.removeCursor();
                            System.out.println("Delivery to " + removedCurr.getDest() + " removed.");
                        } catch (EndOfListException e) {
                            System.out.println(e);
                        }
                    }
                        break;
                    case "x": {
                        try {
                            tmp = currentList.removeCursor();
                            System.out.println("Cursor is cut.");
                        } catch (EndOfListException e) {
                            System.out.println(e);
                        }
                    }
                        break;
                    case "v": {
                        try {
                            Delivery delivery = new Delivery();
                            delivery.setDest(tmp.getDest());
                            delivery.setSource(tmp.getSource());
                            delivery.setIntruction(tmp.getInstruction());
                            currentList.insertAfterCursor(delivery);
                            System.out.println(delivers[curr] + "'s Deliveries:");
                            System.out.print(currentList);
                        } catch (IllegalArgumentException e) {
                            System.out.println("There is noting to paste");
                        }

                    }
                        break;
                    case "s": {
                        if (curr == 0) {
                            currentList = deliveryListB;
                            curr = 1;
                            System.out.println(delivers[curr] + "'s list is selected.");
                        } else if (curr == 1) {
                            currentList = deliveryListA;
                            curr = 0;
                            System.out.println(delivers[curr] + "'s list is selected.");
                        }
                    }
                        break;
                    case "q": {
                        System.out.println("Next time, try UPS!");
                        isEnded = true;
                    }
                        break;
                    default: {
                        System.out.println("Invalid, please try again");
                    }
                }
            } catch (Exception e) {
                System.out.println("Invalid, please try again");
            }
        }
        inp.close();
    }
}
