package org.example.User;

import org.example.Book.Book;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    int register(User user) throws SQLException;
    User login(User user) throws SQLException;
    void addToCart(User user, int id) throws SQLException;
    void purchaseOrCancel(User user, int choice, List<Book> books) throws SQLException;
    List<Book> getAllBooksInCart(User user) throws SQLException;
//    void selectCategory() throws SQLException;
//    void getMoreInfo() throws SQLException;
}
