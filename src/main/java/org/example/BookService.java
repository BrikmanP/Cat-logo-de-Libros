package org.example;

import org.example.model.Book;
import java.util.List;

public interface BookService {
    List<Book> searchBooks(String query);
    List<Book> getTopBooks();
}
