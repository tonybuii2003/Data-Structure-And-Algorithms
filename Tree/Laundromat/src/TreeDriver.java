
//Name: Phi Long Bui ID: 114555975 R-30
import java.util.Scanner;

/**
 * This class contain the main method and the menu that takes commands
 * 
 * Idea for the assignment, I did not use parent label while adding because
 * finding the parent everytime a node is added is not efficient. I create
 * another Node called parent and set it to null, when the cursor go forward,
 * the parent became the cursor and go through the child based on the label. It
 * works like previous in linkedlist. I also
 * create a separate method to read the file and use regex to clasify each line
 * 
 * @author Phi Long Bui
 */
public class TreeDriver {
    /**
     * This is the main method
     * 
     * @param args
     *             The command line arguments.
     */
    public static void main(String[] args) {
        boolean isQuited = false;
        Tree tree = new Tree();
        Scanner inp = new Scanner(System.in);
        while (!isQuited) {
            try {
                System.out.println("\nL - Load a Tree.\n" +
                        "H - Begin a Help Session.\n" +
                        "T - Traverse the Tree in preorder.\n" +
                        "Q - Quit\n");
                System.out.print("Choice> ");
                String input = inp.nextLine().toLowerCase();
                switch (input) {
                    case "l": {
                        try {
                            System.out.print("Enter the file name> ");
                            String filename = inp.nextLine();
                            tree.loadFile(filename);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }

                    }
                        break;
                    case "h": {
                        try {
                            if (tree.getRoot() == null) {
                                throw new Exception("There is no tree loaded");
                            }
                            tree.beginSession();
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                        break;
                    case "t": {
                        try {
                            if (tree.getRoot() == null) {
                                throw new Exception("There is no tree loaded");
                            }
                            tree.preOrder();
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                        break;
                    case "q": {
                        System.out.println("Thank you for using our automated help services!");
                        isQuited = true;
                    }
                        break;
                    default: {
                        System.out.println("Invalid input, please try again!");
                    }
                        break;
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        inp.close();
    }
}
