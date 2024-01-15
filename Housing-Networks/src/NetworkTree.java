
// Name: Phi Long Bui, ID: 114555975, R-01
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.PrintWriter;

/**
 * This class represents a Network tree which contains a root
 * and the cursor to check which root it's at.
 * 
 * @author Phi Long Bui
 */
public class NetworkTree {
    private NetworkNode root;
    private NetworkNode cursor;

    /**
     * This is a Constructor used to create a new NetworkTree object
     */
    public NetworkTree() {

    }

    /**
     * This method return the root of the network tree
     * 
     * @return
     *         The root of the network tree
     */
    public NetworkNode getRoot() {
        return root;
    }

    /**
     * This method returns the cursor of the network tree
     * 
     * @return
     *         The cursor of the network tree
     */
    public NetworkNode getCursor() {
        return cursor;
    }

    /**
     * Thie method sets the root to the given root for the network tree
     * 
     * @param root
     *             The given root
     */
    public void setRoot(NetworkNode root) {
        this.root = root;
    }

    /**
     * This method sets the cursor to the given cursor for the network tree
     * 
     * @param cursor
     *               The given cursor
     */
    public void setCursor(NetworkNode cursor) {
        this.cursor = cursor;
    }

    /**
     * This method sets the cursor to the root of the NetworkTree
     */
    public void cursorToRoot() {
        cursor = NintendoNetwork.tree.root;
    }

    /**
     * This method removes the child at the specified index of the NetworkTree, as
     * well as all of its children.
     * 
     * @return
     *         The removed node
     */
    public NetworkNode cutCursor() {
        NetworkNode tmp = null;
        tmp = cursor;
        cursorToParent(); // Cursor goes to parent
        for (int i = 0; i < cursor.getChildren().length; i++) {
            if (cursor.getChildren()[i] == tmp) {
                cursor.setChildren(null, i);
                break;
            }
        }
        return tmp;
    }

    /**
     * This method adds the given node to the corresponding index of the children
     * array.
     * 
     * @param index
     *              The given index
     * @param node
     *              The given tree node
     * @throws Exception
     *                   if adding the node at the specified index makes a hole in
     *                   the array.
     */
    public void addChild(int index, NetworkNode node) throws Exception {
        int count = (cursor.getChildren().length - cursor.getMaxChildren());
        if (index < 0 || index > count) {
            throw new Exception();
        }
        if (cursor.getChildren()[index] == null) {
            cursor.setChildren(node, index);
            node.setParent(cursor);
            cursor.setMaxChildren(cursor.getMaxChildren() - 1);

        } else {
            for (int i = cursor.getChildren().length - 1; i > index; i--) {
                cursor.setChildren(cursor.getChildren()[i - 1], i);
            }
            cursor.setChildren(node, index);
            node.setParent(cursor);
            cursor.setMaxChildren(cursor.getMaxChildren() - 1);
        }
    }

    /**
     * Moves the cursor to the child node of the of the cursor corresponding to the
     * specified index.
     * 
     * @param index
     *              The given index
     * @throws Exception
     *                   When there is no children at the given index
     * 
     */
    public void cursorToChild(int index) throws Exception {
        if (cursor.getChildren()[index] != null)
            this.cursor = cursor.getChildren()[index];
        else
            throw new Exception("there is no child at this index");
    }

    /**
     * This method moves the cursor to the parent of the current node.
     */
    public void cursorToParent() {
        this.cursor = cursor.getParent();
    }

    /**
     * This recurrsion method prints the network tree
     * 
     * @param root
     *              The root of the tree
     * @param space
     *              The space given for each node to dertermine each tree level.
     */
    public void print(NetworkNode root, int space) {
        NetworkNode current = root;
        if (current.getChildren() != null) {
            String format = "";
            for (int i = 0; i < space; i++) {
                System.out.print("   ");
            }
            space += 1;
            if (current == root) {

                if (current.getIsNintendo() == false) {
                    format = "+";
                } else {
                    format = "-";
                }
                if (current == cursor)
                    format = "->";
                if (current.getIsBroken()) {
                    System.out.printf("%s%s ~Fault~\n", format, current);
                } else {
                    System.out.printf("%s%s\n", format, current);
                }
                for (int i = 0; i < current.getChildren().length; i++) {
                    if (current.getChildren()[i] != null) {
                        print(current.getChildren()[i], space);
                    }
                }
            }
        } else {
            return;
        }
    }

