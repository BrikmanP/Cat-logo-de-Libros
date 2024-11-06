package org.example.service;

import org.example.model.Book;
import org.example.model.GutendexResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Component
public class GutendexClient {

    private static final String API_URL = "https://gutendex.com/books";
    private final RestTemplate restTemplate;

    public GutendexClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Book> searchBooks(String query) {
        String url = API_URL + "?search=" + query;
        GutendexResponse response = restTemplate.getForObject(url, GutendexResponse.class);
        return response != null ? response.getBooks() : List.of();
    }

    public List<Book> getTopBooks() {
        String url = API_URL + "?sort=popular";
        GutendexResponse response = restTemplate.getForObject(url, GutendexResponse.class);
        return response != null ? response.getBooks() : List.of();
    }
}
