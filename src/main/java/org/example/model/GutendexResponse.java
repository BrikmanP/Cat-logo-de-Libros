package org.example.model;

import java.util.List;

public class GutendexResponse {

    private int count;
    private String next;
    private String previous;
    private List<Book> results;

    // Getters
    public int getCount() {
        return count;
    }

    public String getNext() {
        return next;
    }

    public String getPrevious() {
        return previous;
    }

    public List<Book> getBooks() {
        return results;
    }

    // Setters
    public void setCount(int count) {
        this.count = count;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public void setBooks(List<Book> results) {
        this.results = results;
    }
}
