// Name: Phi Long Bui, ID: 114555975, R-01
/**
 * This class repersents the list of deliveries which implements a double
 * linked-list data structure
 * maintain a list of Deliveries by chaining a series of DeliveryListNodes
 * between a head and a tail reference
 * a cursor should be provided to allow a user to traverse the list,
 * selecting individual DeliveryListNodes to allow for insertion,
 * deletion, and manipulation of the Deliveries they contain.
 * 
 * @author Name: Phi Long Bui
 */
public class DeliveryList {
    private DeliveryListNode head, tail, cursor;
    private int count = 0;

    public DeliveryList() {
        this.head = null;
        this.tail = null;
        this.cursor = null;
    }

    /**
     * This method return the number of deliveries the list have
     * 
     * @return the number of deliveries the list have
     */
    public int numDeliveries() {
        return count;
    }

    /**
     * This method gets the reference to the Delivery wrapped by the
     * DeliveryListNode
     * currently referenced by cursor
     * If the cursor is null, then this method should return null as well
     * 
     * @return the reference by the Delivery wrapped by the DeliveryListNode
     *         currently referenced by cursor.
     */
    public Delivery getCursor() {
        if (cursor == null)
            return null;
        return cursor.getData();
    }

    /**
     * This method sets the cursor to the tail oof the list
     */
    public void resetCursorToTail() {
        if (tail != null)
            cursor = tail;
        else
            cursor = null;
    }

    /**
     * This method moves the cursor to select the next DeliveryListNode in the list
     */
    public void resetCursorToHead() {
        if (head != null)
            cursor = head;
        else
            cursor = null;

    }

    /**
     * This method moves the cursor to select the next DeliveryListNode in the list
     * 
     * @throws EndOfListException
     *                            If the cursor is at the tail of the list
     */
    public void cursorForward() throws EndOfListException {
        if (cursor.getNext() == null)
            throw new EndOfListException();
        else
            cursor = cursor.getNext();
    }

    /**
     * This method moves the cursor to select the previous DeliveryListNode in the
     * list
     * 
     * @throws EndOfListException
     *                            If the cursor is at the head of the list
     */
    public void cursorBackward() throws EndOfListException {
        if (cursor == head)
            throw new EndOfListException();
        else
            cursor = cursor.getPrevious();
    }

    /**
     * This method inserts the indicated Delivery after the cursor.
     * newDelivery has been wrapped in a new DeliveryListNode object.
     * If cursor was previously not null, the newly created DeliveryListNode
     * has been inserted into the list after the cursor.
     * If cursor was previously null, the newly created DeliveryListNode
     * has been set as the new head of the list (as well as the tail).
     * The cursor remains unchanged.
     * 
     * @param newDelivery
     *                    is the indicated Delivery
     * @throws IllegalArgumentException
     *                                  when newDelivery is null.
     */
    public void insertAfterCursor(Delivery newDelivery) throws IllegalArgumentException {
        if (newDelivery.getDest() != null
                && newDelivery.getInstruction() != null
                && newDelivery.getSource() != null) {
            DeliveryListNode newNode = new DeliveryListNode(newDelivery);
            if (cursor != null) {
                if (cursor.getNext() == null) {
                    newNode.setNext(cursor.getNext());
                    newNode.setPrevious(cursor);
                    cursor.setNext(newNode);
                    while (tail.getNext() != null) {
                        tail = tail.getNext();
                    }
                } else {
                    newNode.setNext(cursor.getNext());
                    newNode.setPrevious(cursor);
                    cursor.getNext().setPrevious(newNode);
                    cursor.setNext(newNode);
                }
            } else if (cursor == null) {
                if (head == null) {
                    head = newNode;
                    tail = newNode;
                    resetCursorToHead();
                } else {
                    newNode.setPrevious(head);
                    head.setNext(newNode);
                    while (tail.getNext() != null) {
                        tail = tail.getNext();
                    }
                }
            }
            count++;
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * This method inserts the indicated Delivery after the tail of the list.
     * 
     * @param newDelivery
     *                    the indicated Delivery
     * @throws IllegalArgumentException
     *                                  when newDelivery is null.
     */
    public void appendToTail(Delivery newDelivery) throws IllegalArgumentException {
        if (newDelivery.getDest() != null
                && newDelivery.getInstruction() != null
                && newDelivery.getSource() != null)
            throw new IllegalArgumentException();
        else {
            DeliveryListNode newNode = new DeliveryListNode(newDelivery);
            if (head == null) {
                head = newNode;
            } else
                tail.setNext(newNode);
        }
    }

    /**
     * This method removes the DeliveryListNode referenced by cursor and returns the
     * Delivery inside.
     * 
     * @return
     *         the Delivery inside the removed cusor
     * @throws EndOfListException
     *                            if cursor is null
     */
    public Delivery removeCursor() throws EndOfListException {
        Delivery tmp = null;
        if (cursor != null) {
            tmp = cursor.getData();
            if (cursor == head) {
                if (head != null)
                    head = head.getNext();
                else
                    tail = null;
                resetCursorToHead();
            } else if (cursor == tail) {
                tail = tail.getPrevious();
                cursor.getPrevious().setNext(cursor.getNext());
                resetCursorToTail();
            } else {
                cursor.getPrevious().setNext(cursor.getNext());
                cursor.getNext().setPrevious(cursor.getPrevious());
                cursor = cursor.getNext();
            }
            count--;
        } else {
            throw new EndOfListException();
        }
        return tmp;
    }

    /**
     * This method prints the delivery list as string format
     * 
     * @return
     *         the delivery list as string format
     */
    public String toString() {
        String str = "";
        str += "-----------------------------------------------------------\n";
        try {
            DeliveryListNode start = head;

            while (start != null) {
                if (start != cursor)
                    str += String.format("|%-57s|%n", "~");
                else if (start == cursor)
                    str += String.format("|%-57s|%n", "->");
                str += start.getData();
                start = start.getNext();
            }

        } catch (Exception e) {

        }
        str += "-----------------------------------------------------------\n";
        return str;
    }
}
