package org.example.Book;


import java.text.DecimalFormat;

public class Book {
    // id of the current book
    private int id;
    // general book information like title, author, etc.:
    private String title;
    private String author;
    private String category;
    private String description;
    private double price;
    private int isbn;
    // quantity is how many of these books are in the database:
    private int quantity;

    // constructor that takes in all fields of the book class:
    public Book(int id, String title, String author, String category, String description, double price, int isbn, int quantity) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.description = description;
        this.price = price;
        this.isbn = isbn;
        this.quantity = quantity;
    }

    // Getter and Setter Methods:
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // return a simple string with just the title, author, and isbn:
    public String simpleString() {
        return "(" + id + ") " + title + ", by " + author + " (ISBN: " + isbn + ")";
    }

    // return a full string displaying most of the book's attributes:
    public String fullString() {
        String priceString = String.format("%.02f", price);
        return title + " by " + author + ", price: $" + priceString+ ", category: " + category + ", description: " + description + ", " + quantity + " left in stock";
    }

    // default string that prints out all attributes of the book:
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", isbn=" + isbn +
                ", quantity=" + quantity +
                '}';
    }
}
