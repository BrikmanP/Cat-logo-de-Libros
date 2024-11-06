package org.example.service;

import org.example.BookService;
import org.example.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogService implements BookService {

    private final GutendexClient gutendexClient;

    @Autowired
    public CatalogService(GutendexClient gutendexClient) {
        this.gutendexClient = gutendexClient;
    }

    @Override
    public List<Book> searchBooks(String query) {
        return gutendexClient.searchBooks(query);
    }

    @Override
    public List<Book> getTopBooks() {
        return gutendexClient.getTopBooks();
    }
}
