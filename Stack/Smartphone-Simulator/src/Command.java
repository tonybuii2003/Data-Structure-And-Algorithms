// Name: Phi Long Bui, ID: 114555975, R-01
/**
 * This is an interface represents each command entered on the phone app
 * 
 * @author Phi Long Bui
 */
public interface Command {
    /**
     * This method returns whether this Command is valid and can be added to the
     * given CommandStack
     * 
     * @param stack
     * @return
     *         whether this Command is valid and can be added to the given
     *         CommandStack
     */
    public boolean validCommand(CommandStack stack);

    /**
     * This method returns the string representation of this Command in long form
     * (for current screen display)
     * 
     * @return
     *         the String representation of this Command in long form (for current
     *         screen display)
     */
    public String toString();

    /**
     * This method returns the String representation of this Command in short form
     * (for stack display)
     * 
     * @return
     *         the String representation of this Command in short form (for
     *         stack display)
     */
    public String toShortString();
}
