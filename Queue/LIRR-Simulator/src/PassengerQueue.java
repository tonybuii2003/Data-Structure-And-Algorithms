
// Name: Phi Long Bui, ID: 114555975, R-01
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * This class repersents a queue of Passengers
 * 
 * @author Phi Long Bui
 */
public class PassengerQueue extends LinkedList<Passenger> {
    private Queue<Passenger> queue = new LinkedList<Passenger>();;

    /**
     * This Contructor creates a new PassengerQueue object.
     */
    public PassengerQueue() {
    }

    /**
     * This method enqueues the Passenger into the queue.
     * 
     * @param passenger
     *                  The passenger
     */
    public void enqueue(Passenger passenger) {
        queue.add(passenger);
    }

    /**
     * This method removes the Passenger at the front of the queue.
     * 
     * @return
     *         The removed Passenger.
     */
    public Passenger dequeue() {
        Passenger tmp = queue.remove();
        return tmp;

    }

    /**
     * This method gets the Passenger at the front of the queue.
     * 
     * @return
     *         the Passenger at the front of the queue.
     */
    public Passenger peek() {
        return queue.peek();
    }

    /**
     * This mehtod returns the size of the queue.
     * 
     * @return
     *         The size of the queue
     */
    public int size() {
        return queue.size();
    }

    /**
     * This method returns If the queue is empty or not.
     * 
     * @return
     *         true if queue is empty
     *         false if the queue is not empty
     */
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    /**
     * This method return a queue in reverse order without changing the current
     * queue.
     * 
     * @return
     *         a queue in reverse order without changing the current queue.
     */
    public PassengerQueue getReverse() {
        Stack<Passenger> stack = new Stack<Passenger>();
        PassengerQueue tmp = new PassengerQueue();
        for (Passenger i : queue)
            tmp.enqueue(i);
        while (!tmp.isEmpty()) {
            stack.add(tmp.dequeue());
        }
        while (!stack.isEmpty()) {
            tmp.enqueue(stack.pop());
        }
        return tmp;
    }

    /**
     * This method returns the PasssengerQueue repersents as a String.
     * 
     * @return
     *         The PasssengerQueue repersents as a String.
     */
    public String toString() {
        String str = "[";
        if (!queue.isEmpty())
            for (Passenger i : queue) {
                if (i == queue.peek())
                    str += String.format("%s", i);
                else
                    str += String.format(", %s", i);
            }
        else
            str += "empty]";
        return str;
    }

}