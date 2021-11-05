package org.example.User;

import org.example.Book.Book;
import org.example.Book.BookDao;
import org.example.Book.BookDaoFactory;
import org.example.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao{
    Connection connection;

    public UserDaoImpl(){
        this.connection = ConnectionFactory.getConnection();
    }

    @Override
    public int register(User user) throws SQLException {
        String sql = "insert into user (name, password) values (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPassword());
        int count = preparedStatement.executeUpdate();
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        if(count > 0) {
            System.out.println("Thank you for signing up!");
            resultSet.next();
            int id = resultSet.getInt(1);
            return id;
        }
        else{
            System.out.println("Oops! There was an error. Please try again");
            return -1;
        }
    }

    @Override
    public User login(User user) throws SQLException {
        String sql = "select * from user where id = ? AND password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, user.getId());
        preparedStatement.setString(2, user.getPassword());
        ResultSet resultSet = preparedStatement.executeQuery();
        user = null;
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
        BookDao bookDao = BookDaoFactory.getBookDao();
        Book book = bookDao.getBookById(id);
        if(book.getQuantity() <= 0) {
            System.out.println("Book is out of stock.");
        }
        else{
            String sql = "insert into cart (user_id, book_id) values (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setInt(2, id);
            int count = preparedStatement.executeUpdate();
            if(count > 0)
                System.out.println("Book added to cart!");
            else
                System.out.println("Error occurred. Try later.");
        }

    }

    @Override
    public void purchaseOrCancel(User user, int choice, List<Book> books) throws SQLException {
        // purchase:
        if(choice == 1){
            BookDao bookDao = BookDaoFactory.getBookDao();
            String sql = "delete from cart where user_id = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, user.getId());
            int count = preparedStatement.executeUpdate();
            if(count > 0)
                System.out.println("Thank you for purchasing!");
            else
                System.out.println("Error occurred. Please try again.");
            // TODO: decrement quantity:
            for(Book book: books) {
                // decrememnt quantity
                book.setQuantity(book.getQuantity() - 1);
                // update the book table:
                bookDao.update(book);
            }
        // cancel order
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
    public List<Book> getAllBooksInCart(User user) throws SQLException {
        BookDao bookDao = BookDaoFactory.getBookDao();
        String sql = "SELECT book.id from book right join cart on cart.book_id = book.id where cart.user_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,user.getId());
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Book> books = new ArrayList<>();
        while(resultSet.next()) {
            int id = resultSet.getInt(1);
            Book book = bookDao.getBookById(id);
            books.add(book);
        }
        return books;
    }

//    @Override
//    public List<Book> selectCategory(int choice) throws SQLException {
//        List<Book> books = new ArrayList<>();
//        String sql = "select category from book where book = ?";
//        PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        preparedStatement.setInt(1, choice);
//        ResultSet resultSet = preparedStatement.executeQuery();
//        while(resultSet.next()){
//            String title = resultSet.getString("title");
//            String author = resultSet.getString("author");
//            String category = resultSet.getString("category");
//            book = new Book(category)
//        }
//        return books;
//    }
//
//    @Override
//    public void getMoreInfo() throws SQLException {
//
//    }
}
