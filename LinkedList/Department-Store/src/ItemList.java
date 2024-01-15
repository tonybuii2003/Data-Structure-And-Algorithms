// Name: Phi Long Bui, ID: 114555975, R-30
/**
 * This class contains references to the head and tail of a list of ItemInfoNode
 * nodes.
 * 
 * @author Phi Long Bui
 */
public class ItemList {
    private ItemInfoNode head, tail, cursor;

    /**
     * This Contructor is for the Item List
     */
    public ItemList() {
        this.head = null;
        this.tail = null;
        this.cursor = null;
    }

    /**
     * This method Inserts the info into the list in its correct position based on
     * its rfidTagNumber.
     * 
     * Time complexities: best O(1) when the list is empty worst O(n) when there are
     * item in the list and insert the item in order
     * 
     * @param name
     *                     The name of the item
     * @param rfidTag
     *                     The rfidNumber
     * @param price
     *                     The price of the item
     * @param initPosition
     *                     The posision of the item
     */
    public void insertInfo(String name, String rfidTag, double price, String initPosition) {
        ItemInfo itemData = new ItemInfo(name, rfidTag, initPosition, price);
        itemData.setCurrentLocation(initPosition);
        ItemInfoNode newNode = new ItemInfoNode(itemData);
        cursor = head;

        if (cursor == null) {
            if (head == null) {
                head = newNode;
                tail = newNode;
                cursor = newNode;
            } else {
                newNode.setPrev(head);
                head.setNext(newNode);
                while (tail.getNext() != null) { // relocate the tail (best O(1) - when tail is at its tail worst O(n)
                                                 // otherwise
                    tail = tail.getNext();
                }
            }

        } else if (cursor != null) {
            ItemInfoNode prev = null;
            while (cursor != null
                    && cursor.getInfo().getRfidTagNumber().compareTo(newNode.getInfo().getRfidTagNumber()) <= 0) {
                prev = cursor;
                cursor = cursor.getNext();

            }
            if (prev.getNext() == null) {
                newNode.setNext(prev.getNext());
                newNode.setPrev(prev);
                prev.setNext(newNode);
                while (tail.getNext() != null) {
                    tail = tail.getNext();
                }
            } else {
                newNode.setNext(prev.getNext());
                newNode.setPrev(prev);
                prev.getNext().setPrev(newNode);
                prev.setNext(newNode);
            }
        }
    }

    /**
     * This method removes all nodes in the list that have current location listed
     * as "out" and displays a list of all items that have been removed in this
     * fashion.
     * 
     * Time complexities: best worst O(n) when as the list iterate from the head to
     * the tail
     */
    public void removeAllPurchased() {
        ItemInfoNode tmp = head;
        System.out.println("The following item(s) have removed from the system:");
        System.out.print("                               Original        Current\n");
        System.out.print("Item Name         RFID         Location        Location     Price\n");
        System.out.print("---------       ---------     ---------        ---------   ------\n");
        while (tmp != null) {
            if (tmp.getInfo().getCurrentLocaion().equals("out")) {
                System.out.printf("%-16s%-16s%-16s%-12s%-5.2f\n", tmp.getInfo().getName(),
                        tmp.getInfo().getRfidTagNumber(),
                        tmp.getInfo().getLocation(), tmp.getInfo().getCurrentLocaion(), tmp.getInfo().getPrice());
                if (tmp == head) {
                    if (head != null) {
                        head = head.getNext();
                    } else {
                        tail = null;
                    }
                } else if (tmp == tail) {
                    tail = tail.getPrev();
                    tmp.getPrev().setNext(tmp.getNext());
                } else {
                    tmp.getPrev().setNext(tmp.getNext());
                    tmp.getNext().setPrev(tmp.getPrev());
                }
            }
            tmp = tmp.getNext();
        }
    }

    /**
     * Moves an item with a given rfidTagNumber from a source location to a dest
     * location. The return value indicates whether or not an item of the given
     * rfidTagNumber was found at the given source location.
     * Time complexities: best O(1) when the first item is the
     * target worst O(n) when the target is at tail
     * 
     * @param rfidTag
     *                rfid number
     * @param source
     *                The location need to move
     * @param dest
     *                The destination
     * @return
     *         True if the location is found false otherwises
     * @throws Exception
     *                   if dest is of an invalid format (that it is not a cart,
     *                   shelf, or "out") and
     *                   also if source is equal to "out".
     */
    public boolean moveItem(String rfidTag, String source, String dest) throws Exception {
        ItemInfoNode tmp = head;
        if (source.equals("out") && !dest.matches("c[0-9]{3}") && !dest.matches("s[0-9]{5}")) {
            throw new Exception("Invalid format");
        }
        while (tmp != null && !tmp.getInfo().getRfidTagNumber().equals(rfidTag)) {
            tmp = tmp.getNext();
        }
        while (tmp != null && !tmp.getInfo().getCurrentLocaion().equals(source)) {
            tmp = tmp.getNext();
        }
        if (tmp == null)
            return false;
        tmp.getInfo().setCurrentLocation(dest);
        return true;
    }

