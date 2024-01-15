
//Name: Phi Long Bui ID: 114555975 R-30
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.FileOutputStream;

/**
 * This class is for Email
 * 
 * @author Phi Long Bui
 */
public class Mailbox implements Serializable {
    static Scanner inp = new Scanner(System.in);
    private Folder inbox;
    private Folder trash;
    private HashMap<String, Folder> folders;
    public static Mailbox mailbox;

    /**
     * This is the Contructor for Mailbox
     */
    public Mailbox() {
        this.folders = new HashMap<String, Folder>();
        this.inbox = new Folder("Inbox");
        folders.put("Inbox", inbox);
        this.trash = new Folder("Trash");
        folders.put("Trash", trash);

    }

    /**
     * This method gets the folders of the mailbox
     * 
     * @return
     *         The folders of the mailbox
     */
    public HashMap<String, Folder> getFolders() {
        return folders;
    }

    /**
     * This method adds the folder to the mailbox
     * 
     * @param folder
     *               The given folder
     */
    public void addFolder(Folder folder) {
        folders.put(folder.getName(), folder);
    }

    /**
     * This method removes the folder from the mailbox by moved it to Trash
     * 
     * @param name
     *             The name of the folder
     * @throws Exception
     *                   if the mailbox is empty
     */
    public void deleteFolder(String name) throws Exception {
        if (folders.isEmpty()) {
            throw new Exception("\nThere is nothing to remove\n");
        }
        folders.remove(name);
        System.out.printf("\n%s is removed from mailbox\n", name);
    }

    /**
     * This method is for composing an email
     * 
     * @throws Exception
     *                   if the format is invalid
     */
    public void composeEmail() throws Exception {
        System.out.print("Enter recipient (To): ");
        String to = inp.nextLine();
        String[] tmp = to.split(",");
        for (String i : tmp) {
            i = i.trim();
            if (!i.matches("[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}")) {
                throw new Exception("Invalid format, please try again!");
            }
        }
        System.out.print("Enter carbon copy recipients (CC): ");
        String cc = inp.nextLine();
        if (!cc.matches("[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}")) {
            throw new Exception("Invalid format, please try again!");
        }
        System.out.print("Enter blind carbon copy recipients (BCC): ");
        String bcc = inp.nextLine();
        if (!bcc.matches("[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}")) {
            throw new Exception("Invalid format, please try again!");
        }
        System.out.print("Enter subject line: ");
        String subject = inp.nextLine();
        System.out.print("Enter body: ");
        String body = inp.nextLine();
        Email email = new Email(to, cc, bcc, subject, body);
        mailbox.getFolders().get("Inbox").addEmail(email);
        System.out.println("\nEmail successfully added to Inbox.");
    }

    /**
     * This method removes all emails from the trash folder.
     * 
     * @throws Exception
     *                   If the folder is empty
     */
    public void clearTrash() throws Exception {
        if (folders.get("Trash").getEmails().isEmpty()) {
            throw new Exception("The trash is empty.");
        } else {
            int x = folders.get("Trash").getEmails().size();
            folders.get("Trash").setEmails(new ArrayList<Email>());
            System.out.printf("%d item(s) successfully deleted.", x);
        }
    }

