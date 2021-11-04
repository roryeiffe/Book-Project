package org.example;

import java.sql.SQLException;
import java.util.List;

// An interface for all of the methods that will interact with the Dao database:
public interface BookDao {
    // update a book in the db table:
    public void update(Book book) throws SQLException;
    public List<Book> getAll();
    public List<Book> getAllByCategory(String category);
    public Book getBookById(int id);
}
