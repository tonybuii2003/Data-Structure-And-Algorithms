
//Name: Phi Long Bui ID: 114555975 R-30
import java.io.File;
import java.util.Scanner;

/**
 * This class has a reference to the root of the tree and other useful methods
 * 
 * @author Phi Long Bui
 */
public class Tree {
    private TreeNode root;

    /**
     * This Contructor is for Tree
     */
    public Tree() {

    }

    /**
     * This method gets the root of the tree
     * 
     * @return
     */
    public TreeNode getRoot() {
        return root;
    }

    /**
     * This method sets the root to the given root
     * 
     * @param root
     *             The given root
     */
    public void setRoot(TreeNode root) {
        this.root = root;
    }

    /**
     * This method adds a TreeNode to the tree. The location will be a child of
     * parentLabel. The child node should be left justified meaning that it should
     * first be placed in the left most TreeNode reference, then the middle, then
     * the right.
     * 
     * @param label
     *                    The label
     * @param prompt
     *                    The prompt from the label
     * @param message
     *                    The message from the label
     * @param parentLabel
     *                    The label of the parent
     * @return
     *         value of true indicates that the node was successfully added to the
     *         tree. Otherwise, the return value is false.
     * @throws Exception
     */
    public boolean addNode(String label, String prompt, String message, String parentLabel) throws Exception {
        TreeNode newNode = new TreeNode();
        newNode.setLabel(label);
        newNode.setPrompt(prompt);
        newNode.setMessage(message);
        String[] tmp = label.split("-");
        TreeNode parent = null;
        if (Integer.parseInt(tmp[tmp.length - 1]) > 9) {
            throw new Exception("Invalid location, please try again!");
        }
        TreeNode cursor = root;
        for (int i = 0; i < tmp.length; i++) {
            int index = Integer.parseInt(tmp[i]);
            if (i == tmp.length - 1) {
                if (parent != null) {
                    cursor.setParent(parent);
                }
                cursor.getNodes()[index - 1] = newNode;
                return true;
            } else {
                parent = cursor;
                cursor = cursor.getNodes()[index - 1];

            }
        }
        return false;
    }

    /**
     * This method returns a reference to the TreeNode that has the given label. The
     * return
     * value is null if the label is not found. This may be helpful to implement the
     * above method.
     * 
     * @param label
     *              The label node
     * @return
     *         A reference to the TreeNode that has the given label. The return
     *         value is null if the label is not found.
     * 
     */
    public TreeNode getNodeReference(String label) {
        return helperGetNode(root, label);
    }

    /**
     * This helper method is for getNodeReference()
     * 
     * @param current
     *                The current node
     * @param label
     *                The given label
     * @return
     *         The target node
     */
    public TreeNode helperGetNode(TreeNode current, String label) {
        if (current != null) {
            if (current.getLabel().equals(label)) {
                return current;
            } else {
                for (TreeNode node : current.getNodes()) {
                    helperGetNode(node, label);
                }
            }
        }
        return null;

    }

    /**
     * * This method prints the tree in pre order
     */
    public void preOrder() {
        preOrderHelper(root);
    }

    /**
     * This helper method is for preOrder()
     * 
     * @param cursor
     *               The current node
     */
    public void preOrderHelper(TreeNode cursor) {
        System.out.print("\n" + cursor);
        if (cursor.getNodes() != null) {
            for (TreeNode i : cursor.getNodes()) {
                if (i != null)
                    preOrderHelper(i);
            }
        }

    }

    /**
     * This method start the question and answer session
     */
    public void beginSession() {
        System.out.println("Help Session Starting...");
        beginSessionHelpter(root, new Scanner(System.in));
    }

