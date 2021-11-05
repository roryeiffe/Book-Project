package org.example.Book;

// this class ensures that only one instance of the class is created:
public class BookDaoFactory {
    private static BookDaoImpl dao = null;

    private BookDaoFactory() {

    }

    public static BookDao getBookDao() {
        if (dao == null) {
            dao = new BookDaoImpl();
        }
        return dao;
    }
}
