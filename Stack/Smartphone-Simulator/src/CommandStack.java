
// Name: Phi Long Bui, ID: 114555975, R-01
import java.util.Stack;

/**
 * This class repersent the stack of Command
 * 
 * @author Phi Long Bui
 */
public class CommandStack {
    private Stack<Command> stack = new Stack<Command>();

    /**
     * This method pushes command onto the top of the backing data structure.
     * 
     * @param command
     *                the given command
     * @throws InvalidCommandException
     *                                 if the Command is invalid given the current
     *                                 state of this CommandStack
     */
    public void push(Command command) throws InvalidCommandException {
        stack.push(command);
    }

    /**
     * This method removes the topmost Command from the stack and returns it.
     * 
     * @return
     *         The removed command
     * @throws EmptyStackException
     *                             if the stack was empty
     */
    public Command pop() throws EmptyStackException {
        if (stack.isEmpty())
            throw new EmptyStackException();
        else
            return stack.pop();
    }

    /**
     * This method returns the topmost Command from the stack without removing it
     * 
     * @return
     *         The topmost Command from the stack without removing it
     * @throws EmptyStackException
     *                             If the stack was empty
     */
    public Command peek() throws EmptyStackException {
        if (stack.isEmpty())
            throw new EmptyStackException();
        else
            return stack.peek();
    }

    /**
     * This method return the size of the stack
     * 
     * @return
     *         the size of the stack
     */
    public int getSize() {
        return stack.size();
    }

    /**
     * This method returns true if the stack is empty, false otherwise.
     * 
     * @return
     *         true if the stack is empty, false otherwise.
     */
    public boolean isEmpty() {
        return stack.empty();
    }

    /**
     * This method returns a String representation of this CommandStack.
     * 
     * @return
     *         A String representation of this CommandStack.
     * 
     */
    public String toString() {
        String str = "Stack Debug:\n";
        str += String.format("%s", stack.get(0).toShortString());
        for (int i = 1; i < stack.size(); i++) {
            str += String.format("->%s", stack.get(i).toShortString());
        }
        str += "\n";
        return str;
    }

    /**
     * Returns a String representation of the Command that will be displayed on the
     * screen.
     * 
     * @return
     *         A String representation of the Command that will be displayed on the
     *         screen.
     */
    public String getScreenCommand() {
        String str = String.format("%s", stack.peek());
        return str;
    }

}
