
// Name: Phi Long Bui, ID: 114555975, R-01
import java.util.Scanner;

/**
 * This class repersents “P: Plan a route” command for the Maps app.
 * 
 * @author Phi Long Bui
 */
public class PlanRoute implements Command {
    private String source, destination;

    /**
     * * This Constructor used to create a new PlanRoute object
     * 
     * @param scanner
     *                the user input
     */
    public PlanRoute(Scanner scanner) {
        System.out.print("Please enter a source: ");
        this.source = scanner.nextLine();
        System.out.print("Please enter a destination: ");
        this.destination = scanner.nextLine();
    }

    /**
     * This method return the destination
     * 
     * @return
     *         the destination
     */
    public String getDestination() {
        return destination;
    }

    /**
     * This method return the source
     * 
     * @return
     *         The source
     */
    public String getSource() {
        return source;
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
     * This class returns the String representation of this Command in long form
     * (for current screen display)
     * 
     * @return
     *         returns the String representation of this Command in long form (for
     *         current screen display)
     */
    public String toString() {
        String str = String.format("Current Screen: Planning route from %s to %s\n", source, destination);
        return str;
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
        String str = String.format("P:%s-%s", source, destination);
        return str;
    }

}
