// Name: Phi Long Bui, ID: 114555975, R-01
/**
 * This class repersents a book which contains the title of the textbook,
 * the author of the textbook, the name of the borrower,
 * and the condition of the book.
 * 
 * @author Name: Phi Long Bui
 */
public class Book implements Cloneable {
    private String title, author, borrower;
    private int condition;

    /**
     * This Constructor used to create a new Book object
     * By default, the borrower is set to <none>
     * 
     * @param title
     *                  The tilte of the book
     * @param author
     *                  The author of the book
     * @param condition
     *                  The condition of the book
     */
    public Book(String title, String author, int condition) {
        this.title = title;
        this.author = author;
        this.borrower = "<none>";
        this.condition = condition;
    }

    /**
     * This method return the tilte of the book
     * 
     * @return
     *         The tilte of the book
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method sets this book title to a given title
     * 
     * @param title
     *              The given title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * This method return the author of the book
     * 
     * @return
     *         The author of the book
     */
    public String getAuthor() {
        return author;
    }

    /**
     * This method sets this book author to a given author
     * 
     * @param author
     *               The given author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * This method return the borrower of the book
     * 
     * @return
     *         The tilte of the book
     */
    public String getBorrowerName() {
        return borrower;
    }

    /**
     * This method sets this book borrower to a new borrower
     * 
     * @param borrower
     *                 The given borrower
     */
    public void setBorrowerName(String borrower) {
        this.borrower = borrower;
    }

    /**
     * This method return the condition of the book
     * 
     * @return
     *         The condition of the book
     */
    public int getCondition() {
        return condition;
    }

    /**
     * This method sets this book condition to a given condition
     * 
     * @param condition
     *                  The given condition
     */
    public void setCondition(int condition) {
        this.condition = condition;
    }

    /**
     * This method check if this book and the given book is equal
     * 
     * @param o
     *          The given book
     * @return
     *         false if the books is not equal, true if the book is equal
     */
    public boolean equals(Object o) {
        if (o instanceof Book) {
            return (((Book) o).author.equals(author))
                    && (((Book) o).title.equals(title))
                    && (((Book) o).condition == condition);
        }
        return false;
    }

    /**
     * This method clones this book
     * 
     * @return
     *         The clone of the book
     */
    public Object clone() {
        Book clone = null;
        try {
            clone = (Book) super.clone();
            clone.setBorrowerName("<none>");

        } catch (CloneNotSupportedException e) {
            System.out.println(e);
        }

        return clone;
    }

    /**
     * This method returns the book title, author, condition, and borrower in string
     * format
     * 
     * @return
     *         The string of book's title, author, condition, and borrower
     */
    public String toString() {
        String str = String.format("|%-44s|%-25s|%-10s|%-20s|%n", title, author, condition, borrower);
        str += "---------------------"
                + "---------------------------------------------"
                + "-------------------------------------------------\n";
        return str;
    }
}