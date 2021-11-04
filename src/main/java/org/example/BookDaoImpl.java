package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements BookDao{

    Connection connection;

    public BookDaoImpl() {
        connection = ConnectionFactory.getConnection();
    }


    // Given a book object, update the book entry in the database:
    @Override
    public void update(Book book) throws SQLException {
        // For now, only change the quantity, but can add more fields later:
        String sql = "UPDATE book SET quantity = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,book.getQuantity());
        preparedStatement.setInt(2,book.getId());
        int count = preparedStatement.executeUpdate();
        if (count != 1) {
            System.out.println("Oops! Something went wrong with the update!");
        }
    }

    // get all books from the database:
    @Override
    public List<Book> getAll() throws SQLException {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM book";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next()) {
            int id = resultSet.getInt(1);
            String title = resultSet.getString(2);
            String author = resultSet.getString(3);
            String category = resultSet.getString(4);
            String description = resultSet.getString(5);
            double price = resultSet.getDouble(6);
            int isbn = resultSet.getInt(7);
            int quantity = resultSet.getInt(8);
            Book book = new Book(id,title,author,category,description,price,isbn,quantity);
            books.add(book);
        }
        return books;
    }

    @Override
    public List<Book> getAllByCategory(String category) throws SQLException {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM book where category = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,category);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()) {
            int id = resultSet.getInt(1);
            String title = resultSet.getString(2);
            String author = resultSet.getString(3);
            category = resultSet.getString(4);
            String description = resultSet.getString(5);
            double price = resultSet.getDouble(6);
            int isbn = resultSet.getInt(7);
            int quantity = resultSet.getInt(8);
            Book book = new Book(id,title,author,category,description,price,isbn,quantity);
            books.add(book);
        }
        return books;
    }

    @Override
    public Book getBookById(int id) throws SQLException {
        Book book = null;
        String sql = "SELECT * FROM book where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()) {
            id = resultSet.getInt(1);
            String title = resultSet.getString(2);
            String author = resultSet.getString(3);
            String category = resultSet.getString(4);
            String description = resultSet.getString(5);
            double price = resultSet.getDouble(6);
            int isbn = resultSet.getInt(7);
            int quantity = resultSet.getInt(8);
            book = new Book(id,title,author,category,description,price,isbn,quantity);
        }
        if (book == null) {
            System.out.println("Oops! Something went wrong when querying the book.");
        }
        return book;
    }
}
