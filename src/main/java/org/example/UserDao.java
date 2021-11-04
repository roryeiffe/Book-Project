package org.example;

import java.sql.SQLException;

public interface UserDao {
    void register(User user) throws SQLException;
    User login(User user) throws SQLException;
    void addToCart(int id) throws SQLException;
    void purchaseOrCancel(User user, int choice) throws SQLException;
    void selectCategory() throws SQLException;
    void getMoreInfo() throws SQLException;
}
