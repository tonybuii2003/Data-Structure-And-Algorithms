
// Name: Phi Long Bui, ID: 114555975, R-01
import java.util.Scanner;

/**
 * This class represent the “L: FollowLink” command for the Safari app
 * 
 * @author Phi Long Bui
 */
public class FollowLink implements Command {
    private String link;

    /**
     * * This Constructor used to create a new FollowLink object
     * 
     * @param scanner
     *                the user input
     */
    public FollowLink(Scanner scanner) {
        System.out.print("Please enter a link: ");
        this.link = scanner.nextLine();
    }

    /**
     * This method return the link
     * 
     * @return
     *         the link
     */
    public String getLink() {
        return link;
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
        String str = String.format("L:%s", link);
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
        String str = String.format("Current Screen: %s\n", link);
        return str;
    }
}