    /**
     * This method is for the submenu when access a folder
     * 
     * @param inp
     *                      The scanner
     * @param currentFolder
     *                      The current forder
     * @throws Exception
     *                   If the method throw an exception, empty list or invalid
     *                   format
     */
    public void innerLayer(Scanner inp, String currentFolder) throws Exception {

        boolean done = false;
        while (!done) {
            System.out.println(mailbox.getFolder(currentFolder));
            System.out.print("M – Move email\n" +

                    "D – Delete email\n" +

                    "V – View email contents\n" +

                    "SA – Sort by subject line in ascending order\n" +

                    "SD – Sort by subject line in descending order\n" +

                    "DA – Sort by date in ascending order\n" +

                    "DD – Sort by date in descending order\n" +

                    "R – Return to mailbox \n");
            System.out.print("\nEnter a user option: ");
            String input = inp.nextLine().toLowerCase();
            switch (input) {
                case "m": {
                    try {
                        if (mailbox.getFolder(currentFolder).getEmails().isEmpty()) {
                            throw new Exception("There is no email in this folder");
                        }
                        System.out.print("Enter the index of the email to move: ");
                        int index = inp.nextInt();
                        inp.nextLine();
                        index -= 1;
                        if (index < 0 || index > mailbox.getFolder(currentFolder).getEmails().size() - 1) {
                            throw new Exception("Invalid input, please try again.");
                        }
                        System.out.println("\nFolders:");
                        ArrayList<String> names = new ArrayList<String>();
                        for (String i : mailbox.getFolders().keySet()) {
                            names.add(i);
                        }
                        for (int i = names.size() - 1; i >= 0; i--)
                            System.out.printf("%s\n", names.get(i));
                        System.out.printf("\nSelect a folder to move “%s” to: ",
                                mailbox.getFolder(currentFolder).getEmails().get(index).getSubject());
                        String name = mailbox.getFolder(currentFolder).getEmails().get(index).getSubject();
                        String folder = inp.nextLine();
                        Email x = mailbox.getFolder(currentFolder).getEmails().get(index);
                        mailbox.moveEmail(x,
                                mailbox.getFolder(folder));
                        mailbox.removeEmail(currentFolder, x);
                        System.out.printf("“%s” successfully moved to %s.\n",
                                name, folder);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                }
                    break;
                case "sa": {
                    mailbox.getFolder(currentFolder).setCurrentSortingMethod("SA");
                    System.out.print("\nThe current sorting method is now Subject Ascending\n");
                }
                    break;
                case "sd": {
                    mailbox.getFolder(currentFolder).setCurrentSortingMethod("SD");
                    System.out.print("\nThe current sorting method is now Subject Descending\n");
                }
                    break;
                case "da": {
                    mailbox.getFolder(currentFolder).setCurrentSortingMethod("DA");
                    System.out.print("\nThe current sorting method is now Date Ascending\n");

                }
                    break;
                case "dd": {
                    mailbox.getFolder(currentFolder).setCurrentSortingMethod("DD");
                    System.out.print("\nThe current sorting method is now Date Descending\n");

                }
                case "d": {
                    try {
                        if (mailbox.getFolder(currentFolder).getEmails().isEmpty()) {
                            throw new Exception("There is no email in this folder");
                        }
                        System.out.print("Enter email index: ");
                        int index = inp.nextInt();
                        inp.nextLine();
                        index -= 1;
                        if (index < 0 || index > mailbox.getFolder(currentFolder).getEmails().size() - 1) {
                            throw new Exception("Invalid input, please try again.");
                        }
                        if (!currentFolder.equals("Trash")) {
                            String name = mailbox.getFolder(currentFolder).getEmails().get(index).getSubject();
                            Email x = mailbox.getFolder(currentFolder).getEmails().get(index);
                            mailbox.moveEmail(x,
                                    mailbox.getFolder("Trash"));
                            mailbox.removeEmail(currentFolder, x);
                            System.out.printf("\n“%s” has successfully been moved to the trash.\n", name);
                        } else {
                            String name = mailbox.getFolder(currentFolder).getEmails().get(index).getSubject();
                            Email x = mailbox.getFolder(currentFolder).getEmails().get(index);
                            mailbox.removeEmail(currentFolder, x);
                            System.out.printf("\n“%s” has successfully been removed from the trash.\n", name);
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                    break;
                case "v": {
                    try {
                        if (mailbox.getFolder(currentFolder).getEmails().isEmpty()) {
                            throw new Exception("There is no email in this folder");
                        }
                        System.out.print("Enter email index: ");
                        int index = inp.nextInt();
                        inp.nextLine();
                        index -= 1;
                        if (index < 0 || index > mailbox.getFolder(currentFolder).getEmails().size() - 1) {
                            throw new Exception("Invalid input, please try again.");
                        }
                        System.out.print("\n" + mailbox.getFolder(currentFolder).getEmails().get(index));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                    break;
                case "r": {
                    done = true;
                }
                    break;

                default: {
                    System.out.println("Invalid please try again!");
                }
            }
        }

    }

    /**
     * This method moves the given email to the target folder
     * 
     * @param email
     *               The given email
     * @param target
     *               The target folder
     */
    public void moveEmail(Email email, Folder target) {
        target.getEmails().add(email);
    }

    /**
     * This method remove the mail from the current folder
     * 
     * @param currentFolder
     *                      The current folder
     * @param email
     *                      The given email
     * @throws Exception
     */
    public void removeEmail(String currentFolder, Email email) throws Exception {
        for (int i = 0; i < mailbox.getFolder(currentFolder).getEmails().size(); i++) {
            if (mailbox.getFolder(currentFolder).getEmails().get(i).getSubject().equals(email.getSubject())) {
                mailbox.getFolder(currentFolder).removeEmail(i);
            }
        }
    }

    /**
     * This method gets the folder from the mailbox
     * 
     * @param name
     *             The name of the folder
     * @return
     *         The folder from the mailbox
     * @throws Exception
     *                   If the mailbox is empty
     */
    public Folder getFolder(String name) throws Exception {
        if (folders.isEmpty()) {
            throw new Exception("This mailbox is empty");
        }
        return folders.get(name);
    }

    /**
     * This is the main method
     * 
     * @param args
     *             The command line arguments.
     */
    public static void main(String[] args) {
        String filename = "mailbox.obj";
        try {
            FileInputStream inputFile = new FileInputStream(filename);
            ObjectInputStream inStream = new ObjectInputStream(inputFile);
            mailbox = (Mailbox) inStream.readObject();
            System.out.printf("%s was found and loaded.\n", filename);
            inStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("\nPrevious save not found, starting with an empty mailbox.");
            mailbox = new Mailbox();
        } catch (Exception e) {
            System.out.println("\nInvalid, please try again.");
        }

        boolean isQuited = false;

        while (!isQuited) {
            try {
                System.out.print("\nMailbox:\n--------\n");
                ArrayList<String> names = new ArrayList<String>();
                for (String i : mailbox.getFolders().keySet()) {
                    names.add(i);
                }
                for (int i = names.size() - 1; i >= 0; i--)
                    System.out.printf("%s\n", names.get(i));
                System.out.println("\nA – Add folder\n" +
                        "R – Remove folder\n" +

                        "C – Compose email\n" +

                        "F – Open folder\n" +

                        "I – Open Inbox\n" +

                        "T – Open Trash\n" +
                        "E - Empty Trash\n" +

                        "Q – Quit\n");
                System.out.print("Enter a user option: ");
                String input = inp.nextLine().toLowerCase();
                switch (input) {
                    case "a": {
                        System.out.print("\nEnter folder name: ");
                        String name = inp.nextLine();
                        Folder folder = new Folder(name);
                        mailbox.addFolder(folder);
                    }
                        break;
                    case "c": {
                        try {
                            mailbox.composeEmail();
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }

                    }
                        break;
                    case "r": {
                        try {
                            System.out.print("Enter folder's name: ");
                            String name = inp.nextLine();
                            mailbox.deleteFolder(name);
                            System.out.printf("\nFolder %s is removed\n", name);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    }
                    case "e": {
                        try {
                            mailbox.clearTrash();
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                        break;
                    case "i": {
                        try {
                            mailbox.innerLayer(inp, "Inbox");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                        break;
                    case "f": {
                        try {
                            System.out.print("Enter folder name: ");
                            String name = inp.nextLine();
                            if (mailbox.getFolder(name) != null)
                                mailbox.innerLayer(inp, name);
                            else
                                throw new Exception("Item not found.");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                        break;
                    case "t": {
                        try {
                            if (mailbox.getFolder("Trash") != null)
                                mailbox.innerLayer(inp, "Trash");
                            else
                                throw new Exception("Item not found.");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                        break;
                    case "q": {
                        System.out.println("\nProgram successfully exited and mailbox saved.");
                        FileOutputStream file = new FileOutputStream(filename);
                        ObjectOutputStream outStream = new ObjectOutputStream(file);
                        outStream.writeObject(mailbox);
                        outStream.close();
                        isQuited = true;
                    }
                        break;
                    default: {
                        System.out.println("Invalid please try again!");
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        inp.close();
    }
}
