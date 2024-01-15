// Name: Phi Long Bui, ID: 114555975, R-01
/**
 * This class repersents “N: Start Navigation” command for the Maps app.
 * 
 * @author Phi Long Bui
 */
public class StartNavigation implements Command {
    private String source, destination;

    /**
     * * This Constructor used to create a new StartNavigation object
     * 
     * @param commandStack
     *                     the current command stack
     */
    public StartNavigation(CommandStack commandStack) {
        try {
            if (commandStack.peek() instanceof FindPlace)
                this.destination = ((FindPlace) commandStack.peek()).getDestination();
            if (commandStack.peek() instanceof PlanRoute) {
                this.source = ((PlanRoute) commandStack.peek()).getSource();
                this.destination = ((PlanRoute) commandStack.peek()).getDestination();
            }
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
        try {
            if ((stack.peek() instanceof FindPlace) || (stack.peek() instanceof PlanRoute))
                return true;
        } catch (EmptyStackException e) {
            return false;
        }
        return false;
    }

    /**
     * This method return the destination
     * 
     * @return
     *         The destination
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
     * This class returns the String representation of this Command in short form
     * (for stack display)
     * 
     * @return
     *         returns the String representation of this Command in short form (for
     *         stack display)
     */
    public String toShortString() {
        String str = "";
        if (source == null)
            str += String.format("N:%s", destination);
        else
            str += String.format("N:%s-%s", source, destination);
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
        String str = "";
        if (source == null)
            str += String.format("Current Screen: Navigating to %s\n", destination);
        else
            str += String.format("Current Screen: Navigating from %s to %s\n", source, destination);
        return str;
    }
}
