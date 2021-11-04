package org.example;

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
