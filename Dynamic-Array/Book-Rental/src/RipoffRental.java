
// Name: Phi Long Bui, ID: 114555975, R-01
import java.util.Scanner;

/**
 * This is the main class contains 3 static BookShelf and a main method.
 * 
 * @author Name: Phi Long Bui
 */
public class RipoffRental {
    private static Bookshelf shelfA = new Bookshelf();
    private static Bookshelf shelfB = new Bookshelf();
    private static Bookshelf shelfC = new Bookshelf();

    /**
     * This is the main method which will print the UI
     * The UI include the menu contains all commands, each command function
     * differently
     * 
     * @param args The command line arguments.
     */
    public static void main(String[] args) {

        boolean isEnded = false;
        System.out.println("Welcome to Tony's Alibaba' Textbook Rentals, highest price guaranteed!");
        System.out.println("Menu:\n" +
                "     A) Add Book\n" +
                "     S) Swap Books\n" +
                "     L) Loan Book\n" +
                "     R) Remove Book\n" +
                "     D) Duplicate Book\n" +
                "     C) Change Shelf\n" +
                "     O) Overwrite shelf with clone of current shelf\n" +
                "     E) Check if two shelves are equal\n" +
                "     P) Print current bookshelf\n" +
                "     Q) Quit\n");
        Bookshelf currentBookShelf = shelfA;
        String curr = "A";
        Scanner inp = new Scanner(System.in);
        while (isEnded == false) {
            System.out.print("Please select an option:");
            String userInput = inp.nextLine();
            userInput = userInput.toLowerCase();
            try {
                switch (userInput) {
                    case "a": {
                        System.out.print("Please enter a title: ");
                        String title = inp.nextLine();
                        System.out.print("Please enter an author: ");
                        String author = inp.nextLine();
                        System.out.print("Please enter condition(1-5): ");
                        int condition = inp.nextInt();
                        inp.nextLine();
                        if (condition > 5)
                            throw new Exception();
                        System.out.print("Please enter position on shelf: ");
                        int pos = inp.nextInt();
                        inp.nextLine();

                        Book b = new Book(title, author, condition);
                        try {
                            currentBookShelf.addBook(pos - 1, b);
                            System.out.println("Book added!");
                        } catch (FullShelfException e) {
                            System.out.println(e);
                        } catch (IllegalArgumentException e) {
                            System.out.println(e);
                        }
                    }
                        break;
                    case "l": {
                        System.out.print("Please enter the position: ");
                        int index = inp.nextInt();
                        inp.nextLine();
                        System.out.print("Please enter the recipient: ");
                        String recipient = inp.nextLine();
                        System.out.print("Please enter the condition(1-5): ");
                        int condition = inp.nextInt();
                        inp.nextLine();
                        if (condition > 5)
                            throw new Exception();
                        currentBookShelf.getBook(index - 1).setBorrowerName(recipient);
                        currentBookShelf.getBook(index - 1).setCondition(condition);
                        System.out.println(currentBookShelf.getBook(index - 1).getTitle()
                                + " has been loaned to " + recipient);
                    }
                        break;
                    case "c": {
                        System.out.print("Please select shelf to look at: ");
                        String a = inp.nextLine().toLowerCase();
                        if (a.equals("a")) {
                            currentBookShelf = shelfA;
                            curr = "A";
                            System.out.println("Shelf " + curr + " selected");
                        } else if (a.equals("b")) {
                            currentBookShelf = shelfB;
                            curr = "B";
                            System.out.println("Shelf " + curr + " selected");
                        } else if (a.equals("c")) {
                            currentBookShelf = shelfC;
                            curr = "C";
                            System.out.println("Shelf " + curr + " selected");
                        } else {
                            throw new Exception();
                        }

                    }
                        break;
                    case "d": {
                        System.out.print("Position to copy: ");
                        int posCopy = inp.nextInt();
                        inp.nextLine();
                        System.out.print("Position to insert: ");
                        int posInsert = inp.nextInt();
                        inp.nextLine();
                        Book tmp = (Book) currentBookShelf.getBook(posCopy - 1).clone();
                        try {
                            currentBookShelf.addBook(posInsert - 1, tmp);
                            System.out.println("A new copy of " + tmp.getTitle()
                                    + " is in index " + posInsert);
                        } catch (FullShelfException e) {
                            System.out.println(e);
                        } catch (IllegalArgumentException e) {
                            System.out.println(e);
                        }

                    }
                        break;
                    case "e": {
                        Bookshelf[] bookShelfs = { shelfA, shelfB, shelfC };
                        System.out.print("Please enter the first bookshelf: ");
                        String userInput1 = inp.nextLine().toLowerCase();
                        Bookshelf b1 = bookShelfs[userInput1.charAt(0) - 'a'];
                        System.out.print("Please enter the second bookshelf: ");
                        String userInput2 = inp.nextLine().toLowerCase();
                        Bookshelf b2 = bookShelfs[userInput2.charAt(0) - 'a'];
                        if (b1.equals(b2))
                            System.out.println("These shelves are equal");
                        else {
                            System.out.println("These shelves are not equal");
                        }

                    }
                        break;
                    case "o": {
                        System.out.print("Please select shelf to overwrite with: ");
                        String a = inp.nextLine().toLowerCase();
                        try {
                            if (a.equals("a")) {
                                shelfA = (Bookshelf) currentBookShelf.clone();
                                System.out.println("Shelf A" + " overwritten with a copy of"
                                        + " Shelf " + curr);
                            } else if (a.equals("b")) {
                                shelfB = (Bookshelf) currentBookShelf.clone();
                                System.out.println("Shelf B" + " overwritten with a copy of"
                                        + " Shelf " + curr);
                            } else if (a.equals("c")) {
                                shelfC = (Bookshelf) currentBookShelf.clone();
                                System.out.println("Shelf C" + " overwritten with a copy of"
                                        + " Shelf " + curr);
                            } else {
                                throw new Exception();
                            }
                        } catch (CloneNotSupportedException e) {
                            System.out.println(e);
                        }
                    }
                        break;
                    case "s": {
                        System.out.print("Please enter an index: ");
                        int index1 = inp.nextInt();
                        inp.nextLine();
                        System.out.print("Please enter another index: ");
                        int index2 = inp.nextInt();
                        inp.nextLine();
                        currentBookShelf.swapBooks(index1 - 1, index2 - 1);
                        System.out.println(currentBookShelf.getBook(index2 - 1).getTitle() + " has swapped with "
                                + currentBookShelf.getBook(index1 - 1).getTitle());

                    }
                        break;
                    case "r": {
                        System.out.print("Please enter position: ");
                        int index = inp.nextInt();
                        inp.nextLine();
                        try {
                            currentBookShelf.removeBook(index - 1);
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println(e);
                        } catch (EmptyShelfException e) {
                            System.out.println(e);
                        }

                    }
                        break;
                    case "p": {
                        System.out.println("Bookshelf " + curr + ":");
                        System.out.println(currentBookShelf);
                    }
                        break;
                    case "q": {
                        System.out.println("Goodbye!");
                        isEnded = true;
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
