// Name: Phi Long Bui, ID: 114555975, R-01
/**
 * This class wraps a Delivery object to allow it to be inserted into a doubly
 * linked-list data structure
 * Ths class contained in a field called data and there should be two
 * DeliveryListNode references
 * serving as ‘links’ to the previous and next DeliveryListNodes in the list.
 * 
 * @author Name: Phi Long Bui
 */
public class DeliveryListNode {
    private DeliveryListNode next, prev;
    private Delivery data;

    /**
     * This Constructor used to create a new Book object
     * By default, next and prev is set to null
     * 
     * @param initData
     *                 the given Delivery data
     * @throws IllegalArgumentException
     *                                  if initData is null
     */
    public DeliveryListNode(Delivery initData) throws IllegalArgumentException {
        if (initData == null)
            throw new IllegalArgumentException();
        else {
            this.data = initData;
            this.next = null;
            this.prev = null;
        }
    }

    /**
     * This method return the next link of DeliveryListNode
     * 
     * @return
     *         The next link of DeliveryListNode
     */
    public DeliveryListNode getNext() {
        return next;
    }

    /**
     * This method sets this next to a given link
     * 
     * @param next
     *             The given link
     */
    public void setNext(DeliveryListNode next) {
        this.next = next;
    }

    /**
     * This method return the previous link of DeliveryListNode
     * 
     * @return
     *         The previous link of DeliveryListNode
     */
    public DeliveryListNode getPrevious() {
        return prev;
    }

    /**
     * This method sets this previous to a given link
     * 
     * @param prev
     *             The given link
     */
    public void setPrevious(DeliveryListNode prev) {
        this.prev = prev;
    }

    /**
     * This method returns the data from DeliveryListNode's link
     * 
     * @return
     *         The data from DeliveryListNode's link
     */
    public Delivery getData() {
        return data;
    }

    /**
     * This method sets this link data to a given link
     * 
     * @param data
     *             The given link
     */
    public void setData(Delivery data) {
        this.data = data;
    }
}
