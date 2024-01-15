
// Name: Phi Long Bui, ID: 114555975, R-01
import java.util.Scanner;

/**
 * This class repersents “F: Go to favorite/bookmark” command for the Safari app
 * 
 * @author Phi Long Bui
 */
public class GoToBookMark implements Command {
    private String bookmark;

    /**
     * * This Constructor used to create a new GoToBookMark object
     * 
     * @param scanner
     *                the user input
     */
    public GoToBookMark(Scanner scanner) {
        System.out.print("Please enter bookmark name: ");
        this.bookmark = scanner.nextLine();
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
     * This method return the bookmark
     * 
     * @return
     *         the bookmark
     */
    public String getBookMark() {
        return bookmark;
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
        String str = String.format("F:%s", bookmark);
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
        String str = String.format("Current Screen: Showing results for %s\n", bookmark);
        return str;
    }
}
