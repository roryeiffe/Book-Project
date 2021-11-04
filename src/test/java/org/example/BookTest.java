package org.example;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class BookTest {

    Book book;

    @Before
    public void begin() {
        book = new Book(1,"Harry Potter", "JK Rowling", "fantasy", "Magic School", 10.00, 12345678, 100);
    }

    @Test
    public void testBookGet() {
        assertEquals("Harry Potter",book.getTitle());
        assertEquals("JK Rowling",book.getAuthor());
        assertEquals("fantasy",book.getCategory());
        assertEquals("Magic School",book.getDescription());
        assertEquals(10.00,book.getPrice(), 0);
        assertEquals(12345678,book.getIsbn());
        assertEquals(100,book.getQuantity());
    }

    @Test
    public void testBookSet() {
        book.setQuantity(book.getQuantity() - 1);
        assertEquals(99,book.getQuantity());
    }

    @Test
    public void testSimpleBookPrint() {
        assertEquals("(1) Harry Potter, by JK Rowling (ISBN: 12345678)", book.simpleString());
    }

    @Test
    public void testBookPrint() {
        assertEquals("Harry Potter by JK Rowling, price: $10.00, category: fantasy, description: Magic School, 100 left in stock", book.fullString());
    }
}
