package application;

import java.util.LinkedList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    private TableView<Book> table;
    private TextField titleInput, authorInput, isbnInput, yearInput, searchInput;
    private final BookLinkedList bookTable = new BookLinkedList(); 
    /**
     * Launches the JavaFX application.
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
    	Book book1 = new Book("To Kill a Mockingbird", "Harper Lee", "9780446310789", "1960");
    	Book book2 = new Book("1984", "George Orwell", "9780451524935", "1949");
    	bookTable.addBook(book1.getTitle(), book1.getAuthor(), book1.getIsbn(), book1.getYear());
    	bookTable.addBook(book2.getTitle(), book2.getAuthor(), book2.getIsbn(), book2.getYear());
        primaryStage.setTitle("Library Management System");

        // Create form inputs
        Label titleLabel = new Label("Title:");
        titleInput = new TextField();
        HBox titleBox = new HBox(10, titleLabel, titleInput);
        Label authorLabel = new Label("Author:");
        authorInput = new TextField();
        
        HBox authorBox = new HBox(10, authorLabel, authorInput);
        Label isbnLabel = new Label("ISBN:");
        isbnInput = new TextField();
        HBox isbnBox = new HBox(10, isbnLabel, isbnInput);

        Label yearLabel = new Label("Year:");
        yearInput = new TextField();
        HBox yearBox = new HBox(10, yearLabel, yearInput);

        Button addButton = new Button("Add Book");
        addButton.setOnAction(e -> addBook(titleInput.getText(), authorInput.getText(), isbnInput.getText(), yearInput.getText()));

        Button removeButton = new Button("Remove Book");
        removeButton.setOnAction(e -> removeBook());
        
        Button bubbleSortButton = new Button("Bubble Sort");
        bubbleSortButton.setOnAction(e -> {
        	bookTable.bubbleSort();
            LinkedList<Book> temp = bookTable.getAllBooks();
            ObservableList<Book> bookList = FXCollections.observableArrayList();
            bookList.addAll(temp);
            table.setItems(bookList); 

        	
        });

        // Create search bar
        Label searchLabel = new Label("Search:");
        searchInput = new TextField();
        searchInput.setPromptText("Enter search term...");
        Button searchButton = new Button("Search");
        searchButton.setOnAction(e -> searchBooks(searchInput.getText()));
         HBox searchBox = new HBox(10, searchLabel, searchInput, searchButton);
         
         Button showAllButton = new Button("Show All");
         showAllButton.setOnAction(e -> 
         {
             LinkedList<Book> temp = bookTable.getAllBooks();
             
             ObservableList<Book> bookList = FXCollections.observableArrayList();
             bookList.addAll(temp);
             
             table.setItems(bookList); 
         });

        
       

         





         	
        // Create table view
        TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());

        TableColumn<Book, String> authorColumn = new TableColumn<>("Author");
        authorColumn.setCellValueFactory(cellData -> cellData.getValue().authorProperty());

        TableColumn<Book, String> isbnColumn = new TableColumn<>("ISBN");
        isbnColumn.setCellValueFactory(cellData -> cellData.getValue().isbnProperty());

        TableColumn<Book, String> yearColumn = new TableColumn<>("Year");
        yearColumn.setCellValueFactory(cellData -> cellData.getValue().yearProperty());

        table = new TableView<>();
        table.getColumns().addAll(titleColumn, authorColumn, isbnColumn, yearColumn);
        table.getItems().add(book1);
        table.getItems().add(book2);
        // Create main layout
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(titleBox, authorBox, isbnBox, yearBox, addButton, removeButton, searchBox, showAllButton, bubbleSortButton, table);

        Scene scene = new Scene(layout, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
        

    }
   
    /*
     * Adds a new book to the table and the BookLinkedList using the input provided.
     *
     * @param titleInput the title of the new book
     * @param authorInput the author of the new book
     * @param isbnInput the ISBN of the new book
     * @param yearInput the year of publication of the new book
     */

    private void addBook(String titleInput, String authorInput, String isbnInput, String yearInput) {
        String title = titleInput;
        String author = authorInput;
        String isbn = isbnInput;
        String year = yearInput;

        // Validate input
        if (title.isEmpty() || author.isEmpty() || isbn.isEmpty() || year.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid input");
            alert.setContentText("Please enter all book information");
            alert.showAndWait();
            return;
        }
        // Validate ISBN input
        if (isbn.length() != 13) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid ISBN");
            alert.setContentText("ISBN should be 13 digits long");
            alert.showAndWait();
            return;
        }
        // Validate year input
        if (year.length() != 4) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid year");
            alert.setContentText("Year should be 4 digits long");
            alert.showAndWait();
            return;
        }
        Book book = new Book(title, author, isbn, year);
        table.getItems().add(book);
        bookTable.addBook(title, author, isbn, year);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Book added");
        alert.setContentText("The book was added to the library");
        alert.showAndWait();
    }

    /*
 * Removes the selected book from the table and the book table hash table.
 * If no book is selected, displays an error message and returns without doing anything.
 * Otherwise, removes the selected book and displays a success message.
 * */
    private void removeBook() {
        // Get selected book from table
        Book selectedBook = table.getSelectionModel().getSelectedItem();
        if (selectedBook == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No book selected");
            alert.setContentText("Please select a book to remove");
            alert.showAndWait();
            return;
        }

        // Remove book from table and hash table
        table.getItems().remove(selectedBook);
        bookTable.deleteBook(selectedBook.getIsbn());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Book removed");
        alert.setContentText("The book was removed from the library");
        alert.showAndWait();
    }
    
    /**
     * Searches for a book in the book table that matches the given query.
     * If a matching book is found, sets the table to display that book. 
     * If no matching book is found, clears the table.
     * 
     * @param query the search query to match against the books in the table
     */
    private void searchBooks(String query) {
    	
    	    Book searchedBook = null;
    	    for (int i = 0; i < bookTable.getSize(); i++) {
    	        BookLinkedList list = bookTable;
    	        if (list != null) {
    	            Book book = list.searchBook(query);
    	            if (book != null) {
    	                searchedBook = book;
    	                break; // Stop searching as soon as a matching book is found
    	            }
    	        }
    	    }
    	    if (searchedBook != null) {
    	        table.setItems(FXCollections.observableArrayList(searchedBook));
    	    } else {
    	        table.setItems(FXCollections.emptyObservableList()); // Clear the table if no matching book is found
    	    }
    	}
    
 
 


   

}


