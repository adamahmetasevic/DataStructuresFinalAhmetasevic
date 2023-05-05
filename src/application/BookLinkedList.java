package application;
import java.util.Hashtable;
import java.util.LinkedList;

/**
 * The BookLinkedList class represents a collection of Book objects stored in a linked list
 * data structure. It provides methods to add, delete, and search for books, as well as to
 * retrieve all books and sort them by ISBN using the bubble sort algorithm.
 */
public class BookLinkedList {

    /**
     * A linked list of books.
     */
    private LinkedList<Book> books;

    /**
     * A hash table that maps ISBNs to books for fast searching.
     */
    private Hashtable<String, Book> table;

    /**
     * Constructs an empty BookLinkedList.
     */
    public BookLinkedList() {
        this.books = new LinkedList<>();
        this.table = new Hashtable<>();
    }

    /**
     * Adds a book to the collection with the specified title, author, ISBN, and year.
     *
     * @param title the title of the book
     * @param author the author of the book
     * @param isbn the ISBN of the book
     * @param year the year the book was published
     */
    public void addBook(String title, String author, String isbn, String year) {
        Book book = new Book(title, author, isbn, year);
        books.add(book);
        table.put(isbn, book);
    }

    /**
     * Deletes the book with the specified ISBN from the collection, if it exists.
     *
     * @param isbn the ISBN of the book to delete
     */
    public void deleteBook(String isbn) {
        Book book = table.get(isbn);
        if (book != null) {
            books.remove(book);
            table.remove(isbn);
        }
    }

    /**
     * Searches for the book with the specified ISBN in the collection and returns it, if it exists.
     *
     * @param isbn the ISBN of the book to search for
     * @return the Book object with the specified ISBN, or null if it doesn't exist
     */
    public Book searchBook(String isbn) {
        return table.get(isbn);
    }

    /**
     * Returns a linked list containing all books in the collection.
     *
     * @return a linked list containing all books in the collection
     */
    public LinkedList<Book> getAllBooks() {
        return books;
    }

    /**
     * Returns the number of books in the collection.
     *
     * @return the number of books in the collection
     */
    public int getSize() {
        return books.size();
    }
    
    /**
     * Sorts the books in the collection by ISBN using the bubble sort algorithm.
     */
    public void bubbleSort() {
        int n = books.size();
        Book temp;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (books.get(j).getIsbn().compareTo(books.get(j + 1).getIsbn()) > 0) {
                    // swap books[j] and books[j+1]
                    temp = books.get(j);
                    books.set(j, books.get(j + 1));
                    books.set(j + 1, temp);
                }
            }
        }
    }
}
