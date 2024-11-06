package org.example.model;

import java.util.List;

public class Book {
    private String title;
    private List<String> authors;

    // Getters
    public String getTitle() {
        return title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    // Setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }
}
