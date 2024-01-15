// Name: Phi Long Bui, ID: 114555975, R-30
/**
 * This class contains a reference to an ItemInfo object as well as to two other
 * ItemInfoNode objects, referred to as prev and next.
 * 
 * @author Phi Long Bui
 */
public class ItemInfoNode {
    private ItemInfoNode prev, next;
    private ItemInfo info;

    /**
     * This is the Contrustor the create the item info
     * 
     * @param info
     *             the item info
     */
    public ItemInfoNode(ItemInfo info) {
        if (info == null) {
            throw new IllegalArgumentException("This item has no info");
        }
        this.info = info;
        this.next = null;
        this.prev = null;
    }

    /**
     * This method sets this info to the given info
     * 
     * @param info
     *             The given info
     */
    public void setInfo(ItemInfo info) {
        this.info = info;
    }

    /**
     * This method gets the info of the Item
     * 
     * @return
     *         The info of the item
     */
    public ItemInfo getInfo() {
        return info;
    }

    /**
     * This method sets the next value of the linklist to the given node
     * 
     * @param node
     *             The given node
     */
    public void setNext(ItemInfoNode node) {
        this.next = node;
    }

    /**
     * This method sets the next value of the linklist to the given node
     * 
     * @param node
     *             The given node
     */
    public void setPrev(ItemInfoNode node) {
        this.prev = node;
    }

    /**
     * This method gets the next value of the linkedlist
     * 
     * @return
     *         the next value of the linkedlist
     */
    public ItemInfoNode getNext() {
        return next;
    }

    /**
     * This method gets the previous value of the linkedlist
     * 
     * @return
     *         the previous value of the linkedlist
     */
    public ItemInfoNode getPrev() {
        return prev;
    }
}
