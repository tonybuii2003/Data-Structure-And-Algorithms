
// Name: Phi Long Bui, ID: 114555975, R-01
import java.util.Scanner;

/**
 * This class repersents “F: Find a place” command for the Maps app
 * 
 * @author Phi Long Bui
 */
public class FindPlace implements Command {
    private String destination;

    /**
     * * This Constructor used to create a new FindPlace object
     * 
     * @param scanner
     *                the user input
     */
    public FindPlace(Scanner scanner) {
        System.out.print("Please enter a location: ");
        this.destination = scanner.nextLine();
    }

    /**
     * This method returns the destination of the Command
     * 
     * @return
     *         The destination of the Command
     */
    public String getDestination() {
        return destination;
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
        if (stack == Application.mapStack)
            return true;
        return false;
    }

    /**
     * This class returns the String representation of this Command in short form
     * (for stack display)
     * 
     * @return
     *         returns the String representation of this Command in short form (for
     *         stack display)
     */
    public String toShortString() {
        String str = String.format("F:%s", destination);
        return str;
    }

    /**
     * This class returns the String representation of this Command in long form
     * (for current screen display)
     * 
     * @return
     *         returns the String representation of this Command in long form (for
     *         current screen display)
     */
    public String toString() {
        String str = String.format("Current Screen: Showing results for %s\n", destination);
        return str;
    }

}
