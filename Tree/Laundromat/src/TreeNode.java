//Name: Phi Long Bui ID: 114555975 R-30
/**
 * This class stores the data in each node.
 * 
 * @author Phi Long Bui
 */
public class TreeNode {
    private TreeNode[] nodes;
    private String label, message, prompt;
    private TreeNode parent;

    /**
     * This Contructor is for TreeNode
     */
    public TreeNode() {
        this.nodes = null;
        this.label = null;
        this.message = null;
        this.prompt = null;
        this.parent = null;
    }

    /**
     * This method gets the label of the node
     * 
     * @return
     *         The label of the node
     */
    public String getLabel() {
        return label;
    }

    /**
     * This method gets the message of the node.
     * 
     * @return
     *         The message of the node
     */
    public String getMessage() {
        return message;
    }

    /**
     * This method gets the prompt of the node
     * 
     * @return
     *         The prompt of the node.
     */
    public String getPrompt() {
        return prompt;
    }

    /**
     * This method gets the parent of the node
     * 
     * @return
     */
    public TreeNode getParent() {
        return parent;
    }

    /**
     * This method gets the children of the node
     * 
     * @return
     *         The children of the node.
     */
    public TreeNode[] getNodes() {
        return nodes;
    }

    /**
     * This method sets the label to the given label
     * 
     * @param label
     *              The given label
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * This method sets the parent to the given parent
     * 
     * @param parent
     *               The given parent
     */
    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    /**
     * This method sets the message to the given message.
     * 
     * @param message
     *                The given message.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * This method sets the prompt to the given prompt
     * 
     * @param prompt
     *               The given prompt
     */
    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    /**
     * This method sets the children to the given children
     * 
     * @param nodes
     *              The given children
     */
    public void setNodes(TreeNode[] nodes) {
        this.nodes = nodes;
    }

    /**
     * This method returns a string contains the node information
     * 
     * @return
     *         a string contains the node information
     */
    public String toString() {
        String str = "";
        str += String.format("Label: %s\n", label);
        str += String.format("Prompt: %s\n", prompt);
        str += String.format("Message: %s\n", message);
        return str;
    }

}
