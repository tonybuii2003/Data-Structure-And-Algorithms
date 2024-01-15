
// Name: Phi Long Bui, ID: 114555975, R-30
import java.util.LinkedList;
import java.util.Queue;

/**
 * This class persent the queue of requests
 * 
 * @author Phi Long Bui
 */
public class RequestQueue extends LinkedList<Request> {
    private Queue<Request> queue = new LinkedList<Request>();

    /**
     * This Contrustor is for the RequestQueue
     */
    public RequestQueue() {

    }

    /**
     * This method enqueue the request into the queue
     * 
     * @param request
     *                The request
     */
    public void enqueue(Request request) {
        queue.add(request);
    }

    /**
     * This method dequeue the request from the queue
     */
    public Request dequeue() {
        Request tmp = queue.remove();
        return tmp;
    }

    /**
     * This method return the value of the last element added to the queue
     */
    public Request peek() {
        return queue.peek();
    }

    /**
     * This method returns the size of the queue
     */
    public int size() {
        return queue.size();
    }

    /**
     * This method returns true of the queue is empty false otherwise
     * 
     * @return
     *         true of the queue is empty false otherwise
     */
    public boolean isEmtpy() {
        return queue.isEmpty();
    }

    /**
     * This toString method returns a string repersents a request that have the
     * source and destination for debugging
     * 
     * @return
     *         A string repersents a request that have the source and destination
     *         for debugging
     * 
     */
    public String toString() {
        String str = "";
        for (Request request : queue) {
            str += String.format("F%s@F%s,", request.getSourceFloor(), request.getDestinationFloor());
        }

        return str.substring(0, str.length() - 1);
    }
}
