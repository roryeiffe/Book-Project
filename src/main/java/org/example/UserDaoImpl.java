package org.example;

import java.sql.*;
import java.util.ArrayList;

public class UserDaoImpl implements UserDao{
    Connection connection;

    public UserDaoImpl(){
        this.connection = ConnectionFactory.getConnection();
    }

    @Override
    public void register(User user) throws SQLException {
        String sql = "insert into user (name, password) values (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPassword());
        int count = preparedStatement.executeUpdate();
        if(count > 0)
            System.out.println("Thank you for signing up!");
        else
            System.out.println("Oops! There was an error. Please try again");
    }

    @Override
    public User login(User user) throws SQLException {
        String sql = "select * from user where username = ? AND password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPassword());
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String password = resultSet.getString("password");
            user = new User(id, name, password);
        }
        return user;
    }

    @Override
    public void addToCart(User user, int id) throws SQLException { // how to get book title without user knowing
        String sql = "insert into cart (user_id, cart_id) values (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, user.getId());
        preparedStatement.setInt(2, id);
        int count = preparedStatement.executeUpdate();
        if(count > 0)
            System.out.println("Book added to cart!");
        else
            System.out.println("Error occurred. Try later.");
    }

    @Override
    public void purchaseOrCancel(User user, int choice) throws SQLException {
        if(choice == 1){
            String sql = "delete from cart where user_id = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, user.getId());
            int count = preparedStatement.executeUpdate();
            if(count > 0)
                System.out.println("Thank you for purchasing!");
            else
                System.out.println("Error occurred. Please try again.");
        }else if(choice == 2){
            String sql = "delete from cart where user_id = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, user.getId());
            int count = preparedStatement.executeUpdate();
            if(count > 0)
                System.out.println("Cart emptied!");
            else
                System.out.println("Error occurred. Please try again.");
        }else{
            System.out.println("Error occurred. Please try again.");
        }
    }

    @Override
    public List<Book> selectCategory(int choice) throws SQLException {
        List<Book> books = new ArrayList<>();
        String sql = "select category from book where book = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, choice);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
            String category = resultSet.getString("category");

        }
        return books;
    }

    @Override
    public void getMoreInfo() throws SQLException {
        
    }
}
