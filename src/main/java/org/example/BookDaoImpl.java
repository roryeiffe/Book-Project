package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
        String sql = "update book set quantity = ? where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,book.getQuantity());
        preparedStatement.setInt(2,book.getId());
    }

    @Override
    public List<Book> getAll() {
        return null;
    }

    @Override
    public List<Book> getAllByCategory(String category) {
        return null;
    }

    @Override
    public Book getBookById(int id) {
        return null;
    }
}
