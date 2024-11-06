package org.example.service;

import com.ejemplo.catalogo.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogService {

    private final GutendexClient gutendexClient;

    public CatalogService(GutendexClient gutendexClient) {
        this.gutendexClient = gutendexClient;
    }

    public void displayBooks(String query) {
        List<Book> books = gutendexClient.searchBooks(query);
        if (books.isEmpty()) {
            System.out.println("No se encontraron libros para esta búsqueda.");
        } else {
            books.forEach(book -> {
                System.out.println("Título: " + book.getTitle());
                System.out.println("Autores: " + String.join(", ", book.getAuthors()));
                System.out.println("------------------------------");
            });
        }
    }
}
