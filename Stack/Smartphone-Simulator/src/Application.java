
// Name: Phi Long Bui, ID: 114555975, R-01
import java.util.Scanner;

/**
 * This class repersent the applications
 * Implemented 2 classes: Maps and Safari
 * 
 * @author Phi Long Bui
 */
public abstract class Application {
    protected static CommandStack currentStack;
    protected static CommandStack mapStack;
    protected static CommandStack safariStack;

    /**
     * This method reads in input from the scanner to construct a Command and add it
     * to the CommandStack.
     * 
     * @param scanner
     *                user input
     * @throws InvalidCommandException
     *                                 when the Command is invalid given the current
     *                                 state of the stack
     */
    public abstract void readCommand(Scanner scanner) throws InvalidCommandException;

    /**
     * This method returns the application to the state it was before the most
     * recent Command.
     * 
     * @throws EmptyStackException
     *                             when there was no Command entered
     */
    public abstract void goBack() throws EmptyStackException;

}

/**
 * This class represent the application called Maps
 * 
 * @author Phi Long Bui
 */
class Maps extends Application implements Command {
    private Command home = new Command() {
        /**
         * This method returns the string representation of this Command in long form
         * (for current screen display)
         * 
         * @return
         *         the String representation of this Command in long form (for current
         *         screen display)
         */
        public boolean validCommand(CommandStack stack) {
            return stack.isEmpty();
        }

        public String toShortString() {
            return "[Home";
        }

    };

    /**
     * This Constructor used to create a new Maps object
     */
    public Maps() {
        try {
            mapStack = new CommandStack();
            mapStack.push(home);
        } catch (InvalidCommandException e) {
            System.out.println(e);

        }
    }

    /**
     * This method reads in input from the scanner to construct a Command and add it
     * to the CommandStack.
     * 
     * @param scanner
     *                user input
     * @throws InvalidCommandException
     *                                 when the Command is invalid given the current
     *                                 state of the stack
     */
    public void readCommand(Scanner scanner) throws InvalidCommandException {
        System.out.print("Maps Options:\n"
                + "     F) Find a place\n"
                + "     P) Plan a route\n"
                + "     N) Start Navigation\n"
                + "     H) Home Screen\n"
                + "     S) Switch to Safari\n"
                + "     B) Back\n");
        System.out.print("Please select an option: ");
        String command = scanner.nextLine().toLowerCase();
        switch (command) {
            case "f": {
                Command find = new FindPlace(scanner);
                try {
                    if (find.validCommand(currentStack)) {
                        currentStack.push(find);
                        System.out.print(currentStack);
                        System.out.print(currentStack.getScreenCommand());
                        readCommand(scanner);
                    } else {
                        throw new InvalidCommandException();
                    }
                } catch (InvalidCommandException e) {
                    System.out.println(e);
                    System.out.print(currentStack);
                    System.out.print(currentStack.getScreenCommand());
                    readCommand(scanner);
                }
            }
                break;
            case "p": {
                Command plan = new PlanRoute(scanner);
                try {
                    if (plan.validCommand(currentStack)) {
                        currentStack.push(plan);
                        System.out.print(currentStack);
                        System.out.print(currentStack.getScreenCommand());
                        readCommand(scanner);
                    } else {
                        throw new InvalidCommandException();
                    }
                } catch (InvalidCommandException e) {
                    System.out.println(e);
                    System.out.print(currentStack);
                    System.out.print(currentStack.getScreenCommand());
                    readCommand(scanner);
                }
            }
                break;
            case "n": {
                Command navigation = new StartNavigation(currentStack);
                try {
                    if (navigation.validCommand(currentStack)) {
                        currentStack.push(navigation);
                        System.out.print(currentStack);
                        System.out.print(currentStack.getScreenCommand());
                        readCommand(scanner);
                    } else {
                        throw new InvalidCommandException();
                    }
                } catch (InvalidCommandException e) {
                    System.out.println("No route or destination!");
                    System.out.print(currentStack);
                    System.out.print(currentStack.getScreenCommand());
                    readCommand(scanner);
                }
            }
                break;
            case "h": {

            }
                break;
            case "s": {
                currentStack = safariStack;
                if (safariStack.getSize() == 1) {
                    try {
                        currentStack.push(iCatchUp.safari);
                    } catch (InvalidCommandException e) {
                        System.out.println(e);
                        System.out.print(currentStack);
                        System.out.print(currentStack.getScreenCommand());
                        readCommand(scanner);
                    }

                }
                System.out.print(currentStack);
                System.out.print(currentStack.getScreenCommand());
                iCatchUp.safari.readCommand(scanner);
            }
                break;
            case "b": {
                if (currentStack.getSize() < 3) {
                    goBack();
                } else {
                    goBack();
                    System.out.print(currentStack);
                    System.out.print(currentStack.getScreenCommand());
                    readCommand(scanner);
                }
            }
                break;
            default: {
                System.out.println("Invalid, please try again");
                System.out.print(currentStack);
                System.out.print(currentStack.getScreenCommand());
                readCommand(scanner);
            }
        }

    }

