package org.example;
import org.example.model.Book;
import org.example.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class CatalogoLibrosApplication implements CommandLineRunner {

    @Autowired
    private CatalogService catalogService;

    public static void main(String[] args) {
        SpringApplication.run(CatalogoLibrosApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nCatálogo de Libros");
            System.out.println("1. Buscar libros");
            System.out.println("2. Ver libros populares");
            System.out.println("3. Salir");
            System.out.print("Elige una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    System.out.print("Ingresa el término de búsqueda: ");
                    String query = scanner.nextLine();
                    List<Book> books = catalogService.searchBooks(query);
                    displayBooks(books);
                    break;
                case 2:
                    List<Book> topBooks = catalogService.getTopBooks();
                    displayBooks(topBooks);
                    break;
                case 3:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción inválida. Intenta nuevamente.");
            }
        }
    }

    private void displayBooks(List<Book> books) {
        if (books.isEmpty()) {
            System.out.println("No se encontraron libros.");
        } else {
            System.out.println("\nLista de Libros:");
            for (Book book : books) {
                System.out.println("Título: " + book.getTitle());
                System.out.println("Autores: " + String.join(", ", book.getAuthors()));
                System.out.println("--------------------------");
            }
        }
    }
}