    /**
     * This is the helper method for beginSession()
     * 
     * @param cursor
     *               The current node
     * @param inp
     *               The scanner
     */
    public void beginSessionHelpter(TreeNode cursor, Scanner inp) {
        System.out.println(cursor.getMessage());
        if (cursor.getNodes() != null) {
            boolean isDone = false;
            while (!isDone) {
                for (int i = 1; i < cursor.getNodes().length + 1; i++) {
                    System.out.printf("%d %s\n", i, cursor.getNodes()[i - 1].getPrompt());
                }
                System.out.println("B Go Back");
                System.out.println("0 Exit Session.");
                System.out.print("Choice> ");
                String input = inp.nextLine().toLowerCase();
                System.out.println();
                if (input.equals("0")) {
                    System.out.println("\nThank you for using this automated help service!");
                    return;
                }
                for (int i = 1; i < cursor.getNodes().length + 1; i++) {
                    if (input.equals("" + i)) {
                        isDone = true;
                        beginSessionHelpter(cursor.getNodes()[i - 1], inp);
                    }
                }
                if (input.equals("b")) {
                    if (cursor.getParent() != null) {
                        isDone = true;
                        beginSessionHelpter(cursor.getParent(), inp);
                    }
                }
                if (!isDone)
                    System.out.println("Invalid please try again!\n");
            }
        } else {
            System.out.println("\nThank you for using this automated help service!");
            return;
        }
    }

    /**
     * This method reads the file using the file name and store the value inside a
     * tree
     * 
     * @param fileName
     *                 The name of the file
     */
    public void loadFile(String fileName) {
        try {
            Scanner inp = new Scanner(System.in);
            File file = new File(fileName);
            inp = new Scanner(file);
            String line;

            int count = 0;
            while (inp.hasNextLine()) {
                line = inp.nextLine().trim();
                while (line.isEmpty() || line.trim().equals("") || line.trim().equals("\n")) {
                    line = inp.nextLine().trim();
                }
                if (line.equals("root")) {
                    TreeNode root = new TreeNode();
                    root.setLabel(line);
                    count = 0;
                    while (!line.matches("root [0-9]{1}")) {
                        line = inp.nextLine().trim();
                        while (line.isEmpty() || line.trim().equals("") || line.trim().equals("\n")) {
                            line = inp.nextLine().trim();
                        }
                        count += 1;
                        switch (count) {
                            case 1: {
                                root.setPrompt(line);
                            }
                                break;
                            case 2: {
                                root.setMessage(line);
                            }
                                break;
                        }

                    }
                    if (line.matches("root [0-9]{1}")) {
                        String[] tmp = line.split(" ");
                        int numChild = Integer.parseInt(tmp[1]);
                        root.setNodes(new TreeNode[numChild]);
                    }
                    this.setRoot(root);
                } else {

                    String label, prompt = "", message = "", parentLabel = "";
                    label = line;
                    if (line.matches("([0-9]{1}|[0-9](-[0-9]){1,}) [0-9]{1}")) {
                        String[] tmp = label.split(" ");
                        if (tmp[0].matches("[0-9](-[0-9]){1,}")) {
                            String[] location = tmp[0].split("-");
                            TreeNode cursor = root;
                            for (int i = 0; i < location.length; i++) {
                                cursor = cursor.getNodes()[Integer.parseInt(location[i]) - 1];

                            }
                            cursor.setNodes(new TreeNode[Integer.parseInt(tmp[1])]);
                        }
                        if (label.matches("[0-9]{1} [0-9]{1}")) {
                            TreeNode cursor = root.getNodes()[Integer.parseInt(tmp[0]) - 1];
                            cursor.setNodes(new TreeNode[Integer.parseInt(tmp[1])]);
                        }
                    } else {
                        count = 0;
                        while (count != 2) {
                            line = inp.nextLine().trim();
                            while (line.isEmpty() || line.trim().equals("") || line.trim().equals("\n")) {
                                line = inp.nextLine().trim();
                            }
                            count += 1;
                            switch (count) {
                                case 1: {
                                    prompt = line;
                                }
                                    break;
                                case 2: {
                                    message = line;
                                }
                                    break;
                            }
                        }
                        if (label.matches("[0-9]{1}")) {
                            parentLabel = root.getLabel();
                        }
                        this.addNode(label, prompt, message, parentLabel);
                    }
                }
            }
            System.out.println("Tree created successfully!");
            inp.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