    /**
     * This method returns the current stack of the app
     * 
     * @return
     *         the current stack of the app
     */
    public CommandStack getStack() {
        return currentStack;
    }

    /**
     * This method returns the application to the state it was before the most
     * recent Command.
     * 
     * @throws EmptyStackException
     *                             when there was no Command entered
     */
    public void goBack() {
        try {
            currentStack.pop();
        } catch (EmptyStackException e) {
            System.out.println(e);
        }
    }

    /**
     * This method returns the string representation of this Command in long form
     * (for current screen display)
     * 
     * @return
     *         the String representation of this Command in long form (for current
     *         screen display)
     */
    public boolean validCommand(CommandStack stack) {
        return stack.getSize() == 1;
    }

    /**
     * This method returns the String representation of this Command in short form
     * (for stack display)
     * 
     * @return
     *         the String representation of this Command in short form (for
     *         stack display)
     */
    public String toShortString() {
        return "MapsHome";
    }

    /**
     * This method returns the string representation of this Command in long form
     * (for current screen display)
     * 
     * @return
     *         the String representation of this Command in long form (for current
     *         screen display)
     */
    public String toString() {
        String str = String.format("Current Screen: Maps Home\n");
        return str;
    }

}

/**
 * This class represent the application called Safari
 * 
 * @author Phi Long Bui
 */
class Safari extends Application implements Command {
    private Command home = new Command() {
        public boolean validCommand(CommandStack stack) {
            return stack.isEmpty();
        }

        /**
         * This method returns the String representation of this Command in short form
         * (for stack display)
         * 
         * @return
         *         the String representation of this Command in short form (for
         *         stack display)
         */
        public String toShortString() {
            return "[Home";
        }
    };

    /**
     * This Constructor used to create a new Safari object
     */
    public Safari() {
        try {
            safariStack = new CommandStack();
            safariStack.push(home);
        } catch (InvalidCommandException e) {
            System.out.println(e);
        }
    }

