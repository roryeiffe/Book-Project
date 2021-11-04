package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

// This class ensures that only one instance of the connection factory is created.
// So, whenever we need a connection, we can access it using this class:

public class ConnectionFactory {

    private static Connection connection = null;

    // make this connection a singleton:
    private ConnectionFactory() {

    }
    public static Connection getConnection() {
        if (connection == null) {
            // use dbConfig.properties to import our login data:
            String url = "jdbc:mysql://localhost:3306/bookProject";
            String username = "root";
            String password = "roryeiffedatabase9(";
            try {
                connection = DriverManager.getConnection(url,username,password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

}