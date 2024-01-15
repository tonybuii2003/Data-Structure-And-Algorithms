
// Name: Phi Long Bui, ID: 114555975, R-01
import java.util.Scanner;

/**
 * /**
 * This is the main class for the apps that contains the Home UI
 * 
 * @author Name: Phi Long Bui
 */
public class iCatchUp {

    protected static Maps map = new Maps();
    protected static Safari safari = new Safari();

    /**
     * This is the main method which will print the UI
     * The UI include the menu contains all commands, each command function
     * differently
     * 
     * @param args The command line arguments.
     */
    public static void main(String[] args) {

        System.out.println("Welcome to the iPhony pocket telegraph simulator. You are on the home screen.\n");
        boolean isEnded = false;

        Scanner inp = new Scanner(System.in);
        try {
            while (isEnded == false) {
                System.out.print("Home Options:\n"
                        + "     S) Safari\n"
                        + "     M) Maps\n"
                        + "     Q) Quit\n");
                System.out.print("Please select an option: ");
                String command = inp.nextLine().toLowerCase();

                switch (command) {
                    case "s": {
                        try {
                            Application.currentStack = Application.safariStack;
                            if (Application.safariStack.getSize() == 1)
                                Application.currentStack.push(safari);
                        } catch (InvalidCommandException e) {
                            e.printStackTrace();
                        }
                        System.out.print(Application.currentStack);
                        System.out.print(Application.currentStack.getScreenCommand());
                        safari.readCommand(inp);
                    }
                        break;
                    case "m": {
                        try {
                            Application.currentStack = Application.mapStack;
                            if (Application.mapStack.getSize() == 1)
                                Application.mapStack.push(map);
                        } catch (InvalidCommandException e) {
                            e.printStackTrace();
                        }
                        System.out.print(Application.currentStack);
                        System.out.print(Application.currentStack.getScreenCommand());
                        map.readCommand(inp);

                    }
                        break;
                    case "q": {
                        System.out.println("Sorry to see you go, tell the iPod I said hi!");
                        isEnded = true;
                    }
                        break;
                    default: {
                        System.out.println("Invalid, please try again");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Invalid, please try again");
        }
        inp.close();
    }
}
