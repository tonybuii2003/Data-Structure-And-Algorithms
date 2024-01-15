// Name: Phi Long Bui, ID: 114555975, R-01
/**
 * This class is an Exception class that is thrown when trying to push a Command
 * that is not valid for the given CommandStack.
 * 
 * @author Phi Long Bui
 */
public class InvalidCommandException extends Exception {
    /**
     * This is the Contructor of InvalidCommandException
     */
    public InvalidCommandException() {
        super("The command is invalid, please try again");
    }
}