    /**
     * This method reads in input from the scanner to construct a Command and add it
     * to the CommandStack.
     * 
     * @param scanner
     *                user input
     * @throws InvalidCommandException
     *                                 when the Command is invalid given the current
     *                                 state of the stack
     */
    public void readCommand(Scanner scanner) throws InvalidCommandException {
        System.out.print("Safari Options:\n"
                + "     G) Google Something\n"
                + "     F) Go to a favorite (bookmark)\n"
                + "     L) Follow a link\n"
                + "     H) Home Screen\n"
                + "     S) Switch to Maps\n"
                + "     B) Back\n");
        System.out.print("Please select an option: ");
        String command = scanner.nextLine().toLowerCase();
        switch (command) {
            case "g": {
                Command google = new GoogleSomeThing(scanner);

                try {
                    currentStack.push(google);
                    System.out.print(currentStack);
                    System.out.print(currentStack.getScreenCommand());
                    readCommand(scanner);
                } catch (InvalidCommandException e) {
                    System.out.println(e);
                    System.out.print(currentStack);
                    System.out.print(currentStack.getScreenCommand());
                    readCommand(scanner);
                }
            }
                break;
            case "f": {
                Command bookmark = new GoToBookMark(scanner);
                try {
                    if (bookmark.validCommand(currentStack)) {
                        currentStack.push(bookmark);
                        System.out.print(currentStack);
                        System.out.print(currentStack.getScreenCommand());
                        readCommand(scanner);
                    } else {
                        throw new InvalidCommandException();
                    }
                } catch (InvalidCommandException e) {
                    System.out.println(e);
                    System.out.print(currentStack);
                    System.out.print(currentStack.getScreenCommand());
                    readCommand(scanner);
                }
            }
                break;
            case "l": {
                Command link = new FollowLink(scanner);
                try {
                    if (link.validCommand(currentStack)) {
                        currentStack.push(link);
                        System.out.print(currentStack);
                        System.out.print(currentStack.getScreenCommand());
                        readCommand(scanner);
                    } else {
                        throw new InvalidCommandException();
                    }
                } catch (InvalidCommandException e) {
                    System.out.println(e);
                    System.out.print(currentStack);
                    System.out.print(currentStack.getScreenCommand());
                    readCommand(scanner);
                }
            }
                break;
            case "h": {

            }
                break;
            case "s": {

                currentStack = mapStack;

                if (mapStack.getSize() == 1) {
                    try {
                        currentStack.push(iCatchUp.map);
                    } catch (InvalidCommandException e) {
                        System.out.println(e);
                        System.out.print(currentStack);
                        System.out.print(currentStack.getScreenCommand());
                        readCommand(scanner);
                    }

                }
                System.out.print(currentStack);
                System.out.print(currentStack.getScreenCommand());
                try {
                    iCatchUp.map.readCommand(scanner);
                } catch (InvalidCommandException e) {
                    System.out.println(e);
                    System.out.print(currentStack);
                    System.out.print(currentStack.getScreenCommand());
                    readCommand(scanner);
                }
            }
                break;
            case "b": {
                if (currentStack.getSize() < 3) {
                    goBack();
                } else {
                    goBack();
                    System.out.print(currentStack);
                    System.out.print(currentStack.getScreenCommand());
                    readCommand(scanner);
                }
            }
                break;
            default: {
                System.out.println("Invalid, please try again");
                System.out.print(currentStack);
                System.out.print(currentStack.getScreenCommand());
                readCommand(scanner);
            }
        }
    }

    /**
     * This method returns the application to the state it was before the most
     * recent Command.
     * 
     * @throws EmptyStackException
     *                             when there was no Command entered
     */
    public void goBack() {
        try {
            currentStack.pop();
        } catch (EmptyStackException e) {
            System.out.println(e);
        }
    }

    /**
     * This method returns the current stack of the app
     * 
     * @return
     *         the current stack of the app
     */
    public CommandStack getStack() {
        return currentStack;
    }

    /**
     * This method returns the string representation of this Command in long form
     * (for current screen display)
     * 
     * @return
     *         the String representation of this Command in long form (for current
     *         screen display)
     */
    public boolean validCommand(CommandStack stack) {
        return stack.getSize() == 1;
    }

    /**
     * This method returns the String representation of this Command in short form
     * (for stack display)
     * 
     * @return
     *         the String representation of this Command in short form (for
     *         stack display)
     */
    public String toShortString() {
        return "SafariHome";
    }

    /**
     * This method returns the string representation of this Command in long form
     * (for current screen display)
     * 
     * @return
     *         the String representation of this Command in long form (for current
     *         screen display)
     */
    public String toString() {
        String str = String.format("Current Screen: Safari Home\n");
        return str;
    }

}