package org.example;

import org.example.Book.Book;
import org.example.Book.BookDao;
import org.example.Book.BookDaoFactory;
import org.example.User.User;
import org.example.User.UserDao;
import org.example.User.UserDaoFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SQLException {

        // testBooks();

        // DAO's:
        BookDao bookDao = BookDaoFactory.getBookDao();
        UserDao userDao = UserDaoFactory.getUserDao();

        // variables to use for user input
        Scanner numberReader = new Scanner(System.in);
        Scanner stringReader = new Scanner(System.in);

        // variables to store output from table:
        List<Book> books;
        Book book;

        User user = null;

        boolean flag = true;

        while(flag) {
            System.out.println("***********************");
            System.out.println("PRESS 1 to resister");
            System.out.println("PRESS 2 to login");
            System.out.println("PRESS 3 to browse books");
            System.out.println("PRESS 4 to view cart/check out");
            System.out.println("PRESS 5 to quit");
            System.out.println("***********************");
            System.out.print("Please select an option: ");
            int input = numberReader.nextInt();
            switch (input) {
                case (1):
                    // TODO Register user here:
                    System.out.print("What is your name: ");
                    String name = stringReader.nextLine();
                    System.out.print("What is your password: ");
                    String password = stringReader.nextLine();

                    user = new User(name,password);
                    int id = userDao.register(user);
                    user.setId(id);

                    System.out.println("\nYour new id is " + id + ". You will need this to log in.\n");

                    break;
                case (2):
                    // TODO Login user here:
                    System.out.print("Please enter your user id: ");
                    id = numberReader.nextInt();
                    System.out.print("Please enter your password: ");
                    password = stringReader.nextLine();
                    user = new User(id,password);
                    user = userDao.login(user);
                    if(user == null){
                        System.out.println("\nSomething went wrong with log in.\n");
                    }
                    else{
                        System.out.println("\nLog in sucessful!");
                        System.out.println("Welcome " + user.getName() + "!\n");
                    }
                    break;
                case (3):
                    // TODO make sure user is logged in
                    System.out.println("Which category would you like to view?");
                    List<String> categories = bookDao.getAllCategories();
                    for(String category:categories) {
                        System.out.println(category);
                    }
                    String category = stringReader.nextLine();
                    books = bookDao.getAllByCategory(category);
                    System.out.println();
                    for (Book book1 : books) {
                        System.out.println(book1.simpleString());
                    }
                    System.out.print("\nPlease enter the id of the book you would like to view: ");
                    int bookId = numberReader.nextInt();
                    book = bookDao.getBookById(bookId);
                    System.out.println("\n" + book.fullString());
                    if(user == null) {
                        System.out.println("\nPlease sign in to add to cart.\n");
                        break;
                    }
                    System.out.println("\nWould you like to add this book to cart?\n1 - buy\n2 - cancel");
                    int choice = numberReader.nextInt();
                    if (choice == 1) {
                        // TODO: Add book to cart
                        userDao.addToCart(user, bookId);
                    } else if (choice == 2) {
                        System.out.println("Cancelled.");
                    }
                    break;
                case 4:
                    // TODO: Fetch all books in cart and print out
                    // TODO: print total price

                    if(user == null){
                        System.out.println("\nPlease log in to view your cart.\n");
                        break;
                    }
                    books = userDao.getAllBooksInCart(user);
                    double total = 0;
                    for(Book book1: books) {
                        System.out.println(book1.simpleString());
                        total += book1.getPrice();
                    }
                    System.out.println("\nYour total cart comes to $" + total);
                    System.out.println("Would you like to check out?");
                    System.out.println("yes - 1");
                    System.out.println("cancel order - 2");
                    System.out.println("keep shopping - 3");
                    System.out.print("=> ");
                    choice = numberReader.nextInt();
                    if(choice == 3) {
                        break;
                    }
                    // purchase or cancel the order:
                    userDao.purchaseOrCancel(user, choice, books);


                    break;
                case 5:
                    flag = false;
                    break;
            }
        }



    }

    public static void testBooks() throws SQLException {
        // Testing:
        BookDao bookDao = BookDaoFactory.getBookDao();

        // get all books:
        List<Book> books = bookDao.getAll();
        System.out.println("All books:");
        for(Book book: books) {
            System.out.println(book.toString());
        }

        // get all fiction books:
        System.out.println("\nAll fiction books:");
        books = bookDao.getAllByCategory("fiction");
        for(Book book: books) {
            System.out.println(book.toString());
        }

        // get book by id:
        System.out.println("\nGet a singular book:");
        Book book = bookDao.getBookById(3);
        System.out.println(book.toString());

        // update book quantity:
        book = bookDao.getBookById(1);
        System.out.println("Book quantity: " + book.getQuantity());
        book.setQuantity(book.getQuantity() - 1);
        bookDao.update(book);
        book = bookDao.getBookById(book.getId());
        System.out.println("New book quantity: " + book.getQuantity());

    }
}
