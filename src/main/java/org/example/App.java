package org.example;

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

        // variables to use for user input
        Scanner numberReader = new Scanner(System.in);
        Scanner stringReader = new Scanner(System.in);

        // variables to store output from table:
        List<Book> books;
        Book book;

        boolean flag = true;

        while(flag) {
            System.out.println("PRESS 1 to resister");
            System.out.println("PRESS 2 to login");
            System.out.println("PRESS 3 to browse books");
            System.out.println("PRESS 4 to view cart/check out");
            System.out.println("PRESS 5 to quit");
            System.out.print("Please select an option: ");
            int input = numberReader.nextInt();
            switch (input) {
                case (1):
                    // TODO Register user here:
                    break;
                case (2):
                    // TODO Login user here:
                    break;
                case (3):
                    // TODO make sure user is logged in
                    System.out.println("Which category would you like to view?");
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
                    System.out.println("\nWould you like to add this book to cart?\n1 - buy\n2 - cancel");
                    int choice = numberReader.nextInt();
                    if (choice == 1) {
                        // TODO: Add book to cart
                    } else if (choice == 2) {
                        System.out.println("Cancelled.");
                    }
                    break;
                case 4:
                    // TODO: Fetch all books in cart and print out
                    // TODO: print total price

                    System.out.println("Would you like to check out?");
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
    }
}
