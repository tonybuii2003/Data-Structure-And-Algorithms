// Name: Phi Long Bui, ID: 114555975, R-01
/**
 * This class represents a bookshelf which contains a list of all the books on a
 * shelf
 * One book shelf has a maximum capacity
 * 
 * @author Phi Long Bui
 */
public class Bookshelf implements Cloneable {
    static final int CAPACITY = 20;
    private Book[] books;
    private int count;

    /**
     * This is a Constructor used to create a new Booksehlf object
     * The Constructor contains an ArrayList-like data structure with maximum size
     * limited by the final int CAPACITY
     * The list of book may not contain "holes" in it
     */
    public Bookshelf() {
        this.books = new Book[CAPACITY];
        count = 0;
    }

    /**
     * This method returns the total number of books on the shelf
     * 
     * @return
     *         the total number of books on the shelf
     */
    public int numBooks() {
        return count;
    }

    /**
     * This method gets the reference to the Book at the given index
     * 
     * @param index
     *              the given index
     * @return
     *         the reference to the Book at the given index
     */
    public Book getBook(int index) {
        Book res = null;
        try {
            res = books[index];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Sorry, the index you give is invalid");
        }
        return res;
    }

    /**
     * This method removes the given book and moves all books to the right of it
     * leftwards by one index
     * 
     * @param index
     *              The given index
     * @return
     *         the reference to the Book at the given index
     * @throws EmptyShelfException
     *                                   when the shelf at the index is empty
     * @throws IndexOutOfBoundsException
     *                                   when the index is bigger than CAPACITY - 1
     */
    public Book removeBook(int index) throws EmptyShelfException, IndexOutOfBoundsException {
        Book res = null;
        if (index > CAPACITY - 1) {
            throw new IndexOutOfBoundsException("Invalid index");
        }

        if (index >= numBooks()) {
            throw new EmptyShelfException();
        }
        res = books[index];
        count--;
        books[index] = null;
        Book tmp = books[index];
        for (int i = index + 1; i < books.length - 1; i++) {
            books[i - 1] = books[i];
        }
        books[books.length - 1] = tmp;
        return res;
    }

    /**
     * This method adds a book at the given index,
     * moving all books at or after the given index one index to the right
     * 
     * @param index
     *              The given index
     * @param book
     *              The book need to be added
     * @throws FullShelfException
     *                                  when the total amount of book in the shelf
     *                                  is equal to the bookshelf capacity
     * @throws IllegalArgumentException
     *                                  when the index is too high and would create
     *                                  a hole in the array
     */
    public void addBook(int index, Book book) throws FullShelfException, IllegalArgumentException {
        if (index > numBooks() || index < 0) {
            throw new IllegalArgumentException("Invalid index");
        }
        if (numBooks() == CAPACITY) {
            throw new FullShelfException();
        }

        Book tmp = books[books.length - 1];
        for (int i = books.length - 1; i > index; i--) {
            books[i] = books[i - 1];
        }
        books[index] = tmp;
        books[index] = book;
        count++;
    }

    /**
     * This method swaps 2 given books
     * 
     * @param index1
     *               The first given book
     * @param index2
     *               The second given book
     * @throws ArrayIndexOutOfBoundsException
     *                                        when the either the index is invalid
     */
    public void swapBooks(int index1, int index2) throws ArrayIndexOutOfBoundsException {
        try {
            Book tmp = books[index1];
            books[index1] = books[index2];
            books[index2] = tmp;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e);
        }
    }

    /**
     * This method deep clones this bookshelf
     * 
     * @throws CloneNotSupportedException
     *                                    whent the clone is not supported
     */
    public Object clone() throws CloneNotSupportedException {
        Bookshelf clone = new Bookshelf();
        for (int i = 0; i < numBooks(); i++) {
            try {
                clone.addBook(i, (Book) books[i].clone());
            } catch (IllegalArgumentException e) {
                System.out.println(e);
            } catch (FullShelfException e) {
                System.out.println(e);
            }
        }
        return clone;
    }

    /**
     * This method check if this bookshelf and the given bookshelf is equal
     * 
     * @param o
     *          The given bookshelf
     * @return
     *         false if the bookshelves is not equal, true if the book is equal
     */
    public boolean equals(Object o) {
        if (o instanceof Bookshelf) {
            if (!(numBooks() == ((Bookshelf) o).numBooks()))
                return false;
            for (int i = 0; i < numBooks(); i++) {
                if (!(books[i].equals(((Bookshelf) o).books[i])))
                    return false;

            }
        }
        return true;
    }

    /**
     * This method returns the list of all books of the bookshelf in string format
     * 
     * @return
     *         The the list of all books of the bookshelf
     */
    public String toString() {
        String res = "";
        res += "-----------------------------------"
                + "----------------------------------------"
                + "----------------------------------------\n";
        res += String.format("|%-10s|%-44s|%-25s|%-10s|%-20s|%n",
                "Spot", "Title", "Author",
                "Condition", "Borrower");
        res += "-----------------------------------"
                + "----------------------------------------"
                + "----------------------------------------\n";
        int pos = 1;

        for (int i = 0; i < numBooks(); i++) {
            String position = String.format("|%-10s", ("" + pos + "."));
            res += "" + position + books[i];
            pos++;
        }
        return res;
    }
}