    /**
     * Prints a neatly formatted list of all items currently in the list.
     * Time complexities: best worst O(n) when as the list iterate from the head to
     * the tail
     */
    public void printAll() {
        System.out.print("                               Original        Current\n");
        System.out.print("Item Name         RFID         Location        Location     Price\n");
        System.out.print("---------       ---------     ---------        ---------   ------\n");
        ItemInfoNode tmp = head;
        while (tmp != null) {
            System.out.printf("%-16s%-16s%-16s%-12s%-5.2f\n", tmp.getInfo().getName(), tmp.getInfo().getRfidTagNumber(),
                    tmp.getInfo().getLocation(), tmp.getInfo().getCurrentLocaion(), tmp.getInfo().getPrice());
            tmp = tmp.getNext();
        }

    }

    /**
     * This method prints a neatly formatted list of all items in a specified
     * current location. The table should include each item's name, rfidTagNumber,
     * original location, current location, and price. The list must be sorted in
     * order of rfidTagNumber, although duplicate rfidTagNumber entries may be
     * printed in any order.
     * 
     * * Time complexities: best worst O(n) when as the list iterate from the head
     * to
     * the tail
     * 
     * @param location
     *                 The location of the item
     */
    public void printByLocation(String location) {
        System.out.print("                               Original        Current\n");
        System.out.print("Item Name         RFID         Location        Location     Price\n");
        System.out.print("---------       ---------     ---------        ---------   ------\n");
        ItemInfoNode tmp = head;
        while (tmp != null) {
            if (location.equals(tmp.getInfo().getCurrentLocaion())) {
                System.out.printf("%-16s%-16s%-16s%-12s%-5.2f\n", tmp.getInfo().getName(),
                        tmp.getInfo().getRfidTagNumber(),
                        tmp.getInfo().getLocation(), tmp.getInfo().getCurrentLocaion(), tmp.getInfo().getPrice());
            }
            tmp = tmp.getNext();
        }
    }

    /**
     * This method takes every item that is currently in the store and on the wrong
     * shelf and places it back where it belongs (its original location). Items that
     * are "out" or currently in a cart are not affected by this command. Display a
     * table for all out of place items moved in this fashion, including each item's
     * name, rfidTagNumber, current location(before the move), original location and
     * price.
     * Time complexities: best worst O(n) when as the list iterate from the head to
     * the tail
     */
    public void cleanStore() {
        System.out.printf("\nThe following item(s) have been moved back to their original locations:\n\n");
        System.out.print("                               Original        Current\n");
        System.out.print("Item Name         RFID         Location        Location     Price\n");
        System.out.print("---------       ---------     ---------        ---------   ------\n");
        ItemInfoNode tmp = head;
        while (tmp != null) {
            if (!tmp.getInfo().getLocation().equals(tmp.getInfo().getCurrentLocaion())
                    && !tmp.getInfo().getCurrentLocaion().equals("out")) {
                tmp.getInfo().setCurrentLocation(tmp.getInfo().getLocation());
                System.out.printf("%-16s%-16s%-16s%-12s%-5.2f\n", tmp.getInfo().getName(),
                        tmp.getInfo().getRfidTagNumber(),
                        tmp.getInfo().getLocation(), tmp.getInfo().getCurrentLocaion(), tmp.getInfo().getPrice());
            }
            tmp = tmp.getNext();
        }
    }

    /**
     * This method goes through a given cart and checks out each item (changes its
     * location to "out"). A neatly formatted list of the items checked out is to be
     * printed and it must be sorted in order of rfidTagNumber, although duplicate
     * rfidTagNumber entries may be printed in any order. The return value is the
     * total cost for the items that were in the cart. Throw appropriate exceptions
     * for invalid cart numbers.
     * Time complexities: best worst O(n) when as the list iterate from the head to
     * the tail
     */
    public double checkOut(String cartNumber) {
        ItemInfoNode tmp = head;
        double total = 0;
        System.out.print("                               Original        Current\n");
        System.out.print("Item Name         RFID         Location        Location     Price\n");
        System.out.print("---------       ---------     ---------        ---------   ------\n");
        while (tmp != null) {
            if (tmp.getInfo().getCurrentLocaion().equals(cartNumber)) {
                total += tmp.getInfo().getPrice();

                System.out.printf("%-16s%-16s%-16s%-12s%-5.2f\n", tmp.getInfo().getName(),
                        tmp.getInfo().getRfidTagNumber(),
                        tmp.getInfo().getLocation(), tmp.getInfo().getCurrentLocaion(), tmp.getInfo().getPrice());
                tmp.getInfo().setCurrentLocation("out");
            }
            tmp = tmp.getNext();
        }
        return total;
    }

    /**
     * This method prints a neatly formatted list of all items in a specified
     * current rfid. The table should include each item's name, rfidTagNumber,
     * original location, current location, and price. The list must be sorted in
     * order of rfidTagNumber, although duplicate rfidTagNumber entries may be
     * printed in any order.
     * Time complexities: best worst O(n) when as the list iterate from the head to
     * the tail
     * 
     * @param rfid
     *             The given rfid
     */
    public void printByRfid(String rfid) {
        ItemInfoNode tmp = head;
        System.out.print("                               Original        Current\n");
        System.out.print("Item Name         RFID         Location        Location     Price\n");
        System.out.print("---------       ---------     ---------        ---------   ------\n");
        while (tmp != null) {
            if (rfid.equals(tmp.getInfo().getRfidTagNumber())) {

                System.out.printf("%-16s%-16s%-16s%-12s%-5.2f\n", tmp.getInfo().getName(),
                        tmp.getInfo().getRfidTagNumber(),
                        tmp.getInfo().getLocation(), tmp.getInfo().getCurrentLocaion(), tmp.getInfo().getPrice());
            }
            tmp = tmp.getNext();
        }
    }
}
