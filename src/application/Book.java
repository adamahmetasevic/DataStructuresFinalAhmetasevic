package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Book {
    private final String title;
    private final String author;
    private final String isbn;
    private final String year;
    private  Boolean isRented;

    public Book(String title, String author, String isbn, String year) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.year = year;
        isRented = false;
	;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getYear() {
        return year;
    }

    public StringProperty titleProperty() {
        return new SimpleStringProperty(title);
    }

    public StringProperty authorProperty() {
        return new SimpleStringProperty(author);
    }

    public StringProperty isbnProperty() {
        return new SimpleStringProperty(isbn);
    }

    public StringProperty yearProperty() {
        return new SimpleStringProperty(year);
    }

	public boolean isRented() {
		return isRented;
	}

	public void setRented(boolean b) {
		isRented = b;
	}
}