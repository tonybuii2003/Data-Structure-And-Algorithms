// Name: Phi Long Bui, ID: 114555975, R-01
/**
 * This class is an Exception class that is thrown when trying to pop from a
 * stack with no elements.
 * 
 * @author Phi Long Bui
 */
public class EmptyStackException extends Exception {
    /**
     * This is the contructor of EmptyStackException
     */
    public EmptyStackException() {
        super("This stack is empty");
    }
}
