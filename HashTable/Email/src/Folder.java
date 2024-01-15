
//Name: Phi Long Bui ID: 114555975 R-30
import java.util.ArrayList;
import java.util.Collections;
import java.io.Serializable;

/**
 * This class represents an email folder and will handle all of the logic for
 * adding and removing emails
 * 
 * @author Phi Long Bui
 */
public class Folder implements Serializable {
    private ArrayList<Email> emails;
    private String name;
    private String currentSortingMethod;

    public Folder(String name) {
        this.name = name;
        this.emails = new ArrayList<Email>();
        this.currentSortingMethod = "DD";
    }

    /**
     * This method gets the name of the folder
     * 
     * @return
     *         The name of the folder
     */
    public String getName() {
        return name;
    }

    /**
     * This method gets the current sorting method of the folder
     * 
     * @return
     *         The current sorting method of the folder
     */
    public String getCurrentSortingMethod() {
        return currentSortingMethod;
    }

    /**
     * This method gets the emails of the folder
     * 
     * @return
     *         The emails of the folder
     */
    public ArrayList<Email> getEmails() {
        return emails;
    }

    /**
     * This method sets the current name to the given name
     * 
     * @param name
     *             The given name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method sets the current sorting method to the given one
     * 
     * @param currentSortingMethod
     *                             The given sorting method
     */
    public void setCurrentSortingMethod(String currentSortingMethod) {
        this.currentSortingMethod = currentSortingMethod;
    }

    /**
     * This method sets the emails to a given emails
     * 
     * @param emails
     *               The given emails
     */
    public void setEmails(ArrayList<Email> emails) {
        this.emails = emails;
    }

    /**
     * This method add a new email to the folder
     * 
     * @param email
     *              The given email
     */
    public void addEmail(Email email) {
        emails.add(email);
    }

    /**
     * This method removes the email at its index
     * 
     * @param index
     *              The email's index
     * @return
     *         The removed Email
     * @throws Exception
     *                   If the folder is empty
     */
    public Email removeEmail(int index) throws Exception {
        if (emails.isEmpty()) {
            throw new Exception("There is no email to remove");
        }
        Email tmp = emails.remove(index);
        return tmp;
    }

    /**
     * This method sort the folder by Subject in ascending order
     */
    public void sortBySubjectAscending() {
        SubjectComparator comparator = new SubjectComparator();
        Collections.sort(emails, comparator);
    }

    /**
     * This method sorts the folder by Subject by Descending order
     */
    public void sortBySubjectDescending() {
        SubjectComparator comparator = new SubjectComparator();
        Collections.sort(emails, comparator.reversed());
    }

    /**
     * This method sort the folder by Date in ascending order
     */
    public void sortByDateAscending() {
        DateComparator comparator = new DateComparator();
        Collections.sort(emails, comparator);
    }

    /**
     * This method sorts the folder by Date by Descending order
     */
    public void sortByDateDescending() {
        DateComparator comparator = new DateComparator();
        Collections.sort(emails, comparator.reversed());
    }

    /**
     * This is the toString method
     * 
     * @return a String for the Folder following the other depends on the current
     *         sorting method
     */
    public String toString() {
        if (currentSortingMethod.equals("SA")) {
            sortBySubjectAscending();
        }
        if (currentSortingMethod.equals("SD")) {
            sortBySubjectDescending();
        }
        if (currentSortingMethod.equals("DA")) {
            sortByDateAscending();
        }
        if (currentSortingMethod.equals("DD")) {
            sortByDateDescending();
        }
        String str = "";
        str += String.format("\n%s\n", name);
        if (emails.isEmpty()) {
            str += String.format("\n%s is empty.\n", name);
        } else {
            str += String.format("\nIndex |        Time       | Subject\n");
            for (int i = emails.size() - 1; i >= 0; i--) {
                str += String.format("%-6s|%-19s|%-8s\n", emails.size() - i, emails.get(i).getTime(),
                        emails.get(i).getSubject());
            }
        }
        return str;
    }

}
