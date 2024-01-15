// Name: Phi Long Bui, ID: 114555975, R-01
/**
 * This class persents a Network Node which holds the type of component being
 * represented,
 * an array of children (null if this will be a Nintendo), and string for the
 * text
 * 
 * @author Phi Long Bui
 */
public class NetworkNode {
    private String name;
    private boolean isNintenddo;
    private boolean isBroken;
    private NetworkNode parent;
    private NetworkNode[] children;
    private int MAXCHILDREN = 9;

    /**
     * This is a Constructor used to create a new NetworkNode object
     * The Constructor contains an Array-like data structure, children, with maximum
     * size
     * limited by MAXCHILDREN
     * The list of children may not contain "holes" in it
     */
    public NetworkNode(String name, boolean isNintenddo) {
        this.name = name;
        this.parent = null;
        this.children = new NetworkNode[MAXCHILDREN];
        this.isBroken = false;
        this.isNintenddo = isNintenddo;
    }

    /**
     * This method returns the name of the node
     * 
     * @return
     *         The name of the node
     */
    public String getName() {
        return name;
    }

    /**
     * This method returns the maximun amount of children its parent can carry
     * 
     * @return
     *         The maximum amount of children
     */
    public int getMaxChildren() {
        return MAXCHILDREN;
    }

    /**
     * This method returns a boolean value whether the node is nintendo or not
     * 
     * @return
     *         true if the node is nintendo, false otherwise
     */
    public boolean getIsNintendo() {
        return isNintenddo;
    }

    /**
     * This method returns a boolean value determine if the node is broken
     * 
     * @return
     *         true if the node is broken, false otherwise
     */
    public boolean getIsBroken() {
        return isBroken;
    }

    /**
     * This method returns the node's parent
     * 
     * @return
     *         The node's parent
     */
    public NetworkNode getParent() {
        return parent;
    }

    /**
     * This method returns the children
     * 
     * @return
     *         The children
     */
    public NetworkNode[] getChildren() {
        return children;
    }

    /**
     * This method sets this class name to the given name
     * 
     * @param name
     *             The given name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method sets the MAXCHILDREN to the given MAXCHILDREN
     * 
     * @param MAXCHILDREN
     */
    public void setMaxChildren(int MAXCHILDREN) {
        this.MAXCHILDREN = MAXCHILDREN;
    }

    /**
     * This method sets IsNintendo to the given boolean value
     * 
     * @param isNintenddo
     *                    The given value
     */
    public void setIsNintendo(boolean isNintenddo) {
        this.isNintenddo = isNintenddo;
    }

    /**
     * This method set IsBroken to a new boolean value
     * 
     * @param isBroken
     *                 the given boolean value
     */
    public void setIsBroken(boolean isBroken) {
        this.isBroken = isBroken;
    }

    /**
     * This method sets the current parent to the given parent
     * 
     * @param parent
     *               The given parent
     */
    public void setParent(NetworkNode parent) {
        this.parent = parent;
    }

    /**
     * This method sets the Children at the given index to the given children array
     * 
     * @param children
     *                 The given children
     * @param index
     *                 The given index
     */
    public void setChildren(NetworkNode children, int index) {
        this.children[index] = children;
    }

    /**
     * The method return the node as a string.
     * 
     * @return
     *         The node as a string
     */
    public String toString() {
        String str = "";
        if (isNintenddo)
            str += String.format("%s", name.replaceAll("[0-9-]", ""));
        else
            str += String.format("%s", name.replaceAll("[0-9]", ""));
        return str;
    }
}
