
// Name: Phi Long Bui, ID: 114555975, R-01
import java.util.Scanner;

/**
 * This is the main class contains a static NetworkTree and a main method.
 * 
 * @author Name: Phi Long Bui
 */
public class NintendoNetwork {
    public static NetworkTree tree = new NetworkTree();

    /**
     * This is the main method which will print the UI
     * The UI include the menu contains all commands, each command function
     * differently
     * 
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);
        boolean isQuited = false;
        NetworkNode tmp = new NetworkNode(null, false);
        System.out.println("Welcome to the Nintendo Network Manager.\n");
        System.out.println("Menu:\n"
                + "L) Load from file\n"
                + "P) Print tree\n"
                + "C) Move cursor to a child node\n"
                + "R) Move cursor to root\n"
                + "U) Move cursor up to parent\n"
                + "A) Add a child\n"
                + "X) Remove/Cut Cursor and its subtree\n"
                + "V) Paste Cursor and its subtree\n"
                + "S) Save to file\n"
                + "M) Cursor to root of minimal subtree containing all faults\n"
                + "B) Mark cursor as broken/fixed\n"
                + "Q) Quit");

        while (isQuited == false) {
            try {
                System.out.printf("\nPlease select an option: ");
                String input = inp.nextLine().toLowerCase();
                switch (input) {
                    case "l": {
                        System.out.printf("Please enter filename: ");
                        String fileName = inp.nextLine();
                        tree = NetworkTree.readFromFile(fileName);
                    }
                        break;
                    case "p": {
                        try {
                            if (tree.getRoot() != null)
                                tree.print(tree.getRoot(), 0);
                            else
                                throw new Exception();
                        } catch (Exception e) {
                            System.out.println("There is no root to print.");
                        }
                    }
                        break;
                    case "c": {
                        System.out.print("Please enter an index: ");
                        int index = inp.nextInt();
                        inp.nextLine();
                        if ((index - 1) < 0 || (index - 1) > tree.getRoot().getMaxChildren()) {
                            throw new Exception();
                        }
                        if (tree.getCursor().getChildren()[index - 1] == null) {
                            System.out.println("Can not go further");
                        }
                        tree.cursorToChild(index - 1);
                        System.out.printf("Cursor moved to %s.\n", tree.getCursor());

                    }
                        break;
                    case "r": {
                        if (tree.getRoot() == null) {
                            System.out.println("There is no root.");
                        } else {
                            tree.cursorToRoot();
                            System.out.printf("Cursor moved to %s.\n", tree.getCursor());
                        }

                    }
                        break;
                    case "u": {
                        if (tree.getCursor() == tree.getRoot()) {
                            System.out.println("Invalid, please try again.");
                        } else {
                            tree.cursorToParent();
                            System.out.printf("Cursor moved to %s.\n", tree.getCursor());
                        }
                    }
                        break;
                    case "a": {
                        System.out.print("Please enter an index: ");
                        int index = inp.nextInt();
                        inp.nextLine();
                        System.out.print("Please enter device name: ");
                        String name = inp.nextLine();
                        System.out.print("Is this Nintendo (y/n): ");
                        String answer = inp.nextLine().toLowerCase();
                        try {
                            switch (answer) {
                                case "y": {
                                    NetworkNode newNode = new NetworkNode(name, true);
                                    tree.addChild(index - 1, newNode);
                                    tree.cursorToChild(index - 1);
                                    System.out.println("Nintendo added.");
                                }
                                    break;
                                case "n": {
                                    NetworkNode newNode = new NetworkNode(name, false);
                                    tree.addChild(index - 1, newNode);
                                    tree.cursorToChild(index - 1);
                                    System.out.println("Device Added.");
                                }
                                    break;
                            }
                        } catch (Exception e) {
                            System.out.println("Invalid, please try again");
                        }
                    }
                        break;
                    case "x": {
                        tmp = tree.cutCursor();
                        System.out.printf("%s cut, cursor is at %s.\n", tmp, tree.getCursor());
                    }
                        break;
                    case "v": {
                        System.out.print("Please enter an index: ");
                        int index = inp.nextInt();
                        inp.nextLine();
                        if (tmp.getName() == null) {
                            System.out.println("Nothing to paste.");
                        } else {
                            tree.addChild(index - 1, tmp);
                            tree.cursorToChild(index - 1);
                            System.out.printf("%s pasted as child of %s.\n", tmp, tree.getCursor().getParent());
                        }
                    }
                        break;
                    case "s": {
                        try {
                            System.out.print("Please enter a filename: ");
                            String filename = inp.nextLine();
                            if (tree.getRoot() != null) {
                                NetworkTree.writeToFile(tree, filename);
                                System.out.println("File saved");
                            } else {
                                throw new Exception();
                            }
                        } catch (Exception e) {
                            System.out.println("There is no root to write");
                        }
                    }
                        break;
                    case "m": {
                        tree.cursorToMinimalBrokenSubtree();
                        System.out.printf("Cursor moved to %s.\n", tree.getCursor());
                    }
                        break;
                    case "b": {
                        NetworkNode tmpNode = tree.getCursor();
                        tree.getCursor().setIsBroken(true);
                        System.out.printf("%s marked as broken.", tmpNode);
                    }
                        break;
                    case "q": {
                        System.out.println("Make like a tree and leave!");
                        isQuited = true;
                    }
                        break;
                    default: {
                        System.out.println("Invalid, please try again");
                    }
                }
            } catch (Exception e) {
                System.out.println("Invalid, please try again");
            }
        }
        inp.close();

    }
}