    /**
     * This method reads the file and generates the NetworkTree based on the file
     * name that is passed in.
     * 
     * @param filename
     *                 The given file name
     * @return
     *         The network tree it generated.
     */
    public static NetworkTree readFromFile(String filename) {
        try {
            Scanner inp = new Scanner(System.in);
            File theFile = new File(filename);
            inp = new Scanner(theFile);
            while (inp.hasNextLine()) {

                String line = inp.nextLine();
                if (line.matches("([A-Za-z ])+")) {
                    NetworkNode rootNode = new NetworkNode(line, false);
                    NintendoNetwork.tree.setRoot(rootNode);
                    NintendoNetwork.tree.setCursor(NintendoNetwork.tree.getRoot());
                } else {
                    boolean isFound = false;
                    int intNum = 0;
                    while (isFound == false) {
                        String reg = "";
                        reg = String.format("([0-9]){%d}-([A-Za-z ])*", intNum + 1);
                        if (line.matches(reg)) {
                            if (NintendoNetwork.tree.cursor.getName()
                                    .matches(String.format("([0-9]){%d}-([A-Za-z ])*", intNum + 1))
                                    || NintendoNetwork.tree.cursor.getName()
                                            .matches(String.format("([0-9]){%d}([A-Za-z ])*", intNum + 1))) {
                                NintendoNetwork.tree.cursorToParent();
                            } else {
                                if ((NintendoNetwork.tree.cursor.getName()
                                        .matches(String.format("([0-9]){%d}-([A-Za-z ])*", intNum)))
                                        ^ (NintendoNetwork.tree.cursor.getName()
                                                .matches(String.format("([0-9]){%d}([A-Za-z ])*", intNum)))) {
                                } else
                                    NintendoNetwork.tree.cursorToRoot();
                            }
                            NetworkNode node = new NetworkNode(line, true);
                            NintendoNetwork.tree.addChild(line.charAt(intNum) - '1', node);
                            NintendoNetwork.tree.cursorToChild(line.charAt(intNum) - '1');
                            isFound = true;
                            break;
                        }
                        reg = String.format("([0-9]){%d}([A-Za-z ])*", intNum + 1);
                        if (line.matches(reg)) {
                            if (NintendoNetwork.tree.cursor.getName()
                                    .matches(String.format("([0-9]){%d}([A-Za-z ])*", intNum + 1))) {
                                NintendoNetwork.tree.cursorToParent();
                            } else {
                                if ((NintendoNetwork.tree.cursor.getName()
                                        .matches(String.format("([0-9]){%d}-([A-Za-z ])*", intNum)))
                                        ^ (NintendoNetwork.tree.cursor.getName()
                                                .matches(String.format("([0-9]){%d}([A-Za-z ])*", intNum)))) {
                                } else {
                                    NintendoNetwork.tree.cursorToRoot();
                                    int tmp = 0;
                                    while (!NintendoNetwork.tree.cursor.getName()
                                            .matches(String.format("([0-9]){%d}[-]?([A-Za-z ])*", intNum))) {
                                        NintendoNetwork.tree.cursorToChild(line.charAt(tmp) - '1');
                                        tmp++;
                                    }
                                }
                            }

                            NetworkNode node = new NetworkNode(line, false);
                            NintendoNetwork.tree.addChild(line.charAt(intNum) - '1', node);
                            NintendoNetwork.tree.cursorToChild(line.charAt(intNum) - '1');
                            isFound = true;
                            break;
                        }
                        intNum++;
                    }
                }
            }
            NintendoNetwork.tree.cursorToRoot();
            System.out.println(filename + " loaded");
            inp.close();
        } catch (FileNotFoundException e) {
            System.out.printf("%s not found.\n", filename);
        } catch (Exception e) {
            System.out.println(e);
        }

        return NintendoNetwork.tree;
    }

    /**
     * This method recurrsion help WriteToFile print a print into the given file
     * 
     * @param root
     *              The root of the tree
     * @param file
     *              The given file
     * @param index
     *              The index of each node.
     */
    public static void printTreeToFile(NetworkNode root, PrintWriter file, String index) {
        NetworkNode current = root;
        if (current.getChildren() != null) {
            if (current == NintendoNetwork.tree.root) {
                file.printf("%s\n", current);
            } else {
                if (current.getIsNintendo())
                    file.printf("%s\n", index + "-" + current);
                else
                    file.printf("%s\n", index + current);
            }
            for (int i = 0; i < current.getChildren().length; i++) {
                if (current.getChildren()[i] != null) {
                    printTreeToFile(current.getChildren()[i], file, index + "" + (i + 1));
                }
            }
        } else {
            return;
        }

    }

    /**
     * This method generates a text file that reflects the structure of the
     * NetworkTree.
     * 
     * @param tree
     *                 The network tree
     * @param filename
     *                 The given file name
     */
    public static void writeToFile(NetworkTree tree, String filename) {
        File Fileright = new File(filename);

        try {
            PrintWriter file = new PrintWriter(Fileright);
            printTreeToFile(tree.root, file, "");
            file.close();

        } catch (FileNotFoundException e) {
            System.out.println(filename + " is invalid");
        }
    }

    /**
     * This method relocates the cursor to where all of the broken nodes are either
     * the cursor or descendants.
     */
    public void cursorToMinimalBrokenSubtree() {
        int brokenDevices = 0;
        boolean stop = false;
        NetworkNode current = root;
        brokenSubtreeHelper(brokenDevices, current, stop);
    }

    /**
     * This method help cursorToMinimalBrokenSubtree()
     * 
     * @param brokenDevices
     *                      The amount of broken device
     * @param current
     *                      The cursor
     * @param stop
     *                      The boolean say when to stop
     */
    public void brokenSubtreeHelper(int brokenDevices, NetworkNode current, boolean stop) {
        if (stop) {
            return;
        } else {
            for (int i = 0; i < current.getChildren().length; i++) {
                if (current.getChildren()[i] != null) {
                    if (current.getChildren()[i].getIsBroken()) {
                        brokenDevices += 1;
                    }

                }
                if (brokenDevices > 0) {
                    stop = true;
                }
                brokenSubtreeHelper(0, current.getChildren()[i], stop);
            }

        }
    }
}
