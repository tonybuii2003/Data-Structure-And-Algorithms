
// Name: Phi Long Bui, ID: 114555975, R-01
import java.util.Scanner;

/**
 * This class repersents “G: Google something” command for the Safari app
 * 
 * @author Phi Long Bui
 */
public class GoogleSomeThing implements Command {
    private String query;

    /**
     * * This Constructor used to create a new GoogleSomeThing object
     * 
     * @param scanner
     *                the user input
     */
    public GoogleSomeThing(Scanner scanner) {
        System.out.print("Please enter a query: ");
        this.query = scanner.nextLine();
    }

    /**
     * This method return the query
     * 
     * @return
     *         the query
     */
    public String getQuery() {
        return query;
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
        if (stack == Application.safariStack)
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
        String str = String.format("G:%s", query);
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
        String str = String.format("Current Screen: Google: %s\n", query);
        return str;
    }
}
